package design.swira.aennyappdriver.ui.aenny.drivertrips;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.data.api.MapApiClient;
import design.swira.aennyappdriver.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyappdriver.pojo.aennydriver.GetVechealByDriverResponse;
import design.swira.aennyappdriver.pojo.aennydriver.compliants.CompliantResponse;
import design.swira.aennyappdriver.pojo.aennydriver.comptrip.CompletedTripResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.NextScduleResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.ScduleResponse;
import design.swira.aennyappdriver.pojo.aennydriver.tripsrates.TripRatesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsViewModel extends AndroidViewModel {

    public MutableLiveData<List<ScduleResponse>> ScduleResponselistMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<NextScduleResponse> ScduleResponselistMutableLiveData2=new MutableLiveData<>();
    public MutableLiveData<List<CompletedTripResponse>> completedtripslistMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<CompliantResponse> compliantResponseMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<TripRatesResponse> tripRatesResponseMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<GoogleMapsResponse> googleMapsResponseMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<GetVechealByDriverResponse> vechealByDriverResponseMutableLiveData=new MutableLiveData<>();


    public TripsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getScduleTripsListForDriver(int driverId){
        ApiClient.getInstance().getScduleTripsListForDriver(driverId).enqueue(new Callback<List<ScduleResponse>>() {
            @Override
            public void onResponse(Call<List<ScduleResponse>> call, Response<List<ScduleResponse>> response) {
                ScduleResponselistMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ScduleResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getNextScduleTripsForDriver(int driverId){
        ApiClient.getInstance().getNextScduleTripsForDriver(driverId).enqueue(new Callback<NextScduleResponse>() {
            @Override
            public void onResponse(Call<NextScduleResponse> call, Response<NextScduleResponse> response) {
                ScduleResponselistMutableLiveData2.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NextScduleResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getAllCompletedTripsForDriver(int driverid){
        ApiClient.getInstance().getAllCompletedTripsForDriver(driverid).enqueue(new Callback<List<CompletedTripResponse>>() {
            @Override
            public void onResponse(Call<List<CompletedTripResponse>> call, Response<List<CompletedTripResponse>> response) {
                completedtripslistMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CompletedTripResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }



    public void makeDriverCompliant(CompliantResponse compliantResponse){
        ApiClient.getInstance().makeDriverCompliant(compliantResponse).enqueue(new Callback<CompliantResponse>() {
            @Override
            public void onResponse(Call<CompliantResponse> call, Response<CompliantResponse> response) {
                compliantResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CompliantResponse> call, Throwable t) {
                Log.e("err1",t.getMessage().toString());
            }
        });
    }

    public void DriverRateClient(TripRatesResponse tripRatesResponse){
        ApiClient.getInstance().DriverRateClient(tripRatesResponse).enqueue(new Callback<TripRatesResponse>() {
            @Override
            public void onResponse(Call<TripRatesResponse> call, Response<TripRatesResponse> response) {
                tripRatesResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TripRatesResponse> call, Throwable t) {
                Log.e("err1",t.getMessage().toString());
            }
        });
    }

   public MutableLiveData<ClientDataResponse> clientMutableLiveData=new MutableLiveData<>();




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


    public void GetDistanceAndTime(String origin ,String destination , String key){
        MapApiClient.getInstance().getDistanceAndTime(origin,destination,key).enqueue(new Callback<GoogleMapsResponse>() {
            @Override
            public void onResponse(Call<GoogleMapsResponse> call, Response<GoogleMapsResponse> response) {
                googleMapsResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GoogleMapsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void GetVechealByDriver(int driverid){
        ApiClient.getInstance().GetVechealByDriver(driverid).enqueue(new Callback<GetVechealByDriverResponse>() {
            @Override
            public void onResponse(Call<GetVechealByDriverResponse> call, Response<GetVechealByDriverResponse> response) {
                vechealByDriverResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GetVechealByDriverResponse> call, Throwable t) {

            }
        });
    }





}
