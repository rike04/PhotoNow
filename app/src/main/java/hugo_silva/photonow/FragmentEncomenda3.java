package hugo_silva.photonow;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEncomenda3 extends Fragment {

    Encomenda e;

    public FragmentEncomenda3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_encomenda3, container, false);

        Bundle data = getArguments();

        int idAlbum = data.getInt("AlbumKey");
        Double valor = data.getDouble("AlbumValue");

        Album a = fetchAlbum(idAlbum);

        e = new Encomenda(valor, a);

        Double valorTotal = valor + a.getNumeroFotos();

        TextView precoAlbum = (TextView) v.findViewById(R.id.textview_custo);
        precoAlbum.setText(precoAlbum.getText().toString() + Double.toString(valorTotal));

        Button bConcluir = (Button) v.findViewById(R.id.confirmar_encomenda);
        bConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concluir();
            }
        });

        Button bBack = (Button) v.findViewById(R.id.botao_undo_encomenda2);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ImageView imageView = (ImageView) v.findViewById(R.id.foto_capa_encomenda);
        Bitmap image = BitmapFactory.decodeFile(a.getPathToCapa());
        imageView.setImageBitmap(Util.bitmapResizer(image, 320, 240));

        return v;
    }

    private Album fetchAlbum(int idAlbum) {
        Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();
        return current_user.getAlbumbyId(idAlbum);
    }

    private void concluir() {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Util.changeFragments(this, R.id.main_container, new FragmentAlbuns());
    }

}
