package design.swira.aennyapp.pojo.aenny.clients;


import com.google.gson.annotations.SerializedName;


public class ClientDisabilitiesItem{

	@SerializedName("Id")
	private int id;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ClientDisabilitiesItem{" + 
			"id = '" + id + '\'' + 
			"}";
		}

	public ClientDisabilitiesItem(int id) {
		this.id = id;
	}
}