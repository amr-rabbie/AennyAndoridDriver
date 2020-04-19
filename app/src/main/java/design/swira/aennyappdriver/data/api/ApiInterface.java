package design.swira.aennyappdriver.data.api;

import java.util.List;

import design.swira.aennyappdriver.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyappdriver.pojo.aenny.googlemaps.GoogleMapsResponse;
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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("json")
    public Call<GoogleMapsResponse> getDistanceAndTime(
            @Query("origin") String origin,
            @Query("destination") String destination ,
            @Query("key") String key
    );

    @GET("GetNotificationBySenderId/{userTypeId}/{senderId}")
    public Call<List<NotificationsResponse>> getAllNoficationsByClientId(
            @Path("userTypeId") int userTypeId ,
            @Path("senderId") int senderId
    );

    @FormUrlEncoded
    @POST("DriverLogin/Login")
    public Call<DriverResponse> PostclientLogin(
            @Field("mobile") String mobile,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("DriverChangePassword")
    //@FormUrlEncoded
    public Call<DriverResponse> PostchangeClientPassword(
            @Field("mobile") String mobile ,
            @Field("OldPassword") String OldPassword ,
            @Field("NewPassword") String NewPassword
    );

    @GET("Drivers/{id}")
    public Call<DriverDataResponse> getDriverData(
            @Path("id") int id
    );

    @GET("Trips/ScheduledTrips/Driver/{driverId}")
    public Call<List<ScduleResponse>> getScduleTripsListForDriver(
            @Path("driverId") int driverId
    );

    @GET("Trips/NextScheduledTrip/Driver/{driverId}")
    public Call<NextScduleResponse> getNextScduleTripsForDriver(
            @Path("driverId") int driverId
    );

    //@FormUrlEncoded
    @PUT("Drivers/EditDriverIsOnline/{DriverId}/{IsOnline}")
    public Call<IsOnlineResponse> updatedriverstatus(
            @Path("DriverId") int DriverId ,
            @Path("IsOnline") Boolean IsOnline
    );


    @GET("Drivers/DriverSummeryTrips/{driverId}/{DurationType}")
    public Call<DriverStaticsResponse> getDriverStatics(
            @Path("driverId") int driverId ,
            @Path("DurationType") int DurationType
    );

    @POST("DriverLogin/{driverId}/Logout")
    public Call<LogoutResponse> driverLogout(
            @Path("driverId") int driverId
    );

    @GET("Trips/GetAllCompletedDriverTrips/{DriverId}")
    public Call<List<CompletedTripResponse>> getAllCompletedTripsForDriver(
            @Path("DriverId") int driverId
    );


    @POST("Complaints")
    public Call<CompliantResponse> makeDriverCompliant(
            @Body CompliantResponse compliantResponse
    );

    @POST("Trips_Rates")
    public Call<TripRatesResponse> DriverRateClient(
            @Body TripRatesResponse tripRatesResponse
    );


    @GET("Clients")
    public Call<ClientDataResponse> getClientData(
            @Query("id")  int id
    );


    @GET("Trip_Chat/GetTripChatByTripId")
    public Call<List<ChatResponse>> getChat(
            @Query("TripId") int TripId,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );


    @GET("Drivers/GetDriverVehicleByDriverId/{Driver_Id}")
    public Call<GetVechealByDriverResponse> GetVechealByDriver(
          @Path("Driver_Id")   int Driver_Id
    );








}
