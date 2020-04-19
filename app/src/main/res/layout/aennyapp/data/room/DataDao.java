package design.swira.aennyapp.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    void insertData(Data data);

    @Update
    void updateData(Data data);

    @Delete
    void deleteData(Data data);

    @Query("DELETE FROM DATA_TABLE")
    void deleteAllData();

    @Query("SELECT * FROM DATA_TABLE ORDER BY symbol DESC")
    LiveData<List<Data>> getAllData();


}
