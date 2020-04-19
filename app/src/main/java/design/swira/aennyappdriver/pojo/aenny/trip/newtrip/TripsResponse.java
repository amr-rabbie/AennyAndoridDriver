package design.swira.aennyappdriver.pojo.aenny.trip.newtrip;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TripsResponse{

	@SerializedName("Driver_Trips_Accept_Status")
	private List<Object> driverTripsAcceptStatus;

	@SerializedName("Trip_Cost")
	private double tripCost;

	@SerializedName("Driver")
	private Object driver;

	@SerializedName("Trip_Destination_Latt")
	private double tripDestinationLatt;

	@SerializedName("Trip_Destination")
	private String tripDestination;

	@SerializedName("Trip_Destination_Long")
	private double tripDestinationLong;

	@SerializedName("Trips_Rates")
	private List<Object> tripsRates;

	@SerializedName("Payment_Type")
	private Object paymentType;

	@SerializedName("Trip_Time")
	private String tripTime;

	@SerializedName("Trip_Pickup_Long")
	private double tripPickupLong;

	@SerializedName("Trip_Schedule_Time")
	private Object tripScheduleTime;

	@SerializedName("Driver_Id")
	private Object driverId;

	@SerializedName("Trip_Chat")
	private List<Object> tripChat;

	@SerializedName("Vehicle")
	private Object vehicle;

	@SerializedName("Trip_Destination_Time")
	private Object tripDestinationTime;

	@SerializedName("Trips_Status")
	private Object tripsStatus;

	@SerializedName("Client")
	private Object client;

	@SerializedName("Trips_Types")
	private Object tripsTypes;

	@SerializedName("Trip_Notes")
	private Object tripNotes;

	@SerializedName("Goverments_Ratio")
	private Object govermentsRatio;

	@SerializedName("Trip_Type_Id")
	private int tripTypeId;

	@SerializedName("Healthy_Number")
	private int healthyNumber;

	@SerializedName("Trip_Pickup")
	private String tripPickup;

	@SerializedName("Payment_Types")
	private Object paymentTypes;

	@SerializedName("Trip_Key")
	private Object tripKey;

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Vehicle_Id")
	private Object vehicleId;

	@SerializedName("Trips_Navigations")
	private List<Object> tripsNavigations;

	@SerializedName("Trip_Schedule_Date")
	private Object tripScheduleDate;

	@SerializedName("Emergencys")
	private List<Object> emergencys;

	@SerializedName("Trip_Pickup_Latt")
	private double tripPickupLatt;

	@SerializedName("Trip_Distance")
	private double tripDistance;

	@SerializedName("Visa_Number")
	private String visaNumber;

	@SerializedName("Goverment")
	private Object goverment;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("Trip_Status")
	private int tripStatus;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Handicapped_Number")
	private int handicappedNumber;

	@SerializedName("Client_Report")
	private List<Object> clientReport;

	@SerializedName("Goverments_Id")
	private Object govermentsId;

	@SerializedName("Trip_Destination_Date")
	private Object tripDestinationDate;

	@SerializedName("$id")
	private String id;

	public void setDriverTripsAcceptStatus(List<Object> driverTripsAcceptStatus){
		this.driverTripsAcceptStatus = driverTripsAcceptStatus;
	}

	public List<Object> getDriverTripsAcceptStatus(){
		return driverTripsAcceptStatus;
	}

	public void setTripCost(double tripCost){
		this.tripCost = tripCost;
	}

	public double getTripCost(){
		return tripCost;
	}

	public void setDriver(Object driver){
		this.driver = driver;
	}

	public Object getDriver(){
		return driver;
	}

	public void setTripDestinationLatt(double tripDestinationLatt){
		this.tripDestinationLatt = tripDestinationLatt;
	}

	public double getTripDestinationLatt(){
		return tripDestinationLatt;
	}

	public void setTripDestination(String tripDestination){
		this.tripDestination = tripDestination;
	}

	public String getTripDestination(){
		return tripDestination;
	}

	public void setTripDestinationLong(double tripDestinationLong){
		this.tripDestinationLong = tripDestinationLong;
	}

	public double getTripDestinationLong(){
		return tripDestinationLong;
	}

	public void setTripsRates(List<Object> tripsRates){
		this.tripsRates = tripsRates;
	}

	public List<Object> getTripsRates(){
		return tripsRates;
	}

	public void setPaymentType(Object paymentType){
		this.paymentType = paymentType;
	}

	public Object getPaymentType(){
		return paymentType;
	}

	public void setTripTime(String tripTime){
		this.tripTime = tripTime;
	}

	public String getTripTime(){
		return tripTime;
	}

	public void setTripPickupLong(double tripPickupLong){
		this.tripPickupLong = tripPickupLong;
	}

	public double getTripPickupLong(){
		return tripPickupLong;
	}

	public void setTripScheduleTime(Object tripScheduleTime){
		this.tripScheduleTime = tripScheduleTime;
	}

	public Object getTripScheduleTime(){
		return tripScheduleTime;
	}

	public void setDriverId(Object driverId){
		this.driverId = driverId;
	}

	public Object getDriverId(){
		return driverId;
	}

	public void setTripChat(List<Object> tripChat){
		this.tripChat = tripChat;
	}

	public List<Object> getTripChat(){
		return tripChat;
	}

	public void setVehicle(Object vehicle){
		this.vehicle = vehicle;
	}

	public Object getVehicle(){
		return vehicle;
	}

	public void setTripDestinationTime(Object tripDestinationTime){
		this.tripDestinationTime = tripDestinationTime;
	}

	public Object getTripDestinationTime(){
		return tripDestinationTime;
	}

	public void setTripsStatus(Object tripsStatus){
		this.tripsStatus = tripsStatus;
	}

	public Object getTripsStatus(){
		return tripsStatus;
	}

	public void setClient(Object client){
		this.client = client;
	}

	public Object getClient(){
		return client;
	}

	public void setTripsTypes(Object tripsTypes){
		this.tripsTypes = tripsTypes;
	}

	public Object getTripsTypes(){
		return tripsTypes;
	}

	public void setTripNotes(Object tripNotes){
		this.tripNotes = tripNotes;
	}

	public Object getTripNotes(){
		return tripNotes;
	}

	public void setGovermentsRatio(Object govermentsRatio){
		this.govermentsRatio = govermentsRatio;
	}

	public Object getGovermentsRatio(){
		return govermentsRatio;
	}

	public void setTripTypeId(int tripTypeId){
		this.tripTypeId = tripTypeId;
	}

	public int getTripTypeId(){
		return tripTypeId;
	}

	public void setHealthyNumber(int healthyNumber){
		this.healthyNumber = healthyNumber;
	}

	public int getHealthyNumber(){
		return healthyNumber;
	}

	public void setTripPickup(String tripPickup){
		this.tripPickup = tripPickup;
	}

	public String getTripPickup(){
		return tripPickup;
	}

	public void setPaymentTypes(Object paymentTypes){
		this.paymentTypes = paymentTypes;
	}

	public Object getPaymentTypes(){
		return paymentTypes;
	}

	public void setTripKey(Object tripKey){
		this.tripKey = tripKey;
	}

	public Object getTripKey(){
		return tripKey;
	}

	public void setTripDate(String tripDate){
		this.tripDate = tripDate;
	}

	public String getTripDate(){
		return tripDate;
	}

	public void setVehicleId(Object vehicleId){
		this.vehicleId = vehicleId;
	}

	public Object getVehicleId(){
		return vehicleId;
	}

	public void setTripsNavigations(List<Object> tripsNavigations){
		this.tripsNavigations = tripsNavigations;
	}

	public List<Object> getTripsNavigations(){
		return tripsNavigations;
	}

	public void setTripScheduleDate(Object tripScheduleDate){
		this.tripScheduleDate = tripScheduleDate;
	}

	public Object getTripScheduleDate(){
		return tripScheduleDate;
	}

	public void setEmergencys(List<Object> emergencys){
		this.emergencys = emergencys;
	}

	public List<Object> getEmergencys(){
		return emergencys;
	}

	public void setTripPickupLatt(double tripPickupLatt){
		this.tripPickupLatt = tripPickupLatt;
	}

	public double getTripPickupLatt(){
		return tripPickupLatt;
	}

	public void setTripDistance(double tripDistance){
		this.tripDistance = tripDistance;
	}

	public double getTripDistance(){
		return tripDistance;
	}

	public void setVisaNumber(String visaNumber){
		this.visaNumber = visaNumber;
	}

	public String getVisaNumber(){
		return visaNumber;
	}

	public void setGoverment(Object goverment){
		this.goverment = goverment;
	}

	public Object getGoverment(){
		return goverment;
	}

	public void setTripId(int tripId){
		this.tripId = tripId;
	}

	public int getTripId(){
		return tripId;
	}

	public void setTripStatus(int tripStatus){
		this.tripStatus = tripStatus;
	}

	public int getTripStatus(){
		return tripStatus;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setHandicappedNumber(int handicappedNumber){
		this.handicappedNumber = handicappedNumber;
	}

	public int getHandicappedNumber(){
		return handicappedNumber;
	}

	public void setClientReport(List<Object> clientReport){
		this.clientReport = clientReport;
	}

	public List<Object> getClientReport(){
		return clientReport;
	}

	public void setGovermentsId(Object govermentsId){
		this.govermentsId = govermentsId;
	}

	public Object getGovermentsId(){
		return govermentsId;
	}

	public void setTripDestinationDate(Object tripDestinationDate){
		this.tripDestinationDate = tripDestinationDate;
	}

	public Object getTripDestinationDate(){
		return tripDestinationDate;
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
			"TripsResponse{" + 
			"driver_Trips_Accept_Status = '" + driverTripsAcceptStatus + '\'' + 
			",trip_Cost = '" + tripCost + '\'' + 
			",driver = '" + driver + '\'' + 
			",trip_Destination_Latt = '" + tripDestinationLatt + '\'' + 
			",trip_Destination = '" + tripDestination + '\'' + 
			",trip_Destination_Long = '" + tripDestinationLong + '\'' + 
			",trips_Rates = '" + tripsRates + '\'' + 
			",payment_Type = '" + paymentType + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			",trip_Pickup_Long = '" + tripPickupLong + '\'' + 
			",trip_Schedule_Time = '" + tripScheduleTime + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",trip_Chat = '" + tripChat + '\'' + 
			",vehicle = '" + vehicle + '\'' + 
			",trip_Destination_Time = '" + tripDestinationTime + '\'' + 
			",trips_Status = '" + tripsStatus + '\'' + 
			",client = '" + client + '\'' + 
			",trips_Types = '" + tripsTypes + '\'' + 
			",trip_Notes = '" + tripNotes + '\'' + 
			",goverments_Ratio = '" + govermentsRatio + '\'' + 
			",trip_Type_Id = '" + tripTypeId + '\'' + 
			",healthy_Number = '" + healthyNumber + '\'' + 
			",trip_Pickup = '" + tripPickup + '\'' + 
			",payment_Types = '" + paymentTypes + '\'' + 
			",trip_Key = '" + tripKey + '\'' + 
			",trip_Date = '" + tripDate + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",trips_Navigations = '" + tripsNavigations + '\'' + 
			",trip_Schedule_Date = '" + tripScheduleDate + '\'' + 
			",emergencys = '" + emergencys + '\'' + 
			",trip_Pickup_Latt = '" + tripPickupLatt + '\'' + 
			",trip_Distance = '" + tripDistance + '\'' + 
			",visa_Number = '" + visaNumber + '\'' + 
			",goverment = '" + goverment + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",trip_Status = '" + tripStatus + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",handicapped_Number = '" + handicappedNumber + '\'' + 
			",client_Report = '" + clientReport + '\'' + 
			",goverments_Id = '" + govermentsId + '\'' + 
			",trip_Destination_Date = '" + tripDestinationDate + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}