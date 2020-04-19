package design.swira.aennyappdriver.pojo.aenny.cities;


import com.google.gson.annotations.SerializedName;


public class City{

	@SerializedName("City_Id")
	private int cityId;

	@SerializedName("City_Name")
	private String cityName;

	public void setCityId(int cityId){
		this.cityId = cityId;
	}

	public int getCityId(){
		return cityId;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	@Override
 	public String toString(){
		return 
			"City{" + 
			"city_Id = '" + cityId + '\'' + 
			",city_Name = '" + cityName + '\'' + 
			"}";
		}
}