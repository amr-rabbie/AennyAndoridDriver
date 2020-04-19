package design.swira.aennyappdriver.pojo.aennydriver.driverstatics;


import com.google.gson.annotations.SerializedName;


public class DriverStaticsResponse{

	@SerializedName("TripsTotalNumber")
	private int tripsTotalNumber;

	@SerializedName("TripsTotalKM")
	private String tripsTotalKM;

	@SerializedName("TripsTotalFees")
	private String tripsTotalFees;

	@SerializedName("TripsTotalHours")
	private int tripsTotalHours;

	@SerializedName("$id")
	private String id;

	public void setTripsTotalNumber(int tripsTotalNumber){
		this.tripsTotalNumber = tripsTotalNumber;
	}

	public int getTripsTotalNumber(){
		return tripsTotalNumber;
	}

	public void setTripsTotalKM(String tripsTotalKM){
		this.tripsTotalKM = tripsTotalKM;
	}

	public String getTripsTotalKM(){
		return tripsTotalKM;
	}

	public void setTripsTotalFees(String tripsTotalFees){
		this.tripsTotalFees = tripsTotalFees;
	}

	public String getTripsTotalFees(){
		return tripsTotalFees;
	}

	public void setTripsTotalHours(int tripsTotalHours){
		this.tripsTotalHours = tripsTotalHours;
	}

	public int getTripsTotalHours(){
		return tripsTotalHours;
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
			"DriverStaticsResponse{" + 
			"tripsTotalNumber = '" + tripsTotalNumber + '\'' + 
			",tripsTotalKM = '" + tripsTotalKM + '\'' + 
			",tripsTotalFees = '" + tripsTotalFees + '\'' + 
			",tripsTotalHours = '" + tripsTotalHours + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}