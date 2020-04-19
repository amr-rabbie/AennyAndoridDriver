package design.swira.aennyappdriver.pojo.aennydriver.comptrip;


import com.google.gson.annotations.SerializedName;


public class Driver{

	@SerializedName("Driver_Notes")
	private Object driverNotes;

	@SerializedName("Driver_Address")
	private String driverAddress;

	@SerializedName("Driver_Area")
	private int driverArea;

	@SerializedName("Driver_BirthDate")
	private Object driverBirthDate;

	@SerializedName("Driver_IsOnline")
	private boolean driverIsOnline;

	@SerializedName("Driver_Image")
	private String driverImage;

	@SerializedName("Gender_Name")
	private Object genderName;

	@SerializedName("Driver_Identity_Number")
	private Object driverIdentityNumber;

	@SerializedName("Driver_Gender")
	private int driverGender;

	@SerializedName("Driver_JoinDate")
	private String driverJoinDate;

	@SerializedName("Driver_City")
	private int driverCity;

	@SerializedName("City_Name")
	private Object cityName;

	@SerializedName("Driver_Name")
	private String driverName;

	@SerializedName("Driver_Mobile")
	private String driverMobile;

	@SerializedName("Driver_Email")
	private String driverEmail;

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("Vehicle")
	private Object vehicle;

	@SerializedName("Driver_License_Number")
	private Object driverLicenseNumber;

	@SerializedName("Driver_NoOfTrips")
	private int driverNoOfTrips;

	@SerializedName("Driver_AvgRate")
	private int driverAvgRate;

	@SerializedName("Driver_Employed")
	private int driverEmployed;

	@SerializedName("Area_Name")
	private Object areaName;

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

	public void setDriverArea(int driverArea){
		this.driverArea = driverArea;
	}

	public int getDriverArea(){
		return driverArea;
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

	public void setGenderName(Object genderName){
		this.genderName = genderName;
	}

	public Object getGenderName(){
		return genderName;
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

	public void setCityName(Object cityName){
		this.cityName = cityName;
	}

	public Object getCityName(){
		return cityName;
	}

	public void setDriverName(String driverName){
		this.driverName = driverName;
	}

	public String getDriverName(){
		return driverName;
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

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
	}

	public void setVehicle(Object vehicle){
		this.vehicle = vehicle;
	}

	public Object getVehicle(){
		return vehicle;
	}

	public void setDriverLicenseNumber(Object driverLicenseNumber){
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public Object getDriverLicenseNumber(){
		return driverLicenseNumber;
	}

	public void setDriverNoOfTrips(int driverNoOfTrips){
		this.driverNoOfTrips = driverNoOfTrips;
	}

	public int getDriverNoOfTrips(){
		return driverNoOfTrips;
	}

	public void setDriverAvgRate(int driverAvgRate){
		this.driverAvgRate = driverAvgRate;
	}

	public int getDriverAvgRate(){
		return driverAvgRate;
	}

	public void setDriverEmployed(int driverEmployed){
		this.driverEmployed = driverEmployed;
	}

	public int getDriverEmployed(){
		return driverEmployed;
	}

	public void setAreaName(Object areaName){
		this.areaName = areaName;
	}

	public Object getAreaName(){
		return areaName;
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
			"Driver{" + 
			"driver_Notes = '" + driverNotes + '\'' + 
			",driver_Address = '" + driverAddress + '\'' + 
			",driver_Area = '" + driverArea + '\'' + 
			",driver_BirthDate = '" + driverBirthDate + '\'' + 
			",driver_IsOnline = '" + driverIsOnline + '\'' + 
			",driver_Image = '" + driverImage + '\'' + 
			",gender_Name = '" + genderName + '\'' + 
			",driver_Identity_Number = '" + driverIdentityNumber + '\'' + 
			",driver_Gender = '" + driverGender + '\'' + 
			",driver_JoinDate = '" + driverJoinDate + '\'' + 
			",driver_City = '" + driverCity + '\'' + 
			",city_Name = '" + cityName + '\'' + 
			",driver_Name = '" + driverName + '\'' + 
			",driver_Mobile = '" + driverMobile + '\'' + 
			",driver_Email = '" + driverEmail + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",vehicle = '" + vehicle + '\'' + 
			",driver_License_Number = '" + driverLicenseNumber + '\'' + 
			",driver_NoOfTrips = '" + driverNoOfTrips + '\'' + 
			",driver_AvgRate = '" + driverAvgRate + '\'' + 
			",driver_Employed = '" + driverEmployed + '\'' + 
			",area_Name = '" + areaName + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}