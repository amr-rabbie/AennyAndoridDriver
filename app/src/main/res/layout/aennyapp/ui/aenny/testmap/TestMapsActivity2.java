package design.swira.aennyapp.ui.aenny.testmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyapp.R;
import design.swira.aennyapp.ui.aenny.clientfavourites.AddClientFavouriteDialogActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.AddClientFavouriteDialogActivity2;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class TestMapsActivity2 extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    @BindView(R.id.address)
    TextView addresss;
    @BindView(R.id.ok)
    Button ok;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private GpsTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_maps2);
        ButterKnife.bind(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ok.setOnClickListener(this);
        addresss.setOnClickListener(this);
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

        getLocation();

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(TestMapsActivity2.this, R.raw.my_map_style);
        googleMap.setMapStyle(style);


        int height2 = 1;
        int width2 = 1;
        BitmapDrawable bitmapdraw2 = (BitmapDrawable) getResources().getDrawable(R.mipmap.pin);
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



        /*mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng midLatLng = mMap.getCameraPosition().target;
                //getAddress(midLatLng.latitude, midLatLng.longitude);
                longitude = midLatLng.longitude;
                latitude = midLatLng.latitude;
                getAddress();
            }
        });*/

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                LatLng midLatLng = mMap.getCameraPosition().target;
                //getAddress(midLatLng.latitude, midLatLng.longitude);
                longitude = midLatLng.longitude;
                latitude = midLatLng.latitude;
                getAddress();
            }
        });
    }

    public void getLocation() {

        latitude = 30.0135812;
        longitude = 31.2819673;

        if (!Network.isNetworkAvailable(TestMapsActivity2.this)) {
            latitude = 30.0135812;
            longitude = 31.2819673;
            return;
        } else {

            gpsTracker = new GpsTracker(TestMapsActivity2.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(TestMapsActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }


    private void getAddress() {
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
            //binding.curloc.setText(address);
            addresss.setText(address);
            //Toast.makeText(TestMapsActivity2.this, "Long: " + longitude + " - " + "Late: " + latitude + "\n" + address, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId() ){
            case R.id.ok:
                Intent resultIntent = new Intent();
                resultIntent.putExtra("long",longitude);  // put data that you want returned to activity A
                resultIntent.putExtra("late",latitude);  // put data that you want returned to activity A
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.address:
                Intent ii = new Intent(TestMapsActivity2.this, AddClientFavouriteDialogActivity2.class);
                ii.putExtra("longitude",longitude);
                ii.putExtra("latitude",latitude);
                ii.putExtra("addresss",addresss.getText().toString());
                startActivity(ii);
                break;
        }
    }
}
