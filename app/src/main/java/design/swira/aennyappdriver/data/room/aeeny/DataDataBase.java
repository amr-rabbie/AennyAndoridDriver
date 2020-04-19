package design.swira.aennyappdriver.data.room.aeeny;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DisTypesIds.class},version = 2)
public abstract class DataDataBase  extends RoomDatabase {



        public static DataDataBase instance;
        public abstract DisTypesIdDao disTypesIdDao();


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



