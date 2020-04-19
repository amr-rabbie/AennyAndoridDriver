package design.swira.aennyapp.pojo.aenny.clients;


import com.google.gson.annotations.SerializedName;


public class Client{

	@SerializedName("Client_Email")
	private String clientEmail;

	@SerializedName("Client_Name")
	private String clientName;

	@SerializedName("Client_Mobile")
	private String clientMobile;

	@SerializedName("Client_JoinDate")
	private String clientJoinDate;

	@SerializedName("Client_Notes")
	private String clientNotes;

	@SerializedName("Client_City")
	private int clientCity;

	@SerializedName("Client_Key")
	private String clientKey;

	@SerializedName("Client_Password")
	private String clientPassword;

	@SerializedName("Client_BirthDate")
	private String clientBirthDate;

	@SerializedName("Client_Relative_Mobile")
	private String clientRelativeMobile;

	@SerializedName("Client_Area")
	private int clientArea;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client_Relative_Name")
	private String clientRelativeName;

	@SerializedName("Client_Gender")
	private int clientGender;

	@SerializedName("Use_App_Disabled")
	private int useAppDisabled;

	public void setClientEmail(String clientEmail){
		this.clientEmail = clientEmail;
	}

	public String getClientEmail(){
		return clientEmail;
	}

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientMobile(String clientMobile){
		this.clientMobile = clientMobile;
	}

	public String getClientMobile(){
		return clientMobile;
	}

	public void setClientJoinDate(String clientJoinDate){
		this.clientJoinDate = clientJoinDate;
	}

	public String getClientJoinDate(){
		return clientJoinDate;
	}

	public void setClientNotes(String clientNotes){
		this.clientNotes = clientNotes;
	}

	public String getClientNotes(){
		return clientNotes;
	}

	public void setClientCity(int clientCity){
		this.clientCity = clientCity;
	}

	public int getClientCity(){
		return clientCity;
	}

	public void setClientKey(String clientKey){
		this.clientKey = clientKey;
	}

	public String getClientKey(){
		return clientKey;
	}

	public void setClientPassword(String clientPassword){
		this.clientPassword = clientPassword;
	}

	public String getClientPassword(){
		return clientPassword;
	}

	public void setClientBirthDate(String clientBirthDate){
		this.clientBirthDate = clientBirthDate;
	}

	public String getClientBirthDate(){
		return clientBirthDate;
	}

	public void setClientRelativeMobile(String clientRelativeMobile){
		this.clientRelativeMobile = clientRelativeMobile;
	}

	public String getClientRelativeMobile(){
		return clientRelativeMobile;
	}

	public void setClientArea(int clientArea){
		this.clientArea = clientArea;
	}

	public int getClientArea(){
		return clientArea;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setClientRelativeName(String clientRelativeName){
		this.clientRelativeName = clientRelativeName;
	}

	public String getClientRelativeName(){
		return clientRelativeName;
	}

	public void setClientGender(int clientGender){
		this.clientGender = clientGender;
	}

	public int getClientGender(){
		return clientGender;
	}

	public void setUseAppDisabled(int useAppDisabled){
		this.useAppDisabled = useAppDisabled;
	}

	public int getUseAppDisabled(){
		return useAppDisabled;
	}

	@Override
 	public String toString(){
		return 
			"Client{" + 
			"client_Email = '" + clientEmail + '\'' + 
			",client_Name = '" + clientName + '\'' + 
			",client_Mobile = '" + clientMobile + '\'' + 
			",client_JoinDate = '" + clientJoinDate + '\'' + 
			",client_Notes = '" + clientNotes + '\'' + 
			",client_City = '" + clientCity + '\'' + 
			",client_Key = '" + clientKey + '\'' + 
			",client_Password = '" + clientPassword + '\'' + 
			",client_BirthDate = '" + clientBirthDate + '\'' + 
			",client_Relative_Mobile = '" + clientRelativeMobile + '\'' + 
			",client_Area = '" + clientArea + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client_Relative_Name = '" + clientRelativeName + '\'' + 
			",client_Gender = '" + clientGender + '\'' + 
			",use_App_Disabled = '" + useAppDisabled + '\'' + 
			"}";
		}

	public Client(String clientName, String clientMobile, String clientPassword) {
		this.clientName = clientName;
		this.clientMobile = clientMobile;
		this.clientPassword = clientPassword;
	}

	public Client(String clientEmail, String clientName, String clientMobile, int clientCity, String clientPassword, String clientBirthDate, String clientRelativeMobile, int clientArea, int clientGender, int useAppDisabled) {
		this.clientEmail = clientEmail;
		this.clientName = clientName;
		this.clientMobile = clientMobile;
		//this.clientJoinDate = clientJoinDate;
		//this.clientNotes = clientNotes;
		this.clientCity = clientCity;
		//this.clientKey = clientKey;
		this.clientPassword = clientPassword;
		this.clientBirthDate = clientBirthDate;
		this.clientRelativeMobile = clientRelativeMobile;
		this.clientArea = clientArea;
		//this.clientRelativeName = clientRelativeName;
		this.clientGender = clientGender;
		this.useAppDisabled = useAppDisabled;
	}

	public Client(int clientId,String clientEmail, String clientName, String clientMobile, int clientCity, String clientPassword, String clientBirthDate, String clientRelativeMobile, int clientArea, int clientGender, int useAppDisabled) {
		this.clientEmail = clientEmail;
		this.clientName = clientName;
		this.clientMobile = clientMobile;
		//this.clientJoinDate = clientJoinDate;
		//this.clientNotes = clientNotes;
		this.clientCity = clientCity;
		//this.clientKey = clientKey;
		this.clientPassword = clientPassword;
		this.clientBirthDate = clientBirthDate;
		this.clientRelativeMobile = clientRelativeMobile;
		this.clientArea = clientArea;
		//this.clientRelativeName = clientRelativeName;
		this.clientGender = clientGender;
		this.useAppDisabled = useAppDisabled;
		this.clientId=clientId;
	}

	public Client(int clientCity, int clientArea, int clientGender) {
		this.clientCity = clientCity;
		this.clientArea = clientArea;
		this.clientGender = clientGender;
	}
}