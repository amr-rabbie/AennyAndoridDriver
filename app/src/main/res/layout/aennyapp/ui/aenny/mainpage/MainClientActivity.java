package design.swira.aennyapp.ui.aenny.mainpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyapp.pojo.aenny.notifications.NotificationsResponse;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.adapters.ClientFavouriteListAdapter3;
import design.swira.aennyapp.ui.aenny.adapters.ScdulesListAdapter2;
import design.swira.aennyapp.ui.aenny.changepasswordmobile.ChangeMobileActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.AddClientFavouriteDialogActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteListActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteViewModel;
import design.swira.aennyapp.ui.aenny.clientfavourites.OnLocationFavouriteClick;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.ui.aenny.intro.IntroOneActivity;
import design.swira.aennyapp.ui.aenny.longtrip.AddLongTripActivity;
import design.swira.aennyapp.ui.aenny.notifications.NotificationsListActivity;
import design.swira.aennyapp.ui.aenny.notifications.NotificationsViewModel;
import design.swira.aennyapp.ui.aenny.ongonigtrip.OnGoingViewModel;
import design.swira.aennyapp.ui.aenny.ongonigtrip.RouteActivity;
import design.swira.aennyapp.ui.aenny.ongonigtrip.SchduleTripActivity;
import design.swira.aennyapp.ui.aenny.ongonigtrip.UpdateRouteActivity;
import design.swira.aennyapp.ui.aenny.paymentsmethods.PaymentsListActivity;
import design.swira.aennyapp.ui.aenny.regsister.RegsisterViewModel;
import design.swira.aennyapp.ui.aenny.regsister.UpdateProfileActivity;
import design.swira.aennyapp.ui.aenny.settingscreen.MainSettingsActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.ui.aenny.trips.TripsListActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;
import design.swira.aennyapp.utils.SwipeHelper;

