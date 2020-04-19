package design.swira.aennyapp.pojo.aenny.newscduletrip;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class AddScduleTrip{

	@SerializedName("Trip_Cost")
	private Double tripCost;

	@SerializedName("Trip_Type_Id")
	private int tripTypeId;

	@SerializedName("Healthy_Number")
	private int healthyNumber;

	@SerializedName("Trip_Pickup")
	private String tripPickup;

	@SerializedName("Trip_Destination_Latt")
	private Double tripDestinationLatt;

	@SerializedName("Trip_Destination")
	private String tripDestination;

	@SerializedName("Trip_Destination_Long")
	private Double tripDestinationLong;

	@SerializedName("Payment_Type")
	private int paymentType;

	@SerializedName("Trip_Pickup_Long")
	private Double tripPickupLong;

	@SerializedName("Trip_Pickup_Latt")
	private Double tripPickupLatt;

	@SerializedName("Trip_Distance")
	private Double tripDistance;

	@SerializedName("Visa_Number")
	private String visaNumber;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("Trip_Status")
	private int tripStatus;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Handicapped_Number")
	private int handicappedNumber;

	@SerializedName("TripDateTimeList")
	private List<TripDateTimeListItem> tripDateTimeList;

	public void setTripCost(Double tripCost){
		this.tripCost = tripCost;
	}

	public Double getTripCost(){
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

	public void setTripDestinationLatt(Double tripDestinationLatt){
		this.tripDestinationLatt = tripDestinationLatt;
	}

	public Double getTripDestinationLatt(){
		return tripDestinationLatt;
	}

	public void setTripDestination(String tripDestination){
		this.tripDestination = tripDestination;
	}

	public String getTripDestination(){
		return tripDestination;
	}

	public void setTripDestinationLong(Double tripDestinationLong){
		this.tripDestinationLong = tripDestinationLong;
	}

	public Double getTripDestinationLong(){
		return tripDestinationLong;
	}

	public void setPaymentType(int paymentType){
		this.paymentType = paymentType;
	}

	public int getPaymentType(){
		return paymentType;
	}

	public void setTripPickupLong(Double tripPickupLong){
		this.tripPickupLong = tripPickupLong;
	}

	public Double getTripPickupLong(){
		return tripPickupLong;
	}

	public void setTripPickupLatt(Double tripPickupLatt){
		this.tripPickupLatt = tripPickupLatt;
	}

	public Double getTripPickupLatt(){
		return tripPickupLatt;
	}

	public void setTripDistance(Double tripDistance){
		this.tripDistance = tripDistance;
	}

	public Double getTripDistance(){
		return tripDistance;
	}

	public void setVisaNumber(String visaNumber){
		this.visaNumber = visaNumber;
	}

	public String getVisaNumber(){
		return visaNumber;
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

	public void setTripDateTimeList(List<TripDateTimeListItem> tripDateTimeList){
		this.tripDateTimeList = tripDateTimeList;
	}

	public List<TripDateTimeListItem> getTripDateTimeList(){
		return tripDateTimeList;
	}

	@Override
 	public String toString(){
		return 
			"AddScduleTrip{" + 
			"trip_Cost = '" + tripCost + '\'' + 
			",trip_Type_Id = '" + tripTypeId + '\'' + 
			",healthy_Number = '" + healthyNumber + '\'' + 
			",trip_Pickup = '" + tripPickup + '\'' + 
			",trip_Destination_Latt = '" + tripDestinationLatt + '\'' + 
			",trip_Destination = '" + tripDestination + '\'' + 
			",trip_Destination_Long = '" + tripDestinationLong + '\'' + 
			",payment_Type = '" + paymentType + '\'' + 
			",trip_Pickup_Long = '" + tripPickupLong + '\'' + 
			",trip_Pickup_Latt = '" + tripPickupLatt + '\'' + 
			",trip_Distance = '" + tripDistance + '\'' + 
			",visa_Number = '" + visaNumber + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",trip_Status = '" + tripStatus + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",handicapped_Number = '" + handicappedNumber + '\'' + 
			",tripDateTimeList = '" + tripDateTimeList + '\'' + 
			"}";
		}

	public AddScduleTrip(Double tripCost, int tripTypeId, int healthyNumber, String tripPickup, Double tripDestinationLatt, String tripDestination, Double tripDestinationLong, int paymentType, Double tripPickupLong, Double tripPickupLatt, Double tripDistance, String visaNumber, int tripId, int tripStatus, int clientId, int handicappedNumber, List<TripDateTimeListItem> tripDateTimeList) {
		this.tripCost = tripCost;
		this.tripTypeId = tripTypeId;
		this.healthyNumber = healthyNumber;
		this.tripPickup = tripPickup;
		this.tripDestinationLatt = tripDestinationLatt;
		this.tripDestination = tripDestination;
		this.tripDestinationLong = tripDestinationLong;
		this.paymentType = paymentType;
		this.tripPickupLong = tripPickupLong;
		this.tripPickupLatt = tripPickupLatt;
		this.tripDistance = tripDistance;
		this.visaNumber = visaNumber;
		this.tripId = tripId;
		this.tripStatus = tripStatus;
		this.clientId = clientId;
		this.handicappedNumber = handicappedNumber;
		this.tripDateTimeList = tripDateTimeList;
	}
}