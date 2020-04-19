package design.swira.aennyapp.ui.aenny.changepasswordmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityChangeMobileBinding;
import design.swira.aennyapp.databinding.ActivityChangePasswordBinding;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyapp.ui.aenny.AennyMainActivity;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.regsister.UpdateProfileActivity;
import design.swira.aennyapp.utils.Constants;

public class ChangeMobileActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityChangeMobileBinding binding;
    ChangePassMobViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_change_mobile);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_change_mobile);

        viewModel= ViewModelProviders.of(this).get(ChangePassMobViewModel.class);

        binding.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                chaneClientPassword();
                break;
        }
    }

    private void chaneClientPassword() {
        String oldmob=binding.oldmob.getText().toString();
        String newmob=binding.newmob.getText().toString();

        if(oldmob.isEmpty()){
            Toast.makeText(ChangeMobileActivity.this, "Must enter old mobile", Toast.LENGTH_SHORT).show();
            binding.oldmob.setError("Must enter old mobile");
        }else if(newmob.isEmpty()){
            Toast.makeText(ChangeMobileActivity.this, "Must enter new mobile", Toast.LENGTH_SHORT).show();
            binding.newmob.setError("Must enter new mobile");
        }else{
            changePMobile(oldmob,newmob);
        }
    }

    private void changePMobile(final String oldmob, final String newmob) {
        int clientid= Constants.getClientId(ChangeMobileActivity.this);
        /*viewModel.clientChangeMobile(clientid,oldmob,newmob);
        viewModel.changepasswoedmsg.observe(ChangeMobileActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(ChangeMobileActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });*/


        viewModel.PostChangeClientMobile(clientid,oldmob,newmob);

        viewModel.changemobileMutableLiveData.observe(ChangeMobileActivity.this, new Observer<ClientResponse>() {
            @Override
            public void onChanged(ClientResponse clientResponse) {
                if(clientResponse != null){
                    Constants.saveClientData(clientResponse.getClientId(), clientResponse.getClientName(), "", clientResponse.getClientMobile(), clientResponse.getClientEmail(),ChangeMobileActivity.this);
                    Toast.makeText(ChangeMobileActivity.this, "You secssfully updated your mobile number", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ChangeMobileActivity.this, MainClientActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ChangeMobileActivity.this, "Update mobile number failed!,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* viewModel.getClientById(clientid);

        viewModel.clientbyidMutableLiveData.observe(ChangeMobileActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client client) {
                int clientid=Constants.getClientId(ChangeMobileActivity.this);
                if(client.getClientMobile().equals(oldmob)) {
                    client.setClientMobile(newmob);
                    viewModel.updateClientProfile(clientid, client);
                    viewModel.updatemsgMutableLiveData.observe(ChangeMobileActivity.this, new Observer<Client>() {
                        @Override
                        public void onChanged(Client clientdata) {
                            if (clientdata != null) {
                                Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile(), clientdata.getClientEmail(),ChangeMobileActivity.this);
                                Toast.makeText(ChangeMobileActivity.this, "You secssfully updated your mobile number", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ChangeMobileActivity.this, AennyMainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(ChangeMobileActivity.this, "Update mobile number failed!,try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(ChangeMobileActivity.this, "Your old mobile is wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        /*viewModel.deleteProfileClient(clientid);

        Client client=new Client(myclientdat.getClientEmail(), myclientdat.getClientName(), newmob, myclientdat.getClientCity(), myclientdat.getClientPassword(), myclientdat.getClientBirthDate(), myclientdat.getClientRelativeMobile(), myclientdat.getClientArea(), myclientdat.getClientGender(), myclientdat.getUseAppDisabled());

        viewModel.fullRegsisterClientOnline(client);
        viewModel.fullladdmsgMutableLiveData.observe(ChangeMobileActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client clientdata) {
                if(clientdata != null) {
                    Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile(), ChangeMobileActivity.this);



                    Toast.makeText(ChangeMobileActivity.this, clientdata.getClientName() + " secessfully Updated Mobile", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(ChangeMobileActivity.this, AennyMainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ChangeMobileActivity.this, "Update mobile failed!,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}
