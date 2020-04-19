package design.swira.aennyappdriver.pojo.aenny.clientpaymentsmethods;


import com.google.gson.annotations.SerializedName;


public class ClientsPaymentsMethodsResponse{



	@SerializedName("Client_Payments_Methods_id")
	private int Client_Payments_Methods_id;

	@SerializedName("Payment_Type_Id")
	private int paymentTypeId;

	@SerializedName("CVV")
	private String cVV;

	@SerializedName("Card_Number")
	private String cardNumber;

	@SerializedName("Card_Holder_Name")
	private String cardHolderName;

	@SerializedName("Expiration_Date")
	private String expirationDate;

	@SerializedName("Client_Id")
	private int clientId;

	public void setPaymentTypeId(int paymentTypeId){
		this.paymentTypeId = paymentTypeId;
	}

	public int getPaymentTypeId(){
		return paymentTypeId;
	}

	public void setCVV(String cVV){
		this.cVV = cVV;
	}

	public String getCVV(){
		return cVV;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public void setCardHolderName(String cardHolderName){
		this.cardHolderName = cardHolderName;
	}

	public String getCardHolderName(){
		return cardHolderName;
	}

	public void setExpirationDate(String expirationDate){
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate(){
		return expirationDate;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"ClientsPaymentsMethodsResponse{" + 
			"payment_Type_Id = '" + paymentTypeId + '\'' + 
			",cVV = '" + cVV + '\'' + 
			",card_Number = '" + cardNumber + '\'' + 
			",card_Holder_Name = '" + cardHolderName + '\'' + 
			",expiration_Date = '" + expirationDate + '\'' + 
			",client_Id = '" + clientId + '\'' + 
			"}";
		}

	public ClientsPaymentsMethodsResponse(int paymentTypeId, String cVV, String cardNumber, String cardHolderName, String expirationDate, int clientId,int Client_Payments_Methods_id) {
		this.paymentTypeId = paymentTypeId;
		this.cVV = cVV;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.clientId = clientId;
		this.Client_Payments_Methods_id=Client_Payments_Methods_id;
	}

	public ClientsPaymentsMethodsResponse(int paymentTypeId, String cVV, String cardNumber, String cardHolderName, String expirationDate, int clientId) {
		this.paymentTypeId = paymentTypeId;
		this.cVV = cVV;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.clientId = clientId;
	}

	public int getClient_Payments_Methods_id() {
		return Client_Payments_Methods_id;
	}

	public void setClient_Payments_Methods_id(int client_Payments_Methods_id) {
		Client_Payments_Methods_id = client_Payments_Methods_id;
	}

	public String getcVV() {
		return cVV;
	}

	public void setcVV(String cVV) {
		this.cVV = cVV;
	}



}