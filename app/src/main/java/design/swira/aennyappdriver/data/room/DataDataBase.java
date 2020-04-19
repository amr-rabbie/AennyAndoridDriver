package design.swira.aennyappdriver.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class},version = 1)
public abstract class DataDataBase extends RoomDatabase {

    public static DataDataBase instance;
    public abstract DataDao dataDao();


    public static synchronized DataDataBase getInstance(Context context){
        if(instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    DataDataBase.class,"Data_Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
