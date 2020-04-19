package design.swira.aennyapp.pojo.test;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Response{

	@SerializedName("numberOfPages")
	private int numberOfPages;

	@SerializedName("validCriteria")
	private boolean validCriteria;

	@SerializedName("rows")
	private List<RowsItem> rows;

	public void setNumberOfPages(int numberOfPages){
		this.numberOfPages = numberOfPages;
	}

	public int getNumberOfPages(){
		return numberOfPages;
	}

	public void setValidCriteria(boolean validCriteria){
		this.validCriteria = validCriteria;
	}

	public boolean isValidCriteria(){
		return validCriteria;
	}

	public void setRows(List<RowsItem> rows){
		this.rows = rows;
	}

	public List<RowsItem> getRows(){
		return rows;
	}

	@Override
 	public String toString(){
		return 
			"MResponse{" +
			"numberOfPages = '" + numberOfPages + '\'' + 
			",validCriteria = '" + validCriteria + '\'' + 
			",rows = '" + rows + '\'' + 
			"}";
		}
}