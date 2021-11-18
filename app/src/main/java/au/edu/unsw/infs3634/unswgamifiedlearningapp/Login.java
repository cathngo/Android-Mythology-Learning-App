package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//Reference for login with firebase: https://www.youtube.com/watch?v=iSsa9OlQJms&t=805s&ab_channel=SoftCoding
public class Login extends AppCompatActivity {
    /**This class includes the implementation of the login screen to validate user details
     * from firebase**/

    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView txtRegister;
    Button btnLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialise UI elements
        etLoginEmail = findViewById(R.id.editEmail);
        etLoginPassword = findViewById(R.id.editPassword);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        //Invoke login method if user clicked login
        btnLogin.setOnClickListener(view -> {
            loginUser();
        });

        //Switch to the register page if user clicked register
        txtRegister.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, Register.class));
        });
    }

    private void loginUser(){
        //Get user email and password inputted
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        //Check if user left email and password empty
        if (TextUtils.isEmpty(email)){
            //Alert user
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            //Alert user
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            //Validate their details from firebase
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        //Alert user logged in successfully and switch to the homepage of the application
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, HomeActivity.class));
                    }else{
                        //Alert an error occured logging user in
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}