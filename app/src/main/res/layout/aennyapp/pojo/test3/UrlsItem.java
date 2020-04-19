package design.swira.aennyapp.pojo.test3;


import com.google.gson.annotations.SerializedName;


public class UrlsItem{

	@SerializedName("type")
	private String type;

	@SerializedName("url")
	private String url;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"UrlsItem{" + 
			"type = '" + type + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}