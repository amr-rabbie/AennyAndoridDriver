package design.swira.aennyapp.ui.aenny.clientfavourites;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientFavouriteViewModel extends AndroidViewModel {

    MutableLiveData<ClientFavouriteResponse> clientFavouriteMutableLiveData=new MutableLiveData<>();
    public  MutableLiveData<List<ClientFavouriteResponse>> clientFavouritelistMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ClientFavouriteResponse> getclientFavouritebyidMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<ClientFavouriteResponse> deleteclientFavouritebyidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<String> updateclientFavouritebyidMutableLiveData=new MutableLiveData<>();

    public ClientFavouriteViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveClientFavourite(ClientFavouriteResponse clientFavouriteResponse){
        ApiClient.getInstance().saveClientFavourite(clientFavouriteResponse).enqueue(new Callback<ClientFavouriteResponse>() {
            @Override
            public void onResponse(Call<ClientFavouriteResponse> call, Response<ClientFavouriteResponse> response) {
                clientFavouriteMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientFavouriteResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getClientFavouriteById(int clientid){
        ApiClient.getInstance().getClientFavouritesById(clientid).enqueue(new Callback<List<ClientFavouriteResponse>>() {
            @Override
            public void onResponse(Call<List<ClientFavouriteResponse>> call, Response<List<ClientFavouriteResponse>> response) {
                clientFavouritelistMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ClientFavouriteResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getClientFavourite(int id){
        ApiClient.getInstance().getClientFavouriteById(id).enqueue(new Callback<ClientFavouriteResponse>() {
            @Override
            public void onResponse(Call<ClientFavouriteResponse> call, Response<ClientFavouriteResponse> response) {
                getclientFavouritebyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientFavouriteResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void deleteClientFavouritebyid(int id){
        ApiClient.getInstance().deleteClientFavouriteById(id).enqueue(new Callback<ClientFavouriteResponse>() {
            @Override
            public void onResponse(Call<ClientFavouriteResponse> call, Response<ClientFavouriteResponse> response) {
                deleteclientFavouritebyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientFavouriteResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void updateClientFavouriteById(int id ,ClientFavouriteResponse clientFavouriteResponse){
        ApiClient.getInstance().updateClientFavouriteById(id,clientFavouriteResponse).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                updateclientFavouritebyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }
}
