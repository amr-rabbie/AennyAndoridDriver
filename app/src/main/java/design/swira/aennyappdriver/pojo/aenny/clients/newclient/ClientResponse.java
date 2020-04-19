package design.swira.aennyappdriver.pojo.aenny.clients.newclient;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ClientResponse{

	@SerializedName("Client_Name")
	private String clientName;

	@SerializedName("Client_Mobile")
	private String clientMobile;

	@SerializedName("Client_Notes")
	private Object clientNotes;

	@SerializedName("Gender_Name")
	private Object genderName;

	@SerializedName("editmsg")
	private Object editmsg;

	@SerializedName("Client_DisabilityType")
	private Object clientDisabilityType;

	@SerializedName("Client_Relative_Name")
	private Object clientRelativeName;

	@SerializedName("Area_Name")
	private Object areaName;

	@SerializedName("Client_GovermentsId")
	private Object clientGovermentsId;

	@SerializedName("Client_Email")
	private String clientEmail;

	@SerializedName("IsDeleted")
	private Object isDeleted;

	@SerializedName("Client_JoinDate")
	private String clientJoinDate;

	@SerializedName("Client_City")
	private Object clientCity;

	@SerializedName("Client_NoOfTrips")
	private int clientNoOfTrips;

	@SerializedName("Client_BirthDate")
	private String clientBirthDate;

	@SerializedName("Client_Relative_Mobile")
	private Object clientRelativeMobile;

	@SerializedName("City_Name")
	private Object cityName;

	@SerializedName("Client_AvgRate")
	private int clientAvgRate;

	@SerializedName("addmsg")
	private Object addmsg;

	@SerializedName("Client_Area")
	private Object clientArea;

	@SerializedName("ClientDisabilities")
	private List<Object> clientDisabilities;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client_Gender")
	private Object clientGender;

	@SerializedName("Use_App_Disabled")
	private Object useAppDisabled;

	@SerializedName("Goverments_Name")
	private Object govermentsName;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMobile() {
		return clientMobile;
	}

	public void setClientMobile(String clientMobile) {
		this.clientMobile = clientMobile;
	}

	public Object getClientNotes() {
		return clientNotes;
	}

	public void setClientNotes(Object clientNotes) {
		this.clientNotes = clientNotes;
	}

	public Object getGenderName() {
		return genderName;
	}

	public void setGenderName(Object genderName) {
		this.genderName = genderName;
	}

	public Object getEditmsg() {
		return editmsg;
	}

	public void setEditmsg(Object editmsg) {
		this.editmsg = editmsg;
	}

	public Object getClientDisabilityType() {
		return clientDisabilityType;
	}

	public void setClientDisabilityType(Object clientDisabilityType) {
		this.clientDisabilityType = clientDisabilityType;
	}

	public Object getClientRelativeName() {
		return clientRelativeName;
	}

	public void setClientRelativeName(Object clientRelativeName) {
		this.clientRelativeName = clientRelativeName;
	}

	public Object getAreaName() {
		return areaName;
	}

	public void setAreaName(Object areaName) {
		this.areaName = areaName;
	}

	public Object getClientGovermentsId() {
		return clientGovermentsId;
	}

	public void setClientGovermentsId(Object clientGovermentsId) {
		this.clientGovermentsId = clientGovermentsId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Object getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Object isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getClientJoinDate() {
		return clientJoinDate;
	}

	public void setClientJoinDate(String clientJoinDate) {
		this.clientJoinDate = clientJoinDate;
	}

	public Object getClientCity() {
		return clientCity;
	}

	public void setClientCity(Object clientCity) {
		this.clientCity = clientCity;
	}

	public int getClientNoOfTrips() {
		return clientNoOfTrips;
	}

	public void setClientNoOfTrips(int clientNoOfTrips) {
		this.clientNoOfTrips = clientNoOfTrips;
	}

	public String getClientBirthDate() {
		return clientBirthDate;
	}

	public void setClientBirthDate(String clientBirthDate) {
		this.clientBirthDate = clientBirthDate;
	}

	public Object getClientRelativeMobile() {
		return clientRelativeMobile;
	}

	public void setClientRelativeMobile(Object clientRelativeMobile) {
		this.clientRelativeMobile = clientRelativeMobile;
	}

	public Object getCityName() {
		return cityName;
	}

	public void setCityName(Object cityName) {
		this.cityName = cityName;
	}

	public int getClientAvgRate() {
		return clientAvgRate;
	}

	public void setClientAvgRate(int clientAvgRate) {
		this.clientAvgRate = clientAvgRate;
	}

	public Object getAddmsg() {
		return addmsg;
	}

	public void setAddmsg(Object addmsg) {
		this.addmsg = addmsg;
	}

	public Object getClientArea() {
		return clientArea;
	}

	public void setClientArea(Object clientArea) {
		this.clientArea = clientArea;
	}

	public List<Object> getClientDisabilities() {
		return clientDisabilities;
	}

	public void setClientDisabilities(List<Object> clientDisabilities) {
		this.clientDisabilities = clientDisabilities;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Object getClientGender() {
		return clientGender;
	}

	public void setClientGender(Object clientGender) {
		this.clientGender = clientGender;
	}

	public Object getUseAppDisabled() {
		return useAppDisabled;
	}

	public void setUseAppDisabled(Object useAppDisabled) {
		this.useAppDisabled = useAppDisabled;
	}

	public Object getGovermentsName() {
		return govermentsName;
	}

	public void setGovermentsName(Object govermentsName) {
		this.govermentsName = govermentsName;
	}

	@Override
	public String toString() {
		return "ClientResponse{" +
				"clientName='" + clientName + '\'' +
				", clientMobile='" + clientMobile + '\'' +
				", clientNotes=" + clientNotes +
				", genderName=" + genderName +
				", editmsg=" + editmsg +
				", clientDisabilityType=" + clientDisabilityType +
				", clientRelativeName=" + clientRelativeName +
				", areaName=" + areaName +
				", clientGovermentsId=" + clientGovermentsId +
				", clientEmail='" + clientEmail + '\'' +
				", isDeleted=" + isDeleted +
				", clientJoinDate='" + clientJoinDate + '\'' +
				", clientCity=" + clientCity +
				", clientNoOfTrips=" + clientNoOfTrips +
				", clientBirthDate='" + clientBirthDate + '\'' +
				", clientRelativeMobile=" + clientRelativeMobile +
				", cityName=" + cityName +
				", clientAvgRate=" + clientAvgRate +
				", addmsg=" + addmsg +
				", clientArea=" + clientArea +
				", clientDisabilities=" + clientDisabilities +
				", clientId=" + clientId +
				", clientGender=" + clientGender +
				", useAppDisabled=" + useAppDisabled +
				", govermentsName=" + govermentsName +
				'}';
	}
}