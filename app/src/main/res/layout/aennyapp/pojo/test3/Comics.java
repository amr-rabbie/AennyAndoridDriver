package design.swira.aennyapp.pojo.test3;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Comics{

	@SerializedName("collectionURI")
	private String collectionURI;

	@SerializedName("available")
	private int available;

	@SerializedName("returned")
	private int returned;

	@SerializedName("items")
	private List<ItemsItem> items;

	public void setCollectionURI(String collectionURI){
		this.collectionURI = collectionURI;
	}

	public String getCollectionURI(){
		return collectionURI;
	}

	public void setAvailable(int available){
		this.available = available;
	}

	public int getAvailable(){
		return available;
	}

	public void setReturned(int returned){
		this.returned = returned;
	}

	public int getReturned(){
		return returned;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"Comics{" + 
			"collectionURI = '" + collectionURI + '\'' + 
			",available = '" + available + '\'' + 
			",returned = '" + returned + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}