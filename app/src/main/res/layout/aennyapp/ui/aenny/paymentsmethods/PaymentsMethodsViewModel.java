package design.swira.aennyapp.ui.aenny.paymentsmethods;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.paymentsmethods.PaymentsMethodsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentsMethodsViewModel extends AndroidViewModel {

    MutableLiveData<List<PaymentsMethodsResponse>> paymentsmethodsMutableLiveData=new MutableLiveData<>();
    MutableLiveData<ClientsPaymentsMethodsResponse> clientpaymentsmethodsMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<List<ClientsPaymentsMethodsResponse>> clientpaymentsmethodsMutableLiveData2=new MutableLiveData<>();

    MutableLiveData<ClientsPaymentsMethodsResponse> getclientpaymentsmethodsMutableLiveData=new MutableLiveData<>();
    public  MutableLiveData<ClientsPaymentsMethodsResponse> deleteclientpaymentsmethodsMutableLiveData=new MutableLiveData<>();
    MutableLiveData<String> updateclientpaymentsmethodsMutableLiveData=new MutableLiveData<>();


    public PaymentsMethodsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllPaymentsMethods(){
        ApiClient.getInstance().getAllPaymentsMethods().enqueue(new Callback<List<PaymentsMethodsResponse>>() {
            @Override
            public void onResponse(Call<List<PaymentsMethodsResponse>> call, Response<List<PaymentsMethodsResponse>> response) {
                paymentsmethodsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PaymentsMethodsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void saveClientPaymentMethod(ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse){
        ApiClient.getInstance().saveClientPaymentMethod(clientsPaymentsMethodsResponse).enqueue(new Callback<ClientsPaymentsMethodsResponse>() {
            @Override
            public void onResponse(Call<ClientsPaymentsMethodsResponse> call, Response<ClientsPaymentsMethodsResponse> response) {
                clientpaymentsmethodsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientsPaymentsMethodsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void getPaymentsMethodsByClientId(int clientid){
        ApiClient.getInstance().getAllPaymentsMethodsByClientId(clientid).enqueue(new Callback<List<ClientsPaymentsMethodsResponse>>() {
            @Override
            public void onResponse(Call<List<ClientsPaymentsMethodsResponse>> call, Response<List<ClientsPaymentsMethodsResponse>> response) {
                clientpaymentsmethodsMutableLiveData2.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ClientsPaymentsMethodsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }


    public void getClientPaymentById(int id){
        ApiClient.getInstance().getClientPaymentById(id).enqueue(new Callback<ClientsPaymentsMethodsResponse>() {
            @Override
            public void onResponse(Call<ClientsPaymentsMethodsResponse> call, Response<ClientsPaymentsMethodsResponse> response) {
                getclientpaymentsmethodsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientsPaymentsMethodsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void deleteClientPaymentbyid(int id){
        ApiClient.getInstance().deleteClientPaymentById(id).enqueue(new Callback<ClientsPaymentsMethodsResponse>() {
            @Override
            public void onResponse(Call<ClientsPaymentsMethodsResponse> call, Response<ClientsPaymentsMethodsResponse> response) {
                deleteclientpaymentsmethodsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ClientsPaymentsMethodsResponse> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }

    public void updateClientPaymentById(int id ,ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse){
        ApiClient.getInstance().updateClientPaymentById(id,clientsPaymentsMethodsResponse).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //updateclientpaymentsmethodsMutableLiveData.setValue(response.body());
                /*if(response.isSuccessful()){
                    updateclientpaymentsmethodsMutableLiveData.setValue("sucess");
                }*/
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }


}
