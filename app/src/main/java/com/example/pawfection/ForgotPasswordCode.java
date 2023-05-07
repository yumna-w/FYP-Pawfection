package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordCode extends AppCompatActivity {

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
        setContentView(R.layout.activity_forgot_password_code);
    }
}