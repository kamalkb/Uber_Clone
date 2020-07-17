package com.example.uberclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class RideLoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_login);


        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);

        if(ParseUser.getCurrentUser()!=null){
            Intent intent = new Intent(RideLoginActivity.this, RiderActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view) {
        if( TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError( "Email is required!" );
        }else if( TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError( "Password is required!" );
        }else{
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading ...");
            progress.show();
            ParseUser.logInInBackground(edEmail.getText().toString(), edPassword.getText().toString() , new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    progress.dismiss();
                    if (parseUser != null) {
                        Toast.makeText(RideLoginActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RideLoginActivity.this, RiderActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(RideLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void signup(View view) {
        Intent intent = new Intent(RideLoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

//    public void forgotPassword(View view) {
//        Intent intent = new Intent(RideLoginActivity.this, ResetPasswordActivity.class);
//        startActivity(intent);
//    }

}