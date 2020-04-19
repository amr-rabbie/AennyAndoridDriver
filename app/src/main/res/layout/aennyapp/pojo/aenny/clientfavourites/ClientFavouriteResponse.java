package design.swira.aennyapp.pojo.aenny.clientfavourites;


import com.google.gson.annotations.SerializedName;


public class ClientFavouriteResponse{



	@SerializedName("Client_Favourite_Id")
	private int Client_Favourite_Id;

	@SerializedName("Client_Favourite_Lang")
	private double clientFavouriteLang;

	@SerializedName("Client_Favourite_Notes")
	private String clientFavouriteNotes;

	@SerializedName("Client_Favourite_Latt")
	private double clientFavouriteLatt;

	@SerializedName("Client_Id")
	private int clientId;

	@SerializedName("Client_Favourite_Name")
	private String clientFavouriteName;

	@SerializedName("Client_Favourite_Desc")
	private String clientFavouriteDesc;

	public void setClientFavouriteLang(double clientFavouriteLang){
		this.clientFavouriteLang = clientFavouriteLang;
	}

	public double getClientFavouriteLang(){
		return clientFavouriteLang;
	}

	public void setClientFavouriteNotes(String clientFavouriteNotes){
		this.clientFavouriteNotes = clientFavouriteNotes;
	}

	public String getClientFavouriteNotes(){
		return clientFavouriteNotes;
	}

	public void setClientFavouriteLatt(double clientFavouriteLatt){
		this.clientFavouriteLatt = clientFavouriteLatt;
	}

	public double getClientFavouriteLatt(){
		return clientFavouriteLatt;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setClientFavouriteName(String clientFavouriteName){
		this.clientFavouriteName = clientFavouriteName;
	}

	public String getClientFavouriteName(){
		return clientFavouriteName;
	}

	public void setClientFavouriteDesc(String clientFavouriteDesc){
		this.clientFavouriteDesc = clientFavouriteDesc;
	}

	public String getClientFavouriteDesc(){
		return clientFavouriteDesc;
	}

	public int getClient_Favourite_Id() {
		return Client_Favourite_Id;
	}

	public void setClient_Favourite_Id(int client_Favourite_Id) {
		Client_Favourite_Id = client_Favourite_Id;
	}

	@Override
 	public String toString(){
		return 
			"ClientFavouriteResponse{" + 
			"client_Favourite_Lang = '" + clientFavouriteLang + '\'' + 
			",client_Favourite_Notes = '" + clientFavouriteNotes + '\'' + 
			",client_Favourite_Latt = '" + clientFavouriteLatt + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			",client_Favourite_Name = '" + clientFavouriteName + '\'' + 
			",client_Favourite_Desc = '" + clientFavouriteDesc + '\'' + 
			"}";
		}


    public ClientFavouriteResponse(double clientFavouriteLang, String clientFavouriteNotes, double clientFavouriteLatt, int clientId, String clientFavouriteName, String clientFavouriteDesc) {
        this.clientFavouriteLang = clientFavouriteLang;
        this.clientFavouriteNotes = clientFavouriteNotes;
        this.clientFavouriteLatt = clientFavouriteLatt;
        this.clientId = clientId;
        this.clientFavouriteName = clientFavouriteName;
        this.clientFavouriteDesc = clientFavouriteDesc;
    }

	public ClientFavouriteResponse(double clientFavouriteLang, String clientFavouriteNotes, double clientFavouriteLatt, int clientId, String clientFavouriteName, String clientFavouriteDesc,int Client_Favourite_Id) {
		this.clientFavouriteLang = clientFavouriteLang;
		this.clientFavouriteNotes = clientFavouriteNotes;
		this.clientFavouriteLatt = clientFavouriteLatt;
		this.clientId = clientId;
		this.clientFavouriteName = clientFavouriteName;
		this.clientFavouriteDesc = clientFavouriteDesc;
		this.Client_Favourite_Id=Client_Favourite_Id;
	}
}