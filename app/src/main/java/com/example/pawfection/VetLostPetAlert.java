package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityVetLostPetAlertBinding;
import com.example.pawfection.databinding.ActivityVetNavigationDrawerBaseBinding;

public class VetLostPetAlert extends VetNavigationDrawerBase {

    ActivityVetLostPetAlertBinding activityVetLostPetAlertBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVetLostPetAlertBinding = ActivityVetLostPetAlertBinding.inflate(getLayoutInflater());
        setContentView(activityVetLostPetAlertBinding.getRoot());
    }
}