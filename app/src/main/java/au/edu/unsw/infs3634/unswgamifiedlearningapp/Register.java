package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.concurrent.Executors;

//Reference for sign up using Firebase: https://www.youtube.com/watch?v=iSsa9OlQJms&ab_channel=SoftCoding
//Reference for saving data to room database: Week 9 Tutorial Covid Tracker: https://github.com/INFS3634/Covid19Tracker
public class Register extends AppCompatActivity {
    TextInputEditText etUserName;
    TextInputEditText etRegEmail;
    TextInputEditText  etRegPassword;
    TextInputEditText  editFirstName;
    TextInputEditText  editLastName;
    TextView txtLogin;
    Button btnRegister;

    FirebaseAuth mAuth;

    private DatabaseAll mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = findViewById(R.id.editUserName);
        etRegEmail = findViewById(R.id.editEmailReg);
        etRegPassword = findViewById(R.id.editPasswordReg);
        txtLogin = findViewById(R.id.txtLogin);
        btnRegister = findViewById(R.id.btnRegister);
        editFirstName = findViewById(R.id.txtAnswer);
        editLastName = findViewById(R.id.editLastName);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        txtLogin.setOnClickListener(view ->{
            startActivity(new Intent(Register.this, Login.class));
        });


        mDb = Room.databaseBuilder(getApplicationContext(), DatabaseAll.class, "database-all")
                .fallbackToDestructiveMigration()
                .build();
    }

    private void createUser(){
        //Get username, email, password and name inputted from user
        String username = etUserName.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String fName = editFirstName.getText().toString();
        String lName = editLastName.getText().toString();

        //Check if user left any input boxes empty
        if (TextUtils.isEmpty(email)){
            //Alert user has not filled out required information
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            //Alert user has not filled out required information
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        } else{
            //Register user to firebase
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        //insert into user into room database
                        DatabaseAll db  = DatabaseAll.getDbInstance(Register.this);
                        String id = mAuth.getCurrentUser().getUid();
                        insertUserIntoDatabase(email,fName, lName, id, password, 0, username, 0,0,0,0,0, -1, -1);
                        startActivity(new Intent(Register.this, Login.class));
                    }else{
                        //Alert user was not registered successfully
                        Toast.makeText(Register.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Helper method to insert the user into room database given their inputted information
    private void insertUserIntoDatabase(String email, String firstName, String lastName, String id, String password, int level, String username, int progress,int greekProgress, int egyptianProgress, int romanProgress, int quizAttempts, int mythScore, int monsterScore) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //Create new user object with the user's inputted information
                mDb.userDao().insert(new User(email, firstName, lastName, id, password, level, username, progress, greekProgress, egyptianProgress, romanProgress, quizAttempts, mythScore, monsterScore));
            }
        });

    }

    //Helper method to reset the room database
    private void resetDatabase() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().deleteAll();
            }
        });
    }
}