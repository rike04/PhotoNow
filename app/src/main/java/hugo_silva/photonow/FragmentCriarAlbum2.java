package hugo_silva.photonow;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FragmentCriarAlbum2 extends Fragment {

    private String pathToCapa;
    private String titulo;
    private GridView grid;
    private List<Fotografia> arrayGrid;
    private Album novoAlbum;

    //Testes
    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private ArrayList<Uri> uris;
    private String[] arrPath;
    private ImageAdapter imageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_criar_album2, container, false);
        arrayGrid = new ArrayList<>();

        //Adicionar onClickListener ao botão Finalizar
        Button btnFinalizar = (Button) v.findViewById(R.id.btn_finalizar_album);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarNovoAlbum();
            }
        });

        Button btnVoltarAtras = (Button) v.findViewById(R.id.botao_back2);
        btnVoltarAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //Testes
        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor imagecursor = getContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
        this.count = imagecursor.getCount();
        this.thumbnails = new Bitmap[this.count];
        this.arrPath = new String[this.count];
        this.thumbnailsselection = new boolean[this.count];

        uris = new ArrayList<>();

        for (int i = 0; i < this.count; i++) {
            imagecursor.moveToPosition(i);
            int id = imagecursor.getInt(image_column_index);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            Uri imageUri= ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    imagecursor.getInt(imagecursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)));
            uris.add(imageUri);
            thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                        getContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MICRO_KIND, null);
            arrPath[i]= imagecursor.getString(dataColumnIndex);
        }

        GridView imagegrid = (GridView) v.findViewById(R.id.PhoneImageGrid);
        imageAdapter = new ImageAdapter();
        imagegrid.setAdapter(imageAdapter);
        imagecursor.close();

        return v;
    }

    public void setCapa(String capa) {
        this.pathToCapa = capa;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private void criarNovoAlbum() {
        if(titulo != null && pathToCapa != null) {

                //Cria o álbum e adiciona-o ao utilizador atual
               Album novoAlbum = new Album(titulo, pathToCapa);
              final int len = thumbnailsselection.length;
              for (int i =0; i<len; i++) {
                if (thumbnailsselection[i]){
                    String path = null;
                    try {
                        path = Util.getFilePath(getContext(), uris.get(i));
                    } catch (URISyntaxException u) {
                        u.printStackTrace();
                    }
                    novoAlbum.addFotografia(path);
                    Log.d(getClass().getSimpleName(), "Foi inserida uma fotografia");
                }
              }
              Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();
              current_user.addAlbum(novoAlbum);

               //Retira os dois ecrãs da criação do álbum da stack. Evita que ao pressionar o botão
               //para trás o utilizador tenha novamente acesso aos ecrãs de criação.
               getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
               Util.changeFragments(this, R.id.main_container, new FragmentAlbuns());
        }
    }

    //Versão de testes

    public class ImageAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(
                        R.layout.gallery_item, null);
                holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.checkbox.setId(position);
            holder.imageview.setId(position);
            holder.checkbox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    CheckBox cb = (CheckBox) v;
                    int id = cb.getId();
                    if (thumbnailsselection[id]){
                        cb.setChecked(false);
                        thumbnailsselection[id] = false;
                    } else {
                        cb.setChecked(true);
                        thumbnailsselection[id] = true;
                    }
                }
            });
            holder.imageview.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    int id = v.getId();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse("file://" + arrPath[id]), "image/*");
                    startActivity(intent);
                }
            });
            holder.imageview.setImageBitmap(thumbnails[position]);
            holder.checkbox.setChecked(thumbnailsselection[position]);
            holder.id = position;
            return convertView;
        }
    }
    class ViewHolder {
        ImageView imageview;
        CheckBox checkbox;
        int id;
    }

}
