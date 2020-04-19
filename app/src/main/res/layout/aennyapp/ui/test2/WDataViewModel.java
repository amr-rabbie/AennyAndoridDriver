package design.swira.aennyapp.ui.test2;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.test2.WResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WDataViewModel extends AndroidViewModel {

    WDataRepositery wDataRepositery;
    public  MutableLiveData<WResponse> wdataMutableLiveData=new MutableLiveData<>();

    public WDataViewModel(@NonNull Application application) {
        super(application);
        wDataRepositery=new WDataRepositery(application);
    }

    public void wgetData(String q,String appid){
        wdataMutableLiveData.setValue(wDataRepositery.getWAllDataOnline(q,appid));
    }

    public void getWAllDataOnline(String q,String appid){
        ApiClient.getInstance().getWAllData(q,appid).enqueue(new Callback<WResponse>() {
            @Override
            public void onResponse(Call<WResponse> call, Response<WResponse> response) {
                wdataMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });

    }


}
