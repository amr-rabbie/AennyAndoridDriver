package design.swira.aennyappdriver.pojo.aenny.cd;


import com.google.gson.annotations.SerializedName;


public class ClientDisabilitiesResponse{

	@SerializedName("Disability_Type_Id")
	private int disabilityTypeId;

	@SerializedName("Disability_Types")
	private Object disabilityTypes;

	@SerializedName("Client_Disability_Type_Id")
	private int clientDisabilityTypeId;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client")
	private Object client;

	@SerializedName("$id")
	private String id;

	public void setDisabilityTypeId(int disabilityTypeId){
		this.disabilityTypeId = disabilityTypeId;
	}

	public int getDisabilityTypeId(){
		return disabilityTypeId;
	}

	public void setDisabilityTypes(Object disabilityTypes){
		this.disabilityTypes = disabilityTypes;
	}

	public Object getDisabilityTypes(){
		return disabilityTypes;
	}

	public void setClientDisabilityTypeId(int clientDisabilityTypeId){
		this.clientDisabilityTypeId = clientDisabilityTypeId;
	}

	public int getClientDisabilityTypeId(){
		return clientDisabilityTypeId;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ClientDisabilitiesResponse{" + 
			"disability_Type_Id = '" + disabilityTypeId + '\'' + 
			",disability_Types = '" + disabilityTypes + '\'' + 
			",client_Disability_Type_Id = '" + clientDisabilityTypeId + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client = '" + client + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}