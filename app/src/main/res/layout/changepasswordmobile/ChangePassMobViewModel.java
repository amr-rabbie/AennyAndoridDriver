package design.swira.aennyapp.ui.aenny.changepasswordmobile;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.client_disablity.ClientDisability;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.newclient.ClientResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassMobViewModel extends AndroidViewModel {
    MutableLiveData<String> changepasswoedmsg=new MutableLiveData<>();
    MutableLiveData<String> changemobilemsg=new MutableLiveData<>();
    MutableLiveData<Client> updatemsgMutableLiveData=new MutableLiveData<>();
    MutableLiveData<Client> fullladdmsgMutableLiveData=new MutableLiveData<>();
    MutableLiveData<Client> clientbyidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ClientDisability> clientDisabilityMutableLiveData=new MutableLiveData<>();

    MutableLiveData<ClientResponse> changepasswordMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ClientResponse> changemobileMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ClientResponse> forgotpasswordMutableLiveData=new MutableLiveData<>();

    public ChangePassMobViewModel(@NonNull Application application) {
        super(application);
    }

    public void clientChangePassword(int clientid, String oldpassword , String newpassword){
        ApiClient.getInstance().changeClientPassword(clientid,oldpassword,newpassword).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                changepasswoedmsg.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void clientChangeMobile(int clientid, String oldmob, String newmob){
        ApiClient.getInstance().changeClientMobile(clientid,oldmob,newmob).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                changemobilemsg.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void fullRegsisterClientOnline(Client client) {
        ApiClient.getInstance().clientFullRegsister(client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                fullladdmsgMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getClientById(int id){
        ApiClient.getInstance().getClientById(id).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                clientbyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void updateClientProfile(int id,Client client){
        ApiClient.getInstance().updateClientProfile(id,client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                updatemsgMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });
    }

    public void addDisabilityToClient(ClientDisability clientDisability){
        ApiClient.getInstance().addDisabilityToclient(clientDisability).enqueue(new Callback<ClientDisability>() {
            @Override
            public void onResponse(Call<ClientDisability> call, Response<ClientDisability> response) {
                clientDisabilityMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientDisability> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void deleteProfileClient(int id){
        ApiClient.getInstance().deleteClientProfile(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }


    public void PostChangeClientPassword(String mobile , String oldpassword , String newpassword){
        ApiClient.getInstance().PostchangeClientPassword(mobile,oldpassword,newpassword).enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                changepasswordMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void PostChangeClientMobile(int clientid , String oldmob , String newmob){
        ApiClient.getInstance().PostchangeClientMobile(clientid,oldmob,newmob).enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                changemobileMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void ClientForgotPassword(String mobile ,  String newpassword){
        ApiClient.getInstance().ClientForgotPassword(mobile,newpassword).enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                forgotpasswordMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }



}
