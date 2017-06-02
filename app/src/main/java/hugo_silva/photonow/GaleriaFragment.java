package hugo_silva.photonow;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class GaleriaFragment extends Fragment {

    public GaleriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);
        Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();

        final GridView gridView = (GridView) view.findViewById(R.id.galeria);
        gridView.setAdapter(new AdapterImagens(view.getContext(), current_user.getAlbunsPrivados()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editaFoto(parent, position);
            }
        });

        return view;
    }

    private void editaFoto(AdapterView<?> parent, int position) {
        Editar_Foto editFoto = new Editar_Foto();
        Fotografia f = (Fotografia) parent.getItemAtPosition(position);

        Bundle data = new Bundle();
        data.putInt("idFotografia", f.getId());
        editFoto.setArguments(data);

        Util.changeFragments(this, R.id.main_container, editFoto);
    }

}
