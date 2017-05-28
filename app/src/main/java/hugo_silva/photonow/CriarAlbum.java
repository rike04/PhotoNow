package hugo_silva.photonow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
                proximoPasso(container.getId());
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
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        // Start the Intent
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

    private void proximoPasso(final int containerID) {
        if(viewCapa.getVisibility() == View.VISIBLE) {
            if(verificaTitulo(viewTitulo.getText().toString())) {
                CriarAlbum2 c = new CriarAlbum2();
                c.receiveData((Bitmap) ((BitmapDrawable)viewCapa.getDrawable()).getBitmap(),
                        viewTitulo.getText().toString());
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(containerID, c)
                        .addToBackStack(null)
                        .commit();
            } else {
                viewTitulo.setError("Titulo de álbum inválido.");
            }
        }
    }

    private Boolean verificaTitulo(String titulo) {
        if(titulo != null && titulo.length() > 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState(); /* vstup defined here for sure */
        viewTitulo = null;
        viewCapa = null;
    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putString("titulo", viewTitulo.getText().toString());
        if(viewCapa.getVisibility() == View.VISIBLE) {
            state.putByteArray("imagem", Util.bitmapToArray((Bitmap) ((BitmapDrawable)viewCapa.getDrawable()).getBitmap()));
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
