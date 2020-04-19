package design.swira.aennyapp.ui.aenny.signin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityLoginBinding;
import design.swira.aennyapp.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyapp.ui.aenny.changepasswordmobile.ForgotPassword1Activity;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.regsister.ClientRegsisterActivity;
import design.swira.aennyapp.ui.aenny.regsister.CompleteRegsisterActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.Network;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);

        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);


        binding.regsister.setOnClickListener(this);
        binding.forhotpassword.setOnClickListener(this);
        binding.login.setOnClickListener(this);
        binding.seenimg.setOnClickListener(this);


        if (!checkPermission()) {
            requestPermission();
        }

        if(!Constants.getClientMobile(this).isEmpty() ){
            String clientname=Constants.getClientName(this);
            Toast.makeText(LoginActivity.this, clientname + " secessfully log in", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,MainClientActivity.class);
            startActivity(i);
        }




        /*if(! Constants.getClientId(this).isEmpty()){
            Intent i = new Intent(LoginActivity.this, AennyMainActivity.class);
            startActivity(i);
        }*/

        /*binding.password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(binding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_seen), null);
                }else{
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_unseen), null);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.password.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(binding.password.getCompoundDrawables()[2]!=null){
                        if(event.getX() >= (binding.password.getRight()- binding.password.getLeft() - binding.password.getCompoundDrawables()[2].getBounds().width())) {
                            if(binding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                                binding.password.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                            }else{
                                binding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            }
                        }
                    }
                }
                return false;
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.regsister){
            //Intent i=new Intent(LoginActivity.this, ClientRegsisterActivity.class);
            Intent i=new Intent(LoginActivity.this, CompleteRegsisterActivity.class);
            startActivity(i);
        }else if(v.getId() == R.id.forhotpassword){
            Intent i=new Intent(LoginActivity.this, ForgotPassword1Activity.class);
            //Intent i=new Intent(LoginActivity.this, ChangeMobileActivity.class);
            startActivity(i);
        }else if(v.getId() == R.id.seenimg){
            if(binding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                binding.seenimg.setImageResource(R.drawable.ic_unseen);
                binding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                binding.seenimg.setImageResource(R.drawable.ic_seen);
                binding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
        }else if(v.getId() == R.id.login){
            if(!Network.isNetworkAvailable(LoginActivity.this)){
                androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this);
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
                String mobile = binding.mobile.getText().toString();
                String password = binding.password.getText().toString();

                if (mobile.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your mobile", Toast.LENGTH_SHORT).show();
                    binding.mobile.setError("Please enter your mobile");
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    binding.password.setError("Please enter your password");
                } else {
                    clientLogin(mobile, password);
                    // Toast.makeText(LoginActivity.this, "Your Secssfully Login in", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void clientLogin(String mobile, String password) {
       // try {
            //loginViewModel.ClientLoginFromApi(mobile, password);
        loginViewModel.PostClientLoginFromApi(mobile, password);
            loginViewModel.clientMutableLiveData.observe(LoginActivity.this, new Observer<ClientResponse>() {
                @Override
                public void onChanged(ClientResponse client) {
                    if(client != null) {
                        Constants.saveClientData(client.getClientId(), client.getClientName(), "", client.getClientMobile() ,client.getClientEmail() , LoginActivity.this);
                        Toast.makeText(LoginActivity.this, client.getClientName() + " secessfully log in", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainClientActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this, "This is user not found!", Toast.LENGTH_SHORT).show();
                        binding.mobile.setText("");
                        binding.password.setText("");
                    }
                }
            });
        /*}catch (Exception ex){
            Toast.makeText(LoginActivity.this, "This is user not found!", Toast.LENGTH_SHORT).show();
            binding.mobile.setText("");
            binding.password.setText("");
        }*/
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readStorageAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted && readStorageAccepted && writeStorageAccepted)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("التطبيق يحتاج بعض الصلاحيات",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                }

                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoginActivity.this)
                .setMessage(message)
                .setPositiveButton("تم", okListener)
                .setNegativeButton("الغاء", null)
                .create()
                .show();
    }



}
