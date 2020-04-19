package design.swira.aennyappdriver.pojo.aenny.disability_types;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DisabilityTypes{

	@SerializedName("Disability_Type_Id")
	private int disabilityTypeId;

	@SerializedName("Client_Disabilties")
	private List<Object> clientDisabilties;

	@SerializedName("Disability_Type_Name")
	private String disabilityTypeName;

	@SerializedName("$id")
	private String id;

	@SerializedName("Disability_Type_Icon")
	private String disabilityTypeIcon;

	public void setDisabilityTypeId(int disabilityTypeId){
		this.disabilityTypeId = disabilityTypeId;
	}

	public int getDisabilityTypeId(){
		return disabilityTypeId;
	}

	public void setClientDisabilties(List<Object> clientDisabilties){
		this.clientDisabilties = clientDisabilties;
	}

	public List<Object> getClientDisabilties(){
		return clientDisabilties;
	}

	public void setDisabilityTypeName(String disabilityTypeName){
		this.disabilityTypeName = disabilityTypeName;
	}

	public String getDisabilityTypeName(){
		return disabilityTypeName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDisabilityTypeIcon(String disabilityTypeIcon){
		this.disabilityTypeIcon = disabilityTypeIcon;
	}

	public String getDisabilityTypeIcon(){
		return disabilityTypeIcon;
	}

	@Override
 	public String toString(){
		return 
			"DisabilityTypes{" + 
			"disability_Type_Id = '" + disabilityTypeId + '\'' + 
			",client_Disabilties = '" + clientDisabilties + '\'' + 
			",disability_Type_Name = '" + disabilityTypeName + '\'' + 
			",$id = '" + id + '\'' + 
			",disability_Type_Icon = '" + disabilityTypeIcon + '\'' + 
			"}";
		}
}