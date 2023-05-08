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

import org.w3c.dom.Text;

public class ForgotPasswordEmail extends AppCompatActivity {

    private ImageView forgotPasswordEmailImageViewPaw;
    private TextView forgotPasswordEmailTextViewForgotPassword;
    private TextView forgotPasswordEmailTextViewEnterEmail;
    private TextView forgotPasswordEmailTextViewEmailAddress;
    private TextInputLayout forgotPasswordEmailTextInputLayoutEmailAddress;
    private Button forgotPasswordEmailButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_email);

        forgotPasswordEmailImageViewPaw = findViewById(R.id.forgotPasswordEmailImageViewPaw);
        forgotPasswordEmailTextViewForgotPassword = findViewById(R.id.forgotPasswordEmailTextViewForgotPassword);
        forgotPasswordEmailTextViewEnterEmail = findViewById(R.id.forgotPasswordEmailTextViewEnterEmail);
        forgotPasswordEmailTextViewEmailAddress = findViewById(R.id.forgotPasswordEmailTextViewEmailAddress);
        forgotPasswordEmailTextInputLayoutEmailAddress = findViewById(R.id.forgotPasswordEmailTextInputLayoutEmailAddress);
        forgotPasswordEmailButtonSubmit = findViewById(R.id.forgotPasswordEmailButtonSubmit);

        forgotPasswordEmailButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordEmail.this,ForgotPasswordCode.class);

                Pair[] pairs = new Pair[6];

                pairs[0] = new Pair<View,String>(forgotPasswordEmailImageViewPaw, "mainLogo");
                pairs[1] = new Pair<View,String>(forgotPasswordEmailTextViewForgotPassword, "loginText");
                pairs[2] = new Pair<View,String>(forgotPasswordEmailTextViewEnterEmail, "loginText2");
                pairs[3] = new Pair<View,String>(forgotPasswordEmailTextViewEmailAddress, "loginText3");
                pairs[4] = new Pair<View,String>(forgotPasswordEmailTextInputLayoutEmailAddress, "loginField");
                pairs[5] = new Pair<View,String>(forgotPasswordEmailButtonSubmit, "loginButton");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordEmail.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }
}