package design.swira.aennyapp.ui.aenny.settingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityMainSettingsBinding;
import design.swira.aennyapp.ui.aenny.signin.LoginActivity;
import design.swira.aennyapp.utils.Constants;

public class MainSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_settings);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_settings);

        binding.signout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signout){
            Constants.saveClientData(0,"","","","" ,MainSettingsActivity.this);
            Toast.makeText(MainSettingsActivity.this, "You are sign out", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainSettingsActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
