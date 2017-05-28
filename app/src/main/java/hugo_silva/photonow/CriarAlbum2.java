package hugo_silva.photonow;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        return v;
    }

    public void receiveData(Bitmap image, String titulo) {
        capa = image;
        this.titulo = titulo;
    }


}
