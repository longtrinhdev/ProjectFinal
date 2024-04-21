package RoomDatabaseForUser;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);
    @Query("SELECT*FROM user")
    List<User> getList();
    @Query("SELECT* FROM user WHERE email = :email")
    List<User> checkerUser(String email);
    @Update
    void  updateUser(User user);
    @Delete
    void deleteUser(User user);

}
