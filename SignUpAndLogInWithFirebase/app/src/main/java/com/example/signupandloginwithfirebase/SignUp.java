package com.example.signupandloginwithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText signup_name, signup_email, signup_password;
    TextView loginRedirectText;
    Button sign_up_Btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
     SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup_name = findViewById(R.id.sign_up_username);
        signup_email = findViewById(R.id.sign_up_user_email);
        signup_password = findViewById(R.id.sign_up_user_password);
        sharedPreferences = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
        if(isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    startActivity(new Intent(SignUp.this, OnBoardingActivity.class));
                    finish();
        }
        loginRedirectText = findViewById(R.id.login_redirect_text);
        sign_up_Btn = findViewById(R.id.sign_up_btn);
        sign_up_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("user");
                String name = signup_name.getText().toString();
                String mail = signup_email.getText().toString();
                String passcode = signup_password.getText().toString();
                HelperClass helperClass = new HelperClass(name, mail,passcode);
                databaseReference.child(name).setValue(helperClass);
                Toast.makeText(SignUp.this,"Successfully Signed Up",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this,LogInActivity.class);
                startActivity(intent);
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LogInActivity.class);
                startActivity(intent);
            }
        });

    }
}