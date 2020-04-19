package design.swira.aennyapp.ui.aenny.trips;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import design.swira.aennyapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedTripsFragment extends Fragment {


    public CompletedTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_completed_trips, container, false);

        return v;
    }

}
