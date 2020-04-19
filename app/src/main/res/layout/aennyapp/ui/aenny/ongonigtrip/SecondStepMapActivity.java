package design.swira.aennyapp.ui.aenny.ongonigtrip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.google.gson.JsonObject;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import org.json.JSONException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyapp.R;
import design.swira.aennyapp.data.api.MapApiClient;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.googlemaps.Distance;
import design.swira.aennyapp.pojo.aenny.googlemaps.Duration;
import design.swira.aennyapp.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyapp.pojo.aenny.googlemaps.Leg;
import design.swira.aennyapp.pojo.aenny.googlemaps.Route;
import design.swira.aennyapp.pojo.aenny.newscduletrip.AddScduleTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.TripDateTimeListItem;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.adapters.AreasAdapter;
import design.swira.aennyapp.ui.aenny.adapters.ClientPaymentsMethodsAdapter;
import design.swira.aennyapp.ui.aenny.clientfavourites.AddClientFavouriteDialogActivity2;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.paymentsmethods.PaymentsMethodsViewModel;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity2;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class SecondStepMapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener , GoogleMap.OnInfoWindowClickListener {

    @BindView(R.id.disabiliy)
    Spinner disabiliy;
    @BindView(R.id.healthy)
    Spinner healthy;
    @BindView(R.id.paymentmethod)
    Spinner paymentmethod;
    @BindView(R.id.schdule)
    ImageView schdule;
    @BindView(R.id.go)
    Button go;
    private GoogleMap mMap;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    private double oldlatitude;
    private double oldlongitude;
    private double newlatitude;
    private double newlongitude;
    String ori, des;

    private String TAG = "so47492459";

    OnGoingViewModel viewModel;

    String healthynum;
    String handcupnum;
    String visaname;
    String cost;
    String distance;
    int paidtype=1;

    private int markerclicked;
    private Marker marker1;
    private Marker marker2;

    String[] disabilityarray=new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] healtyarray=new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] paymentsmethodsarray=new String[]{"Visa ... 1234","Visa ... 4564","Visa ... 4098"};

    PaymentsMethodsViewModel paymentsMethodsViewModel;
    ClientPaymentsMethodsAdapter clientPaymentsMethodsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_map);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(OnGoingViewModel.class);

        paymentsMethodsViewModel=ViewModelProviders.of(this).get(PaymentsMethodsViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocation();

        oldlatitude = (double) getIntent().getExtras().get("latitude");
        oldlongitude = (double) getIntent().getExtras().get("longitude");
        newlatitude = (double) getIntent().getExtras().get("latitude2");
        newlongitude = (double) getIntent().getExtras().get("longitude2");
        ori = (String) getIntent().getExtras().get("ori");
        des = (String) getIntent().getExtras().get("des");

        fillSpiners();

        healthy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                healthynum = (String) parent.getItemAtPosition(position);
                //Toast.makeText(SecondStepMapActivity.this, "healthynum: " + healthynum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        disabiliy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handcupnum=(String) parent.getItemAtPosition(position);
                //Toast.makeText(SecondStepMapActivity.this, "handcupnum: " + handcupnum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        go.setOnClickListener(this);
        schdule.setOnClickListener(this);



    }

    private void fillSpiners() {
        ArrayAdapter<String> gameKindArray= new ArrayAdapter<String>(SecondStepMapActivity.this,android.R.layout.simple_spinner_item, disabilityarray);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disabiliy.setAdapter(gameKindArray);

        ArrayAdapter<String> gameKindArray2= new ArrayAdapter<String>(SecondStepMapActivity.this,android.R.layout.simple_spinner_item, healtyarray);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        healthy.setAdapter(gameKindArray2);

        /*ArrayAdapter<String> gameKindArray3= new ArrayAdapter<String>(SecondStepMapActivity.this,android.R.layout.simple_spinner_item, paymentsmethodsarray);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentmethod.setAdapter(gameKindArray3);*/

        int clientid= Constants.getClientId(SecondStepMapActivity.this);
        paymentsMethodsViewModel.getPaymentsMethodsByClientId(clientid);

        paymentsMethodsViewModel.clientpaymentsmethodsMutableLiveData2.observe(SecondStepMapActivity.this, new Observer<List<ClientsPaymentsMethodsResponse>>() {
            @Override
            public void onChanged(List<ClientsPaymentsMethodsResponse> clientsPaymentsMethodsResponses) {
                if(clientsPaymentsMethodsResponses != null) {
                    clientPaymentsMethodsAdapter = new ClientPaymentsMethodsAdapter(SecondStepMapActivity.this, clientsPaymentsMethodsResponses);
                    paymentmethod.setAdapter(clientPaymentsMethodsAdapter);


                    paymentmethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            visaname=clientsPaymentsMethodsResponses.get(position).getCardNumber();
                            paidtype=clientsPaymentsMethodsResponses.get(position).getPaymentTypeId();
                            //Toast.makeText(SecondStepMapActivity.this, "visaname: " + visaname, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });

    }

    public void getLocation() {

        latitude = 33.312805;
        longitude = 44.361488;

        if (!Network.isNetworkAvailable(SecondStepMapActivity.this)) {
            latitude = 33.312805;
            longitude = 44.361488;
            return;
        } else {

            gpsTracker = new GpsTracker(SecondStepMapActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(TestMapsActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(SecondStepMapActivity.this, R.raw.my_map_style);
        googleMap.setMapStyle(style);

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


        LatLng barcelona = new LatLng(oldlatitude, oldlongitude);
        //mMap.addMarker(new MarkerOptions().position(barcelona).title("First Place"));

        int height = 100;
        int width = 85;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.pin);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


        marker1=mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng madrid = new LatLng(newlatitude, newlongitude);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
        marker2=mMap.addMarker(new MarkerOptions().position(madrid).title(des).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng curloc = new LatLng(latitude, longitude);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(MapApiClient.GooglemapKey)
                .build();

        String origin = oldlatitude + "," + oldlongitude;
        String destination = newlatitude + "," + newlongitude;

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
            Log.e(TAG, ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLACK).width(10);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        curloc = new LatLng((oldlatitude + newlatitude) / 2, (oldlongitude + newlongitude) / 2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        viewModel.GetDistanceAndTime(origin, destination, key);

        viewModel.googleMapsResponseMutableLiveData.observe(SecondStepMapActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(SecondStepMapActivity.this);
                builder.setTitle("Trip distance and time");
                builder.setMessage("Distance: " + Mydistance + " Km\nDuration: " + mymints + " Mins");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                calcatePrice(Mydistance);

                builder.create().show();


            }
        });


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file infowindowlayout.xml
                View v = getLayoutInflater().inflate(R.layout.custom_window_info, null);

                LatLng latLng = arg0.getPosition();


                TextView address = (TextView) v.findViewById(R.id.address);
                //TextView tv2 = (TextView) v.findViewById(R.id.textView2);
                /*String title=arg0.getTitle();
                String informations=arg0.getSnippet();

                tv1.setText(title);
                tv2.setText(informations);*/




                if(onMarkerClick(arg0)==true && markerclicked==1){
                    address.setText(ori);

                   /* address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/
                            Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
                            ii.putExtra("longitude", oldlongitude);
                            ii.putExtra("latitude", oldlatitude);
                            ii.putExtra("addresss", ori);
                            startActivity(ii);
                       /* }
                    });*/
                }else if(onMarkerClick(arg0)==true && markerclicked==2){
                    address.setText(des);

                    /*address.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/
                            Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
                            ii.putExtra("longitude", newlongitude);
                            ii.putExtra("latitude", newlatitude);
                            ii.putExtra("addresss", des);
                            startActivity(ii);
                       /* }
                    });*/
                }




                return v;

            }
        });



    }




    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(marker1))
        {
            markerclicked=1;
            return true;
        }else if(marker.equals(marker2)){
            markerclicked=2;
            return true;
        }
        return false;
    }





    private void calcatePrice(Double mydistance) {
        Double sum=10 + mydistance * 15;
        distance=mydistance.toString();
        cost=sum.toString();
        go.setText("Go (" + sum + " RS Est.)");
    }


    @Override
    public void onClick(View v) {

        int hnum= Integer.parseInt(healthynum);
        int hcnum=Integer.parseInt(handcupnum);
        Constants.saveTripData2(hnum,hcnum,paidtype,visaname,distance,cost,SecondStepMapActivity.this);


        if(v.getId() == R.id.go){
            Intent intent=getIntent();
            if(intent.hasExtra("selectedDates")) {
                List<Calendar> selectedDates = (List<Calendar>) intent.getSerializableExtra("selectedDates");
                String time = (String) intent.getExtras().get("selectedDTime");

                //for(int i=0;i<selectedDates.size();i++){
                    /*Calendar mcalendar = selectedDates.get(i);

                    String myFormat = "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    String d=sdf.format(mcalendar.getTime());*/

                    //Toast.makeText(SchduleTripActivity.this, "Date: " + d + "\n" + "Time: " + time, Toast.LENGTH_SHORT).show();

                    String getori = Constants.getori(SecondStepMapActivity.this);
                    String getdes = Constants.getdes(SecondStepMapActivity.this);
                    String distance=Constants.getdistance(SecondStepMapActivity.this);
                    String cost=Constants.getcost(SecondStepMapActivity.this);
                    String getlatitude = Constants.getlatitude(SecondStepMapActivity.this);
                    String getlongitude = Constants.getlongitude(SecondStepMapActivity.this);
                    String getlatitude2 = Constants.getlatitude2(SecondStepMapActivity.this);
                    String getlongitude2 = Constants.getlongitude2(SecondStepMapActivity.this);
                    int clientId = Constants.getClientId(SecondStepMapActivity.this);
                    int gethealthy = Constants.gethealthy(SecondStepMapActivity.this);
                    int gethandcap = Constants.gethandcap(SecondStepMapActivity.this);
                    int getpaidtype = Constants.getpaidtype(SecondStepMapActivity.this);
                    String getpaid = Constants.getpaid(SecondStepMapActivity.this);
                    int triptype=2;


                    if(!getori.isEmpty() && !getdes.isEmpty() && !distance.isEmpty() && !cost.isEmpty()){
                        // Toast.makeText(SchduleTripActivity.this, "You must save here", Toast.LENGTH_SHORT).show();

                        //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, time,Double.parseDouble(getlongitude) , String tripScheduleTime,  String tripDestinationTime,  triptype, gethealthy, getori,  d,  String tripScheduleDate,Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap,  String tripDestinationDate);
                        //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, d+" "+time,Double.parseDouble(getlongitude) ,   triptype, gethealthy, getori,  d,  Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap );
                        //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, d+" "+time, Double.parseDouble(getlongitude), d+" "+time, 0, d+" "+time, "", triptype, gethealthy, getori, "", d, 0, d, Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 0, clientId, gethandcap, 0, d);


                        /*TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2),  d+" "+time, Double.parseDouble(getlongitude),  triptype  , gethealthy, getori,  d,   Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 1, clientId, gethandcap );
                        viewModel.saveTrips(tripsResponse);
                        viewModel.savetripMutableLiveData.observe(SecondStepMapActivity.this, new Observer<TripsResponse>() {
                            @Override
                            public void onChanged(TripsResponse tripsResponse) {
                                if(tripsResponse != null){
                                    Toast.makeText(SecondStepMapActivity.this, "Your schdule trip saved sucessfully", Toast.LENGTH_SHORT).show();
                                    Constants.saveTripData("","","","","","",SecondStepMapActivity.this);
                                    Constants.saveTripData2(0,0,0,"","","",SecondStepMapActivity.this);
                                    Intent intt=new Intent(SecondStepMapActivity.this, MainClientActivity.class);
                                    startActivity(intt);
                                }
                            }
                        });*/

                        List<TripDateTimeListItem> dateTimeList = new ArrayList<>();


                        for (int ii = 0; ii < selectedDates.size(); ii++) {
                            Calendar mcalendar2 = selectedDates.get(ii);

                            String myFormat2 = "yyyy-MM-dd";
                            SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.US);

                            String d2 = sdf2.format(mcalendar2.getTime());

                            //TripDateTimeListItem dateTimeListitem = new TripDateTimeListItem(d2,d2+ " " + time);

                            dateTimeList.add(new TripDateTimeListItem(d2, d2 + " " + time));
                        }

                        AddScduleTrip addScduleTrip=new AddScduleTrip(
                                Double.parseDouble(cost), triptype, gethealthy, getori, Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, Double.parseDouble(getlongitude), Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, triptype, 1, clientId, gethandcap, dateTimeList);

                        Log.i("AddScduleTrip",addScduleTrip.toString());


                        viewModel.addScduleTrip(addScduleTrip);

                        viewModel.addScduleTripMutableLiveData.observe(SecondStepMapActivity.this, new Observer<AddScduleTrip>() {
                            @Override
                            public void onChanged(AddScduleTrip addScduleTrip) {
                                if(addScduleTrip != null){
                                    Toast.makeText(SecondStepMapActivity.this, "Your schdule trip saved sucessfully", Toast.LENGTH_SHORT).show();
                                    Constants.saveTripData("", "", "", "", "", "", SecondStepMapActivity.this);
                                    Constants.saveTripData2(0, 0, 0, "", "", "", SecondStepMapActivity.this);
                                    Intent intt = new Intent(SecondStepMapActivity.this, MainClientActivity.class);
                                    startActivity(intt);
                                }
                            }
                        });




                    }else{

                        Intent ii=new Intent(SecondStepMapActivity.this,RouteActivity.class);
                        ii.putExtra("selectedDates", (Serializable) selectedDates);
                        ii.putExtra("selectedDTime",time);
                        ii.putExtra("Scduled","yes");
                        startActivity(ii);
                    }

                //}


            }else {
                Intent i = new Intent(SecondStepMapActivity.this, RequestTripActivity.class);
                startActivityForResult(i, 1);
            }
        }else if(v.getId() == R.id.schdule){
            Intent i=new Intent(SecondStepMapActivity.this,SchduleTripActivity.class);
            startActivityForResult(i,2);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (markerclicked == 1)
        {
            Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
            ii.putExtra("longitude", oldlongitude);
            ii.putExtra("latitude", oldlatitude);
            ii.putExtra("addresss", ori);
            startActivity(ii);
        }else if(markerclicked == 2){
            Intent ii = new Intent(SecondStepMapActivity.this, AddClientFavouriteDialogActivity2.class);
            ii.putExtra("longitude", newlongitude);
            ii.putExtra("latitude", newlatitude);
            ii.putExtra("addresss", des);
            startActivity(ii);
        }

    }



    /*public void clicked(View view) throws JSONException {

        JsonObject locationJsonObject = new JsonObject();
        locationJsonObject.put("origin", "54.406505,18.67708");
        locationJsonObject.put("destination", "54.446251,18.570993");
        LatlngCalc(locationJsonObject);
    }*/

   /* private void LatlngCalc(JsonObject locationJsonObject) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(SecondStepMapActivity.this);
        String url = "http://maps.googleapis.com/maps/api/distancematrix/" +
                "json?origins=" + locationJsonObject.getString("origin") + "&destinations=" + locationJsonObject.getString("destination") + "&mode=driving&" +
                "language=en-EN&sensor=false";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        mTextView.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }*/
}
