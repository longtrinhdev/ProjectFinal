package RoomDatabaseForStudent;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);

    @Query("SELECT *FROM student")
    List<Student> getListStudent();

    @Query("SELECT * FROM student WHERE msv =:maSinhVien")
    List<Student> isCheckStudent(String maSinhVien);
    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM student WHERE ( msv  LIKE '%' || :msv ||'%') OR (hoVaTen LIKE '%' || :msv || '%')")
    List<Student> search(String msv);
}
