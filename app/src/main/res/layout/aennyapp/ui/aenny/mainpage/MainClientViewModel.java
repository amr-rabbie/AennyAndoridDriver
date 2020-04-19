package design.swira.aennyapp.ui.aenny.mainpage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.clients.ClientDataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClientViewModel extends AndroidViewModel {

    MutableLiveData<ClientDataResponse> clientMutableLiveData=new MutableLiveData<>();


    public MainClientViewModel(@NonNull Application application) {
        super(application);
    }

    public void getClientData(int id){
        ApiClient.getInstance().getClientData(id).enqueue(new Callback<ClientDataResponse>() {
            @Override
            public void onResponse(Call<ClientDataResponse> call, Response<ClientDataResponse> response) {
                clientMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientDataResponse> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }
}
