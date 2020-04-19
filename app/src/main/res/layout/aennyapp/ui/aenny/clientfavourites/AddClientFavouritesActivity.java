package design.swira.aennyapp.ui.aenny.clientfavourites;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
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
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity2;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class AddClientFavouritesActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    @BindView(R.id.locname)
    EditText locname;

    @BindView(R.id.save)
    Button save;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.hospital)
    ImageView hospital;
    @BindView(R.id.school)
    ImageView school;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.home2)
    ImageView home2;
    @BindView(R.id.hospital2)
    ImageView hospital2;
    @BindView(R.id.school2)
    ImageView school2;
    @BindView(R.id.location2)
    ImageView location2;
    private GoogleMap mMap;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    String desc;
    String notes="location";
    @BindView(R.id.address)
    TextView addresss;

    ClientFavouriteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_favourites);
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

        /*MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(AddClientFavouritesActivity.this, R.raw.my_map_style);
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

                CameraPosition cameraPosition = new CameraPosition.Builder().target(myloc).zoom(15).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));




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



        mMap.addMarker(new MarkerOptions()
                .position(myloc)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                //.icon(bitmapDescriptorFromVector(this, R.drawable.ic_pin))
                .draggable(true));*/


        getLocation();

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(AddClientFavouritesActivity.this, R.raw.my_map_style);
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
            desc=address;
            //Toast.makeText(TestMapsActivity2.this, "Long: " + longitude + " - " + "Late: " + latitude + "\n" + address, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void getLocation() {

        latitude = 30.0135812;
        longitude = 31.2819673;

        if (!Network.isNetworkAvailable(AddClientFavouritesActivity.this)) {
            latitude = 30.0135812;
            longitude = 31.2819673;
            return;
        } else {

            gpsTracker = new GpsTracker(AddClientFavouritesActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

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
            desc = address;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
               /* home.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home_active);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school);

                notes = "home";
                break;
            case R.id.home2:
                /*home2.setBackgroundColor(Color.LTGRAY);
                home.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home_active);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school);

                notes = "home";
            case R.id.hospital:
                /*hospital.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                home.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hos_active);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school);

                notes = "hospital";
                break;
            case R.id.hospital2:
                /*hospital2.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                home.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hos_active);
                school2.setImageResource(R.drawable.ic_school);

                notes = "hospital";
                break;
            case R.id.school:
                /*school.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                home.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school_active);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school);

                notes = "school";
                break;
            case R.id.school2:
                /*school2.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                home.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school_active);


                notes = "school";
                break;
            case R.id.location:
                /*location.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                home.setBackgroundColor(Color.WHITE);
                location2.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_loc_sel);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_location);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school);

                notes = "location";
                break;
            case R.id.location2:
                /*location2.setBackgroundColor(Color.LTGRAY);
                home2.setBackgroundColor(Color.WHITE);
                hospital.setBackgroundColor(Color.WHITE);
                hospital2.setBackgroundColor(Color.WHITE);
                school.setBackgroundColor(Color.WHITE);
                school2.setBackgroundColor(Color.WHITE);
                location.setBackgroundColor(Color.WHITE);
                home.setBackgroundColor(Color.WHITE);*/

                location.setImageResource(R.drawable.ic_location);
                home.setImageResource(R.drawable.ic_home);
                hospital.setImageResource(R.drawable.ic_hospital);
                school.setImageResource(R.drawable.ic_school);

                location2.setImageResource(R.drawable.ic_loc_sel);
                home2.setImageResource(R.drawable.ic_home);
                hospital2.setImageResource(R.drawable.ic_hospital);
                school2.setImageResource(R.drawable.ic_school);


                notes = "location";
                break;
                /*default:
                    location2.setBackgroundColor(Color.LTGRAY);
                    home2.setBackgroundColor(Color.WHITE);
                    hospital.setBackgroundColor(Color.WHITE);
                    hospital2.setBackgroundColor(Color.WHITE);
                    school.setBackgroundColor(Color.WHITE);
                    school2.setBackgroundColor(Color.WHITE);
                    location.setBackgroundColor(Color.WHITE);
                    home.setBackgroundColor(Color.WHITE);
                    notes = "location";
                    break;*/

        }


        if (v.getId() == R.id.save) {

            getcuraddress();
            saveClientFavourites();
        }


    }

    private void saveClientFavourites() {
        String locnamee = locname.getText().toString();
        int Clientid = Constants.getClientId(AddClientFavouritesActivity.this);
        ClientFavouriteResponse clientFavouriteResponse = new ClientFavouriteResponse(longitude, notes, latitude, Clientid, locnamee, desc);

        viewModel.saveClientFavourite(clientFavouriteResponse);

        viewModel.clientFavouriteMutableLiveData.observe(AddClientFavouritesActivity.this, new Observer<ClientFavouriteResponse>() {
            @Override
            public void onChanged(ClientFavouriteResponse clientFavouriteResponse) {
                if (clientFavouriteResponse != null) {
                    Toast.makeText(AddClientFavouritesActivity.this, "Client Favourite Added Sucessfully", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(AddClientFavouritesActivity.this, ClientFavouriteListActivity.class);
                    startActivity(i2);
                }
            }
        });

    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.ic_pin);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}
