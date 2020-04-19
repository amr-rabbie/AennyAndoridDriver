package design.swira.aennyapp.ui.test3;

import android.app.Application;
import android.util.Log;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.test2.WResponse;
import design.swira.aennyapp.pojo.test3.MResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MDataRespositery {

    MResponse mResponse;

    public MDataRespositery(Application application) {
    }

    public MResponse getMAllDataOnline(String ts , String apikey , String hash){
        ApiClient.getInstance().getMAllData(ts,apikey,hash).enqueue(new Callback<MResponse>() {
            @Override
            public void onResponse(Call<MResponse> call, Response<MResponse> response) {
                mResponse = response.body();
            }

            @Override
            public void onFailure(Call<MResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
        return mResponse;
    }
}
