package design.swira.aennyappdriver.ui.aenny.chat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.pojo.aennydriver.chat.ChatResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends AndroidViewModel {

    public MutableLiveData<List<ChatResponse>> chatResponseMutableLiveData=new MutableLiveData<>();

    public ChatViewModel(@NonNull Application application) {
        super(application);
    }

    public void getChat( int tripid,int pagenum,int pagesize){

        ApiClient.getInstance().getChat(tripid,pagenum,pagesize).enqueue(new Callback<List<ChatResponse>>() {
            @Override
            public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                chatResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ChatResponse>> call, Throwable t) {
                Log.e("err",t.getMessage().toString());
            }
        });
    }
}
