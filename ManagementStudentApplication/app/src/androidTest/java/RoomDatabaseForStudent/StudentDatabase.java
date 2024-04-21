package RoomDatabaseForStudent;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    private static final String student_database = "student.db";
    private static StudentDatabase instance;

    public static synchronized StudentDatabase getInstanceStudent(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,student_database)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract  StudentDao studentDao();

}
