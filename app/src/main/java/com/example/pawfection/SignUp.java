package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    private RadioGroup signUpRadioGroup;
    private Dialog signUpButtonDialog;
    private Button signUpButtonSignUp;
    private TextInputLayout signUpTextInputLayoutName;
    private TextInputLayout getSignUpTextInputLayoutEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpRadioGroup = findViewById(R.id.signUpRadioGroup);
        signUpTextInputLayoutName = findViewById(R.id.signUpTextInputLayoutName);
        getSignUpTextInputLayoutEmailAddress = findViewById(R.id.signUpTextInputLayoutEmailAddress);

        signUpRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.signUpRadioButtonPetLover:

                }
            }
        });

        signUpButtonDialog = new Dialog(SignUp.this);
        signUpButtonDialog.setContentView(R.layout.dialog_sign_up);
        signUpButtonDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog_sign_up));
        signUpButtonDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        signUpButtonDialog.getWindow().getAttributes().windowAnimations = R.style.signUpDialogAnimation;

        Button signUpDialogButtonOkay = signUpButtonDialog.findViewById(R.id.signUpDialogButtonOkay);
        Button signUpDialogButtonCancel = signUpButtonDialog.findViewById(R.id.signUpDialogButtonCancel);

        signUpDialogButtonOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this,"Account successfully created!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
                signUpButtonDialog.dismiss();
            }
        });

        signUpDialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpButtonDialog.dismiss();
            }
        });

        signUpButtonSignUp = findViewById(R.id.signUpButtonSignUp);
        signUpButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signUpButtonDialog.show();
            }
        });

    }

    /*private Boolean validateName() {
        String name = signUpTextInputLayoutName.getEditText().getText().toString();

        // For removing extra spaces in the middle of the text
        name = name.replaceAll("\\s+"," ");
        // For removing extra spaces in the beginning or end of the text
        name = name.trim();

        signUpTextInputLayoutName.getEditText().setText(name);

        //Checking for digits and special characters
        int digitCount = 0;
        int specialCharacterCount = 0;
        for(int i = 0; i < name.length(); i++)
        {
            char character = name.charAt(i);
            if(Character.isDigit(character)) {
                digitCount++;
            }
            else if(!Character.isAlphabetic(character))  {
                specialCharacterCount++;
            }
        }

        if(name.isEmpty()) {
            signUpTextInputLayoutName.setError("Field cannot be empty.");
            return false;
        }
        else if(digitCount > 0) {
            signUpTextInputLayoutName.setError("Field cannot contain numbers.");
            return false;
        }
        else if (specialCharacterCount > 0) {
            signUpTextInputLayoutName.setError("Field cannot contain special characters.");
            return false;
        }
        else {
            signUpTextInputLayoutName.setError(null);
            return true;
        }
    }

    private Boolean validateEmailAddress() {
        String emailAddress = getSignUpTextInputLayoutEmailAddress.getEditText().getText().toString();

        // For removing extra spaces in the middle of the text
        emailAddress = emailAddress.replaceAll("(?=\\s+$)","");
        // For removing extra spaces in the beginning or end of the text
        emailAddress = emailAddress.trim();

        getSignUpTextInputLayoutEmailAddress.getEditText().setText(emailAddress);

        return true;

    }*/

}