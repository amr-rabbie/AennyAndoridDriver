package design.swira.aennyappdriver.pojo.test;

import com.google.gson.annotations.SerializedName;


public class RowsItem{

	@SerializedName("market")
	private String market;

	@SerializedName("profileUrl")
	private String profileUrl;

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("marketUrl")
	private Object marketUrl;

	@SerializedName("price")
	private double price;

	@SerializedName("lastUpdate")
	private String lastUpdate;

	@SerializedName("name")
	private String name;

	@SerializedName("currency")
	private Object currency;

	@SerializedName("changePercentage")
	private double changePercentage;

	@SerializedName("sector")
	private String sector;

	@SerializedName("url")
	private String url;

	public void setMarket(String market){
		this.market = market;
	}

	public String getMarket(){
		return market;
	}

	public void setProfileUrl(String profileUrl){
		this.profileUrl = profileUrl;
	}

	public String getProfileUrl(){
		return profileUrl;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setMarketUrl(Object marketUrl){
		this.marketUrl = marketUrl;
	}

	public Object getMarketUrl(){
		return marketUrl;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCurrency(Object currency){
		this.currency = currency;
	}

	public Object getCurrency(){
		return currency;
	}

	public void setChangePercentage(double changePercentage){
		this.changePercentage = changePercentage;
	}

	public double getChangePercentage(){
		return changePercentage;
	}

	public void setSector(String sector){
		this.sector = sector;
	}

	public String getSector(){
		return sector;
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
			"RowsItem{" + 
			"market = '" + market + '\'' + 
			",profileUrl = '" + profileUrl + '\'' + 
			",symbol = '" + symbol + '\'' + 
			",marketUrl = '" + marketUrl + '\'' + 
			",price = '" + price + '\'' + 
			",lastUpdate = '" + lastUpdate + '\'' + 
			",name = '" + name + '\'' + 
			",currency = '" + currency + '\'' + 
			",changePercentage = '" + changePercentage + '\'' + 
			",sector = '" + sector + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}