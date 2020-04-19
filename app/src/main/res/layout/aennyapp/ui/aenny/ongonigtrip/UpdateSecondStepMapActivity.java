package design.swira.aennyapp.ui.aenny.ongonigtrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
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
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.adapters.ClientPaymentsMethodsAdapter;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.paymentsmethods.PaymentsMethodsViewModel;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class UpdateSecondStepMapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

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
    int id;

    ArrayAdapter<String> gameKindArray;
    ArrayAdapter<String> gameKindArray2;


    private String TAG = "so47492459";

    OnGoingViewModel viewModel;

    String healthynum;
    String handcupnum;
    String visaname;
    String cost;
    String distance;
    int paidtype=1;

    String[] disabilityarray=new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] healtyarray=new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] paymentsmethodsarray=new String[]{"Visa ... 1234","Visa ... 4564","Visa ... 4098"};

    PaymentsMethodsViewModel paymentsMethodsViewModel;
    ClientPaymentsMethodsAdapter clientPaymentsMethodsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_second_step_map);

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
        id = (int) getIntent().getExtras().get("id");

        fillSpiners();

        healthy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                healthynum = (String) parent.getItemAtPosition(position);
                //Toast.makeText(UpdateSecondStepMapActivity.this, "healthynum: " + healthynum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        disabiliy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handcupnum=(String) parent.getItemAtPosition(position);
                //Toast.makeText(UpdateSecondStepMapActivity.this, "handcupnum: " + handcupnum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        go.setOnClickListener(this);
        schdule.setOnClickListener(this);

        fillMyUpdatedData();



    }

    private void fillMyUpdatedData() {
        Intent intent=getIntent();
        if(intent.hasExtra("id")){
            id=(int)intent.getExtras().get("id");

            viewModel.getTripById(id);

            viewModel.gettripbyidMutableLiveData.observe(UpdateSecondStepMapActivity.this, new Observer<TripsResponse>() {
                @Override
                public void onChanged(TripsResponse tripsResponse) {


                    healthy.setSelection(tripsResponse.getHealthyNumber()-1);
                    disabiliy.setSelection(tripsResponse.getHandicappedNumber()-1);

                    /*int spinnerPosition = clientPaymentsMethodsAdapter.getPosition(tripsResponse.getVisaNumber());
                    paymentmethod.setSelection(spinnerPosition);*/



                    String compareValue=tripsResponse.getVisaNumber();

                    //paymentmethod.setSelection(getIndex(paymentmethod, tripsResponse.getVisaNumber()));

                    /*if (compareValue != null) {
                        int spinnerPosition = clientPaymentsMethodsAdapter.getPosition(compareValue);
                        paymentmethod.setSelection(spinnerPosition);
                    }*/


                    //paymentmethod.setSelection(getIndex(paymentmethod, compareValue));



                    /*cost= String.valueOf(tripsResponse.getTripCost());
                    distance= String.valueOf(tripsResponse.getTripDistance());*/


                }
            });


        }
    }

    private void fillSpiners() {
        gameKindArray= new ArrayAdapter<String>(UpdateSecondStepMapActivity.this,android.R.layout.simple_spinner_item, disabilityarray);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disabiliy.setAdapter(gameKindArray);

        gameKindArray2= new ArrayAdapter<String>(UpdateSecondStepMapActivity.this,android.R.layout.simple_spinner_item, healtyarray);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        healthy.setAdapter(gameKindArray2);

        /*ArrayAdapter<String> gameKindArray3= new ArrayAdapter<String>(SecondStepMapActivity.this,android.R.layout.simple_spinner_item, paymentsmethodsarray);
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentmethod.setAdapter(gameKindArray3);*/

        int clientid= Constants.getClientId(UpdateSecondStepMapActivity.this);
        paymentsMethodsViewModel.getPaymentsMethodsByClientId(clientid);

        paymentsMethodsViewModel.clientpaymentsmethodsMutableLiveData2.observe(UpdateSecondStepMapActivity.this, new Observer<List<ClientsPaymentsMethodsResponse>>() {
            @Override
            public void onChanged(List<ClientsPaymentsMethodsResponse> clientsPaymentsMethodsResponses) {
                if(clientsPaymentsMethodsResponses != null) {
                    clientPaymentsMethodsAdapter = new ClientPaymentsMethodsAdapter(UpdateSecondStepMapActivity.this, clientsPaymentsMethodsResponses);
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

        if (!Network.isNetworkAvailable(UpdateSecondStepMapActivity.this)) {
            latitude = 33.312805;
            longitude = 44.361488;
            return;
        } else {

            gpsTracker = new GpsTracker(UpdateSecondStepMapActivity.this);
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

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(UpdateSecondStepMapActivity.this, R.raw.my_map_style);
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

        int height = 59;
        int width = 49;
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);





        mMap.addMarker(new MarkerOptions().position(barcelona).title(ori).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        LatLng madrid = new LatLng(newlatitude, newlongitude);
        //mMap.addMarker(new MarkerOptions().position(madrid).title("Second Place"));
        mMap.addMarker(new MarkerOptions().position(madrid).title(des).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

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

        curloc = new LatLng((oldlatitude + newlatitude)/2, (oldlongitude + newlongitude)/2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curloc, 12));

        String key = MapApiClient.GooglemapKey;

        viewModel.GetDistanceAndTime(origin, destination, key);

        viewModel.googleMapsResponseMutableLiveData.observe(UpdateSecondStepMapActivity.this, new Observer<GoogleMapsResponse>() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSecondStepMapActivity.this);
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





    }

    private void calcatePrice(Double mydistance) {
        Double sum=10 + mydistance * 15;
        distance=mydistance.toString();
        cost=sum.toString();
        go.setText("Next (" + sum + " RS Est.)");
    }


    @Override
    public void onClick(View v) {

        int hnum= Integer.parseInt(healthynum);
        int hcnum=Integer.parseInt(handcupnum);
        Constants.saveTripData2(hnum,hcnum,paidtype,visaname,distance,cost,UpdateSecondStepMapActivity.this);


        if(v.getId() == R.id.go){
            /*Intent intent=getIntent();
            if(intent.hasExtra("selectedDates")) {
                List<Calendar> selectedDates = (List<Calendar>) intent.getSerializableExtra("selectedDates");
                String time = (String) intent.getExtras().get("selectedDTime");

                for(int i=0;i<selectedDates.size();i++){
                    Calendar mcalendar = selectedDates.get(i);

                    String myFormat = "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    String d=sdf.format(mcalendar.getTime());

                    //Toast.makeText(SchduleTripActivity.this, "Date: " + d + "\n" + "Time: " + time, Toast.LENGTH_SHORT).show();

                    String getori = Constants.getori(UpdateSecondStepMapActivity.this);
                    String getdes = Constants.getdes(UpdateSecondStepMapActivity.this);
                    String distance=Constants.getdistance(UpdateSecondStepMapActivity.this);
                    String cost=Constants.getcost(UpdateSecondStepMapActivity.this);
                    String getlatitude = Constants.getlatitude(UpdateSecondStepMapActivity.this);
                    String getlongitude = Constants.getlongitude(UpdateSecondStepMapActivity.this);
                    String getlatitude2 = Constants.getlatitude2(UpdateSecondStepMapActivity.this);
                    String getlongitude2 = Constants.getlongitude2(UpdateSecondStepMapActivity.this);
                    int clientId = Constants.getClientId(UpdateSecondStepMapActivity.this);
                    int gethealthy = Constants.gethealthy(UpdateSecondStepMapActivity.this);
                    int gethandcap = Constants.gethandcap(UpdateSecondStepMapActivity.this);
                    int getpaidtype = Constants.getpaidtype(UpdateSecondStepMapActivity.this);
                    String getpaid = Constants.getpaid(UpdateSecondStepMapActivity.this);
                    int triptype=2;


                    if(!getori.isEmpty() && !getdes.isEmpty() && !distance.isEmpty() && !cost.isEmpty()){
                        // Toast.makeText(SchduleTripActivity.this, "You must save here", Toast.LENGTH_SHORT).show();

                        //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, time,Double.parseDouble(getlongitude) , String tripScheduleTime,  String tripDestinationTime,  triptype, gethealthy, getori,  d,  String tripScheduleDate,Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap,  String tripDestinationDate);
                        //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, d+" "+time,Double.parseDouble(getlongitude) ,   triptype, gethealthy, getori,  d,  Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap );
                        //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, d+" "+time, Double.parseDouble(getlongitude), d+" "+time, 0, d+" "+time, "", triptype, gethealthy, getori, "", d, 0, d, Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 0, clientId, gethandcap, 0, d);
                        TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2),  d+" "+time, Double.parseDouble(getlongitude),  triptype  , gethealthy, getori,  d,   Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 1, clientId, gethandcap );
                        viewModel.saveTrips(tripsResponse);
                        viewModel.savetripMutableLiveData.observe(UpdateSecondStepMapActivity.this, new Observer<TripsResponse>() {
                            @Override
                            public void onChanged(TripsResponse tripsResponse) {
                                if(tripsResponse != null){
                                    Toast.makeText(UpdateSecondStepMapActivity.this, "Your schdule trip saved sucessfully", Toast.LENGTH_SHORT).show();
                                    Constants.saveTripData("","","","","","",UpdateSecondStepMapActivity.this);
                                    Constants.saveTripData2(0,0,0,"","","",UpdateSecondStepMapActivity.this);
                                    Intent intt=new Intent(UpdateSecondStepMapActivity.this, MainClientActivity.class);
                                    startActivity(intt);
                                }
                            }
                        });
                    }else{

                        Intent ii=new Intent(UpdateSecondStepMapActivity.this,RouteActivity.class);
                        ii.putExtra("selectedDates", (Serializable) selectedDates);
                        ii.putExtra("selectedDTime",time);
                        ii.putExtra("Scduled","yes");
                        startActivity(ii);
                    }

                }


            }else {
                Intent i = new Intent(UpdateSecondStepMapActivity.this, RequestTripActivity.class);
                startActivityForResult(i, 1);
            }*/
            Intent i=new Intent(UpdateSecondStepMapActivity.this,UpdateSchduleTripActivity.class);
            i.putExtra("id",id);
            startActivity(i);
        }else if(v.getId() == R.id.schdule){
            Intent i=new Intent(UpdateSecondStepMapActivity.this,SchduleTripActivity.class);
            startActivityForResult(i,2);
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


    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }




}
