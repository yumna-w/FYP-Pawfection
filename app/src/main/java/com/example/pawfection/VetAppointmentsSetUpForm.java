package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityVetAppointmentsSetUpFormBinding;

public class VetAppointmentsSetUpForm extends VetNavigationDrawerBase {

    ActivityVetAppointmentsSetUpFormBinding activityVetAppointmentsSetUpFormBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVetAppointmentsSetUpFormBinding = ActivityVetAppointmentsSetUpFormBinding.inflate(getLayoutInflater());
        setContentView(activityVetAppointmentsSetUpFormBinding.getRoot());
    }
}