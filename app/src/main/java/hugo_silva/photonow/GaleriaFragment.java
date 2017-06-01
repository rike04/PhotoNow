package hugo_silva.photonow;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GaleriaFragment extends Fragment {

    GridView lista;

    public GaleriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();
        lista = (GridView) view.findViewById(R.id.lista_imagens);

        //lista.setAdapter(new CustomAdapter(getContext(), current_user.getAllPhotos()));

        /*
        GridView gridView = (GridView) view.findViewById(R.id.galeria);
        gridView.setAdapter(new AdapterImagens(view.getContext(), current_user.getAlbunsPrivados()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), FullImage.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        */

        return view;

    }

}
