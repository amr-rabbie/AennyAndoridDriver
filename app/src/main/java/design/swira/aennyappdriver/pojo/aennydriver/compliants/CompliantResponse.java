package design.swira.aennyappdriver.pojo.aennydriver.compliants;


import com.google.gson.annotations.SerializedName;


public class CompliantResponse {

	@SerializedName("Created_User_Id")
	private int createdUserId;

	@SerializedName("Trip")
	private Object trip;

	@SerializedName("Complaint_Id")
	private int complaintId;

	@SerializedName("Complaint_DateTime")
	private String complaintDateTime;

	@SerializedName("UserType_Name")
	private Object userTypeName;

	@SerializedName("Trip_Id")
	private int tripId;

	@SerializedName("$id")
	private String id;

	@SerializedName("Complaint_Title")
	private String complaintTitle;

	@SerializedName("Complaint_Description")
	private String complaintDescription;

	@SerializedName("Created_UserType_Id")
	private int createdUserTypeId;

	public void setCreatedUserId(int createdUserId){
		this.createdUserId = createdUserId;
	}

	public int getCreatedUserId(){
		return createdUserId;
	}

	public void setTrip(Object trip){
		this.trip = trip;
	}

	public Object getTrip(){
		return trip;
	}

	public void setComplaintId(int complaintId){
		this.complaintId = complaintId;
	}

	public int getComplaintId(){
		return complaintId;
	}

	public void setComplaintDateTime(String complaintDateTime){
		this.complaintDateTime = complaintDateTime;
	}

	public String getComplaintDateTime(){
		return complaintDateTime;
	}

	public void setUserTypeName(Object userTypeName){
		this.userTypeName = userTypeName;
	}

	public Object getUserTypeName(){
		return userTypeName;
	}

	public void setTripId(int tripId){
		this.tripId = tripId;
	}

	public int getTripId(){
		return tripId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setComplaintTitle(String complaintTitle){
		this.complaintTitle = complaintTitle;
	}

	public String getComplaintTitle(){
		return complaintTitle;
	}

	public void setComplaintDescription(String complaintDescription){
		this.complaintDescription = complaintDescription;
	}

	public String getComplaintDescription(){
		return complaintDescription;
	}

	public void setCreatedUserTypeId(int createdUserTypeId){
		this.createdUserTypeId = createdUserTypeId;
	}

	public int getCreatedUserTypeId(){
		return createdUserTypeId;
	}

	@Override
 	public String toString(){
		return 
			"CompliantResponse{" + 
			"created_User_Id = '" + createdUserId + '\'' + 
			",trip = '" + trip + '\'' + 
			",complaint_Id = '" + complaintId + '\'' + 
			",complaint_DateTime = '" + complaintDateTime + '\'' + 
			",userType_Name = '" + userTypeName + '\'' + 
			",trip_Id = '" + tripId + '\'' + 
			",$id = '" + id + '\'' + 
			",complaint_Title = '" + complaintTitle + '\'' + 
			",complaint_Description = '" + complaintDescription + '\'' + 
			",created_UserType_Id = '" + createdUserTypeId + '\'' + 
			"}";
		}

	public CompliantResponse(int createdUserId, String complaintDateTime, int tripId, String complaintTitle, String complaintDescription, int createdUserTypeId) {
		this.createdUserId = createdUserId;
		this.complaintDateTime = complaintDateTime;
		this.tripId = tripId;
		this.complaintTitle = complaintTitle;
		this.complaintDescription = complaintDescription;
		this.createdUserTypeId = createdUserTypeId;
	}
}