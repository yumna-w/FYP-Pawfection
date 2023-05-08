package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordCode extends AppCompatActivity {

    private ImageView forgotPasswordCodeImageViewPaw;
    private TextView forgotPasswordCodeTextViewForgotPassword;
    private TextView forgotPasswordCodeTextViewEnterCode;
    private TextView forgotPasswordCodeTextViewCode;
    private TextInputLayout forgotPasswordCodeTextInputLayoutCode;
    private Button forgotPasswordCodeButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_code);

        forgotPasswordCodeImageViewPaw = findViewById(R.id.forgotPasswordCodeImageViewPaw);
        forgotPasswordCodeTextViewForgotPassword = findViewById(R.id.forgotPasswordCodeTextViewForgotPassword);
        forgotPasswordCodeTextViewEnterCode = findViewById(R.id.forgotPasswordCodeTextViewEnterCode);
        forgotPasswordCodeTextViewCode = findViewById(R.id.forgotPasswordCodeTextViewCode);
        forgotPasswordCodeTextInputLayoutCode = findViewById(R.id.forgotPasswordCodeTextInputLayoutCode);
        forgotPasswordCodeButtonSubmit = findViewById(R.id.forgotPasswordCodeButtonSubmit);

        forgotPasswordCodeButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordCode.this,ForgotPasswordReset.class);

                Pair[] pairs = new Pair[6];

                pairs[0] = new Pair<View,String>(forgotPasswordCodeImageViewPaw, "mainLogo");
                pairs[1] = new Pair<View,String>(forgotPasswordCodeTextViewForgotPassword, "loginText");
                pairs[2] = new Pair<View,String>(forgotPasswordCodeTextViewEnterCode, "loginText2");
                pairs[3] = new Pair<View,String>(forgotPasswordCodeTextViewCode, "loginText3");
                pairs[4] = new Pair<View,String>(forgotPasswordCodeTextInputLayoutCode, "loginField");
                pairs[5] = new Pair<View,String>(forgotPasswordCodeButtonSubmit, "loginButton");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordCode.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }
}