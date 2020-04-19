package design.swira.aennyapp.pojo.test3;


import com.google.gson.annotations.SerializedName;


public class MResponse {

	@SerializedName("copyright")
	private String copyright;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("attributionHTML")
	private String attributionHTML;

	@SerializedName("attributionText")
	private String attributionText;

	@SerializedName("etag")
	private String etag;

	@SerializedName("status")
	private String status;

	public void setCopyright(String copyright){
		this.copyright = copyright;
	}

	public String getCopyright(){
		return copyright;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setAttributionHTML(String attributionHTML){
		this.attributionHTML = attributionHTML;
	}

	public String getAttributionHTML(){
		return attributionHTML;
	}

	public void setAttributionText(String attributionText){
		this.attributionText = attributionText;
	}

	public String getAttributionText(){
		return attributionText;
	}

	public void setEtag(String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"MResponse{" +
			"copyright = '" + copyright + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",attributionHTML = '" + attributionHTML + '\'' + 
			",attributionText = '" + attributionText + '\'' + 
			",etag = '" + etag + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}