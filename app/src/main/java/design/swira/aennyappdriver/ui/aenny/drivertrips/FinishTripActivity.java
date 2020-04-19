package design.swira.aennyappdriver.ui.aenny.drivertrips;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.data.api.MapApiClient;
import design.swira.aennyappdriver.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Distance;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Duration;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Leg;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Route;
import design.swira.aennyappdriver.pojo.aennydriver.GetVechealByDriverResponse;
import design.swira.aennyappdriver.pojo.aennydriver.tripsrates.TripRatesResponse;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;

public class FinishTripActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    @BindView(R.id.moneysum)
    TextView moneysum;
    @BindView(R.id.kmsum)
    TextView kmsum;
    @BindView(R.id.minsum)
    TextView minsum;
    @BindView(R.id.waitsum)
    TextView waitsum;
    @BindView(R.id.riders)
    TextView riders;
    @BindView(R.id.escort)
    TextView escort;
    @BindView(R.id.pickupcity)
    TextView pickupcity;
    @BindView(R.id.pickup)
    TextView pickup;
    @BindView(R.id.dropoffcity)
    TextView dropoffcity;
    @BindView(R.id.dropoff)
    TextView dropoff;
    @BindView(R.id.clientname)
    TextView clientname;
    @BindView(R.id.diss)
    ImageView diss;
    @BindView(R.id.gender)
    ImageView gender;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.chat)
    ImageView chat;
    @BindView(R.id.call)
    ImageView call;
    @BindView(R.id.clientrating)
    RatingBar clientrating;
    @BindView(R.id.clientcomm)
    EditText clientcomm;
    @BindView(R.id.finish)
    Button finish;
    @BindView(R.id.dlbl)
    TextView dlbl;
    @BindView(R.id.plbl)
    TextView plbl;
    private int clientid;
    double clientpickuplong ,clientpickuplate,clientdestlong,clientdestlate;
    private int rides;
    private GoogleMap mMap;
    private Marker marker1;
    private Marker marker2;
    private TripsViewModel tripsViewModel;
    private String phone;
    private Double tripcost,waitingtime,normalkm,rushkm;
    private HubConnection hubConnection;
    private Double mytripcost;
    private int getTcost=0;
    private int handcap,healthy;
    private Double myytripcost;
    private int tripid;
    int vechealid=0;
    private Double totalkm;
    private Double totaltime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_trip);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tripsViewModel= ViewModelProviders.of(this).get(TripsViewModel.class);

        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        Log.i("mylan",displayLanguage);

        if(displayLanguage.equals("العربية")){
            clientname.setGravity(Gravity.RIGHT);


            pickupcity.setGravity(Gravity.LEFT);
            dropoffcity.setGravity(Gravity.LEFT);
            pickup.setGravity(Gravity.RIGHT);
            dropoff.setGravity(Gravity.RIGHT);
            dlbl.setGravity(Gravity.RIGHT);
            plbl.setGravity(Gravity.RIGHT);
        }else if(displayLanguage.equals("English")){
            clientname.setGravity(Gravity.LEFT);

            pickupcity.setGravity(Gravity.RIGHT);
            dropoffcity.setGravity(Gravity.RIGHT);
            pickup.setGravity(Gravity.LEFT);
            dropoff.setGravity(Gravity.LEFT);
            dlbl.setGravity(Gravity.LEFT);
            plbl.setGravity(Gravity.LEFT);
        }else{
            clientname.setGravity(Gravity.LEFT);

            pickupcity.setGravity(Gravity.RIGHT);
            dropoffcity.setGravity(Gravity.RIGHT);
            pickup.setGravity(Gravity.LEFT);
            dropoff.setGravity(Gravity.LEFT);
            dlbl.setGravity(Gravity.LEFT);
            plbl.setGravity(Gravity.LEFT);
        }


        String signalrurl = ApiClient.signalRBaseUrl;
        hubConnection = HubConnectionBuilder.create(signalrurl).build();
        hubConnection.start();


        Intent intent = getIntent();
        if (intent.hasExtra("clientid")) {
            clientid = (int) intent.getExtras().get("clientid");
        }

        if (intent.hasExtra("clientpickuplong")) {
            clientpickuplong = (Double) intent.getExtras().get("clientpickuplong");
        }

        if (intent.hasExtra("clientpickuplate")) {
            clientpickuplate = (Double) intent.getExtras().get("clientpickuplate");
        }

        if (intent.hasExtra("rides")) {
            rides = (int) intent.getExtras().get("rides");
            //clientsno.setText(rides+"");
        }

        if (intent.hasExtra("handcap")) {
            handcap = (int) intent.getExtras().get("handcap");
            escort.setText(handcap+"");
        }

        if (intent.hasExtra("healthy")) {
            healthy = (int) intent.getExtras().get("healthy");
            riders.setText(healthy+"");
        }

        if(intent.hasExtra("tripid")){
            tripid=(int) intent.getExtras().get("tripid");
            int tt=(int) intent.getExtras().get("tripid");
            Log.i("tiddyjghg",tt+"");
        }





        if (intent.hasExtra("clientdestlong")) {
            clientdestlong = (Double) intent.getExtras().get("clientdestlong");
        }

        if (intent.hasExtra("clientdestlate")) {
            clientdestlate = (Double) intent.getExtras().get("clientdestlate");
        }

        if (intent.hasExtra("totalkm")) {
            totalkm = (Double) intent.getExtras().get("totalkm");
            kmsum.setText(totalkm + "");
        }

        if (intent.hasExtra("totaltime")) {
            totaltime = (Double) intent.getExtras().get("totaltime");
            minsum.setText(totaltime + "");
        }


        clientrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(FinishTripActivity.this, "Ratging: "+rating, Toast.LENGTH_SHORT).show();
                // vechialrating.setRating(rating);
                if(rating <= 2){
                    clientcomm.setVisibility(View.VISIBLE);
                }else{
                    clientcomm.setVisibility(View.INVISIBLE);
                }
            }
        });




        try {


            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //timer.setText("00 : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("done!");
                    try {
                        String driveridd = String.valueOf(Constants.getClientId(FinishTripActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        //Toast.makeText(FinishTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();

        }catch (Exception e){
            Log.e("hub_err",e.getMessage().toString());
        }






        try {
            getpickupcity();
            getpickupaddress();
            getdropoffcity();
            getdropoffaddress();
        }catch (Exception e){

        }



        if(clientid > 0){
            getClientData();
        }


        if(intent.hasExtra("tripcost")){
            myytripcost=(Double) intent.getExtras().get("tripcost");
            moneysum.setText(myytripcost+"");
        }
        if(intent.hasExtra("waitingtime")){
            waitingtime=(Double) intent.getExtras().get("waitingtime");
            waitsum.setText(waitingtime+"");
        }
        if(intent.hasExtra("normalkm")){
            normalkm=(Double) intent.getExtras().get("normalkm");
            //kmsum.setText(normalkm+"");
        }
        if(intent.hasExtra("rushkm")){
            rushkm=(Double) intent.getExtras().get("rushkm");
        }





            try {

               /* hubConnection.on("NotifiedDriverTripCost", (TripCost) -> {


                    if (TripCost != null) {
                        mytripcost = TripCost;
                        Log.i("mytripcost", mytripcost + "");
                        //finishMyTrip();
                        //showtripPrice();
                        moneysum.setText(mytripcost+"");
                        getTcost=1;

                    }else{
                        mytripcost=0.0;
                    }


                }, Double.class);*/
            }catch (Exception e){
                Log.e("mytripcost", e.getMessage().toString());
            }






            finish.setOnClickListener(this);
        chat.setOnClickListener(this);
        call.setOnClickListener(this);
    }

    private void showtripPrice() {
        moneysum.setText(mytripcost+"");
    }

    @Override
    public void onClick(View v) {
        int driverid= Constants.getClientId(FinishTripActivity.this);

        tripsViewModel.GetVechealByDriver(driverid);
        tripsViewModel.vechealByDriverResponseMutableLiveData.observe(FinishTripActivity.this, new Observer<GetVechealByDriverResponse>() {
            @Override
            public void onChanged(GetVechealByDriverResponse getVechealByDriverResponse) {
                if(getVechealByDriverResponse != null){
                    vechealid=getVechealByDriverResponse.getVehicleId();
                }
            }
        });

        int clientrateee= (int) clientrating.getRating();
        String clientcom=clientcomm.getText().toString();

        if(clientrateee <= 0){
            Toast.makeText(FinishTripActivity.this, "You must rate client to end trip", Toast.LENGTH_SHORT).show();
        }else if(clientrateee < 3 && clientcom.isEmpty()){
            Toast.makeText(FinishTripActivity.this, "You must tell us the reason of your rating  to end trip", Toast.LENGTH_SHORT).show();
        }else {

            TripRatesResponse tripRatesResponse = new TripRatesResponse(driverid, clientcom, 5, "", tripid, clientid, clientrateee, vechealid, 5);
            tripsViewModel.DriverRateClient(tripRatesResponse);

            tripsViewModel.tripRatesResponseMutableLiveData.observe(FinishTripActivity.this, new Observer<TripRatesResponse>() {
                @Override
                public void onChanged(TripRatesResponse tripRatesResponse) {
                    if (tripRatesResponse != null) {
                        Toast.makeText(FinishTripActivity.this, "Rating added sucessfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Intent i = new Intent(FinishTripActivity.this, MainDriverActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(FinishTripActivity.this, R.raw.my_map_style);
        mMap.setMapStyle(style);


        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        //LatLng barcelona = new LatLng(driverlate, driverlong);
        LatLng barcelona = new LatLng(clientpickuplate, clientpickuplong);
        //mMap.addMarker(new MarkerOptions().position(barcelona).title("First Place"));


        int height1 = 100;
        int width1 = 85;
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.mipmap.vector_smart_object);
        Bitmap b1 = bitmapdraw1.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, width1, height1, false);


        int height = 90;
        int width = 75;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.pin);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng madrid = new LatLng(clientdestlate, clientdestlong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));

        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        //LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        //String origin = driverlate + "," + driverlong;
        String origin = clientpickuplate + "," + clientpickuplong;
        String destination = clientdestlate + "," + clientdestlong;

        //DirectionsApiRequest req = DirectionsApi.getDirections(context, "41.385064,2.173403", "40.416775,-3.70379");
        DirectionsApiRequest req = DirectionsApi.getDirections(context, origin, destination);

        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("t", ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLACK).width(10);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);


        LatLng curloc = new LatLng((clientpickuplate + clientdestlate) / 2, (clientpickuplong + clientdestlong) / 2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 10));

        String key = MapApiClient.GooglemapKey;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(FinishTripActivity.this, new Observer<GoogleMapsResponse>() {
            @Override
            public void onChanged(GoogleMapsResponse googleMapsResponse) {
                try {
                    Route route = googleMapsResponse.getRoutes().get(0);
                    Leg leg = route.getLegs().get(0);
                    Distance distance = leg.getDistance();
                    Integer value = distance.getValue();
                    Double Mydistance = Double.valueOf(value / 1000);

                    Duration duration = leg.getDuration();
                    Integer value1 = duration.getValue();
                    Double mymints = Double.valueOf(value1 / 60);

                    AlertDialog.Builder builder = new AlertDialog.Builder(FinishTripActivity.this);
                    builder.setTitle("Trip distance and time");
                    builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }catch (Exception e){

                }









            }
        });
    }

    private void getClientData() {
        tripsViewModel.getClientData(clientid);
        tripsViewModel.clientMutableLiveData.observe(FinishTripActivity.this, new Observer<ClientDataResponse>() {
            @Override
            public void onChanged(ClientDataResponse clientDataResponse) {
                if(clientDataResponse != null){
                    clientname.setText(clientDataResponse.getClientName());
                    phone=clientDataResponse.getClientMobile();
                    //birthday.setText(clientDataResponse.getClientBirthDate());
                    birthday.setText(clientDataResponse.getClientAge()+"");
                    int clientGender = clientDataResponse.getClientGender();
                    if(clientGender ==1){
                        gender.setImageResource(R.drawable.ic_male_);
                    }else if(clientGender ==2){
                        gender.setImageResource(R.drawable.ic_female_);
                    }
                }
            }
        });
    }

    private void getpickupaddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(clientpickuplate, clientpickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String myaddr="";
            if (address.length() > 45)
            {
                myaddr = address.substring(0, 45) + " ...";
            }
            else
            {
                myaddr = address;
            }
            pickup.setText(myaddr);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getpickupcity() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(clientpickuplate, clientpickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            pickupcity.setText(city);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void getdropoffaddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            //addresses = geocoder.getFromLocation(driverlate, driverlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(clientdestlate, clientdestlong, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String myaddr="";
            if (address.length() > 45)
            {
                myaddr = address.substring(0, 45) + " ...";
            }
            else
            {
                myaddr = address;
            }
            dropoff.setText(myaddr);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getdropoffcity() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            //addresses = geocoder.getFromLocation(driverlate, driverlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(clientdestlate, clientdestlong, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            dropoffcity.setText(city);

        } catch (IOException e) {
            e.printStackTrace();
        }


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
