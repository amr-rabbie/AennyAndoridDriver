package design.swira.aennyappdriver.data.room.aeeny;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DisTypesIdDao {

    @Insert
    void insert(DisTypesIds disTypesIds);

    @Update
    void update(DisTypesIds disTypesIds);

    @Delete
    void delete(DisTypesIds disTypesIds);

    @Query("DELETE FROM DisTypes_Table")
    void deleteAll();

    @Query("SELECT * FROM DisTypes_Table ORDER BY id DESC")
    LiveData<List<DisTypesIds>> getAll();
}
