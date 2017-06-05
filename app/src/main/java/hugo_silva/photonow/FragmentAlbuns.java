package hugo_silva.photonow;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAlbuns extends Fragment {

    private Utilizador current_user;
    private ListView listView;
    private TextView textoGrid;

    public FragmentAlbuns() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_albuns, container, false);

        current_user = ((MainActivity)getActivity()).getCurrentUser();
        listView = (ListView) v.findViewById(R.id.galeria_albuns);
        textoGrid = (TextView) v.findViewById(R.id.text_albuns);

        setOnClickBotoes(v, this);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstances) {
        super.onViewCreated(view, savedInstances);
        showAlbunsPrivados();
    }

    //Atribui os onClickListeners aos botoes do layout
    private void setOnClickBotoes(View v, final Fragment current) {
        final Button viewBotaoP = (Button) v.findViewById(R.id.botao_privado);
        viewBotaoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlbunsPrivados();
            }
        });

        Button viewBotaoPub = (Button) v.findViewById(R.id.botao_publico);
        viewBotaoPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlbunsPublicos();
            }
        });

        Button viewBotaoNovo = (Button) v.findViewById(R.id.botao_novo);
        viewBotaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.changeFragments(current, R.id.main_container, new FragmentCriarAlbum());
            }
        });
    }

    //Mostra todos os albuns do utilizador com privacidade privada
    private void showAlbunsPrivados() {
        if (current_user.countAlbunsPrivados() > 0) {
            //Esconde a mensagem de erro
            textoGrid.setVisibility(View.GONE);

            //Mostra a grid view
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new CustomAdapter(getView().getContext(),
                    (ArrayList<Album>) current_user.getAlbunsPrivados(), current_user, this));
        } else {
            listView.setVisibility(View.GONE);
            // É mostrada a mensagem sobre a inexistência de álbuns do utilizador
            textoGrid.setText("Não existem álbuns privados.");
            textoGrid.setVisibility(View.VISIBLE);
        }
    }

    //Mostra todos os albuns do utilizador com privacidade pública
    private void showAlbunsPublicos(){
        if(current_user.countAlbunsPublicos() > 0) {
            //Esconde a mensagem de erro
            textoGrid.setVisibility(View.GONE);

            //Mostra a grid view
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new CustomAdapter(getView().getContext(),
                    (ArrayList<Album>) current_user.getAlbunsPublicos(), current_user, this));
        } else {
            listView.setVisibility(View.GONE);
            // É mostrada a mensagem sobre a inexistência de álbuns do utilizador
            textoGrid.setText("Não existem álbuns públicos.");
            textoGrid.setVisibility(View.VISIBLE);
        }
    }

}
