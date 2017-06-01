package hugo_silva.photonow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class EncomendaFragment3 extends Fragment {

    Encomenda e;

    public EncomendaFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_encomenda3, container, false);

        Bundle data = getArguments();

        int idAlbum = data.getInt("AlbumKey");
        Double valor = data.getDouble("AlbumValor");

        Album a = fetchAlbum(idAlbum);

        e = new Encomenda(valor, a);

        TextView tipoAlbum = (TextView) v.findViewById(R.id.tipo_de_album);
        tipoAlbum.setText(e.getTipo());

        TextView precoAlbum = (TextView) v.findViewById(R.id.textview_custo);
        precoAlbum.setText(precoAlbum.getText().toString() + Double.toString(e.getPreco()));

        return v;
    }

    private Album fetchAlbum(int idAlbum) {
        Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();
        return current_user.getAlbumbyId(idAlbum);
    }




}
