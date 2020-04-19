package design.swira.aennyappdriver.ui.aenny.changepasswordmobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.databinding.ActivityChangePasswordBinding;
import design.swira.aennyappdriver.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driver.DriverResponse;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityChangePasswordBinding binding;
    ChangePassMobViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_change_password);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_change_password);

        viewModel= ViewModelProviders.of(this).get(ChangePassMobViewModel.class);

        binding.login.setOnClickListener(this);
        binding.seenimg.setOnClickListener(this);
        binding.seenimg2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                chaneClientPassword();
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

    private void chaneClientPassword() {
        String oldpass=binding.oldpass.getText().toString();
        String newpass=binding.newpass.getText().toString();

        if(oldpass.isEmpty()){
            Toast.makeText(ChangePasswordActivity.this, "Must enter old password", Toast.LENGTH_SHORT).show();
            binding.oldpass.setError("Must enter old password");
        }else if(newpass.isEmpty()){
            Toast.makeText(ChangePasswordActivity.this, "Must enter new password", Toast.LENGTH_SHORT).show();
            binding.newpass.setError("Must enter new password");
        }else{
            changePassword(oldpass,newpass);
        }
    }

    private void changePassword(final String oldpass, final String newpass) {
        int clientid= Constants.getClientId(ChangePasswordActivity.this);
        /*viewModel.clientChangePassword(clientid,oldpass,newpass);
        viewModel.changepasswoedmsg.observe(ChangePasswordActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(ChangePasswordActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });  */

        String mob=Constants.getClientMobile(ChangePasswordActivity.this);

        viewModel.PostChangeClientPassword(mob,oldpass,newpass);

        viewModel.changepasswordMutableLiveData.observe(ChangePasswordActivity.this, new Observer<DriverResponse>() {
            @Override
            public void onChanged(DriverResponse clientResponse) {
                if(clientResponse != null){
                    Constants.saveClientData(clientResponse.getDriverId(), clientResponse.getDriverName(), "", clientResponse.getDriverMobile(), clientResponse.getDriverEmail(),ChangePasswordActivity.this);
                    Toast.makeText(ChangePasswordActivity.this, "You secssfully updated your password", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ChangePasswordActivity.this, MainDriverActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ChangePasswordActivity.this, "Update password failed!,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* viewModel.getClientById(clientid);

        viewModel.clientbyidMutableLiveData.observe(ChangePasswordActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client client) {
                int clientid=Constants.getClientId(ChangePasswordActivity.this);
                if(client.getClientPassword().equals(oldpass)) {
                    client.setClientPassword(newpass);
                    viewModel.updateClientProfile(clientid, client);
                    viewModel.updatemsgMutableLiveData.observe(ChangePasswordActivity.this, new Observer<Client>() {
                        @Override
                        public void onChanged(Client clientdata) {
                            if (clientdata != null) {
                                Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile(),clientdata.getClientEmail() ,ChangePasswordActivity.this);
                                Toast.makeText(ChangePasswordActivity.this, "You secssfully updated your password", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ChangePasswordActivity.this, AennyMainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Update password failed!,try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(ChangePasswordActivity.this, "Your old password is wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

          }

}
