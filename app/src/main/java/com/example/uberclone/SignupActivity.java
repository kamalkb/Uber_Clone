package com.example.uberclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    EditText edName,edEmail,edPassword,edConfirmPassword;
    RadioButton riderordriver;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        radioGroup = findViewById(R.id.riderordriver);

    }

    public void signup(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();
        riderordriver = (RadioButton) findViewById(selectedId);

        if( TextUtils.isEmpty(edName.getText())){
            edName.setError( "Name is required!" );
        }else if( TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError( "Email is required!" );
        }else if( TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError( "Password is required!" );
        }else if( TextUtils.isEmpty(edConfirmPassword.getText())){
            edConfirmPassword.setError( "Confirm password is required!" );
        }else if(!edPassword.getText().toString().equals(edConfirmPassword.getText().toString())){
            Toast.makeText(SignupActivity.this, "Passwords are not the same!", Toast.LENGTH_LONG).show();
        }else if(selectedId==-1){
            Toast.makeText(SignupActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
        }else{

            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading ...");
            progress.show();
            ParseUser user = new ParseUser();
            user.setUsername(edEmail.getText().toString().trim());
            user.setEmail(edEmail.getText().toString().trim());
            user.setPassword(edPassword.getText().toString());
            user.put("name", edName.getText().toString().trim());
            user.put("riderordriver", riderordriver.getText());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    progress.dismiss();
                    if (e == null) {
                        Toast.makeText(SignupActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}