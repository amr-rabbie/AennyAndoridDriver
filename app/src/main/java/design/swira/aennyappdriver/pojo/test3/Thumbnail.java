package design.swira.aennyappdriver.pojo.test3;


import com.google.gson.annotations.SerializedName;


public class Thumbnail{

	@SerializedName("path")
	private String path;

	@SerializedName("extension")
	private String extension;

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setExtension(String extension){
		this.extension = extension;
	}

	public String getExtension(){
		return extension;
	}

	@Override
 	public String toString(){
		return 
			"Thumbnail{" + 
			"path = '" + path + '\'' + 
			",extension = '" + extension + '\'' + 
			"}";
		}
}