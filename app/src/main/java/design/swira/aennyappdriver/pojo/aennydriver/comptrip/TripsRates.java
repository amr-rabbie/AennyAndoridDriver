package design.swira.aennyappdriver.pojo.aennydriver.comptrip;


import com.google.gson.annotations.SerializedName;


public class TripsRates{

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("Trip_Rate_Comment")
	private String tripRateComment;

	@SerializedName("Trip_Rate_Id")
	private int tripRateId;

	@SerializedName("Driver_Rate")
	private int driverRate;

	@SerializedName("Trip_Rate_Notes")
	private String tripRateNotes;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("Client_id")
	private int clientId;

	@SerializedName("Client_Rate")
	private int clientRate;

	@SerializedName("Vehicle_ID")
	private int vehicleID;

	@SerializedName("Vehicle_Rate")
	private int vehicleRate;

	@SerializedName("$id")
	private String id;

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
	}

	public void setTripRateComment(String tripRateComment){
		this.tripRateComment = tripRateComment;
	}

	public String getTripRateComment(){
		return tripRateComment;
	}

	public void setTripRateId(int tripRateId){
		this.tripRateId = tripRateId;
	}

	public int getTripRateId(){
		return tripRateId;
	}

	public void setDriverRate(int driverRate){
		this.driverRate = driverRate;
	}

	public int getDriverRate(){
		return driverRate;
	}

	public void setTripRateNotes(String tripRateNotes){
		this.tripRateNotes = tripRateNotes;
	}

	public String getTripRateNotes(){
		return tripRateNotes;
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

	public void setClientRate(int clientRate){
		this.clientRate = clientRate;
	}

	public int getClientRate(){
		return clientRate;
	}

	public void setVehicleID(int vehicleID){
		this.vehicleID = vehicleID;
	}

	public int getVehicleID(){
		return vehicleID;
	}

	public void setVehicleRate(int vehicleRate){
		this.vehicleRate = vehicleRate;
	}

	public int getVehicleRate(){
		return vehicleRate;
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
			"TripsRates{" + 
			"driver_Id = '" + driverId + '\'' + 
			",trip_Rate_Comment = '" + tripRateComment + '\'' + 
			",trip_Rate_Id = '" + tripRateId + '\'' + 
			",driver_Rate = '" + driverRate + '\'' + 
			",trip_Rate_Notes = '" + tripRateNotes + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",client_id = '" + clientId + '\'' + 
			",client_Rate = '" + clientRate + '\'' + 
			",vehicle_ID = '" + vehicleID + '\'' + 
			",vehicle_Rate = '" + vehicleRate + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}