public class MainClientActivity extends AppCompatActivity implements View.OnClickListener, OnLocationFavouriteClick, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.whereto)
    TextView whereto;
    @BindView(R.id.scdule)
    ImageView scdule;
    @BindView(R.id.homeimg)
    ImageView homeimg;
    @BindView(R.id.hospitalimg)
    ImageView hospitalimg;
    @BindView(R.id.schoolimg)
    ImageView schoolimg;
    @BindView(R.id.locationimg)
    ImageView locationimg;
    @BindView(R.id.favrecycler)
    RecyclerView favrecycler;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.ll1)
    LinearLayout ll1;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @BindView(R.id.notify)
    FrameLayout notify;
    @BindView(R.id.notifybtn)
    Button notifybtn;
    @BindView(R.id.notifyimg)
    ImageView notifyimg;


    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;

    TextView gbs, profile;

    ClientFavouriteViewModel clientFavouriteViewModel;
    ClientFavouriteListAdapter3 clientFavouriteListAdapter;
    NotificationsViewModel notificationsViewModel;

    TextView clientname, clientemail, clientmobil, clientcityarea;
    ImageView editimg, backimg;

    RegsisterViewModel regsisterViewModel;

    RecyclerView recycler;
    OnGoingViewModel viewModel;
    ScdulesListAdapter2 adapter;

    LinearLayout ll5, ll55, ll555, up, down, ll3, ll33;

    MainClientViewModel mainClientViewModel;

    ImageView pimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);
        ButterKnife.bind(this);

        gbs = findViewById(R.id.gbs);
        profile = findViewById(R.id.profile);
        recycler = findViewById(R.id.recycler);
        ll5 = findViewById(R.id.ll5);
        //ll55=findViewById(R.id.ll55);
        ll555 = findViewById(R.id.ll555);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        ll3 = findViewById(R.id.ll3);
        //ll33=findViewById(R.id.ll33);
        pimg=findViewById(R.id.pimg);


        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View header = ((NavigationView) findViewById(R.id.navigationView)).getHeaderView(0);

        clientname = header.findViewById(R.id.clientname);
        clientemail = header.findViewById(R.id.clientemail);
        clientmobil = header.findViewById(R.id.clientmobil);
        clientcityarea = header.findViewById(R.id.clientcityarea);
        editimg = header.findViewById(R.id.editimg);
        backimg = header.findViewById(R.id.backimg);

        clientFavouriteViewModel = ViewModelProviders.of(this).get(ClientFavouriteViewModel.class);

        clientFavouriteListAdapter = new ClientFavouriteListAdapter3(this, this);

        regsisterViewModel = ViewModelProviders.of(this).get(RegsisterViewModel.class);

        viewModel = ViewModelProviders.of(this).get(OnGoingViewModel.class);

        mainClientViewModel = ViewModelProviders.of(this).get(MainClientViewModel.class);

        notificationsViewModel=ViewModelProviders.of(this).get(NotificationsViewModel.class);

        adapter = new ScdulesListAdapter2(this);

        whereto.setOnClickListener(this);
        scdule.setOnClickListener(this);
        homeimg.setOnClickListener(this);
        hospitalimg.setOnClickListener(this);
        schoolimg.setOnClickListener(this);
        locationimg.setOnClickListener(this);
        editimg.setOnClickListener(this);
        backimg.setOnClickListener(this);
        notify.setOnClickListener(this);
        notifybtn.setOnClickListener(this);
        notifyimg.setOnClickListener(this);

        if(!Network.isNetworkAvailable(this)){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Warnning!");
            builder.setMessage("Network not exists,pleae connect to internet");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i=new Intent(MainClientActivity.this, IntroOneActivity.class);
                    startActivity(i);
                }
            });
            builder.create().show();
        }

        fillHeaderData();


        bindFavouritesData();




        /*Intent i=new Intent(MainClientActivity.this, RouteActivity.class);
        startActivity(i);*/

        /*PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setCountry("IN")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        autocompleteFragment.setFilter(filter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                whereto.setText(place.getName());
            }
            @Override
            public void onError(Status status) {
                whereto.setText("No places Avaiable");
            }
        });*/

        gpsTracker = new GpsTracker(MainClientActivity.this);
        if (gpsTracker.canGetLocation()) {
            //gbs.setVisibility(View.INVISIBLE);

        } else {
            //gbs.setVisibility(View.VISIBLE);
        }

        int clientid = Constants.getClientId(MainClientActivity.this);
        regsisterViewModel.getClientById(clientid);

        /*regsisterViewModel.clientbyidMutableLiveData.observe(MainClientActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client client) {

                String clientBirthDate = client.getClientBirthDate();

                if (!clientBirthDate.isEmpty()) {
                    //profile.setVisibility(View.INVISIBLE);
                } else {
                    //profile.setVisibility(View.VISIBLE);
                }


            }
        });*/

        bindData();

        bindMyNotifications();


            Constants.saveTripData("", "", "", "", "", "", MainClientActivity.this);
            Constants.saveTripData2(0, 0, 0, "", "", "", MainClientActivity.this);




    }


    private void bindMyNotifications() {
        int usertype=1;
        int clientid= Constants.getClientId(MainClientActivity.this);
        notificationsViewModel.getAllNoficationsByClientId(usertype,clientid);

        notificationsViewModel.notifylistMutableLiveData.observe(MainClientActivity.this, new Observer<List<NotificationsResponse>>() {
            @Override
            public void onChanged(List<NotificationsResponse> notificationsResponses) {
                if(notificationsResponses != null){

                    if(notificationsResponses.size() > 0) {
                        String notes= String.valueOf(notificationsResponses.size());
                        notifybtn.setText(notes);
                        //notifybtn.setTextColor(Color.RED);
                    }else{
                        notifybtn.setText("0");
                        //notifybtn.setTextColor(Color.GRAY);
                    }

                }else{
                    notifybtn.setText("0");
                    //notifybtn.setTextColor(Color.GRAY);
                }

            }
        });
    }

    private void bindData() {
        int clientid = Constants.getClientId(MainClientActivity.this);

        viewModel.getAllTrips();

        viewModel.getalltripMutableLiveData.observe(this, new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {
                if (tripsResponses != null) {


                    List<TripsResponse> scduledtripslist = new ArrayList<>();
                    for (int i = 0; i < tripsResponses.size(); i++) {
                        TripsResponse trip = tripsResponses.get(i);
                        int clientid = Constants.getClientId(MainClientActivity.this);

                        if (trip.getClientId() == clientid) {


                            if (trip.getTripTypeId() == 2) {
                                scduledtripslist.add(trip);
                            }

                        }
                    }


                    if (scduledtripslist.size() > 0) {

                        Date d;


                        List<TripsResponse> nexttripslist = new ArrayList<>();


                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        List<Date> dates = new ArrayList<>();
                        for (int i = 0; i < scduledtripslist.size(); i++) {
                            TripsResponse tripsResponse = scduledtripslist.get(i);
                            String date = tripsResponse.getTripTime();
                            try {
                                Date dd = dateFormat.parse(date);
                                dates.add(dd);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }

                        Date now = new Date();


                        Date earliest = getNearestDate(dates, now);

                        for (int i = 0; i < scduledtripslist.size(); i++) {
                            TripsResponse tripsResponse = scduledtripslist.get(i);
                            String date = tripsResponse.getTripTime();
                            try {
                                Date ddd = dateFormat.parse(date);

                                if (earliest.equals(ddd)) {
                                    TripsResponse trip = scduledtripslist.get(i);
                                    nexttripslist.add(trip);
                                }


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }


                        //viewData(scduledtripslist);
                        viewData(nexttripslist);

                        pimg.setImageResource(R.drawable.ic_man);
                    } else {
                        //pbar.setVisibility(View.GONE);
                        //notexists.setVisibility(View.VISIBLE);
                        gbs.setVisibility(View.VISIBLE);
                        profile.setVisibility(View.VISIBLE);
                        ll5.setVisibility(View.VISIBLE);
                        //ll55.setVisibility(View.VISIBLE);
                        ll555.setVisibility(View.VISIBLE);
                        up.setVisibility(View.GONE);
                        down.setVisibility(View.VISIBLE);
                        ll3.setVisibility(View.GONE);
                        //ll33.setVisibility(View.VISIBLE);

                        pimg.setImageResource(R.drawable.ic_cwoman);
                    }

                } else {
                    //pbar.setVisibility(View.GONE);
                    //notexists.setVisibility(View.VISIBLE);
                    gbs.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.VISIBLE);
                    ll5.setVisibility(View.VISIBLE);
                    //ll55.setVisibility(View.VISIBLE);
                    ll555.setVisibility(View.VISIBLE);
                    up.setVisibility(View.GONE);
                    down.setVisibility(View.VISIBLE);
                    ll3.setVisibility(View.GONE);
                    //ll33.setVisibility(View.VISIBLE);
                }
            }
        });

        /*viewModel.getNextScduledTripForClientt(clientid);


        viewModel.nextscMutableLiveData.observe(MainClientActivity.this, new Observer<List<ScduledTripsResponse>>() {
            @Override
            public void onChanged(List<ScduledTripsResponse> scduledTripsResponses) {
                if(scduledTripsResponses.size() > 0){
                    viewData(scduledTripsResponses);
                }else{
                    //pbar.setVisibility(View.GONE);
                    //notexists.setVisibility(View.VISIBLE);
                    gbs.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.VISIBLE);
                    ll5.setVisibility(View.VISIBLE);
                    ll55.setVisibility(View.VISIBLE);
                    ll555.setVisibility(View.VISIBLE);
                    up.setVisibility(View.GONE);
                    down.setVisibility(View.VISIBLE);
                    ll3.setVisibility(View.GONE);
                    ll33.setVisibility(View.VISIBLE);
                }
            }
        });*/


    }

    private void viewData(List<TripsResponse> tripsResponses) {
        adapter.setList(tripsResponses);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainClientActivity.this));
        recycler.invalidate();


        SwipeHelper swipeHelper = new SwipeHelper(MainClientActivity.this, recycler) {
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

                                Intent i=new Intent(MainClientActivity.this, MyCustomDialogActivity.class);
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

                                Intent i=new Intent(MainClientActivity.this, UpdateRouteActivity.class);
                                i.putExtra("id",id);
                                startActivity(i);

                            }
                        }
                ));
            }
        };

        //pbar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        gbs.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        ll5.setVisibility(View.GONE);
        //ll55.setVisibility(View.GONE);
        ll555.setVisibility(View.GONE);
        up.setVisibility(View.VISIBLE);
        down.setVisibility(View.GONE);
        ll3.setVisibility(View.VISIBLE);
        //ll33.setVisibility(View.GONE);
    }

    private void fillHeaderData() {
        /*clientname.setText(Constants.getClientName(MainClientActivity.this));
        clientmobil.setText(Constants.getClientMobile(MainClientActivity.this));
        clientemail.setText(Constants.getClientEmail(MainClientActivity.this));*/

        int clientId = Constants.getClientId(MainClientActivity.this);

        mainClientViewModel.getClientData(clientId);

        mainClientViewModel.clientMutableLiveData.observe(MainClientActivity.this, new Observer<ClientDataResponse>() {
            @Override
            public void onChanged(ClientDataResponse clientDataResponse) {
                if (clientDataResponse != null) {
                    String cityName = clientDataResponse.getCityName();
                    String areaName = clientDataResponse.getAreaName();

                    String cityarea = cityName + "," + areaName;
                    clientcityarea.setText(cityarea);

                    clientname.setText(clientDataResponse.getClientName() + "");
                    clientmobil.setText(clientDataResponse.getClientMobile() + "");
                    clientemail.setText(clientDataResponse.getClientEmail() + "");

                }
            }
        });
    }

    private void bindFavouritesData() {
        int clientid = Constants.getClientId(MainClientActivity.this);
        clientFavouriteViewModel.getClientFavouriteById(clientid);

        clientFavouriteViewModel.clientFavouritelistMutableLiveData.observe(MainClientActivity.this, new Observer<List<ClientFavouriteResponse>>() {
            @Override
            public void onChanged(List<ClientFavouriteResponse> clientFavouriteResponses) {
                if (clientFavouriteResponses.size() > 0) {

                    clientFavouriteResponses.add(new ClientFavouriteResponse(0, "location2", 0, clientid, "Add Favourite", ""));

                    clientFavouriteListAdapter.setList(clientFavouriteResponses);
                    favrecycler.setAdapter(clientFavouriteListAdapter);
                    favrecycler.setLayoutManager(new LinearLayoutManager(MainClientActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    ll2.setVisibility(View.VISIBLE);
                    ll1.setVisibility(View.GONE);


                } else {
                    ll2.setVisibility(View.GONE);
                    ll1.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.whereto:
                //showPlaces();
                Intent i7 = new Intent(MainClientActivity.this, RouteActivity.class);
                //i.putExtra("latitude2", latitude);
                //i.putExtra("longitude2", longitude);
                //i.putExtra("des", whereto.getText().toString());
                startActivity(i7);
                break;
            case R.id.scdule:
                Intent i = new Intent(MainClientActivity.this, SchduleTripActivity.class);
                startActivityForResult(i, 1);
                break;
            case R.id.homeimg:
                Intent i2 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i2.putExtra("notes", "home");
                startActivityForResult(i2, 5);
                break;
            case R.id.hospitalimg:
                Intent i3 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i3.putExtra("notes", "hospital");
                startActivityForResult(i3, 6);
                break;
            case R.id.schoolimg:
                Intent i4 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i4.putExtra("notes", "school");
                startActivityForResult(i4, 7);
                break;
            case R.id.locationimg:
                Intent i5 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i5.putExtra("notes", "location");
                startActivityForResult(i5, 8);
                break;
            case R.id.editimg:
                Intent i11 = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(i11);
                break;
            case R.id.backimg:
                drawerLayout.closeDrawers();
                break;
            case R.id.notify:
                Intent int11 = new Intent(MainClientActivity.this, NotificationsListActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(int11);
                break;
            case R.id.notifybtn:
                Intent int111 = new Intent(MainClientActivity.this, NotificationsListActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(int111);
                break;
            case R.id.notifyimg:
                Intent int112 = new Intent(MainClientActivity.this, NotificationsListActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(int112);
                break;

        }
    }

    private void showPlaces() {
        /*Intent intent = null;
        try {
            intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .build(MainClientActivity.this);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        startActivityForResult(intent, 1);*/

        Intent i = new Intent(MainClientActivity.this, TestMapsActivity.class);
        startActivityForResult(i, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("place", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("err", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }*/

        switch (requestCode) {
            case 1:
                //you just got back from activity B - deal with resultCode
                //use data.getExtra(...) to retrieve the returned data

                if (data != null) {

                    longitude = (double) data.getExtras().get("long");
                    latitude = (double) data.getExtras().get("late");
                    getcuraddress();
                    Intent i = new Intent(MainClientActivity.this, RouteActivity.class);
                    i.putExtra("latitude2", latitude);
                    i.putExtra("longitude2", longitude);
                    i.putExtra("des", whereto.getText().toString());
                    startActivity(i);
                    break;

                }


        }

    }


    private void getcuraddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            whereto.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.changepassmob, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changepass:
               /* Intent i=new Intent(AennyMainActivity.this, ChangePasswordActivity.class);
                startActivity(i);*/
                Intent i = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                startActivity(i);
                break;
            case R.id.changemob:
                Intent ii = new Intent(MainClientActivity.this, ChangeMobileActivity.class);
                startActivity(ii);
                break;
            case R.id.myprofile:
                Intent ii3 = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                startActivity(ii3);
                break;
            case R.id.addlongtrip:
                Intent iii = new Intent(MainClientActivity.this, AddLongTripActivity.class);
                startActivity(iii);
                break;
            case R.id.settings:
                Intent iiii = new Intent(MainClientActivity.this, MainSettingsActivity.class);
                startActivity(iiii);
                break;
            case R.id.payments:
                Intent iiiii = new Intent(MainClientActivity.this, PaymentsListActivity.class);
                startActivity(iiiii);
                break;
            case R.id.favourites:
                Intent i2 = new Intent(MainClientActivity.this, ClientFavouriteListActivity.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListClick(ClientFavouriteResponse clientFavouriteResponse) {

        double latitude2 = clientFavouriteResponse.getClientFavouriteLatt();
        double longitude2 = clientFavouriteResponse.getClientFavouriteLang();
        String des = clientFavouriteResponse.getClientFavouriteDesc();

        Intent i = new Intent(MainClientActivity.this, RouteActivity.class);
        i.putExtra("latitude2", latitude2);
        i.putExtra("longitude2", longitude2);
        i.putExtra("des", des);
        startActivity(i);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.changepass:
               /* Intent i=new Intent(AennyMainActivity.this, ChangePasswordActivity.class);
                startActivity(i);*/
                Intent i = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                startActivity(i);
                break;
            case R.id.changemob:
                Intent ii = new Intent(MainClientActivity.this, ChangeMobileActivity.class);
                startActivity(ii);
                break;
            case R.id.myprofile:
                Intent ii3 = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                startActivity(ii3);
                break;
            case R.id.addlongtrip:
                Intent iii = new Intent(MainClientActivity.this, AddLongTripActivity.class);
                startActivity(iii);
                break;
            case R.id.settings:
                Intent iiii = new Intent(MainClientActivity.this, MainSettingsActivity.class);
                startActivity(iiii);
                break;
            case R.id.payments:
                Intent iiiii = new Intent(MainClientActivity.this, PaymentsListActivity.class);
                startActivity(iiiii);
                break;
            case R.id.favourites:
                Intent i2 = new Intent(MainClientActivity.this, ClientFavouriteListActivity.class);
                startActivity(i2);
                break;
            case R.id.mytrips:
                Intent i4 = new Intent(MainClientActivity.this, TripsListActivity.class);
                startActivity(i4);
                break;
            default:
                break;
        }
        return false;
    }

    public static Date getNearestDate(List<Date> dates, Date currentDate) {
        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = Math.abs(currentTime - date.getTime());
            if ((minDiff == -1) || (diff < minDiff)) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }


    @Override
    public void onBackPressed() {
        /*if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/
    }

}
