package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {

    private RadioGroup signUpRadioGroup;
    private Dialog signUpButtonDialog;
    private Button signUpButtonSignUp;
    private TextInputLayout signUpTextInputLayoutName;
    private TextInputLayout signUpTextInputLayoutEmailAddress;
    private TextInputLayout signUpTextInputLayoutCNIC;
    private TextView signUpTextViewRadioGroupError;
    private TextInputLayout signUpTextInputLayoutPassword;
    private TextInputLayout signUpTextInputLayoutPassword2;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String accountType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpRadioGroup = findViewById(R.id.signUpRadioGroup);
        signUpTextInputLayoutName = findViewById(R.id.signUpTextInputLayoutName);
        signUpTextInputLayoutEmailAddress = findViewById(R.id.signUpTextInputLayoutEmailAddress);
        signUpTextInputLayoutCNIC = findViewById(R.id.signUpTextInputLayoutCNIC);
        signUpTextViewRadioGroupError = findViewById(R.id.signUpTextViewRadioGroupError);
        signUpTextInputLayoutPassword = findViewById(R.id.signUpTextInputLayoutPassword);
        signUpTextInputLayoutPassword2 = findViewById(R.id.signUpTextInputLayoutPassword2);

        signUpRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.signUpRadioButtonPetLover:
                        accountType = "petLover";
                        break;
                    case R.id.signUpRadioButtonVet:
                        accountType = "vet";
                        break;
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

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        signUpDialogButtonOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = signUpTextInputLayoutName.getEditText().getText().toString();
                String emailAddress = signUpTextInputLayoutEmailAddress.getEditText().getText().toString();
                String cnic = signUpTextInputLayoutCNIC.getEditText().getText().toString();
                String password = signUpTextInputLayoutPassword.getEditText().getText().toString();

                compositeDisposable.add(myAPI.registerUser(emailAddress, fullName, password, cnic, accountType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Toast.makeText(SignUp.this,s,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUp.this,Login.class);
                                startActivity(intent);
                                signUpButtonDialog.dismiss();
                            }
                        }));
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
                if (validateName() && validateEmailAddress() && validatePassword() && validatePassword2() && validateCNIC() && validateRadioGroup()) {
                    signUpButtonDialog.show();
                }

            }
        });
    }

    private Boolean validateName() {
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
            else if(!Character.isAlphabetic(character) && character != ' ')  {
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
            signUpTextInputLayoutName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmailAddress() {
        String emailAddress = signUpTextInputLayoutEmailAddress.getEditText().getText().toString();

        // For removing extra spaces in the middle of the text
        emailAddress = emailAddress.replaceAll("(?=\\s+$)","");
        signUpTextInputLayoutEmailAddress.getEditText().setText(emailAddress);

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailAddress);

        if (emailAddress.isEmpty()) {
            signUpTextInputLayoutEmailAddress.setError("Field cannot be empty.");
            return false;
        }
        else if (!matcher.matches()) {
            signUpTextInputLayoutEmailAddress.setError("Invalid email address.");
            return false;
        }
        else {
            signUpTextInputLayoutEmailAddress.setError(null);
            signUpTextInputLayoutEmailAddress.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateCNIC(){
        String cnic = signUpTextInputLayoutCNIC.getEditText().getText().toString();

        if (cnic.isEmpty()) {
            signUpTextInputLayoutCNIC.setError("Field cannot be empty.");
            return false;
        }
        else if (cnic.length() != 13) {
            signUpTextInputLayoutCNIC.setError("Field must contain 13 digits.");
            return false;
        }
        else {
            signUpTextInputLayoutCNIC.setError(null);
            signUpTextInputLayoutCNIC.setErrorEnabled(false);
            return true;
        }
    }

    public  Boolean validatePassword(){
        String password = signUpTextInputLayoutPassword.getEditText().getText().toString();

        String regex = "^(?=.*[0-9])" // Look for at least one digit
                + "(?=.*[a-z])(?=.*[A-Z])" // Look for at least one lowercase and one uppercase letter
                + "(?=.*[@#$%^&+=])" // // Look for at least one special character
                + "(?=\\S+$).{8,20}$"; // Make sure there are no whitespace characters and Set the password length to be between 8 and 20 characters

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (password.isEmpty()) {
            signUpTextInputLayoutPassword.setError("Field cannot be empty.");
            return false;
        }
        else if (!matcher.matches()) {
            signUpTextInputLayoutPassword.setError("Password is too weak");
            return false;
        }
        else {
            signUpTextInputLayoutPassword.setError(null);
            signUpTextInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validatePassword2() {
        String password2 = signUpTextInputLayoutPassword2.getEditText().getText().toString();

        if (password2.isEmpty()) {
            signUpTextInputLayoutPassword2.setError("Field cannot be empty.");
            return false;
        }
        else if (!password2.equals(signUpTextInputLayoutPassword.getEditText().getText().toString())) {
            signUpTextInputLayoutPassword2.setError("Passwords do not match.");
            return false;
        }
        else {
            signUpTextInputLayoutPassword2.setError(null);
            signUpTextInputLayoutPassword2.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateRadioGroup() {
        if (accountType.equals("")) {
            signUpTextViewRadioGroupError.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            signUpTextViewRadioGroupError.setVisibility(View.GONE);
            return true;
        }
    }

}