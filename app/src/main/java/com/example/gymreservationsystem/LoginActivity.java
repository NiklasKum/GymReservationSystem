package com.example.gymreservationsystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private String selectUsername;

    private String selectPassword;

    private EditText UsernameET;

    private EditText PasswordET;

    private Button Login;

    private Button toSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MainActivity.CB.NULLWRITEFILEaddUser(new User("admin@mail.com", "admin","1234"));

        UsernameET = findViewById(R.id.Username_inputEditText);
        PasswordET = findViewById(R.id.Password_inputEditText);
        toSignUp = findViewById(R.id.SignUp_Btn);
        Login = findViewById(R.id.LoginButton);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.CB.Login(UsernameET, PasswordET)){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

}
