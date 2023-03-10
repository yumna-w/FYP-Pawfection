package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityVetLostPetAlertBinding;
import com.example.pawfection.databinding.ActivityVetNavigationDrawerBaseBinding;

public class VetLostPetAlert extends VetNavigationDrawerBase {

    ActivityVetNavigationDrawerBaseBinding activityVetNavigationDrawerBaseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVetNavigationDrawerBaseBinding = ActivityVetNavigationDrawerBaseBinding.inflate(getLayoutInflater());
        setContentView(activityVetNavigationDrawerBaseBinding.getRoot());
    }
}