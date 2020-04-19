package design.swira.aennyapp.ui.aenny.trips;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyapp.R;
import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.newscduletrip.ScduleTripsListResponse;
import design.swira.aennyapp.pojo.aenny.scduledtrips.ScduledTripsResponse;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.adapters.ScdulesListAdapter;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteListActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.UpdateClientFavouriteActivity;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.ui.aenny.ongonigtrip.OnGoingViewModel;
import design.swira.aennyapp.ui.aenny.ongonigtrip.UpdateRouteActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.SwipeHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScduleTripsFragment extends Fragment {


    TextView notexists;
    ProgressBar pbar;
    RecyclerView recycler;

    OnGoingViewModel viewModel;
    ScdulesListAdapter adapter;
    List<TripsResponse> myresponses;






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

        viewModel= ViewModelProviders.of(this).get(OnGoingViewModel.class);

        adapter=new ScdulesListAdapter(getContext());

        bindData();

        return v;
    }



    public List<TripsResponse> getAllTrips(){

        myresponses=new ArrayList<>();
        ApiClient.getInstance().getAllTrips().enqueue(new Callback<List<TripsResponse>>() {
            @Override
            public void onResponse(Call<List<TripsResponse>> call, Response<List<TripsResponse>> response) {
                myresponses=response.body();
            }

            @Override
            public void onFailure(Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
        return myresponses;
    }

    private void bindData() {
        int clientid= Constants.getClientId(getContext());

        viewModel.getAllScduledTripsByClientId(clientid);

        viewModel.getAllScduledTripsByClientIdmutablelivedata.observe(getActivity(), new Observer<List<ScduleTripsListResponse>>() {
            @Override
            public void onChanged(List<ScduleTripsListResponse> scduleTripsListResponses) {
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

    private void viewData(List<ScduleTripsListResponse> tripsResponses) {
        adapter.setList(tripsResponses);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.invalidate();

        pbar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);


        SwipeHelper swipeHelper = new SwipeHelper(getContext(), recycler) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
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
                ));

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
                        "Edit",
                        0,
                        Color.parseColor("#0c66f0"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare

                                int id = tripsResponses.get(pos).getTripId();
                                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                Intent i=new Intent(getContext(), UpdateRouteActivity.class);
                                i.putExtra("id",id);
                                startActivity(i);

                            }
                        }
                ));
            }
        };

    }

}
