package RoomDatabaseForUser;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String database_name = "user.db";
    private static UserDatabase instance;

    // Hàm khởi tạo
    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,database_name)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    };
    public abstract UserDao userDao();

}
