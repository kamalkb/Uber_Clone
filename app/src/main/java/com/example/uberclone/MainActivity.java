package com.example.uberclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseAnalytics;

public class MainActivity extends AppCompatActivity {

    String userType;

    public void redirectActivity() {
        if (userType=="rider"){
            Intent intent = new Intent(getApplicationContext(), RideLoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), DriveLoginActivity.class);
            startActivity(intent);
        }
    }

    public void getStarted(View view) {
        Switch userTypeSwitch = (Switch) findViewById(R.id.userTypeSwitch);

        userType  = "rider";
        if (userTypeSwitch.isChecked()){
            userType = "driver";
        }

//        ParseUser.getCurrentUser().put("riderordriver", userType);
//        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                redirectActivity();
//            }
//        });
//        Log.i("Info", "Redirecting as "+userType);

        redirectActivity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

//        if (ParseUser.getCurrentUser() == null) {
//            ParseAnonymousUtils.logIn(new LogInCallback() {
//                @Override
//                public void done(ParseUser user, ParseException e) {
//                    if (e == null){
//                        Log.i("Info","Anonymous Login Sucessfull");
//                    }else{
//                        Log.i("Info","Anonymous Login Failed");
//                    }
//                }
//            });
//        } else {
//            if (ParseUser.getCurrentUser().get("riderordriver")!=null){
//                Log.i("Info", "Redirecting as "+ ParseUser.getCurrentUser().get("riderordriver"));
//                redirectActivity();
//            }
//        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStarted(v);
            }
        });

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }



}