package hugo_silva.photonow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EncomendaFragment2 extends Fragment {

    public EncomendaFragment2() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_encomenda2, container, false);

        //Atribuir os valores de cada encomenda como tag aos botões
        Button bpeq = ((Button) v.findViewById(R.id.botao_encomenda_pequena));
        Button bMed = ((Button) v.findViewById(R.id.botao_encomenda_media));
        Button bGr = ((Button) v.findViewById(R.id.botao_encomenda_grande));


        bpeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Double valor = 2.99;
                proximoPasso(valor);
            }
        });
        bMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valor = 4.99;
                proximoPasso(4.99);
            }
        });
        bGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valor = 6.99;
                proximoPasso(6.99);
            }
        });

        Button botaoUndo = (Button) v.findViewById(R.id.botao_undo_encomenda2);
        botaoUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return v;
    }

    private void proximoPasso(Double valor) {

        Bundle data = getArguments();
        data.putDouble("AlbumValue", valor);

        EncomendaFragment3 e = new EncomendaFragment3();
        e.setArguments(data);
        Util.changeFragments(this, R.id.main_container, e);
    }

}
