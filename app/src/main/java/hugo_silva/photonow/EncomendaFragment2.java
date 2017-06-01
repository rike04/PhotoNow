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
        bpeq.setTag(2.99);
        Button bMed = ((Button) v.findViewById(R.id.botao_encomenda_media));
        bMed.setTag(4.99);
        Button bGr = ((Button) v.findViewById(R.id.botao_encomenda_grande));
        bGr.setTag(6.99);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximoPasso(v.getTag());
            }
        };

        bpeq.setOnClickListener(listener);
        bMed.setOnClickListener(listener);
        bGr.setOnClickListener(listener);

        return v;
    }

    private void proximoPasso(Object valorObj) {
        Double valor;
        if(valorObj instanceof Double) {
            valor = (Double) valorObj;
        } else {
            return;
        }
        Bundle data = getArguments();
        data.putDouble("AlbumValue", valor);

        EncomendaFragment3 e = new EncomendaFragment3();
        e.setArguments(data);
        Util.changeFragments(this, R.id.main_container, e);
    }

}
