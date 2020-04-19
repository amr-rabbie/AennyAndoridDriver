package design.swira.aennyappdriver.ui.aenny.drivertrips;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.ScduleResponse;
import design.swira.aennyappdriver.ui.aenny.adapters.ScdulesListAdapter;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.SwipeHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScduleTripsFragment extends Fragment {


    TextView notexists;
    ProgressBar pbar;
    RecyclerView recycler;

    TripsViewModel viewModel;
    ScdulesListAdapter adapter;
    List<ScduleResponse> myresponses;






    public ScduleTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scdule_trips, container, false);

        notexists=v.findViewById(R.id.notexists);
        recycler=v.findViewById(R.id.recycler);
        pbar=v.findViewById(R.id.pbar);

        //notexists.setVisibility(View.VISIBLE);

        viewModel= ViewModelProviders.of(this).get(TripsViewModel.class);

        adapter=new ScdulesListAdapter(getActivity());

        bindData();

        return v;
    }





    private void bindData() {
        int clientid= Constants.getClientId(getContext());

        viewModel.getScduleTripsListForDriver(clientid);

        viewModel.ScduleResponselistMutableLiveData.observe(getActivity(), new Observer<List<ScduleResponse>>() {
            @Override
            public void onChanged(List<ScduleResponse> scduleTripsListResponses) {
                if(scduleTripsListResponses != null){
                    if(scduleTripsListResponses.size() > 0) {

                        viewData(scduleTripsListResponses);
                    }else{
                        pbar.setVisibility(View.GONE);
                        notexists.setVisibility(View.VISIBLE);
                    }

                }else{
                    pbar.setVisibility(View.GONE);
                    notexists.setVisibility(View.VISIBLE);
                }
            }
        });


        /*viewModel.getScduledTripsForClient(clientid);

        viewModel.scListMutableLiveData.observe(getActivity(), new Observer<List<ScduledTripsResponse>>() {
            @Override
            public void onChanged(List<ScduledTripsResponse> scduledTripsResponses) {
                if(scduledTripsResponses != null){
                    if(scduledTripsResponses.size() > 0) {

                        viewData(scduledTripsResponses);
                    }else{
                        pbar.setVisibility(View.GONE);
                        notexists.setVisibility(View.VISIBLE);
                    }

                }else{
                    pbar.setVisibility(View.GONE);
                    notexists.setVisibility(View.VISIBLE);
                }
            }
        });*/

       /* viewModel.getAllTrips();

        viewModel.getalltripMutableLiveData.observe(this, new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {
                if(tripsResponses != null){



                    List<TripsResponse> scduledtripslist=new ArrayList<>();
                    for(int i=0;i<tripsResponses.size();i++){
                        TripsResponse trip = tripsResponses.get(i);
                        int clientid= Constants.getClientId(getContext());

                        if(trip.getClientId() == clientid) {



                            if (trip.getTripTypeId() == 2) {
                                scduledtripslist.add(trip);
                            }

                        }
                    }



                    if(scduledtripslist.size() > 0) {

                        viewData(scduledtripslist);
                    }else{
                        pbar.setVisibility(View.GONE);
                        notexists.setVisibility(View.VISIBLE);
                    }

                }else{
                    pbar.setVisibility(View.GONE);
                    notexists.setVisibility(View.VISIBLE);
                }
            }
        });*/


    }

    private void viewData(List<ScduleResponse> tripsResponses) {
        adapter.setList(tripsResponses);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.invalidate();

        pbar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);


    }

}
