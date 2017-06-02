package hugo_silva.photonow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;

public class Editar_Foto extends Fragment {

    private Fotografia fotografia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_editar__foto, container, false);

        Bundle data = getArguments();
        int posicao = data.getInt("idFotografia");

        Utilizador current_user = ((MainActivity) getActivity()).getCurrentUser();
        fotografia = current_user.getFotografia(posicao);

        ImageView viewFoto = (ImageView) v.findViewById(R.id.image_view_editar_fotografia);
        Bitmap imagem = BitmapFactory.decodeFile(fotografia.getImagePath());
        viewFoto.setImageBitmap(Util.bitmapResizer(imagem, 320, 240));

        TextView textLocal = (TextView) v.findViewById(R.id.text_view_local);
        textLocal.setText(fotografia.getLocalizacao());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(fotografia.getDataUpload());
        TextView textData = (TextView) v.findViewById(R.id.text_view_data);
        textLocal.setText(s);


        return v;
    }


}
