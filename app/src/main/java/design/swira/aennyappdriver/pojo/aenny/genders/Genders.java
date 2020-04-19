package design.swira.aennyappdriver.pojo.aenny.genders;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Genders{

	@SerializedName("Gender_Id")
	private int genderId;

	@SerializedName("Drivers")
	private List<Object> drivers;

	@SerializedName("Gender_Name")
	private String genderName;

	@SerializedName("Clients")
	private List<Object> clients;

	@SerializedName("$id")
	private String id;

	public void setGenderId(int genderId){
		this.genderId = genderId;
	}

	public int getGenderId(){
		return genderId;
	}

	public void setDrivers(List<Object> drivers){
		this.drivers = drivers;
	}

	public List<Object> getDrivers(){
		return drivers;
	}

	public void setGenderName(String genderName){
		this.genderName = genderName;
	}

	public String getGenderName(){
		return genderName;
	}

	public void setClients(List<Object> clients){
		this.clients = clients;
	}

	public List<Object> getClients(){
		return clients;
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
			"Genders{" + 
			"gender_Id = '" + genderId + '\'' + 
			",drivers = '" + drivers + '\'' + 
			",gender_Name = '" + genderName + '\'' + 
			",clients = '" + clients + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}