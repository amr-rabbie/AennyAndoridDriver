package design.swira.aennyappdriver.ui.aenny.drivertrips;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
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
import com.microsoft.signalr.HubConnectionState;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.data.api.MapApiClient;
import design.swira.aennyappdriver.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Distance;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Duration;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Leg;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.Route;
import design.swira.aennyappdriver.pojo.aennydriver.FinancialsRushHoursDto;
import design.swira.aennyappdriver.pojo.aennydriver.LocationClass;
import design.swira.aennyappdriver.pojo.aennydriver.RashHours;
import design.swira.aennyappdriver.ui.aenny.chat.ChatActivity;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.ui.aenny.signin.LoginActivity;
import design.swira.aennyappdriver.ui.aenny.splash.SplashActivity;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.GpsTracker;
import design.swira.aennyappdriver.utils.Network;
import io.reactivex.Single;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class StartTripActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, LocationListener ,  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.curplace)
    ImageView curplace;
    @BindView(R.id.changestyle)
    ImageView changestyle;
    @BindView(R.id.emergancy)
    ImageView emergancy;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.distance)
    TextView distancee;
    @BindView(R.id.busimg)
    CircleImageView busimg;
    @BindView(R.id.pickupcity)
    TextView pickupcity;
    @BindView(R.id.pickup)
    TextView pickup;
    @BindView(R.id.clientsno)
    TextView clientsno;
    @BindView(R.id.vechialrating)
    RatingBar vechialrating;
    @BindView(R.id.driverimg)
    CircleImageView driverimg;
    @BindView(R.id.clientname)
    TextView clientname;
    @BindView(R.id.diss)
    ImageView diss;
    @BindView(R.id.gender)
    ImageView gender;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.chat1)
    ImageView chat1;
    @BindView(R.id.call)
    ImageView call;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.lbl)
    TextView lbl;

    private HubConnection hubConnection;
    private final int intervaltime = 1000;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    int clientid;
    double clientpickuplong, clientpickuplate;
    private GoogleMap mMap;
    String phone;
    TripsViewModel tripsViewModel;
    String ori, des;
    private int markerclicked;
    private Marker marker1;
    private Marker marker2;
    private int rides;
    private Double clientdestlong;
    private Double clientdestlate;
    Double myoldlate, myoldlong = 0.0;
    private int tripcanceled = 0;
    private static final int PERMISSION_REQUEST_CODE = 200;

    double lats[] = new double[]{31.024694, 31.0567694, 31.144694, 31.2546466};
    double longs[] = new double[]{31.363067, 31.356767, 31.4655757};
    List<LocationClass> loclist;
    int index = 0;
    private int getRashHours = 0;
    private Single<FinancialsRushHoursDto> requestRashHour;
    private String acceptTime;
    private String cancelTime;
    private Double opendoorcost;
    private String tripstarttime;
    private final int intervaltime2 = 100;
    private LocationManager locationManager;
    private int getRashHour=0;
    RashHours todayrashhours;
    private Integer tripid;
    int gettripid=0;
    private int getnewmsg=0;
    int weekDay = 0;
    private int getweekday=0;
    private int getRashH=0;
    private int handcap,healthy;
    private LocationManager fusedLocationClient;
    private LocationCallback locationCallback;

    FusedLocationProviderClient mFusedLocationClient;
    LocationRequest mLocationRequest;
    LocationCallback mLocationCallback;
    private int getmyweekday=0;
    private int myintervaltime=500;
    private int getMyRashHour=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_trip);
        ButterKnife.bind(this);

        tripsViewModel = ViewModelProviders.of(this).get(TripsViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String displayLanguage = Locale.getDefault().getDisplayLanguage();
        Log.i("mylan",displayLanguage);

        if(displayLanguage.equals("العربية")){
            lbl.setGravity(Gravity.RIGHT);
            pickupcity.setGravity(Gravity.RIGHT);
            pickup.setGravity(Gravity.RIGHT);
            clientname.setGravity(Gravity.RIGHT);
        }else if(displayLanguage.equals("English")){
            lbl.setGravity(Gravity.LEFT);
            pickupcity.setGravity(Gravity.LEFT);
            pickup.setGravity(Gravity.LEFT);
            clientname.setGravity(Gravity.LEFT);
        }else{
            lbl.setGravity(Gravity.LEFT);
            pickupcity.setGravity(Gravity.LEFT);
            pickup.setGravity(Gravity.LEFT);
            clientname.setGravity(Gravity.LEFT);
        }


       /* locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                }
            }
        };*/


       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            if (!checkPermission()) {
                requestPermission();
            } else {


                mLocationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        for (Location location : locationResult.getLocations()) {
                            Log.i("MainActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());


                            double latte = location.getLatitude();
                            double longg = location.getLongitude();
                            LatLng driverloc=new LatLng(latte, longg);
                            marker1.setPosition(driverloc);

                            int driverid = Constants.getClientId(StartTripActivity.this);
                            hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longg, latte);
                            //Toast.makeText(StartTripActivity.this, "Your location now: " + latte + "," + longg, Toast.LENGTH_SHORT).show();
                            getDistanceTime(latte,longg);
                            Log.i("DriverLoc", driverid + " " + longg + "," + latte);
                        }
                    }

                    ;

                };


                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                requestLocationUpdates();
            }

        }else {*/

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/

            if (!checkPermission()) {
                requestPermission();
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        5000, 10, this);
            }

       // }

        call.setOnClickListener(this);
        chat1.setOnClickListener(this);
        start.setOnClickListener(this);
        emergancy.setOnClickListener(this);
        cancel.setOnClickListener(this);
        changestyle.setOnClickListener(this);
        curplace.setOnClickListener(this);

       // filllocs();




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
            clientsno.setText(rides+"");
        }


        if (intent.hasExtra("handcap")) {
            handcap = (int) intent.getExtras().get("handcap");
        }

        if (intent.hasExtra("healthy")) {
            healthy = (int) intent.getExtras().get("healthy");
        }

        if (intent.hasExtra("clientdestlong")) {
            clientdestlong = (Double) intent.getExtras().get("clientdestlong");
        }

        if (intent.hasExtra("clientdestlate")) {
            clientdestlate = (Double) intent.getExtras().get("clientdestlate");
        }

        if(intent.hasExtra("acceptTime")){
            acceptTime=(String) intent.getExtras().get("acceptTime");
        }

        if(clientpickuplong > 0 && clientpickuplate > 0){
            pickupcity.setText(getcity(clientpickuplate,clientpickuplong));
            pickup.setText(getaddress(clientpickuplate,clientpickuplong));
        }


        if(intent.hasExtra("tripid")){
            tripid=(int) intent.getExtras().get("tripid");

        }

        if(clientid > 0){
            getClientData();
        }

        getLocation();



        try {


           /* new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    try {
                        String driveridd = String.valueOf(Constants.getClientId(StartTripActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        Toast.makeText(StartTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, intervaltime * 5);*/


            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //timer.setText("00 : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("done!");
                    try {
                        String driveridd = String.valueOf(Constants.getClientId(StartTripActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        //Toast.makeText(StartTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();




          /*  hubConnection.on("NotifiedDriverTripId", (Trip_Id) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("tripid", Trip_Id + " , " );

                tripid=Trip_Id;


                //ss="New Trip coming!";

                gettripid = 1;

                //viewNewTrip();


            }, Integer.class);*/
            




            try {
                getLocation();
                //if(! myoldlate.equals(latitude)  || ! myoldlong.equals(longitude) ) {
                int driverid = Constants.getClientId(StartTripActivity.this);
                hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longitude, latitude);



                Log.i("DriverLoc", driverid + " " + longitude + "," + latitude);
                //draw2Points();
            }catch (Exception e){

            }













            final int delay2 = intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable() {
                public void run() {
                    //do something




                    if(tripcanceled == 1){

                        Toast.makeText(StartTripActivity.this, "Trip canceled by client", Toast.LENGTH_SHORT).show();
                        Toast.makeText(StartTripActivity.this, "Open door cost for client : " + opendoorcost, Toast.LENGTH_SHORT).show();
                        cancelTrip();
                        tripcanceled=0;


                    }


                    if (getnewmsg == 1) {
                        Log.i("enterchat","1");
                        openchat();
                        /*Intent int555=new Intent(StartTripActivity.this, ChatActivity.class);
                        int555.putExtra("tripid", tripid);
                        int555.putExtra("clientid", clientid);
                        int555.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivityForResult(int555,5);*/
                        getnewmsg = 0;
                    }


                    /*if(getweekday == 1 ){
                        getRashHours2();
                    }*/


                   /* if(getweekday != 1) {
                        getRashHours();
                    }else{
                        if(getRashHour != 1) {
                            getRashHours2();
                        }
                    }*/














                        handler2.postDelayed(this, delay2);
                }
            }, delay2);


            try {

                hubConnection.on("NotifiedDriverClientCancleTrip", (DoorOpenCost) -> {


                    Log.i("TripCanceled", "TripCanceled");

                    tripcanceled = 1;

                    opendoorcost = DoorOpenCost;

                    Log.i("clientcancel", DoorOpenCost + "," + tripcanceled);


                }, Double.class);
            }catch (Exception e){
                Log.e("clientcancel", e.getMessage().toString());
            }


            hubConnection.on("ReceiveMessage", (Trip_Id, User_Id_From, UserId_To, Message, UserName, UserTypeFrom,UserTypeTo, MessageTime) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                //Log.i("chatmsg", Trip_Id + " , " + User_Id_From );

                Log.i("recvmsg",Message + MessageTime);
                getnewmsg = 1;


                /*if(tripid == Trip_Id){
                    getnewmsg = 1;
                    Log.i("recvmsg",Message + MessageTime);

                }*/

            }, Integer.class, Integer.class, Integer.class,String.class,String.class, Integer.class,Integer.class,String.class);






           /* final int delay = intervaltime * 15;

            final Handler handler = new Handler();


            //int delay = 1000; //milliseconds

            handler.postDelayed(new Runnable() {
                public void run() {
                    //do something

                    getLocation();
                    //if(! myoldlate.equals(latitude)  || ! myoldlong.equals(longitude) ) {
                    int driverid = Constants.getClientId(StartTripActivity.this);
                    hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longitude, latitude);
                    LatLng driverloc=new LatLng(latitude, longitude);
                    marker1.setPosition(driverloc);
                    getDistanceTime(latitude,longitude);
                    Log.i("DriverLoc", driverid + " " + longitude + "," + latitude);
                    myoldlate = latitude;
                    myoldlong = longitude;
                    //draw2Points();
                    //}
                    handler.postDelayed(this, delay);
                }
            }, delay);*/

        }catch (Exception e){
            Log.e("hub_err",e.getMessage().toString());
        }

        getLocation();

        if (distance(clientpickuplate, clientpickuplong,   latitude, longitude) <= 0.07) { // if distance < 0.1

            start.setVisibility(View.VISIBLE);
            //   launch the activity
        }else {
            start.setVisibility(View.GONE);
        }



    }

    private void openchat() {
        Log.i("enterchat","2");
        Intent int555=new Intent(StartTripActivity.this, ChatActivity.class);
        int555.putExtra("tripid", tripid);
        int555.putExtra("clientid", clientid);
        int555.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Log.i("enterchat","3");
        startActivityForResult(int555,4);
        Log.i("enterchat","4");
    }

    private void cancelTrip() {
        Intent i=new Intent(StartTripActivity.this, MainDriverActivity.class);
        startActivity(i);
    }

    private void filllocs() {
        loclist=new ArrayList<>();
        for(int i=0;i<longs.length;i++){
            loclist.add(new LocationClass(longs[i],lats[i]));
        }

    }

    private void draw2Points() {
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(StartTripActivity.this, R.raw.my_map_style);
        mMap.setMapStyle(style);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        /*LatLng myloc = new LatLng(oldlatitude, oldlongitude);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(oldlatitude, oldlongitude), new LatLng(newlatitude, newlongitude))
                .width(5)
                .color(Color.RED));*/

        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        LatLng barcelona = new LatLng(latitude, longitude);
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


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng madrid = new LatLng(clientpickuplate, clientpickuplong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title(des).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        String origin = latitude + "," + longitude;
        String destination = clientpickuplate + "," + clientpickuplong;

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

        curloc = new LatLng((latitude + clientpickuplate) / 2, (longitude + clientpickuplong) / 2);

        //curloc = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(StartTripActivity.this, new Observer<GoogleMapsResponse>() {
            @Override
            public void onChanged(GoogleMapsResponse googleMapsResponse) {
                Route route = googleMapsResponse.getRoutes().get(0);
                Leg leg = route.getLegs().get(0);
                Distance distance = leg.getDistance();
                Integer value = distance.getValue();
                Double Mydistance = Double.valueOf(value / 1000);

                Duration duration = leg.getDuration();
                Integer value1 = duration.getValue();
                Double mymints = Double.valueOf(value1 / 60);

                AlertDialog.Builder builder = new AlertDialog.Builder(StartTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" KM");




                //calcatePrice(Mydistance);

                //builder.create().show();


            }
        });
    }

    private void getClientData() {
        tripsViewModel.getClientData(clientid);
        tripsViewModel.clientMutableLiveData.observe(StartTripActivity.this, new Observer<ClientDataResponse>() {
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


    public void getLocation() {

        latitude = 0.0;
        longitude = 0.0;

        if (!Network.isNetworkAvailable(StartTripActivity.this)) {
            latitude = 0.0;
            longitude = 0.0;
            return;
        } else {

            gpsTracker = new GpsTracker(StartTripActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(RouteActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }


    private String getcity(Double latitude, Double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(StartTripActivity.this, Locale.getDefault());
        String city = "";

        try {
            addresses = geocoder.getFromLocation(clientpickuplate, clientpickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }


    private String getaddress(Double latitude, Double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(StartTripActivity.this, Locale.getDefault());
        String city = "";
        String address="";
        String knownName="";

        try {
            addresses = geocoder.getFromLocation(clientpickuplate, clientpickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start:
                
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        getRashHours();
                    }
                }, intervaltime * 2);





              /*  getRashHours();
               // getRashHours2();
               // while (getRashHour !=1){



                    getRashHours2();
               // }*/


                /*new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                    }
                }, intervaltime * 1);*/


             /*   if(getRashHour == 1) {




                    try {
                        hubConnection.send("NotifiDriverArrived", clientid);
                        tripstarttime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                        Log.i("driverarrived", tripstarttime);
                    } catch (Exception e) {
                        Log.e("driverarrived", e.getMessage().toString());
                        Toast.makeText(StartTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                    Intent intentt = new Intent(StartTripActivity.this, EndTripActivity.class);
                    intentt.putExtra("clientid", clientid);
                    intentt.putExtra("clientpickuplong", clientpickuplong);
                    intentt.putExtra("clientpickuplate", clientpickuplate);
                    //int rides=healthy+handcap;
                    intentt.putExtra("rides", rides);
                    intentt.putExtra("handcap",handcap);
                    intentt.putExtra("healthy",healthy);
                    intentt.putExtra("clientdestlong", clientdestlong);
                    intentt.putExtra("clientdestlate", clientdestlate);
                    intentt.putExtra("tripstarttime", tripstarttime);
                    intentt.putExtra("tripid", tripid);
                    //intentt.putExtra("requestRashHour",  todayrashhours);
               intentt.putExtra("RashHour_1_From", todayrashhours.getRashHour_1_From());
                intentt.putExtra("RashHour_1_To", todayrashhours.getRashHour_1_To());
                intentt.putExtra("RashHour_2_From", todayrashhours.getRashHour_2_From());
                intentt.putExtra("RashHour_2_To", todayrashhours.getRashHour_2_To());

                intentt.putExtra("Price_Per_KilloMeter_RashHour", todayrashhours.getPrice_Per_KilloMeter_RashHour());
                intentt.putExtra("WeekDayName", todayrashhours.getWeekDayName());



                startActivity(intentt);
                }else {
                    Toast.makeText(StartTripActivity.this, "Wait seconds and try again!", Toast.LENGTH_SHORT).show();
            }*/
                break;
            case R.id.chat1:
               /* Uri uri = Uri.parse("smsto:" + phone);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));*/
                Intent int555=new Intent(StartTripActivity.this, ChatActivity.class);
                int555.putExtra("tripid", tripid);
                int555.putExtra("clientid", clientid);
                int555.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(int555,5);
                break;
            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intent);
                break;
            case R.id.changestyle:
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.curplace:
                getLocation();
                MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(StartTripActivity.this, R.raw.my_map_style);
                mMap.setMapStyle(style);


                int height2 = 1;
                int width2 = 1;
                BitmapDrawable bitmapdraw2 = (BitmapDrawable) getResources().getDrawable(R.mipmap.pin);
                Bitmap b2 = bitmapdraw2.getBitmap();
                Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width2, height2, false);
                /*LatLng myloc = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(myloc).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).title("My Location"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(15).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

                LatLng curloc = new LatLng((latitude + clientpickuplate) / 2, (longitude + clientpickuplong) / 2);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

                break;
            case R.id.emergancy:

                break;
            case R.id.cancel:

                gettimeandDate();

                try{
                    hubConnection.send("NotifiDriverCancleTrip",clientid,acceptTime,cancelTime);
                    Log.i("drivercancel","yes");
                    Toast.makeText(StartTripActivity.this, "Trip canceled by you", Toast.LENGTH_SHORT).show();
                    Intent ii=new Intent(StartTripActivity.this,MainDriverActivity.class);
                    startActivity(ii);
                }catch (Exception e){
                    Log.e("drivercancel",e.getMessage().toString());
                }
                break;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(StartTripActivity.this, R.raw.my_map_style);
        mMap.setMapStyle(style);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        /*LatLng myloc = new LatLng(oldlatitude, oldlongitude);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(oldlatitude, oldlongitude), new LatLng(newlatitude, newlongitude))
                .width(5)
                .color(Color.RED));*/

        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        LatLng barcelona = new LatLng(latitude, longitude);
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


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));

        LatLng madrid = new LatLng(clientpickuplate, clientpickuplong);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title(des).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        String origin = latitude + "," + longitude;
        String destination = clientpickuplate + "," + clientpickuplong;

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

        curloc = new LatLng((latitude + clientpickuplate) / 2, (longitude + clientpickuplong) / 2);

        //curloc = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(StartTripActivity.this, new Observer<GoogleMapsResponse>() {
            @Override
            public void onChanged(GoogleMapsResponse googleMapsResponse) {
                Route route = googleMapsResponse.getRoutes().get(0);
                Leg leg = route.getLegs().get(0);
                Distance distance = leg.getDistance();
                Integer value = distance.getValue();
                Double Mydistance = Double.valueOf(value / 1000);

                Duration duration = leg.getDuration();
                Integer value1 = duration.getValue();
                Double mymints = Double.valueOf(value1 / 60);

                AlertDialog.Builder builder = new AlertDialog.Builder(StartTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" KM");




                //calcatePrice(Mydistance);

                //builder.create().show();


            }
        });

    }

   @Override
    public void onLocationChanged(Location location) {
      /* int height1 = 100;
       int width1 = 85;
       BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.mipmap.vector_smart_object);
       Bitmap b1 = bitmapdraw1.getBitmap();
       Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, width1, height1, false);*/
        double latte = location.getLatitude();
        double longg = location.getLongitude();
        LatLng driverloc=new LatLng(latte, longg);
        marker1.setPosition(driverloc);
       //marker1=mMap.addMarker(new MarkerOptions().position(driverloc).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));
       /* LatLng curloc = new LatLng((latte + clientpickuplate) / 2, (longg + clientpickuplong) / 2);

        //curloc = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 13));*/


       if (distance(clientpickuplate, clientpickuplong,   latte, longg) <= 0.07) { // if distance < 0.1

           start.setVisibility(View.VISIBLE);
           //   launch the activity
       }else {
           start.setVisibility(View.GONE);
       }



       if(hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
           int driverid = Constants.getClientId(StartTripActivity.this);
           hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longg, latte);
           //Toast.makeText(StartTripActivity.this, "Your location now: " + latte + "," + longg, Toast.LENGTH_SHORT).show();
           getDistanceTime(latte, longg);
           Log.i("DriverLoc", driverid + " " + longg + "," + latte);
       }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    private void gettimeandDate() {
        //dateoftrip = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        cancelTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }

    private void getDistanceTime(double latte, double longg) {
        String key = MapApiClient.GooglemapKey;

        String origin = latte + "," + longg;
        String destination = clientpickuplate + "," + clientpickuplong;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(StartTripActivity.this, new Observer<GoogleMapsResponse>() {
            @Override
            public void onChanged(GoogleMapsResponse googleMapsResponse) {
                Route route = googleMapsResponse.getRoutes().get(0);
                Leg leg = route.getLegs().get(0);
                Distance distance = leg.getDistance();
                Integer value = distance.getValue();
                Double Mydistance = Double.valueOf(value / 1000);

                Duration duration = leg.getDuration();
                Integer value1 = duration.getValue();
                Double mymints = Double.valueOf(value1 / 60);

                AlertDialog.Builder builder = new AlertDialog.Builder(StartTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" KM");

            }
        });

    }

    public void getRashHours() {


       /* final int delay = myintervaltime * 1;

        final Handler handler = new Handler();
        //int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable() {
            public void run() {*/


               // if(getmyweekday != 1) {

                    try {


                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_WEEK);

                        switch (day) {
                            case Calendar.SATURDAY:
                                weekDay = 1;
                            case Calendar.SUNDAY:
                                // Current day is Sunday
                                weekDay = 2;
                                break;
                            case Calendar.MONDAY:
                                // Current day is Monday
                                weekDay = 3;
                                break;
                            case Calendar.TUESDAY:
                                // etc.
                                weekDay = 4;
                                break;
                            case Calendar.WEDNESDAY:
                                // Current day is Sunday
                                weekDay = 5;
                                break;
                            case Calendar.THURSDAY:
                                // Current day is Monday
                                weekDay = 6;
                                break;
                            case Calendar.FRIDAY:
                                // etc.
                                weekDay = 7;
                                break;
                        }


                        int driveridd = Constants.getClientId(StartTripActivity.this);
                        hubConnection.send("RequestRashHour", weekDay, driveridd);
                        Log.i("rashhours", weekDay + "," + driveridd);
                        getmyweekday = 1;
                        getRashHours2();



                    } catch (Exception e) {
                        Log.e("rashhours", e.getMessage().toString());
                    }
               /* }else if(getmyweekday == 1){
                    getRashHours2();
                }*/

              /*  handler.postDelayed(this, delay);
            }
        }, delay);*/

    }

    public void getRashHours2() {


       /* final int delay = myintervaltime * 1;

        final Handler handler = new Handler();
        //int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable() {
            public void run() {

                if(getMyRashHour != 1) {*/


        do {
            try {

                hubConnection.on("NotifiedDriverRashHour", (RashHours) -> {


                    if (RashHours != null) {


                        todayrashhours = new RashHours(RashHours.getFinancials_RushHours_Id(), RashHours.getRashHour_1_From(),
                                RashHours.getRashHour_1_To(), RashHours.getRashHour_2_From(), RashHours.getRashHour_2_To(),
                                RashHours.getWeek_Day(), RashHours.getWeekDayName(), RashHours.getPrice_Per_KilloMeter_RashHour());

                        Log.i("todayrashhours", todayrashhours.toString());

                        if (todayrashhours != null) {
                            getMyRashHour = 1;
                            if (getMyRashHour == 1 && todayrashhours != null) {
                                startMyTrip();
                            }

                        }

                    }


                }, RashHours.class);
            } catch (Exception e) {
                Log.e("todayrashhours", e.getMessage().toString());
            }
        } while (getMyRashHour == 1);
                /*}else if(getMyRashHour == 1) {
                    if (todayrashhours != null) {
                        startMyTrip();
                    }
                }*/


               /* handler.postDelayed(this, delay);
            }
        }, delay);*/

    }

    private void startMyTrip() {
        try {
            hubConnection.send("NotifiDriverArrived", clientid);
            //tripstarttime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            tripstarttime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
            Log.i("driverarrived", tripstarttime);
        } catch (Exception e) {
            Log.e("driverarrived", e.getMessage().toString());
            //Toast.makeText(StartTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        Intent intentt = new Intent(StartTripActivity.this, EndTripActivity.class);
        intentt.putExtra("clientid", clientid);
        intentt.putExtra("clientpickuplong", clientpickuplong);
        intentt.putExtra("clientpickuplate", clientpickuplate);
        //int rides=healthy+handcap;
        intentt.putExtra("rides", rides);
        intentt.putExtra("handcap",handcap);
        intentt.putExtra("healthy",healthy);
        intentt.putExtra("clientdestlong", clientdestlong);
        intentt.putExtra("clientdestlate", clientdestlate);
        intentt.putExtra("tripstarttime", tripstarttime);
        intentt.putExtra("tripid", tripid);
        //intentt.putExtra("requestRashHour",  todayrashhours);
        intentt.putExtra("RashHour_1_From", todayrashhours.getRashHour_1_From());
        intentt.putExtra("RashHour_1_To", todayrashhours.getRashHour_1_To());
        intentt.putExtra("RashHour_2_From", todayrashhours.getRashHour_2_From());
        intentt.putExtra("RashHour_2_To", todayrashhours.getRashHour_2_To());

        intentt.putExtra("Price_Per_KilloMeter_RashHour", todayrashhours.getPrice_Per_KilloMeter_RashHour());
        intentt.putExtra("WeekDayName", todayrashhours.getWeekDayName());



        startActivity(intentt);
    }

    private void startdelaytime() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

            }
        }, intervaltime * 1);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        /*int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);*/

        return result == PackageManager.PERMISSION_GRANTED /*&&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED*/;
    }

    private void requestPermission() {

        //ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    /*boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readStorageAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;*/

                    //if (locationAccepted && cameraAccepted && readStorageAccepted && writeStorageAccepted)
                    if (locationAccepted )
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("التطبيق يحتاج بعض الصلاحيات",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    //requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                }

                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new androidx.appcompat.app.AlertDialog.Builder(StartTripActivity.this)
                .setMessage(message)
                .setPositiveButton("تم", okListener)
                .setNegativeButton("الغاء", null)
                .create()
                .show();
    }


    private void startLocationUpdates() {
       /* fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());*/
    }

    public void checkPermisionsForAndroid10(){
        boolean permissionAccessCoarseLocationApproved =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;

        if (permissionAccessCoarseLocationApproved) {
            boolean backgroundLocationPermissionApproved =
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            == PackageManager.PERMISSION_GRANTED;

            if (backgroundLocationPermissionApproved) {
                // App can access location both in the foreground and in the background.
                // Start your service that doesn't have a foreground service type
                // defined.
            } else {
                // App can only access location in the foreground. Display a dialog
                // warning the user that your app must have all-the-time access to
                // location in order to function properly. Then, request background
                // location.
                ActivityCompat.requestPermissions(this, new String[] {
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                        101);
            }
        } else {
            // App doesn't have access to the device's location at all. Make full request
            // for permission.
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    },
                    101);
        }
    }


    public void requestLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); // two minute interval
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (mFusedLocationClient != null) {
            requestLocationUpdates();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
       /* if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }*/
    }




    /** calculates the distance between two locations in MILES */
    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometers

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist;
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
