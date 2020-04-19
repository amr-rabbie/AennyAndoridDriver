package design.swira.aennyapp.ui.test;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.data.room.Data;
import design.swira.aennyapp.data.room.DataDao;
import design.swira.aennyapp.data.room.DataDataBase;
import design.swira.aennyapp.pojo.test.Country;
import design.swira.aennyapp.pojo.test.Response;
import design.swira.aennyapp.pojo.test.RowsItem;
import retrofit2.Call;
import retrofit2.Callback;

public class DataRepositery {

    List<RowsItem> datalist;
    List<Country> cdatalist;
    DataDao dataDao;
    LiveData<List<Data>> alldata;

    private String[] countryArray = {"Egypt Stock", "Uae Stock", "Ksa Stock", "Qatar Stock"};
    private int[] flagsArray = {R.drawable.egyptf, R.drawable.uaef, R.drawable.ksa, R.drawable.qatar};


    public DataRepositery(Application application) {
        DataDataBase dataDataBase=DataDataBase.getInstance(application);
        dataDao=dataDataBase.dataDao();
        alldata=dataDao.getAllData();
    }

    public List<Country> getcAllDataDb(){
        cdatalist=new ArrayList<>();
        for(int i=0;i<countryArray.length;i++){
            cdatalist.add(new Country(flagsArray[i],countryArray[i]));
        }
        return cdatalist;
    }


    public List<RowsItem> getAllDataOnline(String country){
        ApiClient.getInstance().getAllData(country).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response data = response.body();
                datalist = data.getRows();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
        return datalist;
    }

    public void insertData(Data data){
        new insertDataAsy(dataDao).execute(data);
    }

    public static class insertDataAsy extends AsyncTask<Data,Void,Void>{

        private DataDao dataDao;

        public insertDataAsy(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.insertData(data[0]);
            return null;
        }
    }

    public void updateData(Data data){
        new updateDataAsy(dataDao).execute(data);
    }

    public static class updateDataAsy extends AsyncTask<Data,Void,Void>{

        private DataDao dataDao;

        public updateDataAsy(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.updateData(data[0]);
            return null;
        }
    }

    public void deleteData(Data data){
        new deleteDataAsy(dataDao).execute(data);
    }

    public static class deleteDataAsy extends AsyncTask<Data,Void,Void>{

        private DataDao dataDao;

        public deleteDataAsy(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Data... data) {
            dataDao.deleteData(data[0]);
            return null;
        }
    }

    public void deleteAllData(){
        new deleteAllDataAsy(dataDao).execute();
    }

    public static class deleteAllDataAsy extends AsyncTask<Void,Void,Void>{

        private DataDao dataDao;

        public deleteAllDataAsy(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataDao.deleteAllData();
            return null;
        }
    }


    public LiveData<List<Data>> getAlldata() {
        return alldata;
    }
}
