package au.edu.unsw.infs3634.unswgamifiedlearningapp;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;

public class LevelUp {

    //method which adds +25% to user's progress everytime module is completed, and levels the user up once reaches 100%
    //Takes 4 modules to level up if call once every module completed. Can call multiple times if want
    //to increase the percentage to the user's progress.
    //to use this method, must pass in the context i.e write the code:

    /**
     * Context context = getApplicationContext();
     * LevelUp.lvlUserUp(context);
     **/

    public static void lvlUserUp(Context context) {

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
                    int newProgress = currProgress + 25;

                    if (newProgress == 100) {
                        int newLevel = currLevel + 1;
                        newProgress = 0;
                        currentUser.setProgress(newProgress);
                        currentUser.setLevel(newLevel);
                        mDb.userDao().updateUser(currentUser);
                    } else {
                        currentUser.setProgress(newProgress);
                        mDb.userDao().updateUser(currentUser);

                    }
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
}
