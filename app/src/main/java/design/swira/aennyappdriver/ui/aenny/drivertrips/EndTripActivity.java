package design.swira.aennyappdriver.ui.aenny.drivertrips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

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
import design.swira.aennyappdriver.pojo.aennydriver.PriceCostDto;
import design.swira.aennyappdriver.pojo.aennydriver.RashHours;
import design.swira.aennyappdriver.pojo.aennydriver.TripCost;
import design.swira.aennyappdriver.ui.aenny.chat.ChatActivity;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.ui.aenny.signin.LoginActivity;
import design.swira.aennyappdriver.ui.aenny.splash.SplashActivity;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.GpsTracker;
import design.swira.aennyappdriver.utils.Network;
import io.reactivex.Single;

public class EndTripActivity extends AppCompatActivity implements View.OnClickListener , OnMapReadyCallback , LocationListener {


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
    @BindView(R.id.end)
    Button end;
    @BindView(R.id.lbl)
    TextView lbl;
    private HubConnection hubConnection;
    private final int intervaltime = 1000;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    int clientid;
    double clientpickuplong,clientpickuplate;
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
    Double myoldlate,myoldlong=0.0;
    private String tripstarttime;
    private String triprndTime;
    private FinancialsRushHoursDto requestRashHour;
    private final int intervaltime2 = 100;
    private LocationManager locationManager;
    //private RashHours todayRashHours;
    double tripcost=0.0;
    private int getTripCost=0;


