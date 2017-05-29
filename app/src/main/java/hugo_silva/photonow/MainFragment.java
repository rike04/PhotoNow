package hugo_silva.photonow;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

       Utilizador u = ((MainActivity)getActivity()).getCurrentUser();

        TextView vi = (TextView) v.findViewById(R.id.mainview);
        vi.setText(u.getUsername());

        // Inflate the layout for this fragment
        return v;
    }

}
