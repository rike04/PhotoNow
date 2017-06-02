package hugo_silva.photonow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;

public class Editar_Foto extends Fragment {

    private Fotografia fotografia;
    private EditText textLocal, textData, descr;

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

        textLocal = (EditText) v.findViewById(R.id.text_view_local);
        if(fotografia.getLocalizacao() != null) {
            textLocal.setText(fotografia.getLocalizacao());
        }

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(fotografia.getDataUpload());
        textData = (EditText) v.findViewById(R.id.text_view_data);
        textData.setText(s);

        descr = (EditText) v.findViewById(R.id.text_view_descricao);
        if(fotografia.getDescricao() != null) {
            descr.setText(fotografia.getDescricao());
        }

        Button backButton = (Button) v.findViewById(R.id.botao_undo_editar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        final Button concluirButton = (Button) v.findViewById(R.id.botao_concluir);
        concluirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concluirEdicao();
            }
        });

        return v;
    }

    private void concluirEdicao() {
        if(descr.getText() != null && descr.getText().length() > 0) {
            fotografia.setDescricao(descr.getText().toString());
        }

        if(textLocal.getText() != null && textLocal.getText().length() > 0) {
            fotografia.setLocalizacao(textLocal.getText().toString());
        }

        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Util.changeFragments(this, R.id.main_container, new GaleriaFragment());
    }


}
