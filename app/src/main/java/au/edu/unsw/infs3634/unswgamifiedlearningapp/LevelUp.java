package au.edu.unsw.infs3634.unswgamifiedlearningapp;


import android.content.Context;

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

     Context context = getApplicationContext();
     LevelUp.lvlUserUp(context);

     **/

    public static void lvlUserUp(Context context){
        DatabaseAll mDb;

        mDb = Room.databaseBuilder(context, DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //find their current progress and level
                User currentUser = mDb.userDao().getUser(email);
                int currProgress = currentUser.getProgress();
                int currLevel = currentUser.getLevel();
                int newProgress = currProgress +25;

                if (newProgress == 100) {
                    int newLevel = currLevel + 1;
                    newProgress = 0;
                    currentUser.setProgress(newProgress);
                    currentUser.setLevel(newLevel);
                    mDb.userDao().updateUser(currentUser);
                }  else {
                    currentUser.setProgress(newProgress);
                    mDb.userDao().updateUser(currentUser);

                }
            }
        });
    }

}
