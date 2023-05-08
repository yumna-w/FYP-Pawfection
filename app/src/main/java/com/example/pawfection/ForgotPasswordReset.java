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

public class ForgotPasswordReset extends AppCompatActivity {

    private ImageView forgotPasswordResetImageViewPaw;
    private TextView forgotPasswordResetTextViewForgotPassword;
    private TextView forgotPasswordResetTextViewEnterPassword;
    private TextView forgotPasswordResetTextViewPassword;
    private TextInputLayout forgotPasswordResetTextInputLayoutPassword;
    private TextView forgotPasswordResetTextViewPassword2;
    private TextInputLayout forgotPasswordResetTextInputLayoutPassword2;
    private Button forgotPasswordResetButtonReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_reset);

        forgotPasswordResetImageViewPaw = findViewById(R.id.forgotPasswordResetImageViewPaw);
        forgotPasswordResetTextViewForgotPassword = findViewById(R.id.forgotPasswordResetTextViewForgotPassword);
        forgotPasswordResetTextViewEnterPassword = findViewById(R.id.forgotPasswordResetTextViewEnterPassword);
        forgotPasswordResetTextViewPassword = findViewById(R.id.forgotPasswordResetTextViewPassword);
        forgotPasswordResetTextInputLayoutPassword = findViewById(R.id.forgotPasswordResetTextInputLayoutPassword);
        forgotPasswordResetTextViewPassword2 = findViewById(R.id.forgotPasswordResetTextViewPassword2);
        forgotPasswordResetTextInputLayoutPassword2 = findViewById(R.id.forgotPasswordResetTextInputLayoutPassword2);
        forgotPasswordResetButtonReset = findViewById(R.id.forgotPasswordResetButtonReset);

        forgotPasswordResetButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordReset.this,Login.class);
                //startActivity(intent);

                Pair[] pairs = new Pair[8];

                pairs[0] = new Pair<View,String>(forgotPasswordResetImageViewPaw, "mainLogo");
                pairs[1] = new Pair<View,String>(forgotPasswordResetTextViewForgotPassword, "loginText");
                pairs[2] = new Pair<View,String>(forgotPasswordResetTextViewEnterPassword, "loginText2");
                pairs[3] = new Pair<View,String>(forgotPasswordResetTextViewPassword, "loginText3");
                pairs[4] = new Pair<View,String>(forgotPasswordResetTextInputLayoutPassword, "loginField");
                pairs[5] = new Pair<View,String>(forgotPasswordResetTextViewPassword2, "loginText4");
                pairs[6] = new Pair<View,String>(forgotPasswordResetTextInputLayoutPassword2, "loginField2");
                pairs[7] = new Pair<View,String>(forgotPasswordResetButtonReset, "loginButton");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordReset.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });



    }
}