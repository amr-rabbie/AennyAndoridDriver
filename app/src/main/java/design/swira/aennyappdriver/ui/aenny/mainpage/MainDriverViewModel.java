package design.swira.aennyappdriver.ui.aenny.mainpage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.pojo.aennydriver.driver.DriverDataResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverisonline.IsOnlineResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverstatics.DriverStaticsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDriverViewModel extends AndroidViewModel {

    MutableLiveData<DriverDataResponse> driverDataResponseMutableLiveData=new MutableLiveData<>();
    MutableLiveData<DriverStaticsResponse> driverStaticsResponseMutableLiveData=new MutableLiveData<>();
    MutableLiveData<IsOnlineResponse> isOnlineResponseMutableLiveData=new MutableLiveData<>();

    public MainDriverViewModel(@NonNull Application application) {
        super(application);
    }

    public void getDriverData(int id){
        ApiClient.getInstance().getDriverData(id).enqueue(new Callback<DriverDataResponse>() {
            @Override
            public void onResponse(Call<DriverDataResponse> call, Response<DriverDataResponse> response) {
                driverDataResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DriverDataResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getDriverStatics(int driverId ,int DurationType){
        ApiClient.getInstance().getDriverStatics(driverId,DurationType).enqueue(new Callback<DriverStaticsResponse>() {
            @Override
            public void onResponse(Call<DriverStaticsResponse> call, Response<DriverStaticsResponse> response) {
                driverStaticsResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DriverStaticsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void updatedriverstatus(int DriverId ,Boolean IsOnline){
        ApiClient.getInstance().updatedriverstatus(DriverId,IsOnline).enqueue(new Callback<IsOnlineResponse>() {
            @Override
            public void onResponse(Call<IsOnlineResponse> call, Response<IsOnlineResponse> response) {
                isOnlineResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<IsOnlineResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }
}
