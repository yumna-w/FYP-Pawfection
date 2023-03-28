package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityPetLoverLostPetAlertBinding;
import com.example.pawfection.databinding.ActivityPetLoverNavigationDrawerBaseBinding;

public class PetLoverLostPetAlert extends PetLoverNavigationDrawerBase {

    ActivityPetLoverLostPetAlertBinding activityPetLoverLostPetAlertBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverLostPetAlertBinding = ActivityPetLoverLostPetAlertBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverLostPetAlertBinding.getRoot());
    }
}