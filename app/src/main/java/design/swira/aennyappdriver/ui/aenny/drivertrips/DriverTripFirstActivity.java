package design.swira.aennyappdriver.ui.aenny.drivertrips;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.GpsTracker;
import design.swira.aennyappdriver.utils.Network;

public class DriverTripFirstActivity extends AppCompatActivity implements View.OnClickListener , OnMapReadyCallback {

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
    @BindView(R.id.chat)
    ImageView chat;
    @BindView(R.id.call)
    ImageView call;
    @BindView(R.id.cancel)
    Button cancel;
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
    int tripcanceled=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trip_first);
        ButterKnife.bind(this);



        tripsViewModel= ViewModelProviders.of(this).get(TripsViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        call.setOnClickListener(this);
        chat.setOnClickListener(this);
        cancel.setOnClickListener(this);
        emergancy.setOnClickListener(this);


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

        if(clientid > 0){
            getClientData();
        }

        getLocation();

        try {


            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //timer.setText("00 : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("done!");
                    try {
                        String driveridd = String.valueOf(Constants.getClientId(DriverTripFirstActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        Toast.makeText(DriverTripFirstActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();


            getLocation();
            //if(! myoldlate.equals(latitude)  || ! myoldlong.equals(longitude) ) {
            int driverid = Constants.getClientId(DriverTripFirstActivity.this);
            hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longitude, latitude);
            Log.i("DriverLoc", driverid + " " + longitude + "," + latitude);
            draw2Points();

            final int delay2=intervaltime * 2;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable(){
                public void run(){
                    //do something


                    if(tripcanceled == 1){

                        Toast.makeText(DriverTripFirstActivity.this, "Trip canceled by client", Toast.LENGTH_SHORT).show();
                        tripcanceled=0;
                        Intent i=new Intent(DriverTripFirstActivity.this,MainDriverActivity.class);
                        startActivity(i);
                    }

                    handler2.postDelayed(this, delay2);
                }
            }, delay2);

            hubConnection.on("NotifiedDriverClientCancleTrip",() -> {


                Log.i("TripCanceled","TripCanceled");

                tripcanceled=1;




            });


            final int delay = intervaltime * 15;

            final Handler handler = new Handler();


            //int delay = 1000; //milliseconds

            handler.postDelayed(new Runnable() {
                public void run() {
                    //do something

                    getLocation();
                    //if(! myoldlate.equals(latitude)  || ! myoldlong.equals(longitude) ) {
                    int driverid = Constants.getClientId(DriverTripFirstActivity.this);
                    hubConnection.send("GetCurrentLongAndLattForSpecificDriver", clientid, driverid, longitude, latitude);
                    Log.i("DriverLoc", driverid + " " + longitude + "," + latitude);
                    myoldlate = latitude;
                    myoldlong = longitude;
                    draw2Points();
                    //}
                    handler.postDelayed(this, delay);
                }
            }, delay);






        }catch (Exception e){
            Log.e("hub_err",e.getMessage().toString());
        }



    }

    private void draw2Points() {
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(DriverTripFirstActivity.this, R.raw.my_map_style);
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

        tripsViewModel.googleMapsResponseMutableLiveData.observe(DriverTripFirstActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(DriverTripFirstActivity.this);
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
        tripsViewModel.clientMutableLiveData.observe(DriverTripFirstActivity.this, new Observer<ClientDataResponse>() {
            @Override
            public void onChanged(ClientDataResponse clientDataResponse) {
                if(clientDataResponse != null){
                    clientname.setText(clientDataResponse.getClientName());
                    phone=clientDataResponse.getClientMobile();
                    String date=clientDataResponse.getClientBirthDate();
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    try {
                        Date d=dateFormat.parse(date);
                        String myFormat = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        String myday=sdf.format(d);
                        birthday.setText(myday);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    birthday.setText(clientDataResponse.getClientAge()+"");
                    //birthday.setText(clientDataResponse.getClientBirthDate());
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

        if (!Network.isNetworkAvailable(DriverTripFirstActivity.this)) {
            latitude = 0.0;
            longitude = 0.0;
            return;
        } else {

            gpsTracker = new GpsTracker(DriverTripFirstActivity.this);
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
        geocoder = new Geocoder(DriverTripFirstActivity.this, Locale.getDefault());
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
        geocoder = new Geocoder(DriverTripFirstActivity.this, Locale.getDefault());
        String city = "";
        String address="";

        try {
            addresses = geocoder.getFromLocation(clientpickuplate, clientpickuplong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
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
            case R.id.cancel:


                try {
                    hubConnection.send("NotifiDriverArrived", clientid);

                } catch (Exception e) {
                    Log.e("hub_error", e.getMessage().toString());
                    Toast.makeText(DriverTripFirstActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                Intent intentt = new Intent(DriverTripFirstActivity.this, StartTripActivity.class);
                intentt.putExtra("clientid",clientid);
                intentt.putExtra("clientpickuplong",clientpickuplong);
                intentt.putExtra("clientpickuplate",clientpickuplate);
                //int rides=healthy+handcap;
                intentt.putExtra("rides",rides);
                intentt.putExtra("clientdestlong",clientdestlong);
                intentt.putExtra("clientdestlate",clientdestlate);
                startActivity(intentt);
                break;
            case R.id.chat:
                Uri uri = Uri.parse("smsto:" + phone);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
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
                MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(DriverTripFirstActivity.this, R.raw.my_map_style);
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

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(DriverTripFirstActivity.this, R.raw.my_map_style);
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

        tripsViewModel.googleMapsResponseMutableLiveData.observe(DriverTripFirstActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(DriverTripFirstActivity.this);
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
}
