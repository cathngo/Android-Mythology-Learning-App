package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1)
public abstract class DatabaseAll extends RoomDatabase {

    public abstract UserDao userDao();

    private static DatabaseAll INSTANCE;

    public static DatabaseAll getDbInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseAll.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

}