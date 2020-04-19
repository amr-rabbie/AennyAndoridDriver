package design.swira.aennyapp.ui.test3;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.test3.MResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MDataViewModel extends AndroidViewModel {

    MDataRespositery mDataRespositery;
    MutableLiveData<MResponse> mdataMutableLiveData=new MutableLiveData<>();


    public MDataViewModel(@NonNull Application application) {
        super(application);
        mDataRespositery=new MDataRespositery(application);
    }

    public void getMAllData(String ts , String apikey , String hash){
        mdataMutableLiveData.setValue(mDataRespositery.getMAllDataOnline(ts,apikey,hash));
    }


    public void getMAllDataOnline(String ts , String apikey , String hash){
        ApiClient.getInstance().getMAllData(ts,apikey,hash).enqueue(new Callback<MResponse>() {
            @Override
            public void onResponse(Call<MResponse> call, Response<MResponse> response) {
                mdataMutableLiveData.setValue(response.body());
                Log.i("response",response.body().toString());
            }

            @Override
            public void onFailure(Call<MResponse> call, Throwable t) {
                Log.e("response_error",t.getMessage().toString());
            }
        });

    }


}
