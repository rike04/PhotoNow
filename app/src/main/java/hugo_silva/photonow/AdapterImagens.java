package hugo_silva.photonow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AdapterImagens extends BaseAdapter {
    private Context context;

    public Integer[] imagens = {
            R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo,
    };

    public AdapterImagens(Context c) {
        context = c;
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
        imageView.setImageResource(imagens[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(225, 225));
        return imageView;
    }
}
