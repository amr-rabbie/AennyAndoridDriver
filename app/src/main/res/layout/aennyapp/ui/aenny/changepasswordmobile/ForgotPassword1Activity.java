package design.swira.aennyapp.ui.aenny.changepasswordmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityForgotPassword1Binding;
import design.swira.aennyapp.utils.Constants;

public class ForgotPassword1Activity extends AppCompatActivity implements View.OnClickListener {

    ActivityForgotPassword1Binding binding;
    ChangePassMobViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forgot_password1);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password1);

        viewModel= ViewModelProviders.of(this).get(ChangePassMobViewModel.class);

        binding.next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String clientMobile = Constants.getClientMobile(ForgotPassword1Activity.this);
        if(v.getId() == R.id.next){
            String mob=binding.mob.getText().toString();

            if(mob.isEmpty()){
                Toast.makeText(ForgotPassword1Activity.this, "must enter your regsistered mobile", Toast.LENGTH_SHORT).show();
            }/*else if(!mob.equals(clientMobile)){
                Toast.makeText(ForgotPassword1Activity.this, "mobile entered not matched mobile regsisted on this divce", Toast.LENGTH_SHORT).show();
            }*/else{
                Intent i=new Intent(ForgotPassword1Activity.this,ForgotPassword2Activity.class);
                i.putExtra("mob",mob);
                startActivity(i);
            }
        }
    }
}
