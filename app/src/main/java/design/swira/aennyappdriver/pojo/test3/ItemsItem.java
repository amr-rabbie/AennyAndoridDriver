package design.swira.aennyappdriver.pojo.test3;


import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("resourceURI")
	private String resourceURI;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setResourceURI(String resourceURI){
		this.resourceURI = resourceURI;
	}

	public String getResourceURI(){
		return resourceURI;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"name = '" + name + '\'' + 
			",resourceURI = '" + resourceURI + '\'' + 
			"}";
		}
}