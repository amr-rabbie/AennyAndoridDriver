package design.swira.aennyapp.data.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
import design.swira.aennyapp.pojo.aenny.longtrip.LongTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.AddScduleTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.ScduleTripsListResponse;
import design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleResponse;
import design.swira.aennyapp.pojo.aenny.paymentsmethods.PaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.scduledtrips.ScduledTripsResponse;
import design.swira.aennyapp.pojo.aenny.schduletrips.UpdateScduleTrip;
import design.swira.aennyapp.pojo.aenny.trips.TripsResponse;
import design.swira.aennyapp.pojo.test.Response;
import design.swira.aennyapp.pojo.test2.WResponse;
import design.swira.aennyapp.pojo.test3.MResponse;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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






    public Call<Response> getAllData(String country){
        return apiInterface.getAllData(country);
    }

    public Call<WResponse> getWAllData(String q,String appid){
        return apiInterface.getWAllData(q,appid);
    }

    public Call<MResponse> getMAllData(String ts , String apikey , String hash){
        return apiInterface.getMAllData(ts,apikey,hash);
    }

   public Call<List<City>> getAllCities(){
        return apiInterface.getAllCities();
   }

    public Call<List<Areas>> getAllAreas(){
        return apiInterface.getAllAreas();
    }

    public Call<List<Areas>> getAreasByCityid(int cityid){
        return  apiInterface.getAllAreasByCityid(cityid);
    }

    public Call<List<Genders>> getAllGenders(){
        return apiInterface.getAllGenders();
    }

    public Call<List<DisabilityTypes>> getAllDisabilityTypes(){
        return apiInterface.getAllDisabilityTypes();
    }

   public Call<Client> clientLogin(String mobile, String password){
        return apiInterface.clientLogin(mobile,password);
   }

    public Call<ClientResponse> PostclientLogin(String mobile, String password){
        return apiInterface.PostclientLogin(mobile,password);
    }

   /*public Call<String> clientRegsister(Client client){
        return apiInterface.clientRegsister(client);
   }*/

   public Call<String> changeClientMobile(int clientid, String oldmob, String newmob){
        return apiInterface.changeClientMobile(clientid,oldmob,newmob);
    }

    public Call<ClientResponse> PostchangeClientMobile(int clientid, String oldmob, String newmob){
        return apiInterface.PostchangeClientMobile(clientid,oldmob,newmob);
    }

    public Call<String> changeClientPassword(int clientid, String oldpassword , String newpassword){
        return apiInterface.changeClientPassword(clientid,oldpassword,newpassword);
    }

    public Call<ClientResponse> PostchangeClientPassword(String mobile , String oldpassword , String newpassword){
        return apiInterface.PostchangeClientPassword(mobile,oldpassword,newpassword);
    }

    public Call<ClientResponse> ClientForgotPassword(String mobile ,  String newpassword){
        return apiInterface.ClientForgotPassword(mobile,newpassword);
    }

    public Call<Client> clientRegsister(String name,String mobile , String password){
        return apiInterface.clientRegsister(name,mobile,password);
    }

    public Call<Client> clientFullRegsister(Client client){
        return apiInterface.clientFullRegsister(client);
    }

    public Call<Client> getClientById(int id){
       return apiInterface.getClientById(id);
    }

    public Call<Client> updateClientProfile(int id ,Client client){
       return apiInterface.updateClientProfile(id,client);
    }

    public Call<ClientDisability> addDisabilityToclient(ClientDisability clientDisability){
       return apiInterface.addDisabilityToclient(clientDisability);
    }

    public Call<String> deleteClientProfile(int id){
       return apiInterface.deleteClientProfile(id);
    }

    public Call<String> deleteDisabilityTypesByClient(int id){
       return apiInterface.deleteDisabilityTypesByClients(id);
    }

    public Call<List<ClientDisability>> getDisabilityTypesByClient(int id){
        return apiInterface.getDisabilityTypesByClients(id);
    }

    public Call<LongTrip> saveLongTrip(LongTrip longTrip){
       return apiInterface.saveLongTrip(longTrip);
    }

    public Call<List<PaymentsMethodsResponse>> getAllPaymentsMethods(){
       return apiInterface.getAllPaymentsMethods();
    }

    public Call<ClientsPaymentsMethodsResponse> saveClientPaymentMethod(ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse){
       return apiInterface.saveClientPaymentsMethod(clientsPaymentsMethodsResponse);
    }

    public Call<List<ClientsPaymentsMethodsResponse>> getPaymentsMethodsByClientId(int clientid){
       return apiInterface.getPaymentsMethodsByClientId(clientid);
    }

    public Call<ClientFavouriteResponse> saveClientFavourite(ClientFavouriteResponse clientFavouriteResponse){
       return apiInterface.saveClientFavourite(clientFavouriteResponse);
    }

    public Call<List<ClientFavouriteResponse>> getClientFavouritesById(int clientid){
       return apiInterface.getClientFavouritesById(clientid);
    }

    public Call<List<ClientsPaymentsMethodsResponse>> getAllPaymentsMethodsByClientId(int clientid){
       return apiInterface.getAllPaymentsMethodsByClientId(clientid);
    }

    public Call<ClientsPaymentsMethodsResponse> getClientPaymentById(int id){
       return apiInterface.getClientPaymentById(id);
    }

    public Call<ClientsPaymentsMethodsResponse> deleteClientPaymentById(int id){
        return apiInterface.deleteClientPaymentById(id);
    }

    public Call<String> updateClientPaymentById(int id,ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse){
        return apiInterface.updateClientPaymentsMethod(id,clientsPaymentsMethodsResponse);
    }

    public Call<ClientFavouriteResponse> getClientFavouriteById(int id){
        return apiInterface.getClientFavouriteById(id);
    }

    public Call<ClientFavouriteResponse> deleteClientFavouriteById(int id){
        return apiInterface.deleteClientFavouriteById(id);
    }

    public Call<String> updateClientFavouriteById(int id,ClientFavouriteResponse clientFavouriteResponse){
        return apiInterface.updateClientFavourite(id,clientFavouriteResponse);
    }

    public Call<TripsResponse> saveTrip(TripsResponse tripsResponse){
       return apiInterface.saveTrip(tripsResponse);
    }

    public Call<ResponseBody> updateTrip(int id, design.swira.aennyapp.pojo.aenny.trip.ResponseTrips tripsResponse){
       return apiInterface.updateTrip(id,tripsResponse);
    }

    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> getTripById(int id){
       return apiInterface.getTripById(id);
    }

    public Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getScduledTrips(){
       return apiInterface.getScduledTrips();
    }

    public Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getScduledTripsbyClientId(int clientid){
        return apiInterface.getScduledTripsByClientId(clientid);
    }

    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> getNextScduledTripByClientId(int clientid){
        return apiInterface.getNextScduledTripByClientId(clientid);
    }

    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> saveTrips(design.swira.aennyapp.pojo.aenny.trip.TripsResponse tripsResponse){
       return apiInterface.saveTrips(tripsResponse);
    }

    public Call<design.swira.aennyapp.pojo.aenny.trip.TripsResponse> deleteTripById(int id){
       return apiInterface.deleteTripById(id);
    }

    public Call<List<design.swira.aennyapp.pojo.aenny.trip.TripsResponse>> getAllTrips(){
       return apiInterface.getAllTrips();
    }

    public Call<ClientDataResponse> getClientData(int id){
       return apiInterface.getClientData(id);
    }

    public Call<List<ScduledTripsResponse>> getScduledTripsForClient(int clientid){
       return apiInterface.getScduledTripsForClient(clientid);
    }

    public Call<List<ScduledTripsResponse>> getNextScduledTripForClient(int clientid){
        return apiInterface.getNextScduledTripForClient(clientid);
    }

    /*public Call<List<NotificationsResponse>> getAllNoficationsByClientId(int userTypeId , int senderId){
       return  apiInterface.getAllNoficationsByClientId(userTypeId,senderId);
    }*/

    public Call<UpdateClientResponse> UpdateClientData(int id , UpdateClientResponse updateClientResponse){
        return apiInterface.UpdateClientData(id,updateClientResponse);
    }

    public Call<ScduledTripsResponse> UpdateScduledTrip(int id, UpdateScduleTrip updateScduleTrip){
        return apiInterface.UpdateScduledTrip(id,updateScduleTrip);
    }

    public Call<AddScduleTrip> addScduleTrip(AddScduleTrip addScduleTrip){
        return apiInterface.addScduleTrip(addScduleTrip);
    }

    public Call<List<ScduleTripsListResponse>> getAllScduledTripsByClientId(int clientid){
        return apiInterface.getAllScduledTripsByClientId(clientid);
    }



    public Call<List<ScduleTripsListResponse>> getNextScduledTripsByClientId(int clientid){
        return apiInterface.getNextScduledTripsByClientId(clientid);
    }


    public Call<List<ClientDisabilitiesResponse>> getAllClientDisabilities(int clientid){
        return apiInterface.getAllClientDisabilities(clientid);
    }


    public Call<UpdateScduleResponse> updateScduledTrip(int id, design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleTrip updateScduleTrip){
        return apiInterface.updateScduledTrip(id,updateScduleTrip);
    }






}
