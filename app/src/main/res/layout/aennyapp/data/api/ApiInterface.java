package design.swira.aennyapp.data.api;

import java.util.List;

import design.swira.aennyapp.pojo.aenny.areas.Areas;
import design.swira.aennyapp.pojo.aenny.cd.ClientDisabilitiesResponse;
import design.swira.aennyapp.pojo.aenny.cities.City;
import design.swira.aennyapp.pojo.aenny.client_disablity.ClientDisability;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyapp.pojo.aenny.clients.UpdateClientResponse;
import design.swira.aennyapp.pojo.aenny.clients.newclient.ClientResponse;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.pojo.aenny.genders.Genders;
import design.swira.aennyapp.pojo.aenny.googlemaps.GoogleMapsResponse;
import design.swira.aennyapp.pojo.aenny.longtrip.LongTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.AddScduleTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.ScduleTripsListResponse;
import design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleResponse;
import design.swira.aennyapp.pojo.aenny.notifications.NotificationsResponse;
import design.swira.aennyapp.pojo.aenny.paymentsmethods.PaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.scduledtrips.ScduledTripsResponse;
import design.swira.aennyapp.pojo.aenny.schduletrips.UpdateScduleTrip;
import design.swira.aennyapp.pojo.aenny.trips.TripsResponse;
import design.swira.aennyapp.pojo.test.Response;
import design.swira.aennyapp.pojo.test2.WResponse;
import design.swira.aennyapp.pojo.test3.MResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("listed-companies")
    public Call<Response> getAllData(
            @Query("country") String country
    );

    @GET("weather")
    public Call<WResponse> getWAllData(
          @Query("q") String q ,
          @Query("appid") String appid
    );

    @GET("characters")
    public Call<MResponse> getMAllData(
         @Query("ts") String ts ,
         @Query("apikey") String apikey ,
         @Query("hash") String hash
    );


    @GET("Cities")
    public Call<List<City>> getAllCities();

    @GET("Areas")
    public Call<List<Areas>> getAllAreas();

    @GET("AreasByCity")
    public Call<List<Areas>> getAllAreasByCityid(
            @Query("cityid") int cityid
    );

    @GET("Genders")
    public Call<List<Genders>> getAllGenders();

    @GET("Disability_Types")
    public Call<List<DisabilityTypes>> getAllDisabilityTypes();

    @GET("ClientLogin/Login")
    public Call<Client> clientLogin(
           @Query("mobile") String mobile,
           @Query("password") String password
    );

    @FormUrlEncoded
    @POST("ClientLogin/Login")
    public Call<ClientResponse> PostclientLogin(
            @Field("mobile") String mobile,
            @Field("password") String password
    );

    /*@POST("ClientRegsister/Regsister")
    public Call<String> clientRegsister(
            @Body Client client
    );*/

    @POST("ClientRegsister/Regsister")
    //@FormUrlEncoded
    public Call<Client> clientRegsister(
            @Query("name") String name ,
            @Query("mobile") String mobile ,
            @Query("password") String password
    );

    @POST("ClientChangeMobile/ChangeMobile")
    //@FormUrlEncoded
    public Call<String> changeClientMobile(
            @Query("clientid") int clientid ,
            @Query("oldmob") String oldmob ,
            @Query("newmob") String newmob
    );



    @FormUrlEncoded
    @POST("ClientChangeMobile")
    //@FormUrlEncoded
    public Call<ClientResponse> PostchangeClientMobile(
            @Field("Id") int Id ,
            @Field("OldMobile") String OldMobile ,
            @Field("NewMobile") String NewMobile
    );

    @POST("ClientChangePassword/ChangePassword")
    //@FormUrlEncoded
    public Call<String> changeClientPassword(
            @Query("clientid") int clientid ,
            @Query("oldpassword") String oldpassword ,
            @Query("newpassword") String newpassword
    );

    @FormUrlEncoded
    @POST("ClientChangePassword")
    //@FormUrlEncoded
    public Call<ClientResponse> PostchangeClientPassword(
            @Field("mobile") String mobile ,
            @Field("OldPassword") String OldPassword ,
            @Field("NewPassword") String NewPassword
    );

    @FormUrlEncoded
    @POST("ClientForgotPassword")
    //@FormUrlEncoded
    public Call<ClientResponse> ClientForgotPassword(
            @Field("mobile") String mobile ,
            @Field("NewPassword") String NewPassword
    );

    @POST("Clients")
    public Call<Client> clientFullRegsister(
            @Body Client client
    );

    //@FormUrlEncoded
    @DELETE("Clients")
    public Call<String> deleteClientProfile(
            @Query("id") int id
    );

    @PUT("Clients")
    public Call<Client> updateClientProfile(
            @Query("id") int id ,
            @Body Client client
    );

    @GET("Clients")
    public Call<Client> getClientById(
            @Query("id") int id
    );

    @POST("Client_Disabilties")
    public Call<ClientDisability> addDisabilityToclient(
            @Body ClientDisability clientDisability
    );

    @DELETE("DisabilityTypeByClientId")
    public Call<String> deleteDisabilityTypesByClients(
            @Query("id") int id
    );

    @GET("DisabilityTypeByClientId")
    public Call<List<ClientDisability>> getDisabilityTypesByClients(
            @Query("id") int id
    );

    @POST("Long_Trips")
    public Call<LongTrip> saveLongTrip(
            @Body LongTrip longTrip
    );

    @GET("json")
    public Call<GoogleMapsResponse> getDistanceAndTime(
            @Query("origin") String origin,
            @Query("destination") String destination ,
            @Query("key") String key
    );

    @GET("Payment_Methods")
    public Call<List<PaymentsMethodsResponse>> getAllPaymentsMethods(

    );

    @POST("Client_Payments_Methods")
    public Call<ClientsPaymentsMethodsResponse> saveClientPaymentsMethod(
            @Body  ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse
    );

    @GET("PaymentsMethodByClientId")
    public Call<List<ClientsPaymentsMethodsResponse>> getPaymentsMethodsByClientId(
            @Query("clientid") int clientid
    );

    @POST("Clients_Favourites")
    public Call<ClientFavouriteResponse> saveClientFavourite(
            @Body ClientFavouriteResponse clientFavouriteResponse
    );


    @GET("Clients_Favourites/ClientsFavouritesByClient")
    public Call<List<ClientFavouriteResponse>> getClientFavouritesById(
          @Query("clientid") int clientid
    );

    @GET("PaymentsMethodByClientId/AllPayments")
    public Call<List<ClientsPaymentsMethodsResponse>> getAllPaymentsMethodsByClientId(
          @Query("clientid") int clientid
    );



    @GET("Client_Payments_Methods")
    public Call<ClientsPaymentsMethodsResponse> getClientPaymentById(
            @Query("id") int id
    );

    @DELETE("Client_Payments_Methods")
    public Call<ClientsPaymentsMethodsResponse> deleteClientPaymentById(
            @Query("id") int id
    );


    @PUT("Client_Payments_Methods")
    public Call<String> updateClientPaymentsMethod(
            @Query("id") int id ,
            @Body  ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse
    );

    @GET("Clients_Favourites")
    public Call<ClientFavouriteResponse> getClientFavouriteById(
            @Query("id") int id
    );

    @DELETE("Clients_Favourites")
    public Call<ClientFavouriteResponse> deleteClientFavouriteById(
            @Query("id") int id
    );

    @PUT("Clients_Favourites")
    public Call<String> updateClientFavourite(
            @Query("id") int id ,
            @Body ClientFavouriteResponse clientFavouriteResponse
    );

    @POST("Trips")
    public Call<design.swira.aennyapp.pojo.aenny.trips.TripsResponse> saveTrip(
            @Body TripsResponse tripsResponse
    );







    @PUT("Trips")
    public Call<ResponseBody> updateTrip(
            @Query("id") int id ,
            @Body design.swira.aennyapp.pojo.aenny.trip.ResponseTrips tripsResponse
    );

    @GET("Trips")
    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> getTripById(
            @Query("id") int id
    );

    @GET("Venusera/Trips/GetAllScheduledTrip")
    public Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getScduledTrips(

    );

    @GET("Trips/ScheduledTrips/Client")
    public Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getScduledTripsByClientId(
            @Query("clientId") int clientId
    );

    @GET("Trips/NextScheduledTrip/Client")
    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> getNextScduledTripByClientId(
            @Query("clientId") int clientId
    );


    //@Headers("Content-Type: application/json")
    @POST("Trips")
    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> saveTrips(
            @Body design.swira.aennyapp.pojo.aenny.trip.TripsResponse tripsResponse
    );

    @DELETE("Trips")
    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> deleteTripById(
            @Query("id") int id
    );

    @GET("Trips")
    public Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getAllTrips(

    );

    @GET("Clients")
    public Call<ClientDataResponse> getClientData(
          @Query("id")  int id
    );


    @GET("Trips/ScheduledTrips/Client/{clientId}")
    public Call<List<ScduledTripsResponse>> getScduledTripsForClient(
            @Path("clientId") int clientId
    );

    @GET("Trips/NextScheduledTrip/Client/{clientId}")
    public Call<List<ScduledTripsResponse>> getNextScduledTripForClient(
            @Path("clientId") int clientId
    );


    @GET("GetNotificationBySenderId/{userTypeId}/{senderId}")
    public Call<List<NotificationsResponse>> getAllNoficationsByClientId(
            @Path("userTypeId") int userTypeId ,
            @Path("senderId") int senderId
    );



    @PUT("Clients/{id}")
    public Call<UpdateClientResponse> UpdateClientData(
            @Path("id") int id ,
            @Body UpdateClientResponse updateClientResponse
    );


    @Headers("Content-Type: application/json")
    //@FormUrlEncoded
    @PUT("Trips/{id}")
    public Call<ScduledTripsResponse> UpdateScduledTrip(
            @Path("id") int id ,
            @Body UpdateScduleTrip updateScduleTrip
    );



    @POST("Trips/AddScheduleTrip")
    public Call<AddScduleTrip> addScduleTrip(
          @Body AddScduleTrip addScduleTrip
    );

    @GET("Trips/ScheduledTrips/Client/{clientId}")
    public Call<List<ScduleTripsListResponse>> getAllScduledTripsByClientId(
            @Path("clientId") int clientId
    );

    @GET("Trips/NextScheduledTrip/Client/{clientId}")
    public Call<List<ScduleTripsListResponse>> getNextScduledTripsByClientId(
            @Path("clientId") int clientId
    );

    @GET("Clients/DisabilityTypeByClientId/{ClientId}")
    public  Call<List<ClientDisabilitiesResponse>> getAllClientDisabilities(
            @Path("clientId") int clientId
    );


    @PUT("Trips/{id}")
    public Call<UpdateScduleResponse> updateScduledTrip(
            @Path("id") int id ,
            @Body design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleTrip updateScduleTrip
    );







}
