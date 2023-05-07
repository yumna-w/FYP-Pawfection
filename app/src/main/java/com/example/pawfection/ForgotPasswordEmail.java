package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordEmail extends AppCompatActivity {

    private Button ForgotPasswordEmailButtonSubmit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_email);


        ImageViewPaw = findViewById(R.id.loginImageViewPaw);
        loginTextViewWelcome = findViewById(R.id.loginTextViewWelcome);
        loginTextViewLogin = findViewById(R.id.loginTextViewLogin);
        loginTextViewEmailAddress = findViewById(R.id.loginTextViewEmailAddress);
        loginTextInputLayoutEmailAddress = findViewById(R.id.loginTextInputLayoutEmailAddress);
        loginTextViewPassword = findViewById(R.id.loginTextViewPassword);
        loginTextInputLayoutPassword = findViewById(R.id.loginTextInputLayoutPassword);
        loginButtonLogin = findViewById(R.id.forgotPasswordEmailButtonSubmit);

        loginButtonSignUp = findViewById(R.id.loginButtonSignUp);
        loginButtonSignUp.setOnClickListener(new View.OnClickListener(){
    }
}
}