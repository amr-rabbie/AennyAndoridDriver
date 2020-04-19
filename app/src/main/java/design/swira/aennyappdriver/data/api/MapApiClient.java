package design.swira.aennyappdriver.data.api;

import java.util.concurrent.TimeUnit;

import design.swira.aennyappdriver.pojo.aenny.googlemaps.GoogleMapsResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapApiClient {

    //private static final String GoogleMapsApi="https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyAyKWcogS2vgE52G5ZBj9IXtgqQ7n3cP5A";
    private static final String GoogleMapsApi="https://maps.googleapis.com/maps/api/directions/";
    public static String GooglemapKey="AIzaSyAyKWcogS2vgE52G5ZBj9IXtgqQ7n3cP5A";
    private ApiInterface apiInterface;
    private static MapApiClient instance;


    public MapApiClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GoogleMapsApi)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

    }



    public static MapApiClient getInstance(){
        if(null == instance){
            instance=new MapApiClient();
        }
        return instance;
    }

    public Call<GoogleMapsResponse> getDistanceAndTime(String origin ,String destination , String key){
        return apiInterface.getDistanceAndTime(origin,destination,key);
    }

}
