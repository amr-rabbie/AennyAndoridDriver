package design.swira.aennyappdriver.ui.aenny.drivertrips;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.pojo.aennydriver.comptrip.CompletedTripResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.ScduleResponse;
import design.swira.aennyappdriver.ui.aenny.adapters.ScdulesListAdapter3;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.SwipeHelper;


public class CompletedTripsFragment extends Fragment {


    TextView notexists;
    ProgressBar pbar;
    RecyclerView recycler;

    TripsViewModel viewModel;
    ScdulesListAdapter3 adapter;
    List<ScduleResponse> myresponses;

    public CompletedTripsFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_completed_trips, container, false);

        notexists=v.findViewById(R.id.notexists);
        recycler=v.findViewById(R.id.recycler);
        pbar=v.findViewById(R.id.pbar);

        //notexists.setVisibility(View.VISIBLE);

        viewModel= ViewModelProviders.of(this).get(TripsViewModel.class);

        adapter=new ScdulesListAdapter3(getActivity());

        bindData();

        return v;
    }





    private void bindData() {
        int clientid= Constants.getClientId(getContext());

        viewModel.getAllCompletedTripsForDriver(clientid);

        viewModel.completedtripslistMutableLiveData.observe(getActivity(), new Observer<List<CompletedTripResponse>>() {
            @Override
            public void onChanged(List<CompletedTripResponse> scduleTripsListResponses) {

                //Toast.makeText(getActivity(), scduleTripsListResponses.toString(), Toast.LENGTH_SHORT).show();

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

    private void viewData(List<CompletedTripResponse> tripsResponses) {
        adapter.setList(tripsResponses);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.invalidate();

        pbar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);

        SwipeHelper swipeHelper = new SwipeHelper(getContext(), recycler) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                /*underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#ea0000"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete

                                int id = tripsResponses.get(pos).getTripId();
                                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                Intent i=new Intent(getContext(), MyCustomDialogActivity.class);
                                i.putExtra("id",id);
                                i.putExtra("title","Confirm delete");
                                i.putExtra("msg","Are you sure you want to delete this client scdule trip ?");
                                i.putExtra("flag","scdule");
                                startActivity(i);
                            }
                        }
                ));*/

                            /*underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Transfer",
                                    0,
                                    Color.parseColor("#FF9502"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnTransfer
                                        }
                                    }
                            ));*/
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Invoice",
                        0,
                        Color.parseColor("#008631"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare

                                int id = tripsResponses.get(pos).getTripId();
                                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                /*Intent i=new Intent(getContext(), UpdateRouteActivity.class);
                                i.putExtra("id",id);
                                startActivity(i);*/

                            }
                        }
                ));
            }
        };



    }

}
