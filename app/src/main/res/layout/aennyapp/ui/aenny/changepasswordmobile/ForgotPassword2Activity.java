package design.swira.aennyapp.ui.aenny.changepasswordmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityForgotPassword1Binding;
import design.swira.aennyapp.databinding.ActivityForgotPassword2Binding;
import design.swira.aennyapp.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.signin.LoginActivity;
import design.swira.aennyapp.utils.Constants;

public class ForgotPassword2Activity extends AppCompatActivity implements View.OnClickListener {

    ActivityForgotPassword2Binding binding;
    ChangePassMobViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_forgot_password2);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password2);

        viewModel= ViewModelProviders.of(this).get(ChangePassMobViewModel.class);

        binding.save.setOnClickListener(this);
        binding.seenimg.setOnClickListener(this);
        binding.seenimg2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                ForgotPassword();
                break;
            case R.id.seenimg:
                if(binding.oldpass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.seenimg.setImageResource(R.drawable.ic_unseen);
                    binding.oldpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    binding.seenimg.setImageResource(R.drawable.ic_seen);
                    binding.oldpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                break;
            case R.id.seenimg2:
                if(binding.newpass.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.seenimg2.setImageResource(R.drawable.ic_unseen);
                    binding.newpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    binding.seenimg2.setImageResource(R.drawable.ic_seen);
                    binding.newpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                break;
        }
    }

    private void ForgotPassword() {

        Intent intent=getIntent();
        if(intent.hasExtra("mob")){
            String mob=(String) intent.getExtras().get("mob");

            String password=binding.newpass.getText().toString();

            if(password.isEmpty()){
                Toast.makeText(ForgotPassword2Activity.this, "must enter your new password", Toast.LENGTH_SHORT).show();
            }else{
                viewModel.ClientForgotPassword(mob,password);

                viewModel.forgotpasswordMutableLiveData.observe(ForgotPassword2Activity.this, new Observer<ClientResponse>() {
                    @Override
                    public void onChanged(ClientResponse clientResponse) {
                        if(clientResponse != null){
                            if(clientResponse != null){
                                //Constants.saveClientData(clientResponse.getClientId(), clientResponse.getClientName(), "", clientResponse.getClientMobile(), clientResponse.getClientEmail(),ChangePasswordActivity.this);
                                Toast.makeText(ForgotPassword2Activity.this, "You secssfully updated your password", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ForgotPassword2Activity.this, LoginActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(ForgotPassword2Activity.this, "Update password failed!,try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }


        }
    }
}
