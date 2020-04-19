package design.swira.aennyapp.pojo.aenny.notifications;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class NotificationsResponse{

	@SerializedName("Notification_TimeStamp")
	private String notificationTimeStamp;

	@SerializedName("NotificationDestinations")
	private List<Object> notificationDestinations;

	@SerializedName("Notification_FromUserId")
	private int notificationFromUserId;

	@SerializedName("Notification_Description")
	private String notificationDescription;

	@SerializedName("Notification_Id")
	private int notificationId;

	@SerializedName("Notification_Title")
	private String notificationTitle;

	@SerializedName("Notification_FromUser_TypeId")
	private int notificationFromUserTypeId;

	@SerializedName("Notification_Link")
	private String notificationLink;

	@SerializedName("$id")
	private String id;

	public void setNotificationTimeStamp(String notificationTimeStamp){
		this.notificationTimeStamp = notificationTimeStamp;
	}

	public String getNotificationTimeStamp(){
		return notificationTimeStamp;
	}

	public void setNotificationDestinations(List<Object> notificationDestinations){
		this.notificationDestinations = notificationDestinations;
	}

	public List<Object> getNotificationDestinations(){
		return notificationDestinations;
	}

	public void setNotificationFromUserId(int notificationFromUserId){
		this.notificationFromUserId = notificationFromUserId;
	}

	public int getNotificationFromUserId(){
		return notificationFromUserId;
	}

	public void setNotificationDescription(String notificationDescription){
		this.notificationDescription = notificationDescription;
	}

	public String getNotificationDescription(){
		return notificationDescription;
	}

	public void setNotificationId(int notificationId){
		this.notificationId = notificationId;
	}

	public int getNotificationId(){
		return notificationId;
	}

	public void setNotificationTitle(String notificationTitle){
		this.notificationTitle = notificationTitle;
	}

	public String getNotificationTitle(){
		return notificationTitle;
	}

	public void setNotificationFromUserTypeId(int notificationFromUserTypeId){
		this.notificationFromUserTypeId = notificationFromUserTypeId;
	}

	public int getNotificationFromUserTypeId(){
		return notificationFromUserTypeId;
	}

	public void setNotificationLink(String notificationLink){
		this.notificationLink = notificationLink;
	}

	public String getNotificationLink(){
		return notificationLink;
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
			"NotificationsResponse{" + 
			"notification_TimeStamp = '" + notificationTimeStamp + '\'' + 
			",notificationDestinations = '" + notificationDestinations + '\'' + 
			",notification_FromUserId = '" + notificationFromUserId + '\'' + 
			",notification_Description = '" + notificationDescription + '\'' + 
			",notification_Id = '" + notificationId + '\'' + 
			",notification_Title = '" + notificationTitle + '\'' + 
			",notification_FromUser_TypeId = '" + notificationFromUserTypeId + '\'' + 
			",notification_Link = '" + notificationLink + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}