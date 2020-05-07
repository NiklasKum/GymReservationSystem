package com.example.gymreservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText usernameET;
    private EditText passwordET;
    private Button signUpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailET = findViewById(R.id.SignUp_email);
        usernameET = findViewById(R.id.SignUp_Username);
        passwordET = findViewById(R.id.SignUp_password);
        signUpbtn = findViewById(R.id.SignUp);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sings up
                if(MainActivity.CB.SignUp(usernameET, passwordET, emailET)){
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }


}
