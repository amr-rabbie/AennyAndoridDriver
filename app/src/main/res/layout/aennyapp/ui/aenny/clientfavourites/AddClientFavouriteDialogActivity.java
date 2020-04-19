package design.swira.aennyapp.ui.aenny.clientfavourites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class AddClientFavouriteDialogActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    @BindView(R.id.locname)
    EditText locname;
    @BindView(R.id.home)
    FrameLayout home;
    @BindView(R.id.hospital)
    FrameLayout hospital;
    @BindView(R.id.school)
    FrameLayout school;
    @BindView(R.id.location)
    FrameLayout location;
    @BindView(R.id.home2)
    FrameLayout home2;
    @BindView(R.id.hospital2)
    FrameLayout hospital2;
    @BindView(R.id.school2)
    FrameLayout school2;
    @BindView(R.id.location2)
    FrameLayout location2;
    @BindView(R.id.save)
    Button save;
    private GoogleMap mMap;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    String desc;
    String notes;

    ClientFavouriteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_add_client_favourite_dialog);

        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        viewModel = ViewModelProviders.of(this).get(ClientFavouriteViewModel.class);


        getLocation();

        save.setOnClickListener(this);
        home.setOnClickListener(this);
        home2.setOnClickListener(this);
        hospital.setOnClickListener(this);
        hospital2.setOnClickListener(this);
        school.setOnClickListener(this);
        school2.setOnClickListener(this);
        location.setOnClickListener(this);
        location2.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(AddClientFavouriteDialogActivity.this, R.raw.my_map_style);
        googleMap.setMapStyle(style);

        int height2 = 1;
        int width2 = 1;
        BitmapDrawable bitmapdraw2 = (BitmapDrawable)getResources().getDrawable(R.mipmap.pin);
        Bitmap b2 = bitmapdraw2.getBitmap();
        Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width2, height2, false);

        // Add a marker in Sydney and move the camera
        LatLng myloc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(myloc).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)).title("My Location"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));

        // Enable the zoom controls for the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);*/
        /*googleMap.setTrafficEnabled(true);*/
        mMap.getUiSettings().setMapToolbarEnabled(true);


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-generated method stub
                Log.d("System out", "onMarkerDragStart..." + arg0.getPosition().latitude + "..." + arg0.getPosition().longitude);


            }

            @SuppressWarnings("unchecked")
            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub
                Log.d("System out", "onMarkerDragEnd..." + arg0.getPosition().latitude + "..." + arg0.getPosition().longitude);

                //Toast.makeText(TestMapsActivity.this, "latitude: " + arg0.getPosition().latitude + " - longitude: " + arg0.getPosition().longitude, Toast.LENGTH_SHORT).show();

                //mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));



                latitude = arg0.getPosition().latitude;
                longitude = arg0.getPosition().longitude;
                LatLng myloc = new LatLng(latitude, longitude);

                //Toast.makeText(AddClientFavouriteDialogActivity.this, latitude + "-" + longitude, Toast.LENGTH_SHORT).show();

                CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(15).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



                /*Intent resultIntent = new Intent();
                resultIntent.putExtra("long",arg0.getPosition().longitude);  // put data that you want returned to activity A
                resultIntent.putExtra("late",arg0.getPosition().latitude);  // put data that you want returned to activity A
                setResult(Activity.RESULT_OK, resultIntent);
                finish();*/
            }

            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub
                Log.i("System out", "onMarkerDrag...");
            }
        });

//Don't forget to Set draggable(true) to marker, if this not set marker does not drag.

        int height = 100;
        int width = 85;
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.mipmap.pin);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        /*mMap.addMarker(new MarkerOptions()
                .position(myloc)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapbin))
                .draggable(true));*/

        mMap.addMarker(new MarkerOptions()
                .position(myloc)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .draggable(true));
    }


    public void getLocation() {

        latitude = 30.0135812;
        longitude = 31.2819673;

        if (!Network.isNetworkAvailable(AddClientFavouriteDialogActivity.this)) {
            latitude = 30.0135812;
            longitude = 31.2819673;
            return;
        } else {

            gpsTracker = new GpsTracker(AddClientFavouriteDialogActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                Log.i("infffff",latitude+"");
                longitude = gpsTracker.getLongitude();
                Log.i("infffff",longitude+"");

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(TestMapsActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
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
            desc=address;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.home:
                home.setBackgroundColor(Color.LTGRAY);
                notes="home";
                break;
            case R.id.home2:
                home2.setBackgroundColor(Color.LTGRAY);
                notes="home";
            case R.id.hospital:
                hospital.setBackgroundColor(Color.LTGRAY);
                notes="hospital";
                break;
            case R.id.hospital2:
                hospital2.setBackgroundColor(Color.LTGRAY);
                notes="hospital";
                break;
            case R.id.school:
                school.setBackgroundColor(Color.LTGRAY);
                notes="school";
                break;
            case R.id.school2:
                school2.setBackgroundColor(Color.LTGRAY);
                notes="school";
                break;
            case R.id.location:
                location.setBackgroundColor(Color.LTGRAY);
                notes="location";
                break;
            case R.id.location2:
                location2.setBackgroundColor(Color.LTGRAY);
                notes="location";
                break;
        }


        if(v.getId() == R.id.save){

            getcuraddress();
            saveClientFavourites();
        }


    }

    private void saveClientFavourites() {
        String locnamee=locname.getText().toString();
        int Clientid= Constants.getClientId(AddClientFavouriteDialogActivity.this);

        notes=getIntent().getStringExtra("notes");




        ClientFavouriteResponse clientFavouriteResponse=new ClientFavouriteResponse(longitude, notes, latitude, Clientid, locnamee, desc);

        viewModel.saveClientFavourite(clientFavouriteResponse);

        viewModel.clientFavouriteMutableLiveData.observe(AddClientFavouriteDialogActivity.this, new Observer<ClientFavouriteResponse>() {
            @Override
            public void onChanged(ClientFavouriteResponse clientFavouriteResponse) {
                if(clientFavouriteResponse != null){
                    Toast.makeText(AddClientFavouriteDialogActivity.this, "Client Favourite Added Sucessfully", Toast.LENGTH_SHORT).show();
                    /*Intent i2=new Intent(AddClientFavouriteDialogActivity.this, ClientFavouriteListActivity.class);
                    startActivity(i2);*/
                    /*Intent resultIntent = new Intent();
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();*/

                    Intent i=new Intent(AddClientFavouriteDialogActivity.this, MainClientActivity.class);
                    startActivity(i);
                }
            }
        });

    }
}
