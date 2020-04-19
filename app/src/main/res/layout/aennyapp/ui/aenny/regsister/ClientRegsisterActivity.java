package design.swira.aennyapp.ui.aenny.regsister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityClientRegsisterBinding;
import design.swira.aennyapp.pojo.aenny.areas.Areas;
import design.swira.aennyapp.pojo.aenny.cities.City;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.pojo.aenny.genders.Genders;
import design.swira.aennyapp.ui.aenny.AennyMainActivity;
import design.swira.aennyapp.ui.aenny.intro.IntroOneActivity;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.signin.LoginActivity;
import design.swira.aennyapp.ui.aenny.terms.TermssActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.Network;

public class ClientRegsisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityClientRegsisterBinding binding;
    RegsisterViewModel regsisterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_client_regsister);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_client_regsister);
        
        regsisterViewModel= ViewModelProviders.of(this).get(RegsisterViewModel.class);

        binding.signin.setOnClickListener(this);
        binding.regsister.setOnClickListener(this);
        binding.seenimg.setOnClickListener(this);
        binding.seenimgagain.setOnClickListener(this);
        binding.improve.setOnClickListener(this);
        binding.terms.setOnClickListener(this);

        bindData();
    }

    private void bindData() {
        regsisterViewModel.getCities();
        regsisterViewModel.getAreas();
        regsisterViewModel.getGenders();
        regsisterViewModel.getDisabilityTypes();

        regsisterViewModel.citiesMutableLiveData.observe(ClientRegsisterActivity.this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {

            }
        });

        regsisterViewModel.areasMutableLiveData.observe(ClientRegsisterActivity.this, new Observer<List<Areas>>() {
            @Override
            public void onChanged(List<Areas> areas) {

            }
        });

        regsisterViewModel.gendersMutableLiveData.observe(ClientRegsisterActivity.this, new Observer<List<Genders>>() {
            @Override
            public void onChanged(List<Genders> genders) {

            }
        });

        regsisterViewModel.disabilitytypesMutableLiveData.observe(ClientRegsisterActivity.this, new Observer<List<DisabilityTypes>>() {
            @Override
            public void onChanged(List<DisabilityTypes> disabilityTypes) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin:
                Intent i=new Intent(ClientRegsisterActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.regsister:
                clientRegsister();
                break;
            case R.id.seenimg:
                if(binding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.seenimg.setImageResource(R.drawable.ic_unseen);
                    binding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    binding.seenimg.setImageResource(R.drawable.ic_seen);
                    binding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                break;
            case R.id.terms:
                Intent ii9 = new Intent(ClientRegsisterActivity.this, TermssActivity.class);
                           startActivity(ii9);
            case R.id.seenimgagain:
                if(binding.passwordagain.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.seenimgagain.setImageResource(R.drawable.ic_unseen);
                    binding.passwordagain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    binding.seenimgagain.setImageResource(R.drawable.ic_seen);
                    binding.passwordagain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                break;
            case R.id.improve:
                String name=binding.name.getText().toString();
                String mob=binding.mobile.getText().toString();
                String password=binding.password.getText().toString();
                String newpass=binding.passwordagain.getText().toString();
                if(!password.equals(newpass)){
                    Toast.makeText(ClientRegsisterActivity.this, "Your password not match", Toast.LENGTH_SHORT).show();
                }else {
                    Intent ii = new Intent(ClientRegsisterActivity.this, CompleteRegsisterActivity.class);
                    ii.putExtra("name",name);
                    ii.putExtra("mob",mob);
                    ii.putExtra("password",password);
                    startActivity(ii);
                }
                break;
        }

    }

    private void clientRegsister() {

        if(!Network.isNetworkAvailable(ClientRegsisterActivity.this)){
            AlertDialog.Builder builder=new AlertDialog.Builder(ClientRegsisterActivity.this);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Warnning!");
            builder.setMessage("Network not exists,pleae connect to internet");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*Intent i=new Intent(ClientRegsisterActivity.this, IntroOneActivity.class);
                    startActivity(i);*/
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else {


            String name = binding.name.getText().toString();
            String mob = binding.mobile.getText().toString();
            String password = binding.password.getText().toString();
            String newpass = binding.passwordagain.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(ClientRegsisterActivity.this, "Must enter your name", Toast.LENGTH_SHORT).show();
                binding.name.setError("Must enter your name");
            } else if (mob.isEmpty()) {
                Toast.makeText(ClientRegsisterActivity.this, "Must enter your mobile", Toast.LENGTH_SHORT).show();
                binding.mobile.setError("Must enter your mobile");
            } else if (password.isEmpty()) {
                Toast.makeText(ClientRegsisterActivity.this, "Must enter your password", Toast.LENGTH_SHORT).show();
                binding.password.setError("Must enter your password");
            } else if (newpass.isEmpty()) {
                Toast.makeText(ClientRegsisterActivity.this, "Must enter your password again", Toast.LENGTH_SHORT).show();
                binding.passwordagain.setError("Must enter your password again");
            } else if (!password.equals(newpass)) {
                Toast.makeText(ClientRegsisterActivity.this, "Your password not match", Toast.LENGTH_SHORT).show();
                binding.passwordagain.setError("Your password not match");
            } else {
                doClientRegsister(name, mob, password);
                //Toast.makeText(ClientRegsisterActivity.this, "Your Secssfully Regsistered", Toast.LENGTH_SHORT).show();

            }

        }
    }

    /*private void doClientRegsister(String name, String mob, String password) {
        Client client=new Client(name, mob, password);
        regsisterViewModel.regsisterClientOnline(client);
        regsisterViewModel.addmsgMutableLiveData.observe(ClientRegsisterActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(ClientRegsisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void doClientRegsister(String name, String mob, String password) {
        Client client=new Client(name, mob, password);
        regsisterViewModel.regsisterClientOnline(name,mob,password);
        regsisterViewModel.addmsgMutableLiveData.observe(ClientRegsisterActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client clientdata) {
                if(clientdata != null) {
                    Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile(),clientdata.getClientEmail() ,ClientRegsisterActivity.this);
                    Toast.makeText(ClientRegsisterActivity.this, clientdata.getClientName() + " secessfully Regsister", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(ClientRegsisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(ClientRegsisterActivity.this, "Regsister failed!,try again", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
