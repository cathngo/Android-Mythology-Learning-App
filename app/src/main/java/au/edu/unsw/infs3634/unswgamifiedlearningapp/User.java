package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    /**This class is the User class representing all users whom contain the following attributes:
     * email: the user's email
     * firstName: the user's first name
     * lastName: the user's last name
     * userID: A unique ID of the user
     * password; the user's password
     * username: the user's handle
     * level: the user's current level
     * progress: How much of the user's current level they have completed
     * greekProgress: How much of the greek mythology module they have completed
     * romanProgress: How much of the roman mythology module they have completed
     * egyptianProgress: How much of the egyptian mythology module they have completed
     * quizAttempts: The number of attempts for the main quiz the user has taken
     * mythScore: The user's latest score in the Myth Scramble game
     * monsterScore; The user's latest score in the Monster Match game**/

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

    @ColumnInfo(name = "mythScore")
    private int mythScore;

    @ColumnInfo(name = "monsterScore")
    private int monsterScore;

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

    public User(String email, String firstName, String lastName, String userId, String password, int level, String username, int progress, int greekProgress, int egyptianProgress, int romanProgress, int quizAttempts, int mythScore, int monsterScore){
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
        this.mythScore = mythScore;
        this.monsterScore = monsterScore;
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

    public int getMythScore() {
        return mythScore;
    }

    public void setMythScore(int mythScore) {
        this.mythScore = mythScore;
    }

    public int getMonsterScore() {
        return monsterScore;
    }

    public void setMonsterScore(int monsterScore) {
        this.monsterScore = monsterScore;
    }
}
