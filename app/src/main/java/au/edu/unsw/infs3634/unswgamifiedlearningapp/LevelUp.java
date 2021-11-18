package au.edu.unsw.infs3634.unswgamifiedlearningapp;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;

//Reference for obtaining user details from firebase: https://firebase.google.com/docs/auth/web/manage-users
//Reference for obtaining data from room database: Week 9 Tutorial Covid Tracker: https://github.com/INFS3634/Covid19Tracker
public class LevelUp {
    /**This class includes helper methods to update and get user information from the room database **/

    /**
     * Context context = getApplicationContext();
     * LevelUp.lvlUserUp(context, int increase);
     **/

    public static void lvlUserUp(Context context, int increase) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    int currProgress = currentUser.getProgress();
                    int currLevel = currentUser.getLevel();
                    int newProg = currProgress + increase;

                    double newLevel = Math.floor((double)newProg/100.0);
                    int inputLevel = (int)newLevel;
                    int actualprog = newProg % 100;
                    int leveled = inputLevel + currLevel;
                    currentUser.setProgress(actualprog);
                    currentUser.setLevel(leveled);
                    mDb.userDao().updateUser(currentUser);

                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }

    //This method increases the user's progress for the greek module. Takes in the context and
    //the amount in percentage to increase the progress
    /**
     * Context context = getApplicationContext();
     * LevelUp.increaseGreekProgress(context, 10); //this will increase the user's progress on the greek module by 10%
     **/

    public static void increaseGreekProgress(Context context, int increase) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    int currGreekProgress = currentUser.getGreekProgress();
                    int newProgress = currGreekProgress + increase;
                    currentUser.setGreekProgress(newProgress);
                    mDb.userDao().updateUser(currentUser);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }

    //This method increases the user's progress for the egyptian module. Takes in the context and
    //the amount in percentage to increase the progress
    /**
     * Context context = getApplicationContext();
     * LevelUp.increaseEgyptianProgress(context, 10);
     **/

    public static void increaseEgyptianProgress(Context context, int increase) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    int currEgyptianProgress = currentUser.getEgyptianProgress();
                    int newProgress = currEgyptianProgress + increase;
                    currentUser.setEgyptianProgress(newProgress);
                    mDb.userDao().updateUser(currentUser);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }

    //This method increases the user's progress for the roman module. Takes in the context and
    //the amount in percentage to increase the progress
    /**
     * Context context = getApplicationContext();
     * LevelUp.increaseRomanProgress(context, 10);
     **/

    public static void increaseRomanProgress(Context context, int increase) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    int currRomanProgress = currentUser.getRomanProgress();
                    int newProgress = currRomanProgress + increase;
                    currentUser.setRomanProgress(newProgress);
                    mDb.userDao().updateUser(currentUser);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }


    //This method increases the user's quiz attempts by 1, Takes in a context as argument
    /**
     * Context context = getApplicationContext();
     * LevelUp.increaseQuizAttempts(context);
     **/

    public static void increaseQuizAttempts(Context context) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    int newProgress = currentUser.getQuizAttempts() + 1;
                    currentUser.setQuizAttempts(newProgress);
                    mDb.userDao().updateUser(currentUser);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }


    //This method saves the user's score in games for myth
    /**
     * Context context = getApplicationContext();
     * LevelUp.increaseQuizAttempts(context);
     **/

    public static void saveMythScore(Context context, int correct) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    currentUser.setMythScore(correct);
                    mDb.userDao().updateUser(currentUser);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }

    //This method saves the user's score in games for monster match
    /**
     * Context context = getApplicationContext();
     * LevelUp.increaseQuizAttempts(context);
     **/

    public static void saveMonsterScore(Context context, int correct) {

        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in, show user data
            String email = user.getEmail();

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    //find their current progress and level
                    User currentUser = mDb.userDao().getUser(email);
                    currentUser.setMonsterScore(correct);
                    mDb.userDao().updateUser(currentUser);
                }
            });
        } else {
            // user is signed out, show sign-in form
            Toast.makeText(context, "User has been signed out, please log in again", Toast.LENGTH_SHORT).show();
        }
    }




}

