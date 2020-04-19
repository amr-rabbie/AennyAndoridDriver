package design.swira.aennyappdriver.pojo.aennydriver;

import com.google.gson.annotations.SerializedName;

public class GetVechealByDriverResponse{

	@SerializedName("Vehicle")
	private Object vehicle;

	@SerializedName("Driver_Id")
	private int driverId;

	@SerializedName("To_Date")
	private Object toDate;

	@SerializedName("Is_Current")
	private boolean isCurrent;

	@SerializedName("From_Date")
	private String fromDate;

	@SerializedName("Driver")
	private Object driver;

	@SerializedName("Driver_Vehicle_Id")
	private int driverVehicleId;

	@SerializedName("Vehicle_Id")
	private int vehicleId;

	@SerializedName("$id")
	private String id;

	public void setVehicle(Object vehicle){
		this.vehicle = vehicle;
	}

	public Object getVehicle(){
		return vehicle;
	}

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
	}

	public void setToDate(Object toDate){
		this.toDate = toDate;
	}

	public Object getToDate(){
		return toDate;
	}

	public void setIsCurrent(boolean isCurrent){
		this.isCurrent = isCurrent;
	}

	public boolean isIsCurrent(){
		return isCurrent;
	}

	public void setFromDate(String fromDate){
		this.fromDate = fromDate;
	}

	public String getFromDate(){
		return fromDate;
	}

	public void setDriver(Object driver){
		this.driver = driver;
	}

	public Object getDriver(){
		return driver;
	}

	public void setDriverVehicleId(int driverVehicleId){
		this.driverVehicleId = driverVehicleId;
	}

	public int getDriverVehicleId(){
		return driverVehicleId;
	}

	public void setVehicleId(int vehicleId){
		this.vehicleId = vehicleId;
	}

	public int getVehicleId(){
		return vehicleId;
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
			"GetVechealByDriverResponse{" + 
			"vehicle = '" + vehicle + '\'' + 
			",driver_Id = '" + driverId + '\'' + 
			",to_Date = '" + toDate + '\'' + 
			",is_Current = '" + isCurrent + '\'' + 
			",from_Date = '" + fromDate + '\'' + 
			",driver = '" + driver + '\'' + 
			",driver_Vehicle_Id = '" + driverVehicleId + '\'' + 
			",vehicle_Id = '" + vehicleId + '\'' + 
			",$id = '" + id + '\'' + 
			"}";
		}
}