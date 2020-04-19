package design.swira.aennyapp.ui.aenny.testmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

import design.swira.aennyapp.R;
import design.swira.aennyapp.ui.aenny.AennyMainActivity;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class TestMapsActivity extends AppCompatActivity implements OnMapReadyCallback , View.OnClickListener {

    private GoogleMap mMap;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);



        setContentView(R.layout.activity_test_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ok=findViewById(R.id.ok);

        ok.setOnClickListener(this);

        getLocation();




    }

    public void getLocation(){

        latitude=30.0135812;
        longitude=31.2819673;

        if(!Network.isNetworkAvailable(TestMapsActivity.this)){
            latitude=30.0135812;
            longitude=31.2819673;
            return;
        }
        else {

            gpsTracker = new GpsTracker(TestMapsActivity.this);
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

        /*googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        googleMap.setTrafficEnabled(true);*/

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(TestMapsActivity.this, R.raw.my_map_style);
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
                Log.d("System out", "onMarkerDragStart..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub
                Log.d("System out", "onMarkerDragEnd..." + arg0.getPosition().latitude + "..." + arg0.getPosition().longitude);

                //Toast.makeText(TestMapsActivity.this, "latitude: " + arg0.getPosition().latitude + " - longitude: " + arg0.getPosition().longitude, Toast.LENGTH_SHORT).show();

                //mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));



                longitude=arg0.getPosition().longitude;
                latitude=arg0.getPosition().latitude;
                LatLng myloc = new LatLng(latitude, longitude);

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


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ok){
            Intent resultIntent = new Intent();
            resultIntent.putExtra("long",longitude);  // put data that you want returned to activity A
            resultIntent.putExtra("late",latitude);  // put data that you want returned to activity A
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }

    }
}
