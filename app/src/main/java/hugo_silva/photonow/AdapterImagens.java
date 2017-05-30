package hugo_silva.photonow;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class AdapterImagens extends BaseAdapter {

    private Context context;
   // public Integer[] imagens = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo,};
    public Bitmap[] imagens;

    public AdapterImagens(Context c) {
        context = c;
    }

    public AdapterImagens(Context c, List<Album> lista) {
        this.context = c;
        imagens = new Bitmap[lista.size()];
        setImagensIDS(lista);
    }

    private void setImagensIDS(List<Album> l) {
        for(int i = 0; i < imagens.length; i++) {
            imagens[i] = l.get(i).getImagemCapa();
        }
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
