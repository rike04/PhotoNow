package hugo_silva.photonow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EncomendaFragment extends Fragment {

    private Album album;

    public EncomendaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_encomenda, container, false);

        Bundle data = getArguments();
        fetchAlbum(data);

        //Mostra o número de fotografias do album escolhido
        TextView nrPaginasView = (TextView) v.findViewById(R.id.encomenda_numero_paginas);
        nrPaginasView.setText(Integer.toString(album.getNumeroFotos()));

        Button botaoUndo = (Button) v.findViewById(R.id.botao_undo_encomenda);
        botaoUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Button botaoContinuar = (Button) v.findViewById(R.id.encomenda_confirmar1);
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximoPasso();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstances) {
        super.onViewCreated(view, savedInstances);
        ImageView viewCapa = (ImageView) view.findViewById(R.id.capa_album_encomendado);
        viewCapa.setImageBitmap(album.getImagemCapa());

    }

    private void fetchAlbum(Bundle data) {
        int id = data.getInt("AlbumKey");
        Utilizador user = ((MainActivity) getActivity()).getCurrentUser();
        album = user.getAlbumbyId(id);
    }

    private void proximoPasso() {
        Bundle data = getArguments();
        if(data != null) {
            EncomendaFragment2 e = new EncomendaFragment2();
            e.setArguments(data);
            Util.changeFragments(this, R.id.main_container, e);
        }
    }


}
