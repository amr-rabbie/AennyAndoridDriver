package design.swira.aennyapp.ui.aenny.regsister;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.data.room.aeeny.DisTypesIds;
import design.swira.aennyapp.pojo.aenny.areas.Areas;
import design.swira.aennyapp.pojo.aenny.cd.ClientDisabilitiesResponse;
import design.swira.aennyapp.pojo.aenny.cities.City;
import design.swira.aennyapp.pojo.aenny.client_disablity.ClientDisability;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.UpdateClientResponse;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.pojo.aenny.genders.Genders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegsisterViewModel extends AndroidViewModel {

    MutableLiveData<Client> addmsgMutableLiveData=new MutableLiveData<>();
    MutableLiveData<Client> updatemsgMutableLiveData=new MutableLiveData<>();
    MutableLiveData<Client> fullladdmsgMutableLiveData=new MutableLiveData<>();
    public  MutableLiveData<Client> clientbyidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<City>> citiesMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<Areas>> areasMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<Areas>> areasbycityMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<Genders>> gendersMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<ClientDisability>> disbyclientidMutableLiveData=new MutableLiveData<>();
    MutableLiveData<List<DisabilityTypes>> disabilitytypesMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ClientDisability> clientDisabilityMutableLiveData=new MutableLiveData<>();
    DisTypesRepositery disTypesRepositery;
    LiveData<List<DisTypesIds>> alldata;

    MutableLiveData<UpdateClientResponse> updateClientResponseMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<List<ClientDisabilitiesResponse>> getallcliendis=new MutableLiveData<>();

    public RegsisterViewModel(@NonNull Application application) {
        super(application);
        disTypesRepositery=new DisTypesRepositery(application);
        alldata=disTypesRepositery.getAllgithubs();
    }


   /* public void regsisterClientOnline(Client client) {
        ApiClient.getInstance().clientRegsister(client).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                addmsgMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }*/

    public void getCities() {
        ApiClient.getInstance().getAllCities().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                citiesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getAreas() {
        ApiClient.getInstance().getAllAreas().enqueue(new Callback<List<Areas>>() {
            @Override
            public void onResponse(Call<List<Areas>> call, Response<List<Areas>> response) {
                areasMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Areas>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getAreasByCityid(int cityid){
        ApiClient.getInstance().getAreasByCityid(cityid).enqueue(new Callback<List<Areas>>() {
            @Override
            public void onResponse(Call<List<Areas>> call, Response<List<Areas>> response) {
                areasbycityMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Areas>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getGenders() {
        ApiClient.getInstance().getAllGenders().enqueue(new Callback<List<Genders>>() {
            @Override
            public void onResponse(Call<List<Genders>> call, Response<List<Genders>> response) {
                gendersMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Genders>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getDisabilityTypes() {
        ApiClient.getInstance().getAllDisabilityTypes().enqueue(new Callback<List<DisabilityTypes>>() {
            @Override
            public void onResponse(Call<List<DisabilityTypes>> call, Response<List<DisabilityTypes>> response) {
                disabilitytypesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DisabilityTypes>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void regsisterClientOnline(String name,String mobile , String password) {
        ApiClient.getInstance().clientRegsister(name,mobile,password).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                addmsgMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void fullRegsisterClientOnline(Client client) {
        ApiClient.getInstance().clientFullRegsister(client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                fullladdmsgMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getClientById(int id){
        ApiClient.getInstance().getClientById(id).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                clientbyidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void updateClientProfile(int id,Client client){
        ApiClient.getInstance().updateClientProfile(id,client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                updatemsgMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });
    }

    public void addDisabilityToClient(ClientDisability clientDisability){
        ApiClient.getInstance().addDisabilityToclient(clientDisability).enqueue(new Callback<ClientDisability>() {
            @Override
            public void onResponse(Call<ClientDisability> call, Response<ClientDisability> response) {
                clientDisabilityMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientDisability> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void deleteProfileClient(int id){
        ApiClient.getInstance().deleteClientProfile(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void deleteDisabilityTypesByClientId(int id){
        ApiClient.getInstance().deleteDisabilityTypesByClient(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void getDisabilityTypesByClientId(int id){
        ApiClient.getInstance().getDisabilityTypesByClient(id).enqueue(new Callback<List<ClientDisability>>() {
            @Override
            public void onResponse(Call<List<ClientDisability>> call, Response<List<ClientDisability>> response) {
                disbyclientidMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ClientDisability>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<DisTypesIds>> getAlldata() {
        return alldata;
    }

    public void deleteall(){
        disTypesRepositery.deleteall();
    }

    public void insert(DisTypesIds gitHubs){
        disTypesRepositery.insert(gitHubs);
    }

    public void update(DisTypesIds gitHubs){
        disTypesRepositery.update(gitHubs);
    }

    public void delete(DisTypesIds gitHubs){
        disTypesRepositery.delete(gitHubs);
    }

    public void  UpdateClientData(int id , UpdateClientResponse updateClientResponse){
        ApiClient.getInstance().UpdateClientData(id,updateClientResponse).enqueue(new Callback<UpdateClientResponse>() {
            @Override
            public void onResponse(Call<UpdateClientResponse> call, Response<UpdateClientResponse> response) {
                updateClientResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UpdateClientResponse> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

    public void getAllClientDisabilities(int clientid){
        ApiClient.getInstance().getAllClientDisabilities(clientid).enqueue(new Callback<List<ClientDisabilitiesResponse>>() {
            @Override
            public void onResponse(Call<List<ClientDisabilitiesResponse>> call, Response<List<ClientDisabilitiesResponse>> response) {
                getallcliendis.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ClientDisabilitiesResponse>> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }

}
