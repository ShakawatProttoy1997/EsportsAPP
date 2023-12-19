package com.example.signupandloginwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LogInActivity extends AppCompatActivity {

    EditText login_Username, login_password;
    Button logIn_Btn;
    TextView sign_up_redirect_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        login_Username = findViewById(R.id.login_username);
        login_password= findViewById(R.id.login_user_password);
        logIn_Btn = findViewById(R.id.login_btn);
        sign_up_redirect_text = findViewById(R.id.sign_up_redirect_text);
        logIn_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName() | !validatePassword()){
                      login_Username.setError("invalid");
                }
                else check_User_Already_Has_Or_Not();
            }
        });
        sign_up_redirect_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LogInActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateName(){
        String name  = login_Username.getText().toString();
        if(name.isEmpty()){
            login_Username.setError("please tell me your name..");
           return false;
        }
        else{
            login_Username.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String pin  = login_password.getText().toString();
        if(pin.isEmpty()){
            login_password.setError("please give your password..");
            return false;
        }
        else{
            login_password.setError(null);
            return true;
        }
    }
    public void check_User_Already_Has_Or_Not(){
        String exist_name  = login_Username.getText().toString().trim();
        String exist_passcode = login_password.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUserDatabase = databaseReference.orderByChild("username").equalTo(exist_name);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    login_Username.setError(null);
                    String userpasswordFromDatabase = snapshot.child(exist_name).child("password").getValue(String.class);

                    if(userpasswordFromDatabase.equals(exist_passcode)){
                        login_Username.setError(null);
                        Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        login_password.setError("invalid!!");
                        login_password.requestFocus();
                    }
                }
                else{
                    login_Username.setError("User does not exist..");
                    login_Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}