package design.swira.aennyapp.ui.aenny.notifications;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import design.swira.aennyapp.data.api.ApiClient2;
import design.swira.aennyapp.pojo.aenny.notifications.NotificationsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsViewModel extends AndroidViewModel {

    public  MutableLiveData<List<NotificationsResponse>> notifylistMutableLiveData=new MutableLiveData<>();

    public NotificationsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllNoficationsByClientId(int userTypeId , int senderId){
        ApiClient2.getInstance().getAllNoficationsByClientId(userTypeId,senderId).enqueue(new Callback<List<NotificationsResponse>>() {
            @Override
            public void onResponse(Call<List<NotificationsResponse>> call, Response<List<NotificationsResponse>> response) {
                notifylistMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NotificationsResponse>> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
    }
}
