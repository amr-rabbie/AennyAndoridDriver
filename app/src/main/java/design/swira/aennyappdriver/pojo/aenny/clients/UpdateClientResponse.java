package design.swira.aennyappdriver.pojo.aenny.clients;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UpdateClientResponse{

	@SerializedName("Client_Name")
	private String clientName;

	@SerializedName("Client_Mobile")
	private String clientMobile;

	@SerializedName("Client_City")
	private int clientCity;

	@SerializedName("Client_Area")
	private int clientArea;

	@SerializedName("Client_BirthDate")
	private String clientBirthDate;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client_Gender")
	private int clientGender;

	@SerializedName("Client_Relative_Mobile")
	private String clientRelativeMobile;

	@SerializedName("Client_Disabilities")
	private List<ClientDisabilitiesItem> clientDisabilities;

	@SerializedName("Use_App_Disabled")
	private int useAppDisabled;

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

	public void setClientCity(int clientCity){
		this.clientCity = clientCity;
	}

	public int getClientCity(){
		return clientCity;
	}

	public void setClientArea(int clientArea){
		this.clientArea = clientArea;
	}

	public int getClientArea(){
		return clientArea;
	}

	public void setClientBirthDate(String clientBirthDate){
		this.clientBirthDate = clientBirthDate;
	}

	public String getClientBirthDate(){
		return clientBirthDate;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setClientGender(int clientGender){
		this.clientGender = clientGender;
	}

	public int getClientGender(){
		return clientGender;
	}

	public void setClientRelativeMobile(String clientRelativeMobile){
		this.clientRelativeMobile = clientRelativeMobile;
	}

	public String getClientRelativeMobile(){
		return clientRelativeMobile;
	}

	public void setClientDisabilities(List<ClientDisabilitiesItem> clientDisabilities){
		this.clientDisabilities = clientDisabilities;
	}

	public List<ClientDisabilitiesItem> getClientDisabilities(){
		return clientDisabilities;
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
			"UpdateClientResponse{" + 
			"client_Name = '" + clientName + '\'' + 
			",client_Mobile = '" + clientMobile + '\'' + 
			",client_City = '" + clientCity + '\'' + 
			",client_Area = '" + clientArea + '\'' + 
			",client_BirthDate = '" + clientBirthDate + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client_Gender = '" + clientGender + '\'' + 
			",client_Relative_Mobile = '" + clientRelativeMobile + '\'' + 
			",client_Disabilities = '" + clientDisabilities + '\'' + 
			",use_App_Disabled = '" + useAppDisabled + '\'' + 
			"}";
		}


	public UpdateClientResponse(String clientName, String clientMobile, int clientCity, int clientArea, String clientBirthDate, int clientId, int clientGender, String clientRelativeMobile, List<ClientDisabilitiesItem> clientDisabilities, int useAppDisabled) {
		this.clientName = clientName;
		this.clientMobile = clientMobile;
		this.clientCity = clientCity;
		this.clientArea = clientArea;
		this.clientBirthDate = clientBirthDate;
		this.clientId = clientId;
		this.clientGender = clientGender;
		this.clientRelativeMobile = clientRelativeMobile;
		this.clientDisabilities = clientDisabilities;
		this.useAppDisabled = useAppDisabled;
	}
}