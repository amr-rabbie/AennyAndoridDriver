package design.swira.aennyapp.pojo.aenny.areas;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Areas{

	@SerializedName("Drivers")
	private List<Object> drivers;

	@SerializedName("City_Id")
	private int cityId;

	@SerializedName("Area_Id")
	private int areaId;

	@SerializedName("City")
	private Object city;

	@SerializedName("Clients")
	private List<Object> clients;

	@SerializedName("Area_Name")
	private String areaName;

	@SerializedName("$id")
	private String id;

	public void setDrivers(List<Object> drivers){
		this.drivers = drivers;
	}

	public List<Object> getDrivers(){
		return drivers;
	}

	public void setCityId(int cityId){
		this.cityId = cityId;
	}

	public int getCityId(){
		return cityId;
	}

	public void setAreaId(int areaId){
		this.areaId = areaId;
	}

	public int getAreaId(){
		return areaId;
	}

	public void setCity(Object city){
		this.city = city;
	}

	public Object getCity(){
		return city;
	}

	public void setClients(List<Object> clients){
		this.clients = clients;
	}

	public List<Object> getClients(){
		return clients;
	}

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getAreaName(){
		return areaName;
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
			"Areas{" + 
			"drivers = '" + drivers + '\'' + 
			",city_Id = '" + cityId + '\'' + 
			",area_Id = '" + areaId + '\'' + 
			",city = '" + city + '\'' + 
			",clients = '" + clients + '\'' + 
			",area_Name = '" + areaName + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}