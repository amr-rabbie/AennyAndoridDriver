package design.swira.aennyappdriver.pojo.aenny.loginresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse{

	@SerializedName("Client_Name")
	private String clientName;

	@SerializedName("Client_Mobile")
	private String clientMobile;

	@SerializedName("Client_Notes")
	private Object clientNotes;

	@SerializedName("Gender")
	private Object gender;

	@SerializedName("Trips_Rates")
	private List<Object> tripsRates;

	@SerializedName("Trip_Chat")
	private List<Object> tripChat;

	@SerializedName("Clients_Favourites")
	private List<Object> clientsFavourites;

	@SerializedName("Trips")
	private List<Object> trips;

	@SerializedName("Client_Relative_Name")
	private Object clientRelativeName;

	@SerializedName("Client_Email")
	private Object clientEmail;

	@SerializedName("Long_Trips")
	private List<Object> longTrips;

	@SerializedName("Client_JoinDate")
	private Object clientJoinDate;

	@SerializedName("Client_City")
	private Object clientCity;

	@SerializedName("Client_Key")
	private String clientKey;

	@SerializedName("Client_Password")
	private String clientPassword;

	@SerializedName("Client_BirthDate")
	private Object clientBirthDate;

	@SerializedName("City")
	private Object city;

	@SerializedName("Client_Relative_Mobile")
	private Object clientRelativeMobile;

	@SerializedName("Area")
	private Object area;

	@SerializedName("Emergencys")
	private List<Object> emergencys;

	@SerializedName("Client_Area")
	private Object clientArea;

	@SerializedName("Client_Disabilties")
	private List<Object> clientDisabilties;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client_Gender")
	private Object clientGender;

	@SerializedName("Client_Report")
	private List<Object> clientReport;

	@SerializedName("Use_App_Disabled")
	private Object useAppDisabled;

	@SerializedName("$id")
	private String id;

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientMobile(String clientMobile){
		this.clientMobile = clientMobile;
	}

	public String getClientMobile(){
		return clientMobile;
	}

	public void setClientNotes(Object clientNotes){
		this.clientNotes = clientNotes;
	}

	public Object getClientNotes(){
		return clientNotes;
	}

	public void setGender(Object gender){
		this.gender = gender;
	}

	public Object getGender(){
		return gender;
	}

	public void setTripsRates(List<Object> tripsRates){
		this.tripsRates = tripsRates;
	}

	public List<Object> getTripsRates(){
		return tripsRates;
	}

	public void setTripChat(List<Object> tripChat){
		this.tripChat = tripChat;
	}

	public List<Object> getTripChat(){
		return tripChat;
	}

	public void setClientsFavourites(List<Object> clientsFavourites){
		this.clientsFavourites = clientsFavourites;
	}

	public List<Object> getClientsFavourites(){
		return clientsFavourites;
	}

	public void setTrips(List<Object> trips){
		this.trips = trips;
	}

	public List<Object> getTrips(){
		return trips;
	}

	public void setClientRelativeName(Object clientRelativeName){
		this.clientRelativeName = clientRelativeName;
	}

	public Object getClientRelativeName(){
		return clientRelativeName;
	}

	public void setClientEmail(Object clientEmail){
		this.clientEmail = clientEmail;
	}

	public Object getClientEmail(){
		return clientEmail;
	}

	public void setLongTrips(List<Object> longTrips){
		this.longTrips = longTrips;
	}

	public List<Object> getLongTrips(){
		return longTrips;
	}

	public void setClientJoinDate(Object clientJoinDate){
		this.clientJoinDate = clientJoinDate;
	}

	public Object getClientJoinDate(){
		return clientJoinDate;
	}

	public void setClientCity(Object clientCity){
		this.clientCity = clientCity;
	}

	public Object getClientCity(){
		return clientCity;
	}

	public void setClientKey(String clientKey){
		this.clientKey = clientKey;
	}

	public String getClientKey(){
		return clientKey;
	}

	public void setClientPassword(String clientPassword){
		this.clientPassword = clientPassword;
	}

	public String getClientPassword(){
		return clientPassword;
	}

	public void setClientBirthDate(Object clientBirthDate){
		this.clientBirthDate = clientBirthDate;
	}

	public Object getClientBirthDate(){
		return clientBirthDate;
	}

	public void setCity(Object city){
		this.city = city;
	}

	public Object getCity(){
		return city;
	}

	public void setClientRelativeMobile(Object clientRelativeMobile){
		this.clientRelativeMobile = clientRelativeMobile;
	}

	public Object getClientRelativeMobile(){
		return clientRelativeMobile;
	}

	public void setArea(Object area){
		this.area = area;
	}

	public Object getArea(){
		return area;
	}

	public void setEmergencys(List<Object> emergencys){
		this.emergencys = emergencys;
	}

	public List<Object> getEmergencys(){
		return emergencys;
	}

	public void setClientArea(Object clientArea){
		this.clientArea = clientArea;
	}

	public Object getClientArea(){
		return clientArea;
	}

	public void setClientDisabilties(List<Object> clientDisabilties){
		this.clientDisabilties = clientDisabilties;
	}

	public List<Object> getClientDisabilties(){
		return clientDisabilties;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setClientGender(Object clientGender){
		this.clientGender = clientGender;
	}

	public Object getClientGender(){
		return clientGender;
	}

	public void setClientReport(List<Object> clientReport){
		this.clientReport = clientReport;
	}

	public List<Object> getClientReport(){
		return clientReport;
	}

	public void setUseAppDisabled(Object useAppDisabled){
		this.useAppDisabled = useAppDisabled;
	}

	public Object getUseAppDisabled(){
		return useAppDisabled;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"client_Name = '" + clientName + '\'' + 
			",client_Mobile = '" + clientMobile + '\'' + 
			",client_Notes = '" + clientNotes + '\'' + 
			",gender = '" + gender + '\'' + 
			",trips_Rates = '" + tripsRates + '\'' + 
			",trip_Chat = '" + tripChat + '\'' + 
			",clients_Favourites = '" + clientsFavourites + '\'' + 
			",trips = '" + trips + '\'' + 
			",client_Relative_Name = '" + clientRelativeName + '\'' + 
			",client_Email = '" + clientEmail + '\'' + 
			",long_Trips = '" + longTrips + '\'' + 
			",client_JoinDate = '" + clientJoinDate + '\'' + 
			",client_City = '" + clientCity + '\'' + 
			",client_Key = '" + clientKey + '\'' + 
			",client_Password = '" + clientPassword + '\'' + 
			",client_BirthDate = '" + clientBirthDate + '\'' + 
			",city = '" + city + '\'' + 
			",client_Relative_Mobile = '" + clientRelativeMobile + '\'' + 
			",area = '" + area + '\'' + 
			",emergencys = '" + emergencys + '\'' + 
			",client_Area = '" + clientArea + '\'' + 
			",client_Disabilties = '" + clientDisabilties + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client_Gender = '" + clientGender + '\'' + 
			",client_Report = '" + clientReport + '\'' + 
			",use_App_Disabled = '" + useAppDisabled + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}