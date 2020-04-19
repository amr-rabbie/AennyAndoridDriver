package design.swira.aennyapp.pojo.aenny.trips;


import com.google.gson.annotations.SerializedName;


public class TripsResponse{

	@SerializedName("Trip_Notes")
	private String tripNotes;

	@SerializedName("Trip_Cost")
	private double tripCost;

	@SerializedName("Trip_Type_Id")
	private int tripTypeId;

	@SerializedName("Healthy_Number")
	private int healthyNumber;

	@SerializedName("Trip_Pickup")
	private String tripPickup;

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Vehicle_Id")
	private int vehicleId;

	@SerializedName("Trip_Destination_Latt")
	private double tripDestinationLatt;

	@SerializedName("Trip_Destination")
	private String tripDestination;

	@SerializedName("Trip_Destination_Long")
	private double tripDestinationLong;

	@SerializedName("Payment_Type")
	private int paymentType;

	@SerializedName("Trip_Time")
	private String tripTime;

	@SerializedName("Trip_Pickup_Long")
	private double tripPickupLong;

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("Trip_Pickup_Latt")
	private double tripPickupLatt;

	@SerializedName("Trip_Distance")
	private double tripDistance;

	@SerializedName("Visa_Number")
	private String visaNumber;

	@SerializedName("Trip_Status")
	private int tripStatus;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Handicapped_Number")
	private int handicappedNumber;

	@SerializedName("Goverments_Id")
	private int govermentsId;

	public void setTripNotes(String tripNotes){
		this.tripNotes = tripNotes;
	}

	public String getTripNotes(){
		return tripNotes;
	}

	public void setTripCost(double tripCost){
		this.tripCost = tripCost;
	}

	public double getTripCost(){
		return tripCost;
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

	public void setTripDate(String tripDate){
		this.tripDate = tripDate;
	}

	public String getTripDate(){
		return tripDate;
	}

	public void setVehicleId(int vehicleId){
		this.vehicleId = vehicleId;
	}

	public int getVehicleId(){
		return vehicleId;
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

	public void setPaymentType(int paymentType){
		this.paymentType = paymentType;
	}

	public int getPaymentType(){
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

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
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

	public void setGovermentsId(int govermentsId){
		this.govermentsId = govermentsId;
	}

	public int getGovermentsId(){
		return govermentsId;
	}

	@Override
 	public String toString(){
		return 
			"TripsResponse{" + 
			"trip_Notes = '" + tripNotes + '\'' + 
			",trip_Cost = '" + tripCost + '\'' + 
			",trip_Type_Id = '" + tripTypeId + '\'' + 
			",healthy_Number = '" + healthyNumber + '\'' + 
			",trip_Pickup = '" + tripPickup + '\'' + 
			",trip_Date = '" + tripDate + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",trip_Destination_Latt = '" + tripDestinationLatt + '\'' + 
			",trip_Destination = '" + tripDestination + '\'' + 
			",trip_Destination_Long = '" + tripDestinationLong + '\'' + 
			",payment_Type = '" + paymentType + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			",trip_Pickup_Long = '" + tripPickupLong + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",trip_Pickup_Latt = '" + tripPickupLatt + '\'' + 
			",trip_Distance = '" + tripDistance + '\'' + 
			",visa_Number = '" + visaNumber + '\'' + 
			",trip_Status = '" + tripStatus + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",handicapped_Number = '" + handicappedNumber + '\'' + 
			",goverments_Id = '" + govermentsId + '\'' + 
			"}";
		}

   /* public TripsResponse(String tripNotes, double tripCost, int tripTypeId, int healthyNumber, String tripPickup, String tripDate, int vehicleId, double tripDestinationLatt, String tripDestination, double tripDestinationLong, int paymentType, String tripTime, double tripPickupLong, int driverId, double tripPickupLatt, double tripDistance, String visaNumber, int tripStatus, int clientId, int handicappedNumber, int govermentsId) {
        this.tripNotes = tripNotes;
        this.tripCost = tripCost;
        this.tripTypeId = tripTypeId;
        this.healthyNumber = healthyNumber;
        this.tripPickup = tripPickup;
        this.tripDate = tripDate;
        this.vehicleId = vehicleId;
        this.tripDestinationLatt = tripDestinationLatt;
        this.tripDestination = tripDestination;
        this.tripDestinationLong = tripDestinationLong;
        this.paymentType = paymentType;
        this.tripTime = tripTime;
        this.tripPickupLong = tripPickupLong;
        this.driverId = driverId;
        this.tripPickupLatt = tripPickupLatt;
        this.tripDistance = tripDistance;
        this.visaNumber = visaNumber;
        this.tripStatus = tripStatus;
        this.clientId = clientId;
        this.handicappedNumber = handicappedNumber;
        this.govermentsId = govermentsId;
    }*/
}