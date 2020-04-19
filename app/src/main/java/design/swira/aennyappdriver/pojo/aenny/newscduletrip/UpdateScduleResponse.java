package design.swira.aennyappdriver.pojo.aenny.newscduletrip;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UpdateScduleResponse{

	@SerializedName("Driver_Trips_Accept_Status")
	private List<Object> driverTripsAcceptStatus;

	@SerializedName("Trip_Normal_Distance")
	private Object tripNormalDistance;

	@SerializedName("Trip_Cost")
	private double tripCost;

	@SerializedName("IsAdditionalFees")
	private Object isAdditionalFees;

	@SerializedName("Trip_Destination")
	private String tripDestination;

	@SerializedName("Trip_Destination_Long")
	private double tripDestinationLong;

	@SerializedName("Payment_Type")
	private Object paymentType;

	@SerializedName("Total_Waiting_Minutes")
	private Object totalWaitingMinutes;

	@SerializedName("Trip_Time")
	private String tripTime;

	@SerializedName("Trip_Chat")
	private List<Object> tripChat;

	@SerializedName("Vehicle")
	private Object vehicle;

	@SerializedName("Driver_Id")
	private Object driverId;

	@SerializedName("Complaints")
	private List<Object> complaints;

	@SerializedName("Client")
	private Object client;

	@SerializedName("Trips_Types")
	private Object tripsTypes;

	@SerializedName("Goverments_Ratio")
	private Object govermentsRatio;

	@SerializedName("Trip_Key")
	private Object tripKey;

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Trip_Pickup_Latt")
	private double tripPickupLatt;

	@SerializedName("Trip_Distance")
	private double tripDistance;

	@SerializedName("Visa_Number")
	private Object visaNumber;

	@SerializedName("Trip_Status")
	private int tripStatus;

	@SerializedName("Client_Report")
	private List<Object> clientReport;

	@SerializedName("Goverments_Id")
	private Object govermentsId;

	@SerializedName("Trip_Destination_Date")
	private Object tripDestinationDate;

	@SerializedName("$id")
	private String id;

	@SerializedName("Driver")
	private Object driver;

	@SerializedName("Trip_Destination_Latt")
	private double tripDestinationLatt;

	@SerializedName("Trips_Rates")
	private List<Object> tripsRates;

	@SerializedName("Trip_Pickup_Long")
	private double tripPickupLong;

	@SerializedName("Trip_Schedule_Time")
	private Object tripScheduleTime;

	@SerializedName("Trip_Destination_Time")
	private Object tripDestinationTime;

	@SerializedName("Trips_Status")
	private Object tripsStatus;

	@SerializedName("Trip_Notes")
	private Object tripNotes;

	@SerializedName("Trip_Type_Id")
	private int tripTypeId;

	@SerializedName("Healthy_Number")
	private int healthyNumber;

	@SerializedName("Payment_Types")
	private Object paymentTypes;

	@SerializedName("Trip_Pickup")
	private String tripPickup;

	@SerializedName("Trips_Navigations")
	private List<Object> tripsNavigations;

	@SerializedName("Vehicle_Id")
	private Object vehicleId;

	@SerializedName("Trip_Schedule_Date")
	private Object tripScheduleDate;

	@SerializedName("Emergencys")
	private List<Object> emergencys;

	@SerializedName("Goverment")
	private Object goverment;

	@SerializedName("UnEmployeedDriverRatio")
	private Object unEmployeedDriverRatio;

	@SerializedName("Trip_Rash_Distance")
	private Object tripRashDistance;

	@SerializedName("Trip_Schedule_Id")
	private Object tripScheduleId;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Handicapped_Number")
	private int handicappedNumber;

	public void setDriverTripsAcceptStatus(List<Object> driverTripsAcceptStatus){
		this.driverTripsAcceptStatus = driverTripsAcceptStatus;
	}

	public List<Object> getDriverTripsAcceptStatus(){
		return driverTripsAcceptStatus;
	}

	public void setTripNormalDistance(Object tripNormalDistance){
		this.tripNormalDistance = tripNormalDistance;
	}

	public Object getTripNormalDistance(){
		return tripNormalDistance;
	}

	public void setTripCost(double tripCost){
		this.tripCost = tripCost;
	}

	public double getTripCost(){
		return tripCost;
	}

	public void setIsAdditionalFees(Object isAdditionalFees){
		this.isAdditionalFees = isAdditionalFees;
	}

	public Object getIsAdditionalFees(){
		return isAdditionalFees;
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

	public void setPaymentType(Object paymentType){
		this.paymentType = paymentType;
	}

	public Object getPaymentType(){
		return paymentType;
	}

	public void setTotalWaitingMinutes(Object totalWaitingMinutes){
		this.totalWaitingMinutes = totalWaitingMinutes;
	}

	public Object getTotalWaitingMinutes(){
		return totalWaitingMinutes;
	}

	public void setTripTime(String tripTime){
		this.tripTime = tripTime;
	}

	public String getTripTime(){
		return tripTime;
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

	public void setDriverId(Object driverId){
		this.driverId = driverId;
	}

	public Object getDriverId(){
		return driverId;
	}

	public void setComplaints(List<Object> complaints){
		this.complaints = complaints;
	}

	public List<Object> getComplaints(){
		return complaints;
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

	public void setGovermentsRatio(Object govermentsRatio){
		this.govermentsRatio = govermentsRatio;
	}

	public Object getGovermentsRatio(){
		return govermentsRatio;
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

	public void setVisaNumber(Object visaNumber){
		this.visaNumber = visaNumber;
	}

	public Object getVisaNumber(){
		return visaNumber;
	}

	public void setTripStatus(int tripStatus){
		this.tripStatus = tripStatus;
	}

	public int getTripStatus(){
		return tripStatus;
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

	public void setTripsRates(List<Object> tripsRates){
		this.tripsRates = tripsRates;
	}

	public List<Object> getTripsRates(){
		return tripsRates;
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

	public void setTripNotes(Object tripNotes){
		this.tripNotes = tripNotes;
	}

	public Object getTripNotes(){
		return tripNotes;
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

	public void setPaymentTypes(Object paymentTypes){
		this.paymentTypes = paymentTypes;
	}

	public Object getPaymentTypes(){
		return paymentTypes;
	}

	public void setTripPickup(String tripPickup){
		this.tripPickup = tripPickup;
	}

	public String getTripPickup(){
		return tripPickup;
	}

	public void setTripsNavigations(List<Object> tripsNavigations){
		this.tripsNavigations = tripsNavigations;
	}

	public List<Object> getTripsNavigations(){
		return tripsNavigations;
	}

	public void setVehicleId(Object vehicleId){
		this.vehicleId = vehicleId;
	}

	public Object getVehicleId(){
		return vehicleId;
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

	public void setGoverment(Object goverment){
		this.goverment = goverment;
	}

	public Object getGoverment(){
		return goverment;
	}

	public void setUnEmployeedDriverRatio(Object unEmployeedDriverRatio){
		this.unEmployeedDriverRatio = unEmployeedDriverRatio;
	}

	public Object getUnEmployeedDriverRatio(){
		return unEmployeedDriverRatio;
	}

	public void setTripRashDistance(Object tripRashDistance){
		this.tripRashDistance = tripRashDistance;
	}

	public Object getTripRashDistance(){
		return tripRashDistance;
	}

	public void setTripScheduleId(Object tripScheduleId){
		this.tripScheduleId = tripScheduleId;
	}

	public Object getTripScheduleId(){
		return tripScheduleId;
	}

	public void setTripId(int tripId){
		this.tripId = tripId;
	}

	public int getTripId(){
		return tripId;
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

	@Override
 	public String toString(){
		return 
			"UpdateScduleResponse{" + 
			"driver_Trips_Accept_Status = '" + driverTripsAcceptStatus + '\'' + 
			",trip_Normal_Distance = '" + tripNormalDistance + '\'' + 
			",trip_Cost = '" + tripCost + '\'' + 
			",isAdditionalFees = '" + isAdditionalFees + '\'' + 
			",trip_Destination = '" + tripDestination + '\'' + 
			",trip_Destination_Long = '" + tripDestinationLong + '\'' + 
			",payment_Type = '" + paymentType + '\'' + 
			",total_Waiting_Minutes = '" + totalWaitingMinutes + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			",trip_Chat = '" + tripChat + '\'' + 
			",vehicle = '" + vehicle + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",complaints = '" + complaints + '\'' + 
			",client = '" + client + '\'' + 
			",trips_Types = '" + tripsTypes + '\'' + 
			",goverments_Ratio = '" + govermentsRatio + '\'' + 
			",trip_Key = '" + tripKey + '\'' + 
			",trip_Date = '" + tripDate + '\'' + 
			",trip_Pickup_Latt = '" + tripPickupLatt + '\'' + 
			",trip_Distance = '" + tripDistance + '\'' + 
			",visa_Number = '" + visaNumber + '\'' + 
			",trip_Status = '" + tripStatus + '\'' + 
			",client_Report = '" + clientReport + '\'' + 
			",goverments_Id = '" + govermentsId + '\'' + 
			",trip_Destination_Date = '" + tripDestinationDate + '\'' + 
			",$id = '" + id + '\'' + 
			",driver = '" + driver + '\'' + 
			",trip_Destination_Latt = '" + tripDestinationLatt + '\'' + 
			",trips_Rates = '" + tripsRates + '\'' + 
			",trip_Pickup_Long = '" + tripPickupLong + '\'' + 
			",trip_Schedule_Time = '" + tripScheduleTime + '\'' + 
			",trip_Destination_Time = '" + tripDestinationTime + '\'' + 
			",trips_Status = '" + tripsStatus + '\'' + 
			",trip_Notes = '" + tripNotes + '\'' + 
			",trip_Type_Id = '" + tripTypeId + '\'' + 
			",healthy_Number = '" + healthyNumber + '\'' + 
			",payment_Types = '" + paymentTypes + '\'' + 
			",trip_Pickup = '" + tripPickup + '\'' + 
			",trips_Navigations = '" + tripsNavigations + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",trip_Schedule_Date = '" + tripScheduleDate + '\'' + 
			",emergencys = '" + emergencys + '\'' + 
			",goverment = '" + goverment + '\'' + 
			",unEmployeedDriverRatio = '" + unEmployeedDriverRatio + '\'' + 
			",trip_Rash_Distance = '" + tripRashDistance + '\'' + 
			",trip_Schedule_Id = '" + tripScheduleId + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",handicapped_Number = '" + handicappedNumber + '\'' + 
			"}";
		}
}