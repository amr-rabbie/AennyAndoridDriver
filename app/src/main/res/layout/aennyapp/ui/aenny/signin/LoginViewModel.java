package design.swira.aennyapp.ui.aenny.signin;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyapp.pojo.aenny.loginresponse.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    LoginResponse client;
    Context context;
    MutableLiveData<ClientResponse> clientMutableLiveData=new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public void ClientLoginFromApi(String mobile, String password){
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
    }

    public void PostClientLoginFromApi(String mobile, String password){
        try{
            ApiClient.getInstance().PostclientLogin(mobile,password).enqueue(new Callback<ClientResponse>() {
                @Override
                public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                    //client =  response.body();
                    clientMutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<ClientResponse> call, Throwable t) {
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
