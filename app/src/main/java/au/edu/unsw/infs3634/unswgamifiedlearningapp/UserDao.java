package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {
    /**This interface is used to retrieve user details from room database**/

    @Query("SELECT * FROM User")
    List<User> getUsers();

    @Query("SELECT * FROM User WHERE email == :email")
    User getUser(String email);

    @Query("DELETE FROM User")
    void deleteAll();

    @Insert
    void insert(User... users);

    @Update
    void updateUser(User user);


}
