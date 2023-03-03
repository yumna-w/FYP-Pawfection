package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    ImageView mainLogo;
    TextView mainSlogan;
    private static int SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        mainLogo = findViewById(R.id.mainLogo);
        mainSlogan = findViewById(R.id.mainSlogan);

        mainLogo.setAnimation(topAnimation);
        mainSlogan.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                //startActivity(intent);
                //finish();

                Pair pair = new Pair<View,String>(mainLogo, "mainLogo");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pair);
                startActivity(intent,options.toBundle());
                finish();
            }
        }, SPLASH_SCREEN);
    }
}