package design.swira.aennyappdriver.pojo.aenny.newscduletrip;


import com.google.gson.annotations.SerializedName;


public class TripDateTimeListItem{

	@SerializedName("Trip_Date")
	private String tripDate;

	@SerializedName("Trip_Time")
	private String tripTime;

	public void setTripDate(String tripDate){
		this.tripDate = tripDate;
	}

	public String getTripDate(){
		return tripDate;
	}

	public void setTripTime(String tripTime){
		this.tripTime = tripTime;
	}

	public String getTripTime(){
		return tripTime;
	}

	@Override
 	public String toString(){
		return 
			"TripDateTimeListItem{" + 
			"trip_Date = '" + tripDate + '\'' + 
			",trip_Time = '" + tripTime + '\'' + 
			"}";
		}

	public TripDateTimeListItem(String tripDate, String tripTime) {
		this.tripDate = tripDate;
		this.tripTime = tripTime;
	}
}