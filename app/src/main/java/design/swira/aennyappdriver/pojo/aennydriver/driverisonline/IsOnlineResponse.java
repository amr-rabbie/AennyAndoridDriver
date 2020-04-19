package design.swira.aennyappdriver.pojo.aennydriver.driverisonline;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class IsOnlineResponse{

	@SerializedName("Driver_Notes")
	private Object driverNotes;

	@SerializedName("Driver_Address")
	private String driverAddress;

	@SerializedName("Driver_Trips_Accept_Status")
	private List<Object> driverTripsAcceptStatus;

	@SerializedName("Driver_Area")
	private int driverArea;

	@SerializedName("ModifiedOn")
	private Object modifiedOn;

	@SerializedName("Driver_Identity_Number")
	private Object driverIdentityNumber;

	@SerializedName("Driver_Gender")
	private int driverGender;

	@SerializedName("Driver_Key")
	private String driverKey;

	@SerializedName("Driver_Login_Log")
	private List<Object> driverLoginLog;

	@SerializedName("Gender")
	private Object gender;

	@SerializedName("Trips_Rates")
	private List<Object> tripsRates;

	@SerializedName("Driver_Name")
	private String driverName;

	@SerializedName("Trip_Chat")
	private List<Object> tripChat;

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("Driver_License_Number")
	private Object driverLicenseNumber;

	@SerializedName("Drivers_TurnOns")
	private List<Object> driversTurnOns;

	@SerializedName("Driver_Employed")
	private int driverEmployed;

	@SerializedName("Trips")
	private List<Object> trips;

	@SerializedName("Driver_Report")
	private List<Object> driverReport;

	@SerializedName("IsDeleted")
	private Object isDeleted;

	@SerializedName("Driver_BirthDate")
	private Object driverBirthDate;

	@SerializedName("Driver_IsOnline")
	private boolean driverIsOnline;

	@SerializedName("Driver_Image")
	private String driverImage;

	@SerializedName("Driver_JoinDate")
	private String driverJoinDate;

	@SerializedName("Driver_City")
	private int driverCity;

	@SerializedName("City")
	private Object city;

	@SerializedName("Driver_Mobile")
	private String driverMobile;

	@SerializedName("Driver_Email")
	private String driverEmail;

	@SerializedName("Area")
	private Object area;

	@SerializedName("Emergencys")
	private List<Object> emergencys;

	@SerializedName("Driver_Password")
	private String driverPassword;

	@SerializedName("Client_Report")
	private List<Object> clientReport;

	@SerializedName("Driver_Vehicle")
	private List<Object> driverVehicle;

	@SerializedName("$id")
	private String id;

	public void setDriverNotes(Object driverNotes){
		this.driverNotes = driverNotes;
	}

	public Object getDriverNotes(){
		return driverNotes;
	}

	public void setDriverAddress(String driverAddress){
		this.driverAddress = driverAddress;
	}

	public String getDriverAddress(){
		return driverAddress;
	}

	public void setDriverTripsAcceptStatus(List<Object> driverTripsAcceptStatus){
		this.driverTripsAcceptStatus = driverTripsAcceptStatus;
	}

	public List<Object> getDriverTripsAcceptStatus(){
		return driverTripsAcceptStatus;
	}

	public void setDriverArea(int driverArea){
		this.driverArea = driverArea;
	}

	public int getDriverArea(){
		return driverArea;
	}

	public void setModifiedOn(Object modifiedOn){
		this.modifiedOn = modifiedOn;
	}

	public Object getModifiedOn(){
		return modifiedOn;
	}

	public void setDriverIdentityNumber(Object driverIdentityNumber){
		this.driverIdentityNumber = driverIdentityNumber;
	}

	public Object getDriverIdentityNumber(){
		return driverIdentityNumber;
	}

	public void setDriverGender(int driverGender){
		this.driverGender = driverGender;
	}

	public int getDriverGender(){
		return driverGender;
	}

	public void setDriverKey(String driverKey){
		this.driverKey = driverKey;
	}

	public String getDriverKey(){
		return driverKey;
	}

	public void setDriverLoginLog(List<Object> driverLoginLog){
		this.driverLoginLog = driverLoginLog;
	}

	public List<Object> getDriverLoginLog(){
		return driverLoginLog;
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

	public void setDriverName(String driverName){
		this.driverName = driverName;
	}

	public String getDriverName(){
		return driverName;
	}

	public void setTripChat(List<Object> tripChat){
		this.tripChat = tripChat;
	}

	public List<Object> getTripChat(){
		return tripChat;
	}

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
	}

	public void setDriverLicenseNumber(Object driverLicenseNumber){
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public Object getDriverLicenseNumber(){
		return driverLicenseNumber;
	}

	public void setDriversTurnOns(List<Object> driversTurnOns){
		this.driversTurnOns = driversTurnOns;
	}

	public List<Object> getDriversTurnOns(){
		return driversTurnOns;
	}

	public void setDriverEmployed(int driverEmployed){
		this.driverEmployed = driverEmployed;
	}

	public int getDriverEmployed(){
		return driverEmployed;
	}

	public void setTrips(List<Object> trips){
		this.trips = trips;
	}

	public List<Object> getTrips(){
		return trips;
	}

	public void setDriverReport(List<Object> driverReport){
		this.driverReport = driverReport;
	}

	public List<Object> getDriverReport(){
		return driverReport;
	}

	public void setIsDeleted(Object isDeleted){
		this.isDeleted = isDeleted;
	}

	public Object getIsDeleted(){
		return isDeleted;
	}

	public void setDriverBirthDate(Object driverBirthDate){
		this.driverBirthDate = driverBirthDate;
	}

	public Object getDriverBirthDate(){
		return driverBirthDate;
	}

	public void setDriverIsOnline(boolean driverIsOnline){
		this.driverIsOnline = driverIsOnline;
	}

	public boolean isDriverIsOnline(){
		return driverIsOnline;
	}

	public void setDriverImage(String driverImage){
		this.driverImage = driverImage;
	}

	public String getDriverImage(){
		return driverImage;
	}

	public void setDriverJoinDate(String driverJoinDate){
		this.driverJoinDate = driverJoinDate;
	}

	public String getDriverJoinDate(){
		return driverJoinDate;
	}

	public void setDriverCity(int driverCity){
		this.driverCity = driverCity;
	}

	public int getDriverCity(){
		return driverCity;
	}

	public void setCity(Object city){
		this.city = city;
	}

	public Object getCity(){
		return city;
	}

	public void setDriverMobile(String driverMobile){
		this.driverMobile = driverMobile;
	}

	public String getDriverMobile(){
		return driverMobile;
	}

	public void setDriverEmail(String driverEmail){
		this.driverEmail = driverEmail;
	}

	public String getDriverEmail(){
		return driverEmail;
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

	public void setDriverPassword(String driverPassword){
		this.driverPassword = driverPassword;
	}

	public String getDriverPassword(){
		return driverPassword;
	}

	public void setClientReport(List<Object> clientReport){
		this.clientReport = clientReport;
	}

	public List<Object> getClientReport(){
		return clientReport;
	}

	public void setDriverVehicle(List<Object> driverVehicle){
		this.driverVehicle = driverVehicle;
	}

	public List<Object> getDriverVehicle(){
		return driverVehicle;
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
			"IsOnlineResponse{" + 
			"driver_Notes = '" + driverNotes + '\'' + 
			",driver_Address = '" + driverAddress + '\'' + 
			",driver_Trips_Accept_Status = '" + driverTripsAcceptStatus + '\'' + 
			",driver_Area = '" + driverArea + '\'' + 
			",modifiedOn = '" + modifiedOn + '\'' + 
			",driver_Identity_Number = '" + driverIdentityNumber + '\'' + 
			",driver_Gender = '" + driverGender + '\'' + 
			",driver_Key = '" + driverKey + '\'' + 
			",driver_Login_Log = '" + driverLoginLog + '\'' + 
			",gender = '" + gender + '\'' + 
			",trips_Rates = '" + tripsRates + '\'' + 
			",driver_Name = '" + driverName + '\'' + 
			",trip_Chat = '" + tripChat + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",driver_License_Number = '" + driverLicenseNumber + '\'' + 
			",drivers_TurnOns = '" + driversTurnOns + '\'' + 
			",driver_Employed = '" + driverEmployed + '\'' + 
			",trips = '" + trips + '\'' + 
			",driver_Report = '" + driverReport + '\'' + 
			",isDeleted = '" + isDeleted + '\'' + 
			",driver_BirthDate = '" + driverBirthDate + '\'' + 
			",driver_IsOnline = '" + driverIsOnline + '\'' + 
			",driver_Image = '" + driverImage + '\'' + 
			",driver_JoinDate = '" + driverJoinDate + '\'' + 
			",driver_City = '" + driverCity + '\'' + 
			",city = '" + city + '\'' + 
			",driver_Mobile = '" + driverMobile + '\'' + 
			",driver_Email = '" + driverEmail + '\'' + 
			",area = '" + area + '\'' + 
			",emergencys = '" + emergencys + '\'' + 
			",driver_Password = '" + driverPassword + '\'' + 
			",client_Report = '" + clientReport + '\'' + 
			",driver_Vehicle = '" + driverVehicle + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}