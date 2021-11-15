package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

//Reference for sign up using Firebase: https://www.youtube.com/watch?v=iSsa9OlQJms&ab_channel=SoftCoding

public class Register extends AppCompatActivity {
    TextInputEditText etUserName;
    TextInputEditText etRegEmail;
    TextInputEditText  etRegPassword;
    TextInputEditText  editFirstName;
    TextInputEditText  editLastName;
    TextView txtLogin;
    Button btnRegister;

    boolean username_taken = false;

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
        editFirstName = findViewById(R.id.editFirstName);
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

        //resetDatabase();
    }

    private void createUser(){
        String username = etUserName.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String fName = editFirstName.getText().toString();
        String lName = editLastName.getText().toString();



        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }
        else if (username_taken == true) {
            etUserName.setError("Username is already taken");
            etUserName.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        //insert into room Dao
                        DatabaseAll db  = DatabaseAll.getDbInstance(Register.this);
                        String id = mAuth.getCurrentUser().getUid();
                        insertUserIntoDatabase(email,fName, lName, id, password, 0, username, 0);
                        startActivity(new Intent(Register.this, Login.class));
                    }else{
                        Toast.makeText(Register.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void insertUserIntoDatabase(String email, String firstName, String lastName, String id, String password, int level, String username, int progress) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().insert(new User(email, firstName, lastName, id, password, level, username, progress));

                List<User> userAccounts = mDb.userDao().getUsers();
                for(User userAccount : userAccounts) {
                    Log.d("Printing user emails" , userAccount.getEmail());
                    Log.d("Printing user first nname" , userAccount.getFirstName());
                    Log.d("Printing user last nnane" , userAccount.getLastName());
                    Log.d("Printing user uid" , userAccount.getUserId());
                    Log.d("Printing user pw" , userAccount.getPassword());
                    Log.d("Printing user level" , String.valueOf(userAccount.getLevel()));
                    Log.d("Printing username" , String.valueOf(userAccount.getUsername()));
                    Log.d("Printing username" , String.valueOf(userAccount.getProgress()));

                }
            }
        });

    }


    private void resetDatabase() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().deleteAll();


            }
        });
    }
}