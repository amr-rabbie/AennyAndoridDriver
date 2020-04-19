package design.swira.aennyapp.ui.aenny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import design.swira.aennyapp.R;
import design.swira.aennyapp.ui.aenny.changepasswordmobile.ChangeMobileActivity;
import design.swira.aennyapp.ui.aenny.longtrip.AddLongTripActivity;
import design.swira.aennyapp.ui.aenny.paymentsmethods.AddPaymentsMethodActivity;
import design.swira.aennyapp.ui.aenny.regsister.UpdateProfileActivity;
import design.swira.aennyapp.ui.aenny.settingscreen.MainSettingsActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.utils.GpsTracker;
import design.swira.aennyapp.utils.Network;

public class AennyMainActivity extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aenny_main);

        /*Intent i=new Intent(AennyMainActivity.this, TestMapsActivity.class);
        startActivity(i);*/

        //getLocation();


    }

    public void getLocation(){

        latitude=33.312805;
        longitude=44.361488;

        if(!Network.isNetworkAvailable(AennyMainActivity.this)){
            latitude=33.312805;
            longitude=44.361488;
            return;
        }
        else {

            gpsTracker = new GpsTracker(AennyMainActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }

        //Toast.makeText(AennyMainActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.changepassmob,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.changepass:
               /* Intent i=new Intent(AennyMainActivity.this, ChangePasswordActivity.class);
                startActivity(i);*/
                Intent i=new Intent(AennyMainActivity.this, UpdateProfileActivity.class);
                startActivity(i);
                break;
            case R.id.changemob:
                Intent ii=new Intent(AennyMainActivity.this, ChangeMobileActivity.class);
                startActivity(ii);
                break;
            case R.id.addlongtrip:
                Intent iii=new Intent(AennyMainActivity.this, AddLongTripActivity.class);
                startActivity(iii);
                break;
            case R.id.settings:
                Intent iiii=new Intent(AennyMainActivity.this, MainSettingsActivity.class);
                startActivity(iiii);
                break;
            case R.id.payments:
                Intent iiiii=new Intent(AennyMainActivity.this, AddPaymentsMethodActivity.class);
                startActivity(iiiii);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
