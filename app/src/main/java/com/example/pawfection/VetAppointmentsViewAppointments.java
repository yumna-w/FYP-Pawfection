package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityVetAppointmentsViewAppointmentsBinding;

public class VetAppointmentsViewAppointments extends VetNavigationDrawerBase {

    ActivityVetAppointmentsViewAppointmentsBinding activityVetAppointmentsViewAppointmentsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVetAppointmentsViewAppointmentsBinding = ActivityVetAppointmentsViewAppointmentsBinding.inflate(getLayoutInflater());
        setContentView(activityVetAppointmentsViewAppointmentsBinding.getRoot());
    }
}