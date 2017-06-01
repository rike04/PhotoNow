package hugo_silva.photonow;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rike4 on 01/06/2017.
 */

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Album> list = new ArrayList<>();
    private Context context;

    public CustomAdapter(Context c, ArrayList<Album> list) {
        this.context = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.album_list_item, null);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image);
        imageView.setImageBitmap(list.get(position).getImagemCapa());

        TextView tituloView = (TextView) view.findViewById(R.id.lista_album_title);
        tituloView.setText(list.get(position).getTitulo());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
