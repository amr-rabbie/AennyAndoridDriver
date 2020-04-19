package design.swira.aennyapp.ui.test;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.data.room.Data;
import design.swira.aennyapp.pojo.test.Country;
import design.swira.aennyapp.pojo.test.Response;
import design.swira.aennyapp.pojo.test.RowsItem;
import retrofit2.Call;
import retrofit2.Callback;

public class DataViewModel extends AndroidViewModel {

    DataRepositery  dataRepositery;
    MutableLiveData<List<RowsItem>> alldataMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<Country>> calldataMutableLiveData=new MutableLiveData<>();
    public LiveData<List<Data>> alldata;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepositery=new DataRepositery(application);
        alldata=dataRepositery.getAlldata();
    }


    /*public void getAllData(String country){
        alldataMutableLiveData.setValue(dataRepositery.getAllDataOnline(country));
    }*/

    public void getcAllData(){
        calldataMutableLiveData.setValue(dataRepositery.getcAllDataDb());
    }

    public void getAllDataOnline(String country){
        ApiClient.getInstance().getAllData(country).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response data = response.body();
                alldataMutableLiveData.setValue(data.getRows());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });

    }

    public LiveData<List<Data>> getAlldata() {
        return alldata;
    }

    public void insertData(Data data){
        dataRepositery.insertData(data);
    }

    public void updateData(Data data){
        dataRepositery.updateData(data);
    }

    public void deleteData(Data data){
        dataRepositery.deleteData(data);
    }

    public void deleteAllData(){
        dataRepositery.deleteAllData();
    }
}
