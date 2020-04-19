package design.swira.aennyappdriver.pojo.aennydriver.chat;

import com.google.gson.annotations.SerializedName;

public class ChatResponse {

	@SerializedName("Driver_Id")
	private Object driverId;

	@SerializedName("Trip")
	private Object trip;

	@SerializedName("Chat_Id")
	private int chatId;

	@SerializedName("Message")
	private String message;

	@SerializedName("Driver")
	private Object driver;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client")
	private Object client;

	@SerializedName("UserType")
	private int userType;

	@SerializedName("TimeStamp")
	private String timeStamp;

	@SerializedName("$id")
	private String id;

	public void setDriverId(Object driverId){
		this.driverId = driverId;
	}

	public Object getDriverId(){
		return driverId;
	}

	public void setTrip(Object trip){
		this.trip = trip;
	}

	public Object getTrip(){
		return trip;
	}

	public void setChatId(int chatId){
		this.chatId = chatId;
	}

	public int getChatId(){
		return chatId;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setDriver(Object driver){
		this.driver = driver;
	}

	public Object getDriver(){
		return driver;
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

	public void setClient(Object client){
		this.client = client;
	}

	public Object getClient(){
		return client;
	}

	public void setUserType(int userType){
		this.userType = userType;
	}

	public int getUserType(){
		return userType;
	}

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
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
			"ChatResponse{" + 
			"driver_Id = '" + driverId + '\'' + 
			",trip = '" + trip + '\'' + 
			",chat_Id = '" + chatId + '\'' + 
			",message = '" + message + '\'' + 
			",driver = '" + driver + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client = '" + client + '\'' + 
			",userType = '" + userType + '\'' + 
			",timeStamp = '" + timeStamp + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}