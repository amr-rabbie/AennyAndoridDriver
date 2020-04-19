package design.swira.aennyappdriver.data.api;


import android.text.GetChars;

import java.util.List;
import java.util.concurrent.TimeUnit;


import design.swira.aennyappdriver.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyappdriver.pojo.aenny.notifications.NotificationsResponse;
import design.swira.aennyappdriver.pojo.aennydriver.GetVechealByDriverResponse;
import design.swira.aennyappdriver.pojo.aennydriver.chat.ChatResponse;
import design.swira.aennyappdriver.pojo.aennydriver.compliants.CompliantResponse;
import design.swira.aennyappdriver.pojo.aennydriver.comptrip.CompletedTripResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driver.DriverDataResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driver.DriverResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverisonline.IsOnlineResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.NextScduleResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.ScduleResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverstatics.DriverStaticsResponse;
import design.swira.aennyappdriver.pojo.aennydriver.logout.LogoutResponse;
import design.swira.aennyappdriver.pojo.aennydriver.tripsrates.TripRatesResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {



    private static final String ApiBaseUrl="http://localhost:49950/api/";
    private static final String Apiurl="http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/api/";

    private static final String BaseUrl="https://www.mubasher.info/api/1/";
    private static final String BaseUrl2="https://samples.openweathermap.org/data/2.5/";
    private static final String BaseUrl3="http://gateway.marvel.com/v1/public/";

    private static final String GoogleMapsApi="https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyAyKWcogS2vgE52G5ZBj9IXtgqQ7n3cP5A";

    private ApiInterface apiInterface;
    private static ApiClient instance;

    //public static final String signalRBaseUrl="http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/";
    public static final String signalRBaseUrl="http://doaaberam2020-001-site1.htempurl.com/TripNotification";


    public ApiClient() {

        /*OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5,TimeUnit.MINUTES)
                .build();*/


        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();



        Retrofit  retrofit=new Retrofit.Builder()
                .baseUrl(Apiurl)
                //.baseUrl(ApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

    }



    public static ApiClient getInstance(){
        if(null == instance){
            instance=new ApiClient();
        }
        return instance;
    }


    public Call<List<NotificationsResponse>> getAllNoficationsByClientId(int userTypeId , int senderId){
        return  apiInterface.getAllNoficationsByClientId(userTypeId,senderId);
    }

    public Call<DriverResponse> PostclientLogin(String mobile, String password){
        return apiInterface.PostclientLogin(mobile,password);
    }

    public Call<DriverResponse> PostchangeClientPassword(String mobile , String oldpassword , String newpassword){
        return apiInterface.PostchangeClientPassword(mobile,oldpassword,newpassword);
    }

    public Call<DriverDataResponse> getDriverData(int id){
        return apiInterface.getDriverData(id);
    }

    public Call<List<ScduleResponse>> getScduleTripsListForDriver(int driverId){
        return apiInterface.getScduleTripsListForDriver(driverId);
    }

    public Call<NextScduleResponse> getNextScduleTripsForDriver(int driverId){
        return apiInterface.getNextScduleTripsForDriver(driverId);
    }

    public Call<IsOnlineResponse> updatedriverstatus(int DriverId ,Boolean IsOnline){
        return  apiInterface.updatedriverstatus(DriverId,IsOnline);
    }

    public Call<DriverStaticsResponse> getDriverStatics(int driverId ,int DurationType){
        return apiInterface.getDriverStatics(driverId,DurationType);
    }

    public Call<LogoutResponse> driverLogout(int driverid){
        return apiInterface.driverLogout(driverid);
    }

    public Call<List<CompletedTripResponse>> getAllCompletedTripsForDriver(int driverid){
        return apiInterface.getAllCompletedTripsForDriver(driverid);
    }

    public Call<CompliantResponse> makeDriverCompliant(CompliantResponse compliantResponse){
        return apiInterface.makeDriverCompliant(compliantResponse);
    }

    public Call<TripRatesResponse> DriverRateClient(TripRatesResponse tripRatesResponse){
        return apiInterface.DriverRateClient(tripRatesResponse);
    }


    public Call<ClientDataResponse> getClientData(int id){
        return apiInterface.getClientData(id);
    }




    public Call<List<ChatResponse>> getChat(int tripid, int pagenum, int pagesize){
        return apiInterface.getChat(tripid,pagenum,pagesize);
    }

    public Call<GetVechealByDriverResponse> GetVechealByDriver(int driverid){
        return apiInterface.GetVechealByDriver(driverid);
    }





}
