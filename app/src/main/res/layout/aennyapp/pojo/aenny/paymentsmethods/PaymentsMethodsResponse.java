package design.swira.aennyapp.pojo.aenny.paymentsmethods;


import com.google.gson.annotations.SerializedName;


public class PaymentsMethodsResponse{

	@SerializedName("Payment_Methods_Id")
	private int paymentMethodsId;

	@SerializedName("Payment_Methods_Name")
	private String paymentMethodsName;

	@SerializedName("Payment_Methods_Icon")
	private Object paymentMethodsIcon;

	@SerializedName("$id")
	private String id;

	public void setPaymentMethodsId(int paymentMethodsId){
		this.paymentMethodsId = paymentMethodsId;
	}

	public int getPaymentMethodsId(){
		return paymentMethodsId;
	}

	public void setPaymentMethodsName(String paymentMethodsName){
		this.paymentMethodsName = paymentMethodsName;
	}

	public String getPaymentMethodsName(){
		return paymentMethodsName;
	}

	public void setPaymentMethodsIcon(Object paymentMethodsIcon){
		this.paymentMethodsIcon = paymentMethodsIcon;
	}

	public Object getPaymentMethodsIcon(){
		return paymentMethodsIcon;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"PaymentsMethodsResponse{" + 
			"payment_Methods_Id = '" + paymentMethodsId + '\'' + 
			",payment_Methods_Name = '" + paymentMethodsName + '\'' + 
			",payment_Methods_Icon = '" + paymentMethodsIcon + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}