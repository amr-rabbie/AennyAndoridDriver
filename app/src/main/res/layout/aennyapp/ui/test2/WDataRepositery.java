package design.swira.aennyapp.ui.test2;

import android.app.Application;
import android.util.Log;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.test2.WResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WDataRepositery {

    WResponse wResponse;

    public WDataRepositery(Application application) {

    }

    public WResponse getWAllDataOnline(String q,String appid){
        ApiClient.getInstance().getWAllData(q,appid).enqueue(new Callback<WResponse>() {
            @Override
            public void onResponse(Call<WResponse> call, Response<WResponse> response) {
                wResponse  = response.body();
            }

            @Override
            public void onFailure(Call<WResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });

        return  wResponse;
    }


}
