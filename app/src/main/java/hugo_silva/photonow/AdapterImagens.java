package hugo_silva.photonow;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class AdapterImagens extends BaseAdapter {

    private Context context;
    public Bitmap[] imagens;

    public AdapterImagens(Context c) {
        context = c;
    }

    public AdapterImagens(Context c, List<Album> lista) {
        this.context = c;
        imagens = new Bitmap[totalAlbumSize(lista)];
        setImagensIDS(lista);
    }

    private void setImagensIDS(List<Album> l) {
        int i = 0;
        while(i < imagens.length) {
            for (Album a: l) {

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
        return imagens[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
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