    int tripid=10;
    double normalkm=0;
    double rashkm=0;
    double waitingtime=0;
    boolean additationfees=true;
    private int sendTripParms=0;
    private int getnewmsg=0;
    private String RashHour_1_From;
    private String RashHour_1_To;
    private String RashHour_2_From;
    private String RashHour_2_To;
    private Double Price_Per_KilloMeter_RashHour;
    private String WeekDayName;
    private Double totaldistance,totalminutes;
    private CountDownTimer counttimer;
    long rashtime=0;
    Date RashHour_1_From1,RashHour_1_To1,RashHour_2_From1,RashHour_2_To1,tripstarttime1,triprndTime1;
    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
    private CountDownTimer counttimer2;
    private int waittime=0;
    private float speed1=0;
    int mytripid=0;
    private int getTcost=0;
    double mytripcost=0.0;
    private int handcap,healthy;
    private Double MyTc=0.0;
    private int sendmyTripParms=0;
    private int getmyTcost=0;
    private Double mtotaldistance=0.0;
    private Double mtotalminutes=0.0;
    private double myllatitude,myllongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_trip);

        ButterKnife.bind(this);

        tripsViewModel= ViewModelProviders.of(this).get(TripsViewModel.class);

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

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 10, this);

        call.setOnClickListener(this);
        chat1.setOnClickListener(this);
        end.setOnClickListener(this);
        emergancy.setOnClickListener(this);
        changestyle.setOnClickListener(this);
        curplace.setOnClickListener(this);






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

        if(clientpickuplong > 0 && clientpickuplate > 0){
            pickupcity.setText(getcity(clientpickuplate,clientpickuplong));
            pickup.setText(getaddress(clientpickuplate,clientpickuplong));
        }

        if(intent.hasExtra("tripid")){
            tripid=(int) intent.getExtras().get("tripid");
            int tt=(int) intent.getExtras().get("tripid");
            Log.i("tiddyjghg",tt+"");
        }

        if (intent.hasExtra("tripstarttime")) {
            tripstarttime = (String) intent.getExtras().get("tripstarttime");
            try {
                tripstarttime1 = format.parse(tripstarttime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

       /*if (intent.hasExtra("requestRashHour")) {
            todayRashHours = (RashHours)intent.getSerializableExtra("requestRashHour");
            Log.i("todayrashhours2", todayRashHours.toString());
        }*/

        if (intent.hasExtra("RashHour_1_From")) {
            RashHour_1_From = (String) intent.getExtras().get("RashHour_1_From");
            try {
                RashHour_1_From1 = format.parse(RashHour_1_From);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (intent.hasExtra("RashHour_1_To")) {
            RashHour_1_To = (String) intent.getExtras().get("RashHour_1_To");
            try {
                RashHour_1_To1 = format.parse(RashHour_1_To);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (intent.hasExtra("RashHour_2_From")) {
            RashHour_2_From = (String) intent.getExtras().get("RashHour_2_From");
            try {
                RashHour_2_From1 = format.parse(RashHour_2_From);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (intent.hasExtra("RashHour_2_To")) {
            RashHour_2_To = (String) intent.getExtras().get("RashHour_2_To");
            try {
                RashHour_2_To1 = format.parse(RashHour_2_To);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if (intent.hasExtra("Price_Per_KilloMeter_RashHour")) {
            Price_Per_KilloMeter_RashHour = (Double) intent.getExtras().get("Price_Per_KilloMeter_RashHour");
        }

        if (intent.hasExtra("WeekDayName")) {
            WeekDayName = (String) intent.getExtras().get("WeekDayName");
        }

        

        








        if(clientid > 0){
            getClientData();
        }

        getLocation();


       // getFullTimeAndDistanceForTrip();

        try {


            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //timer.setText("00 : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("done!");
                    try {
                        String driveridd = String.valueOf(Constants.getClientId(EndTripActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        //Toast.makeText(EndTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();


            hubConnection.on("ReceiveMessage", (Trip_Id, User_Id_From, UserId_To, Message, UserName, UserTypeFrom,UserTypeTo, MessageTime) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                //Log.i("chatmsg", Trip_Id + " , " + User_Id_From );
                getnewmsg = 1;
                Log.i("recvmsg",Message + MessageTime);
                /*if(tripid == Trip_Id){
                    getnewmsg = 1;

                }*/

            }, Integer.class, Integer.class, Integer.class,String.class,String.class, Integer.class,Integer.class,String.class);






            getLocation();
            //if(! myoldlate.equals(latitude)  || ! myoldlong.equals(longitude) ) {
            int driverid = Constants.getClientId(EndTripActivity.this);
            hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longitude, latitude);
            Log.i("DriverLoc", driverid + " " + longitude + "," + latitude);


           /* try {

                hubConnection.on("NotifiedDriverTripCost", (TripCost) -> {


                    if (TripCost != null) {
                        mytripcost = TripCost;
                        Log.i("mytripcost", mytripcost + "");
                        //finishMyTrip();
                        getTcost=1;

                    }else{
                        tripcost=0;
                    }


                }, Double.class);
            }catch (Exception e){
                Log.e("mytripcost", e.getMessage().toString());
            }*/




            final int delay2=intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable(){
                public void run(){
                    //do something

                    if (getnewmsg == 1) {
                        openchat();
                        getnewmsg = 0;
                    }

                  /* if(getTcost == 1 ){
                       // finishMyTrip();
                        finishTrip();
                    }*/

                   /* if(sendTripParms == 1){
                       // getTripCost();
                    }*/

                   /* if(sendTripParms == 1 &&  getTripCost == 1 && tripcost >= 0){

                        finishMyTrip();
                        getTripCost=0;
                        sendTripParms=0;


                    }*/

                    handler2.postDelayed(this, delay2);
                }
            }, delay2);









           /* hubConnection.on("NotifiedDriverTripCost", (TripCost) -> {
                //Log.i("LocClient",latitude + "," + longitude);


                if(TripCost != null){
                    tripcost=TripCost;
                    Log.i("tripcost", tripcost+"" );
                    if(tripcost >= 0){
                        getTripCost=1;
                    }
                }









            }, Double.class);*/

           startMyTimer();
           startMyTimer2();
            //getDistanceTime(clientpickuplate, clientpickuplong);





           /* final int delay = intervaltime * 15;

            final Handler handler = new Handler();


            //int delay = 1000; //milliseconds

            handler.postDelayed(new Runnable() {
                public void run() {
                    //do something

                    getLocation();
                    //if(! myoldlate.equals(latitude)  || ! myoldlong.equals(longitude) ) {
                    int driverid = Constants.getClientId(EndTripActivity.this);
                    hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longitude, latitude);
                    Log.i("DriverLoc", driverid + " " + longitude + "," + latitude);
                    myoldlate = latitude;
                    myoldlong = longitude;
                    draw2Points();
                    //}
                    handler.postDelayed(this, delay);
                }
            }, delay);*/

        }catch (Exception e){
            Log.e("hub_err",e.getMessage().toString());
        }



    }

    private void finishTrip() {
        finishMyTrip();
    }

    private void startMyTimer2() {
        counttimer2=new CountDownTimer(1000 * 60 * 60 * 10, 1000) {

            public void onTick(long millisUntilFinished) {



                try {

                    if (speed1 < 10) {
                        waittime=waittime + 5 ;
                    }
                }catch (Exception e){
                    Log.e("speed",e.getMessage().toString());
                }


                //timer.setText("00 : " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                //timer.setText("done!");

            }
        }.start();
    }

    private void startMyTimer() {






        counttimer=new CountDownTimer(1000 * 60 * 60 * 10, 1000) {

            public void onTick(long millisUntilFinished) {

                //String timenow = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                String timenow = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
                Date timenow1 = null;
                try {
                    timenow1 = format.parse(timenow);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(timenow1.getTime() >= RashHour_1_From1.getTime() && timenow1.getTime() <= RashHour_1_To1.getTime() ){
                    rashtime = rashtime +1;
                }

                if(timenow1.getTime() >= RashHour_2_From1.getTime() && timenow1.getTime() <= RashHour_2_To1.getTime() ){
                    rashtime = rashtime +1;
                }


                //timer.setText("00 : " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                //timer.setText("done!");

            }
        }.start();
    }

    private void openchat() {
        Intent int555=new Intent(EndTripActivity.this, ChatActivity.class);
        int555.putExtra("tripid", tripid);
        int555.putExtra("clientid", clientid);
        int555.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(int555,4);
    }

    private void finishMyTrip() {


        if(getmyTcost == 1 && MyTc >= 0) {


            getMyLocation();
            Intent intentt = new Intent(EndTripActivity.this, FinishTripActivity.class);
            intentt.putExtra("clientid", clientid);
            intentt.putExtra("clientpickuplong", clientpickuplong);
            intentt.putExtra("clientpickuplate", clientpickuplate);
            //int rides=healthy+handcap;
            intentt.putExtra("rides", rides);
            intentt.putExtra("handcap", handcap);
            intentt.putExtra("healthy", healthy);
            intentt.putExtra("clientdestlong", myllongitude);
            intentt.putExtra("clientdestlate", myllatitude);
            intentt.putExtra("tripid", tripid);


            //if(MyTc > 0) {
            intentt.putExtra("tripcost", MyTc);
            intentt.putExtra("waitingtime", waitingtime);
            intentt.putExtra("normalkm", normalkm);
            intentt.putExtra("rushkm", rashkm);
            double totkm=mtotaldistance ;
            intentt.putExtra("totalkm", totkm);
            double tottime=mtotalminutes-waitingtime;
            intentt.putExtra("totaltime", tottime);
            //counttimer.cancel();
            //counttimer2.cancel();


            startActivity(intentt);
        /*}else{
            Toast.makeText(EndTripActivity.this, "Wait seconds , and try again", Toast.LENGTH_SHORT).show();
        }*/
        }

    }

    private void draw2Points() {
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(EndTripActivity.this, R.raw.my_map_style);
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

        LatLng madrid = new LatLng(clientdestlate, clientdestlong);
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

        curloc = new LatLng((latitude + clientdestlate) / 2, (longitude + clientdestlong) / 2);

        //curloc = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(EndTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(EndTripActivity.this);
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
        tripsViewModel.clientMutableLiveData.observe(EndTripActivity.this, new Observer<ClientDataResponse>() {
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

        if (!Network.isNetworkAvailable(EndTripActivity.this)) {
            latitude = 0.0;
            longitude = 0.0;
            return;
        } else {

            gpsTracker = new GpsTracker(EndTripActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(RouteActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }

    public void getMyLocation() {

        myllatitude = 0.0;
        myllongitude = 0.0;

        if (!Network.isNetworkAvailable(EndTripActivity.this)) {
            myllatitude = 0.0;
            myllongitude = 0.0;
            return;
        } else {

            gpsTracker = new GpsTracker(EndTripActivity.this);
            if (gpsTracker.canGetLocation()) {
                myllatitude = gpsTracker.getLatitude();
                myllongitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(RouteActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }



    private String getcity(Double latitude, Double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(EndTripActivity.this, Locale.getDefault());
        String city = "";

        try {
            addresses = geocoder.getFromLocation(clientdestlate, clientdestlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
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
        geocoder = new Geocoder(EndTripActivity.this, Locale.getDefault());
        String city = "";
        String address="";

        try {
            addresses = geocoder.getFromLocation(clientdestlate, clientdestlong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.end:

/*

                try {


                    int weekDay = 1;
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


                    getFullTimeAndDistanceForTrip();

                    if(rashtime > 0) {
                        long Rashtimemin = rashtime /  60;



                        double rashtimeratio = Rashtimemin / totalminutes;

                        rashkm = totaldistance * rashtimeratio;
                        normalkm = totaldistance - rashkm;
                    }else {
                        rashkm = 0;
                        normalkm = totaldistance;
                    }

                    if(waittime > 0){
                        waitingtime=waittime /  60 ;
                    }else{
                        waitingtime=0;
                    }


                    try {
                        triprndTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                        PriceCostDto priceCostDto=new PriceCostDto(tripid,normalkm,rashkm,waitingtime,additationfees,tripstarttime,triprndTime,weekDay);
                        int dreiverid=Constants.getClientId(EndTripActivity.this);
                        hubConnection.send("RequestPriceCost",priceCostDto,clientid,dreiverid);
                        Log.i("sendcostpar", priceCostDto.toString() + "," + clientid + "," + dreiverid);
                        sendTripParms=1;


                            getTripCost();



                        finishMyTrip();




                    }catch (Exception e){
                        Log.e("cost", e.getMessage().toString());
                    }




                    Log.i("tripcost",clientid+"");

                } catch (Exception e) {
                    Log.e("tripcost", e.getMessage().toString());
                    Toast.makeText(EndTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

                */

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        getMyTripCost();
                    }
                }, intervaltime * 2);






                break;
            case R.id.chat1:
                /*Uri uri = Uri.parse("smsto:" + phone);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));*/
                Intent int555=new Intent(EndTripActivity.this, ChatActivity.class);
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
                MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(EndTripActivity.this, R.raw.my_map_style);
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
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(EndTripActivity.this, R.raw.my_map_style);
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

        LatLng madrid = new LatLng(clientdestlate, clientdestlong);
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

        curloc = new LatLng((latitude + clientdestlate) / 2, (longitude + clientdestlong) / 2);

        //curloc = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(EndTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(EndTripActivity.this);
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
        /*marker1=mMap.addMarker(new MarkerOptions().position(driverloc).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));
        LatLng curloc = new LatLng((latte + clientpickuplate) / 2, (longg + clientpickuplong) / 2);

        //curloc = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 13));*/



        float speed = location.getSpeed();
        speed1 = (speed * 60 * 60) / 1000;
        Log.e("speed",speed1+"");





        if(hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
            int driverid = Constants.getClientId(EndTripActivity.this);
            hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longg, latte);
            //Toast.makeText(EndTripActivity.this, "Your location now: " + latte + "," + longg, Toast.LENGTH_SHORT).show();
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

    private void getDistanceTime(double latte, double longg) {
        String key = MapApiClient.GooglemapKey;

        String origin = latte + "," + longg;
        String destination = clientdestlate + "," + clientdestlong;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(EndTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(EndTripActivity.this);
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

    public void getFullTimeAndDistanceForTrip(){
        String key = MapApiClient.GooglemapKey;

        getMyLocation();

        String origin = clientpickuplate + "," + clientpickuplong;
        //String destination = clientdestlate + "," + clientdestlong;
        String destination = myllatitude + "," + myllongitude;

        tripsViewModel.GetDistanceAndTime(origin, destination, key);

        tripsViewModel.googleMapsResponseMutableLiveData.observe(EndTripActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(EndTripActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                /*time.setText(mymints+" Minutes");
                distancee.setText(Mydistance+" KM");*/

                mtotaldistance=Mydistance;
                mtotalminutes=mymints;

            }
        });
    }


    public void getTripCost(){

        do {

            try {

                hubConnection.on("NotifiedDriverTripCost", (TripCost) -> {


                    if (TripCost != null) {
                        MyTc = TripCost;

                        //finishMyTrip();
                        getmyTcost = 1;
                        Log.i("tripcost", MyTc + " " + getmyTcost);
                        finishMyTrip();

                    } else {
                        MyTc = 0.0;
                    }


                }, Double.class);
            } catch (Exception e) {
                Log.e("tripcost", e.getMessage().toString());
            }
        }while (getmyTcost == 1);
    }


    @Override
    public void onBackPressed() {
        /*if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/
    }


    public void getMyTripCost(){
        try {
            //hubConnection.send("NotifiDriverArrived", clientid);

            int weekDay = 1;
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


            getFullTimeAndDistanceForTrip();

            if(rashtime > 0) {
                long Rashtimemin = rashtime /  60;



                double rashtimeratio = Rashtimemin / mtotalminutes;

                rashkm = mtotaldistance * rashtimeratio;
                normalkm = mtotaldistance - rashkm;
            }else {
                rashkm = 0;
                normalkm = mtotaldistance;
            }

            if(waittime > 0){
                waitingtime=waittime /  60 ;
            }else{
                waitingtime=0;
            }


            try {
                //triprndTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                triprndTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
                double totmins=mtotalminutes - waitingtime;
                PriceCostDto priceCostDto=new PriceCostDto(tripid,normalkm,rashkm,waitingtime,additationfees,tripstarttime,triprndTime,weekDay,totmins);
                int dreiverid=Constants.getClientId(EndTripActivity.this);
                hubConnection.send("RequestPriceCost",priceCostDto,clientid,dreiverid);
                Log.i("sendcostpar", priceCostDto.toString() + "," + clientid + "," + dreiverid);
                sendmyTripParms=1;

                if(sendmyTripParms == 1){
                    getTripCost();
                }











            }catch (Exception e){
                Log.e("cost", e.getMessage().toString());
            }
            //double tripcost=200;





        } catch (Exception e) {
            Log.e("tripcost", e.getMessage().toString());
            Toast.makeText(EndTripActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }



}
