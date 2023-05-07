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
import android.widget.Toast;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

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
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int accountTypeFlag = 0;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginImageViewPaw = findViewById(R.id.loginImageViewPaw);
        loginTextViewWelcome = findViewById(R.id.loginTextViewWelcome);
        loginTextViewLogin = findViewById(R.id.loginTextViewLogin);
        loginTextViewEmailAddress = findViewById(R.id.loginTextViewEmailAddress);
        loginTextInputLayoutEmailAddress = findViewById(R.id.loginTextInputLayoutEmailAddress);
        loginTextViewPassword = findViewById(R.id.loginTextViewPassword);
        loginTextInputLayoutPassword = findViewById(R.id.loginTextInputLayoutPassword);

        loginButtonLogin = findViewById(R.id.loginButtonLogin);
        loginButtonSignUp = findViewById(R.id.loginButtonSignUp);

        loginButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                //startActivity(intent);

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

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        loginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = loginTextInputLayoutEmailAddress.getEditText().getText().toString();
                String password = loginTextInputLayoutPassword.getEditText().getText().toString();
                compositeDisposable.add(myAPI.loginUser(emailAddress, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                if(s.contains("password")) {
                                    Intent intent = new Intent(Login.this, PetLoverLostPetAlert.class);

                                    JSONObject jsonObject = new JSONObject(s);
                                    String accountType = jsonObject.getString("accountType");
                                    if (accountType.equals("vet")) {
                                        intent = new Intent(Login.this,VetLostPetAlert.class);
                                    }
                                    Pair[] pairs = new Pair[3];

                                    pairs[0] = new Pair<View,String>(loginImageViewPaw, "mainLogo");
                                    pairs[1] = new Pair<View,String>(loginTextViewWelcome, "loginText");
                                    pairs[2] = new Pair<View,String>(loginTextViewLogin, "loginText2");

                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                                    startActivity(intent, options.toBundle());

                                }
                                else {
                                    Toast.makeText(Login.this, "" + s, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                );
            }
        });
    }
}