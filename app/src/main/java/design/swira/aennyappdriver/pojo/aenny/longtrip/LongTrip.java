package design.swira.aennyappdriver.pojo.aenny.longtrip;


import com.google.gson.annotations.SerializedName;


public class LongTrip{

	@SerializedName("LongTrip_EndDate")
	private String longTripEndDate;

	@SerializedName("LongTrip_Days_Count")
	private int longTripDaysCount;

	@SerializedName("LongTrip_id")
	private int longTripId;

	@SerializedName("Client_Mobile")
	private String clientMobile;

	@SerializedName("LongTrip_StartDate")
	private String longTripStartDate;

	@SerializedName("LongTrip_Description")
	private String longTripDescription;

	@SerializedName("LongTrip_Notes")
	private String longTripNotes;

	@SerializedName("Client_Id")
	private int clientId;

	public void setLongTripEndDate(String longTripEndDate){
		this.longTripEndDate = longTripEndDate;
	}

	public String getLongTripEndDate(){
		return longTripEndDate;
	}

	public void setLongTripDaysCount(int longTripDaysCount){
		this.longTripDaysCount = longTripDaysCount;
	}

	public int getLongTripDaysCount(){
		return longTripDaysCount;
	}

	public void setLongTripId(int longTripId){
		this.longTripId = longTripId;
	}

	public int getLongTripId(){
		return longTripId;
	}

	public void setClientMobile(String clientMobile){
		this.clientMobile = clientMobile;
	}

	public String getClientMobile(){
		return clientMobile;
	}

	public void setLongTripStartDate(String longTripStartDate){
		this.longTripStartDate = longTripStartDate;
	}

	public String getLongTripStartDate(){
		return longTripStartDate;
	}

	public void setLongTripDescription(String longTripDescription){
		this.longTripDescription = longTripDescription;
	}

	public String getLongTripDescription(){
		return longTripDescription;
	}

	public void setLongTripNotes(String longTripNotes){
		this.longTripNotes = longTripNotes;
	}

	public String getLongTripNotes(){
		return longTripNotes;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"LongTrip{" + 
			"longTrip_EndDate = '" + longTripEndDate + '\'' + 
			",longTrip_Days_Count = '" + longTripDaysCount + '\'' + 
			",longTrip_id = '" + longTripId + '\'' + 
			",client_Mobile = '" + clientMobile + '\'' + 
			",longTrip_StartDate = '" + longTripStartDate + '\'' + 
			",longTrip_Description = '" + longTripDescription + '\'' + 
			",longTrip_Notes = '" + longTripNotes + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			"}";
		}

	public LongTrip(String longTripEndDate, String clientMobile, String longTripStartDate, String longTripDescription, int clientId) {
		this.longTripEndDate = longTripEndDate;
		this.clientMobile = clientMobile;
		this.longTripStartDate = longTripStartDate;
		this.longTripDescription = longTripDescription;
		this.clientId = clientId;
	}
}