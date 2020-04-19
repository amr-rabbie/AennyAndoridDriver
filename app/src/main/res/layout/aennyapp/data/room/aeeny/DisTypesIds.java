package design.swira.aennyapp.data.room.aeeny;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DisTypes_Table")
public class DisTypesIds {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "distypeid")
    private int distypeid;

    public DisTypesIds() {
    }

    public DisTypesIds(int distypeid) {
        this.distypeid = distypeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistypeid() {
        return distypeid;
    }

    public void setDistypeid(int distypeid) {
        this.distypeid = distypeid;
    }
}
