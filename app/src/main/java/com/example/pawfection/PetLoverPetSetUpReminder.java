package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityPetLoverPetSetUpReminderBinding;

public class PetLoverPetSetUpReminder extends PetLoverNavigationDrawerBase {

    ActivityPetLoverPetSetUpReminderBinding activityPetLoverPetSetUpReminderBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverPetSetUpReminderBinding = ActivityPetLoverPetSetUpReminderBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverPetSetUpReminderBinding.getRoot());
    }
}