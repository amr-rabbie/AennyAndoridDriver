package design.swira.aennyapp.ui.aenny.ongonigtrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityRequestTripBinding;
import design.swira.aennyapp.databinding.ActivityUpdateRequestTripBinding;

public class UpdateRequestTripActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityUpdateRequestTripBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_update_request_trip);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_request_trip);

        binding.cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cancel){
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
