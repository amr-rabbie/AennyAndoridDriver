package design.swira.aennyapp.ui.aenny.longtrip;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.longtrip.LongTrip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LongTripViewModel extends AndroidViewModel {

    MutableLiveData<LongTrip> longTripMutableLiveData=new MutableLiveData<>();

    public LongTripViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveLongTrip(final LongTrip longTrip){
        ApiClient.getInstance().saveLongTrip(longTrip).enqueue(new Callback<LongTrip>() {
            @Override
            public void onResponse(Call<LongTrip> call, Response<LongTrip> response) {
                longTripMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<LongTrip> call, Throwable t) {

            }
        });
    }
}
