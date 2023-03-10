package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityPetLoverNavigationDrawerBaseBinding;

public class PetLoverLostPetAlert extends PetLoverNavigationDrawerBase {

    ActivityPetLoverNavigationDrawerBaseBinding activityPetLoverNavigationDrawerBaseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverNavigationDrawerBaseBinding = ActivityPetLoverNavigationDrawerBaseBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverNavigationDrawerBaseBinding.getRoot());
    }
}