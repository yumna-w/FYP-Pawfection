package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Pair;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private Button loginButtonSignUp;
    private ImageView loginImageViewPaw;
    private TextView loginTextViewWelcome;
    private TextView loginTextViewLogin;
    private TextView loginTextViewEmailAddress;
    private TextInputLayout loginTextInputLayoutEmailAddress;
    private TextView loginTextViewPassword;
    private TextInputLayout loginTextInputLayoutPassword;
    private Button loginButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButtonSignUp = findViewById(R.id.loginButtonSignUp);
        loginButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                //startActivity(intent);

                loginImageViewPaw = findViewById(R.id.loginImageViewPaw);
                loginTextViewWelcome = findViewById(R.id.loginTextViewWelcome);
                loginTextViewLogin = findViewById(R.id.loginTextViewLogin);
                loginTextViewEmailAddress = findViewById(R.id.loginTextViewEmailAddress);
                loginTextInputLayoutEmailAddress = findViewById(R.id.loginTextInputLayoutEmailAddress);
                loginTextViewPassword = findViewById(R.id.loginTextViewPassword);
                loginTextInputLayoutPassword = findViewById(R.id.loginTextInputLayoutPassword);
                loginButtonLogin = findViewById(R.id.loginButtonLogin);

                Pair[] pairs = new Pair[8];

                pairs[0] = new Pair<View,String>(loginImageViewPaw, "mainLogo");
                pairs[1] = new Pair<View,String>(loginTextViewWelcome, "loginText");
                pairs[2] = new Pair<View,String>(loginTextViewLogin, "loginText2");
                pairs[3] = new Pair<View,String>(loginTextViewEmailAddress, "loginText3");
                pairs[4] = new Pair<View,String>(loginTextInputLayoutEmailAddress, "loginField");
                pairs[5] = new Pair<View,String>(loginTextViewPassword, "loginText4");
                pairs[6] = new Pair<View,String>(loginTextInputLayoutPassword, "loginField2");
                pairs[7] = new Pair<View,String>(loginButtonLogin, "loginButton");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent, options.toBundle());

            }
        });
    }
}