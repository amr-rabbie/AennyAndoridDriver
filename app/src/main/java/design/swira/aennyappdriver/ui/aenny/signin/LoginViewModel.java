package design.swira.aennyappdriver.ui.aenny.signin;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.pojo.aenny.clients.Client;
import design.swira.aennyappdriver.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyappdriver.pojo.aenny.loginresponse.LoginResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driver.DriverResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    LoginResponse client;
    Context context;
    MutableLiveData<DriverResponse> clientMutableLiveData=new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    /*public void ClientLoginFromApi(String mobile, String password){
        try{
        ApiClient.getInstance().clientLogin(mobile,password).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                //client =  response.body();
                //clientMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("login_error",t.getMessage().toString());
                //Toast.makeText(context, "This is user not found!", Toast.LENGTH_SHORT).show();
            }
        });
        //return client;
        }catch (Exception ex){
           // Toast.makeText(context, "This is user not found!", Toast.LENGTH_SHORT).show();
        }
    }*/

    public void PostClientLoginFromApi(String mobile, String password){
        try{
            ApiClient.getInstance().PostclientLogin(mobile,password).enqueue(new Callback<DriverResponse>() {
                @Override
                public void onResponse(Call<DriverResponse> call, Response<DriverResponse> response) {
                    //client =  response.body();
                    clientMutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<DriverResponse> call, Throwable t) {
                    Log.e("login_error",t.getMessage().toString());
                    //Toast.makeText(context, "This is user not found!", Toast.LENGTH_SHORT).show();
                }
            });
            //return client;
        }catch (Exception ex){
            // Toast.makeText(context, "This is user not found!", Toast.LENGTH_SHORT).show();
        }
    }


    public void ClientLogin(String mobile, String password){
        //clientMutableLiveData.setValue(ClientLoginFromApi(mobile,password));
    }


}
