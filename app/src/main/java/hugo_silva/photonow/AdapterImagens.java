package hugo_silva.photonow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AdapterImagens extends BaseAdapter {

    private Context context;
    private Bitmap[] imagens;
    private Fotografia[] fotografias;

    public AdapterImagens(Context c) {
        context = c;
    }

    public AdapterImagens(Context c, List<Album> lista) {
        this.context = c;
        imagens = new Bitmap[totalAlbumSize(lista)];
        fotografias = new Fotografia[imagens.length];
        setImagensIDS(lista);
    }

    private void setImagensIDS(List<Album> l) {
        int i = 0;
        while(i < imagens.length) {
            for (Album a: l) {
                ArrayList<Fotografia> listaFotos = a.getAllPhotos();
                for(Fotografia f: listaFotos) {
                    imagens[i] = BitmapFactory.decodeFile(f.getImagePath());
                    fotografias[i] = f;
                    i++;
                }
            }
        }
    }

    private int totalAlbumSize(List<Album> l) {
        int contador = 0;
        for(Album a: l) {
            contador+= a.getNumeroFotos();
        }
        return contador;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public Object getItem(int position) {
        return fotografias[position];
    }

    @Override
    public long getItemId(int position) {
       return fotografias[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        imageView = new ImageView(context);
        imageView.setImageBitmap(imagens[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(225, 225));
        return imageView;
    }


}
