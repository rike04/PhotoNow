package hugo_silva.photonow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbunsFragment extends Fragment {

    Utilizador current_user;
    GridView gridView;
    TextView textoGrid;

    public AlbunsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_albuns, container, false);
        setOnClickBotoes(v);

        current_user = ((MainActivity)getActivity()).getCurrentUser();

        gridView = (GridView) v.findViewById(R.id.galeria_albuns);

        textoGrid = (TextView) v.findViewById(R.id.text_albuns);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstances) {
        super.onViewCreated(view, savedInstances);
        getAlbunsPrivados();
    }

    //Atribui os onClickListeners aos botoes do layout
    private void setOnClickBotoes(View v) {

        Button viewBotaoP = (Button) v.findViewById(R.id.botaoPrivado);
        viewBotaoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlbunsPrivados();
            }
        });

        Button viewBotaoPub = (Button) v.findViewById(R.id.botaoPublico);
        viewBotaoPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlbunsPublicos();
            }
        });

        Button viewBotaoNovo = (Button) v.findViewById(R.id.botaoNovo);
        viewBotaoPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoAlbum();
            }
        });

    }

    //Mostra todos os albuns do utilizador com privacidade privada
    private void getAlbunsPrivados() {
        //Reiniciar as visibilidades da grid view e da text view
        if (current_user.countAlbunsPrivados() == 0) {
            //Esconde a mensagem de erro
            textoGrid.setVisibility(View.GONE);

            //Mostra a grid view
            gridView.setVisibility(View.VISIBLE);
            gridView.setAdapter(new AdapterImagens(getView().getContext()));
        } else {
            gridView.setVisibility(View.GONE);
            // É mostrada a mensagem sobre a inexistência de álbuns do utilizador
            textoGrid.setText("Não existem álbuns privados.");
            textoGrid.setVisibility(View.VISIBLE);
        }
    }

    //Mostra todos os albuns do utilizador com privacidade pública
    private void getAlbunsPublicos(){
        if(current_user.countAlbunsPublicos() > 0) {

        } else {

        }
    }

    private void novoAlbum() {

    }


}
