package hugo_silva.photonow;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class CriarAlbum2 extends Fragment {

    private Bitmap capa;
    private String titulo;
    private List<Bitmap> imagens;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_criar_album2, container, false);

        //Extracção do título e imagem de capa enviados pelo array anterior
        Bundle data = getArguments();
        titulo = data.getString("titulo");
        byte[] arrayCapa = data.getByteArray("capa");
        capa = Util.arrayToBitmap(arrayCapa);

        //Adicionar onClickListener ao botão Finalizar
        Button btnFinalizar = (Button) v.findViewById(R.id.btn_finalizar_album);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarNovoAlbum(container.getId());
            }
        });


        return v;
    }

    private void criarNovoAlbum(int idContainer) {
        if(titulo != null && titulo.length() > 4) {
           if(capa != null) {
               Album novoAlbum = new Album(titulo, capa);
               Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();
               current_user.addAlbum(novoAlbum);
               Util.changeFragments(this, idContainer, new AlbunsFragment());
           }


        }
    }


}
