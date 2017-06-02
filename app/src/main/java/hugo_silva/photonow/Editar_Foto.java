package hugo_silva.photonow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Editar_Foto extends Fragment {

    private Fotografia fotografia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_editar__foto, container, false);

        return v;
    }

    public void setFotografia(Fotografia f) {
        this.fotografia = f;
    }

}
