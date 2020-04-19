package design.swira.aennyapp.ui.aenny.ongonigtrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityRouteBinding;
import design.swira.aennyapp.databinding.ActivityUpdateRouteBinding;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.adapters.ClientFavouriteListAdapter2;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteListActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteViewModel;
import design.swira.aennyapp.ui.aenny.clientfavourites.OnLocationFavouriteClick;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity2;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class UpdateRouteActivity extends AppCompatActivity implements View.OnClickListener , OnLocationFavouriteClick {

    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    private double latitude2;
    private double longitude2;
    ActivityUpdateRouteBinding binding;
    ClientFavouriteViewModel clientFavouriteViewModel;
    ClientFavouriteListAdapter2 clientFavouriteListAdapter;
    OnGoingViewModel onGoingViewModel;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_route);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_route);

        clientFavouriteViewModel= ViewModelProviders.of(this).get(ClientFavouriteViewModel.class);

        clientFavouriteListAdapter=new ClientFavouriteListAdapter2(this);

        onGoingViewModel=ViewModelProviders.of(this).get(OnGoingViewModel.class);


        getLocation();
        getcuraddress();

        getDesAddress();

        binding.curloc.setOnClickListener(this);
        binding.curlocimg.setOnClickListener(this);
        binding.goingloc.setOnClickListener(this);
        binding.goinglocimg.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.favouritemore.setOnClickListener(this);

        binding.switchimg.setOnClickListener(this);

        bindFavouritesData();

        //getDatesListandTime();

        bindMyUpdateData();

        Places.initialize(getApplicationContext(), "AIzaSyAyKWcogS2vgE52G5ZBj9IXtgqQ7n3cP5A");

    }

    private void bindMyUpdateData() {
        Intent intent=getIntent();
        if(intent.hasExtra("id")){
            id=(int)intent.getExtras().get("id");

            onGoingViewModel.getTripById(id);

            onGoingViewModel.gettripbyidMutableLiveData.observe(UpdateRouteActivity.this, new Observer<TripsResponse>() {
                @Override
                public void onChanged(TripsResponse tripsResponse) {
                    longitude=tripsResponse.getTripPickupLong();
                    latitude=tripsResponse.getTripPickupLatt();
                    binding.curloc.setText(tripsResponse.getTripPickup());
                    longitude2=tripsResponse.getTripDestinationLong();
                    latitude2=tripsResponse.getTripDestinationLatt();
                    binding.goingloc.setText(tripsResponse.getTripDestination());


                }
            });


        }
    }

    private void getDatesListandTime() {
        Intent intent=getIntent();
        if(intent.hasExtra("selectedDates")){
            List<Calendar> selectedDates = (List<Calendar>)intent.getSerializableExtra("selectedDates");
            String selectedDTime = (String) intent.getExtras().get("selectedDTime");
            String scduled = (String)intent.getExtras().get("Scduled");

            for(int i=0;i<selectedDates.size();i++) {
                Calendar mcalendar = selectedDates.get(i);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                String d = sdf.format(mcalendar.getTime());

                Toast.makeText(UpdateRouteActivity.this, "Date: " + d + "\n" + "Time: " + selectedDTime, Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void bindFavouritesData() {
        int clientid= Constants.getClientId(UpdateRouteActivity.this);
        clientFavouriteViewModel.getClientFavouriteById(clientid);

        clientFavouriteViewModel.clientFavouritelistMutableLiveData.observe(UpdateRouteActivity.this, new Observer<List<ClientFavouriteResponse>>() {
            @Override
            public void onChanged(List<ClientFavouriteResponse> clientFavouriteResponses) {
                if(clientFavouriteResponses != null) {
                    List<ClientFavouriteResponse> list=new ArrayList<>();
                    for(int i=0;i<clientFavouriteResponses.size();i++) {
                        if(i<3) {
                            ClientFavouriteResponse clientFavouriteResponse = clientFavouriteResponses.get(i);
                            list.add(new ClientFavouriteResponse( clientFavouriteResponse.getClientFavouriteLang() , clientFavouriteResponse.getClientFavouriteNotes(),clientFavouriteResponse.getClientFavouriteLatt(), clientFavouriteResponse.getClientId(), clientFavouriteResponse.getClientFavouriteName(), clientFavouriteResponse.getClientFavouriteDesc()));
                        }
                    }
                    clientFavouriteListAdapter.setList(list);
                    binding.favouriterecycler.setAdapter(clientFavouriteListAdapter);
                    binding.favouriterecycler.setLayoutManager(new LinearLayoutManager(UpdateRouteActivity.this));
                    binding.pbar.setVisibility(View.GONE);
                    binding.favouriterecycler.setVisibility(View.VISIBLE);
                    binding.favouritetxt.setVisibility(View.GONE);



                }else{
                    binding.pbar.setVisibility(View.GONE);
                    binding.favouriterecycler.setVisibility(View.GONE);
                    binding.favouritetxt.setVisibility(View.VISIBLE);
                }

                if(clientFavouriteResponses.size() <=0){
                    binding.favouritetxt.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getDesAddress() {
        Intent intent = getIntent();
        if (intent.hasExtra("latitude2")) {
            latitude2 = (double) getIntent().getExtras().get("latitude2");
            longitude2 = (double) getIntent().getExtras().get("longitude2");
            String des = (String) getIntent().getExtras().get("des");
            binding.goingloc.setText(des);
        }
    }

    public void getLocation(){

        latitude=33.312805;
        longitude=44.361488;

        if(!Network.isNetworkAvailable(UpdateRouteActivity.this)){
            latitude=33.312805;
            longitude=44.361488;
            return;
        }
        else {

            gpsTracker = new GpsTracker(UpdateRouteActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }

        //Toast.makeText(RouteActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
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
            binding.curloc.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getgoingaddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude2, longitude2, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            binding.goingloc.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.curlocimg){
            //Intent i=new Intent(UpdateRouteActivity.this, TestMapsActivity.class);
            Intent i=new Intent(UpdateRouteActivity.this, TestMapsActivity2.class);
            startActivityForResult(i,1);
        }else if(v.getId() == R.id.goinglocimg){
            //Intent i=new Intent(UpdateRouteActivity.this, TestMapsActivity.class);
            Intent i=new Intent(UpdateRouteActivity.this, TestMapsActivity2.class);
            startActivityForResult(i,2);
        }else if(v.getId() == R.id.next){
            Intent i=new Intent(UpdateRouteActivity.this, UpdateSecondStepMapActivity.class);
            i.putExtra("latitude",latitude);
            i.putExtra("longitude",longitude);
            i.putExtra("latitude2",latitude2);
            i.putExtra("longitude2",longitude2);
            i.putExtra("ori",binding.curloc.getText().toString());
            i.putExtra("des",binding.goingloc.getText().toString());
            i.putExtra("id",id);
            String lat=latitude+"";
            String lon=longitude+"";
            String ori=binding.curloc.getText().toString();
            String lat2=latitude2+"";
            String lon2=longitude2+"";
            String des=binding.goingloc.getText().toString();
            Constants.saveTripData(lat,lon,ori,lat2,lon2,des,UpdateRouteActivity.this);
            /*Intent intent=getIntent();
            if(intent.hasExtra("selectedDates")) {
                List<Calendar> selectedDates = (List<Calendar>) intent.getSerializableExtra("selectedDates");
                String selectedDTime = (String) intent.getExtras().get("selectedDTime");
                String scduled = (String) intent.getExtras().get("Scduled");
                i.putExtra("selectedDates", (Serializable) selectedDates);
                i.putExtra("selectedDTime",selectedDTime);
                i.putExtra("Scduled","yes");
            }*/
            startActivity(i);
        }else if(v.getId() == R.id.favouritemore){
            Intent i=new Intent(UpdateRouteActivity.this, ClientFavouriteListActivity.class);
            startActivity(i);
        }else if(v.getId() == R.id.curloc){
            startAutocompleteActivity();
        }else if(v.getId() == R.id.goingloc){
            startAutocompleteActivity2();
        }else if(v.getId() == R.id.switchimg){
            double long3,lat3;
            String cloc=binding.curloc.getText().toString();
            String gloc=binding.goingloc.getText().toString();

            long3=longitude2;
            longitude2=longitude;
            longitude=long3;

            lat3=latitude2;
            latitude2=latitude;
            latitude=lat3;

            binding.curloc.setText(gloc);
            binding.goingloc.setText(cloc);






        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:
                //you just got back from activity B - deal with resultCode
                //use data.getExtra(...) to retrieve the returned data

                if(data != null) {

                    longitude = (double) data.getExtras().get("long");
                    latitude = (double) data.getExtras().get("late");
                    getcuraddress();
                    break;
                }
            case 2:

                if(data != null) {
                    //you just got back from activity C - deal with resultCode
                    longitude2 = (double) data.getExtras().get("long");
                    latitude2 = (double) data.getExtras().get("late");
                    getgoingaddress();
                    break;
                }

            case 1001:
                if (resultCode == RESULT_OK) {
                    Place place = Autocomplete.getPlaceFromIntent(data);
                    //Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                    //Toast.makeText(RouteActivity.this, place.getAddress() + "\n" + place.getLatLng(), Toast.LENGTH_SHORT).show();
                    binding.curloc.setText(place.getAddress());
                    LatLng latLng = place.getLatLng();
                    latitude=latLng.latitude;
                    longitude=latLng.longitude;
                    getcuraddress();

                } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                    // TODO: Handle the error.
                    Status status = Autocomplete.getStatusFromIntent(data);
                    Log.i("err", status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;

            case 1002:
                if (resultCode == RESULT_OK) {
                    Place place = Autocomplete.getPlaceFromIntent(data);
                    //Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                    //Toast.makeText(RouteActivity.this, place.getAddress() + "\n" + place.getLatLng(), Toast.LENGTH_SHORT).show();
                    binding.goingloc.setText(place.getAddress());
                    LatLng latLng = place.getLatLng();
                    latitude2=latLng.latitude;
                    longitude2=latLng.longitude;
                    getgoingaddress();

                } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                    // TODO: Handle the error.
                    Status status = Autocomplete.getStatusFromIntent(data);
                    Log.i("err", status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;
        }


    }


    @Override
    public void onListClick(ClientFavouriteResponse clientFavouriteResponse) {
        /*if(binding.curloc.isFocused()){
            //EditText1 is focused
            binding.curloc.setText(clientFavouriteResponse.getClientFavouriteDesc());
            latitude=clientFavouriteResponse.getClientFavouriteLatt();
            longitude=clientFavouriteResponse.getClientFavouriteLang();
        }else {
            //EditText2 is focused
            binding.goingloc.setText(clientFavouriteResponse.getClientFavouriteDesc());
            latitude2=clientFavouriteResponse.getClientFavouriteLatt();
            longitude2=clientFavouriteResponse.getClientFavouriteLang();
        }*/

        binding.goingloc.setText(clientFavouriteResponse.getClientFavouriteDesc());
        latitude2=clientFavouriteResponse.getClientFavouriteLatt();
        longitude2=clientFavouriteResponse.getClientFavouriteLang();

    }


    private void startAutocompleteActivity() {
        List<com.google.android.libraries.places.api.model.Place.Field> placeFields = new ArrayList<>(Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.values()));
        List<TypeFilter> typeFilters = new ArrayList<>(Arrays.asList(TypeFilter.values()));
// Create a RectangularBounds object.
        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng(-33.880490, 151.184363),
                new LatLng(-33.858754, 151.229596));
        Intent autocompleteIntent =
                new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, placeFields)
                        .setLocationBias(bounds)
                        .setTypeFilter(typeFilters.get(0))
                        .build(this);
        startActivityForResult(autocompleteIntent, 1001);
    }

    private void startAutocompleteActivity2() {
        List<com.google.android.libraries.places.api.model.Place.Field> placeFields = new ArrayList<>(Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.values()));
        List<TypeFilter> typeFilters = new ArrayList<>(Arrays.asList(TypeFilter.values()));
// Create a RectangularBounds object.
        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng(-33.880490, 151.184363),
                new LatLng(-33.858754, 151.229596));
        Intent autocompleteIntent =
                new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, placeFields)
                        .setLocationBias(bounds)
                        .setTypeFilter(typeFilters.get(0))
                        .build(this);
        startActivityForResult(autocompleteIntent, 1002);
    }

}
