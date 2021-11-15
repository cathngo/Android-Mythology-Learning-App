package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String email;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "userId")
    private String userId;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "level")
    private int level;

    @ColumnInfo(name = "progress")
    private int progress;

    @ColumnInfo(name = "greekProgress")
    private int greekProgress;

    @ColumnInfo(name = "egyptianProgress")
    private int egyptianProgress;

    @ColumnInfo(name = "romanProgress")
    private int romanProgress;

    @ColumnInfo(name = "quizAttempts")
    private int quizAttempts;


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email, String firstName, String lastName, String userId, String password, int level, String username, int progress, int greekProgress, int egyptianProgress, int romanProgress, int quizAttempts){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        this.level = level;
        this.username = username;
        this.progress = progress;
        this.greekProgress = greekProgress;
        this.egyptianProgress = egyptianProgress;
        this.romanProgress = romanProgress;
        this.quizAttempts = quizAttempts;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public int getGreekProgress() {
        return greekProgress;
    }

    public void setGreekProgress(int greekProgress) {
        this.greekProgress = greekProgress;
    }

    public int getEgyptianProgress() {
        return egyptianProgress;
    }

    public void setEgyptianProgress(int egyptianProgress) {
        this.egyptianProgress = egyptianProgress;
    }

    public int getRomanProgress() {
        return romanProgress;
    }

    public void setRomanProgress(int romanProgress) {
        this.romanProgress = romanProgress;
    }

    public int getQuizAttempts() {
        return quizAttempts;
    }

    public void setQuizAttempts(int quizAttempts) {
        this.quizAttempts = quizAttempts;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
