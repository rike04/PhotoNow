package hugo_silva.photonow;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class CriarAlbum extends Fragment {

    private static final int SELECT_PHOTO = 100;
    private Button botaoAdicionar;
    private Bundle savedState = null;
    private EditText viewTitulo;
    private ImageView viewCapa;

    public CriarAlbum() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_criar_album, container, false);

        viewTitulo = (EditText) v.findViewById(R.id.titulo_album);
        viewCapa = (ImageView) v.findViewById(R.id.img_capa_album);

        botaoAdicionar = (Button) v.findViewById(R.id.botao_adicionar_album);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarAlbum();
            }
        });

        Button botaoProximoPasso = (Button) v.findViewById(R.id.botao_prox_passo1);
        botaoProximoPasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximoPasso();
            }
        });

        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle("Album");
        }
        if(savedState != null) {
            ImageView image = (ImageView) v.findViewById(R.id.img_capa_album);
            image.setImageBitmap(Util.arrayToBitmap(savedState.getByteArray("imagem")));
            image.setVisibility(View.VISIBLE);

            EditText texto = (EditText) v.findViewById(R.id.titulo_album);
            texto.setText(savedState.getString("titulo"));
        }
        savedState = null;
        return v;
    }

    private void adicionarAlbum() {
        // Cria Intent para escolher fotografias da galeria
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        // Inicia o Intent
        startActivityForResult(galleryIntent, SELECT_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    viewCapa.setImageBitmap(yourSelectedImage);
                    viewCapa.setVisibility(View.VISIBLE);
                    botaoAdicionar.setVisibility(View.GONE);
                }
        }
    }

    //Verifica se os dados que devem ser preenchidos foram inseridos correctamente
    private void proximoPasso() {
        //Se a a ImageView da capa do álbum não estiver visivel significa que não foi inserida
        //qualquer fotografia
        if(viewCapa.getVisibility() == View.VISIBLE) {
            String titulo = viewTitulo.getText().toString();
            if(titulo != null && titulo.length() > 3) {
                if(verificaTitulo(titulo)) {
                    CriarAlbum2 c = new CriarAlbum2();

                    //Preparar os dados para enviar para o fragment c
                    Bundle data = new Bundle();
                    data.putByteArray("capa",
                            Util.bitmapToArray(((BitmapDrawable) viewCapa.getDrawable()).getBitmap()));
                    data.putString("titulo", titulo);
                    //Envio do Bundle
                    c.setArguments(data);
                    Util.changeFragments(this, R.id.main_container, c);
                } else {
                    viewTitulo.setError("Já existe um álbum com este título.");
                }
            } else {
                viewTitulo.setError("Titulo de álbum inválido.");
            }
        }
    }

    private Boolean verificaTitulo(String titulo) {
        Utilizador current_user = ((MainActivity)getActivity()).getCurrentUser();
        return current_user.tituloJaExiste(titulo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Guarda o estado do fragment
        savedState = saveState();
        //Reinicia as views
        viewTitulo = null;
        viewCapa = null;
    }

    private Bundle saveState() {
        Bundle state = new Bundle();
        state.putString("titulo", viewTitulo.getText().toString());
        if(viewCapa.getVisibility() == View.VISIBLE) {
            state.putByteArray("imagem",
                    Util.bitmapToArray(((BitmapDrawable)viewCapa.getDrawable()).getBitmap()));
        } else {
            state.putByteArray("imagem", null);
        }
        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* If onDestroyView() is called first, we can use the previously savedState but we can't call saveState() anymore */
        /* If onSaveInstanceState() is called first, we don't have savedState, so we need to call saveState() */
        /* => (?:) operator inevitable! */
        outState.putBundle("album", (savedState != null) ? savedState : saveState());
    }

}
