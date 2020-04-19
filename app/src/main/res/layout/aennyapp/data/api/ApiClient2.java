package design.swira.aennyapp.data.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import design.swira.aennyapp.pojo.aenny.notifications.NotificationsResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {


    private static final String ApiBaseUrl="http://localhost:49950/api/";
    private static final String Apiurl="http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/";

    private static final String BaseUrl="https://www.mubasher.info/api/1/";
    private static final String BaseUrl2="https://samples.openweathermap.org/data/2.5/";
    private static final String BaseUrl3="http://gateway.marvel.com/v1/public/";

    private static final String GoogleMapsApi="https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyAyKWcogS2vgE52G5ZBj9IXtgqQ7n3cP5A";

    private ApiInterface apiInterface;
    private static ApiClient2 instance;


    public ApiClient2() {

        /*OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5,TimeUnit.MINUTES)
                .build();*/


        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();



        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apiurl)
                //.baseUrl(ApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

    }



    public static ApiClient2 getInstance(){
        if(null == instance){
            instance=new ApiClient2();
        }
        return instance;
    }

    public Call<List<NotificationsResponse>> getAllNoficationsByClientId(int userTypeId , int senderId){
        return  apiInterface.getAllNoficationsByClientId(userTypeId,senderId);
    }

}
