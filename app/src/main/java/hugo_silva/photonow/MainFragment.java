package hugo_silva.photonow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

        TinyDB t = new TinyDB(getActivity().getApplicationContext());
        String x = t.getString("username");
        Log.d(getClass().getSimpleName(), x);
        TextView vi = (TextView) v.findViewById(R.id.mainview);
        vi.setText(x);

        // Inflate the layout for this fragment
        return v;
    }

}
