package design.swira.aennyapp.ui.aenny.cities;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.cities.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitiesViewModel extends AndroidViewModel {

    MutableLiveData<List<City>> citylistMutableLiveData=new MutableLiveData<>();

    public CitiesViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllCitiesOnline(){
        ApiClient.getInstance().getAllCities().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                citylistMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }


}
