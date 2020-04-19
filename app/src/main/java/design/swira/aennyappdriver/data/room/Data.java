package design.swira.aennyappdriver.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Data_Table")
public class Data {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "symbol")
    private String code;

    private String name;

    private String price;

    @ColumnInfo(name = "changePercentage")
    private String ratio;

    @ColumnInfo(name = "stock")
    private String country;

    public Data() {
    }

    public Data(String code, String name, String price, String ratio, String country) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ratio = ratio;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
