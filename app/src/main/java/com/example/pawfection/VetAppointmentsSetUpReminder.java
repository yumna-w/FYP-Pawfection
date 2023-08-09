package com.example.pawfection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pawfection.databinding.ActivityVetAppointmentsSetUpReminderBinding;

public class VetAppointmentsSetUpReminder extends VetNavigationDrawerBase {

    ActivityVetAppointmentsSetUpReminderBinding activityVetAppointmentsSetUpReminderBinding;
    Button vetAppointmentsSetUpReminderButtonProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVetAppointmentsSetUpReminderBinding = ActivityVetAppointmentsSetUpReminderBinding.inflate(getLayoutInflater());
        setContentView(activityVetAppointmentsSetUpReminderBinding.getRoot());

        vetAppointmentsSetUpReminderButtonProceed = findViewById(R.id.vetAppointmentsSetUpReminderButtonProceed);
        vetAppointmentsSetUpReminderButtonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VetAppointmentsSetUpReminder.this,VetAppointmentsSetUpForm.class);
                startActivity(intent);
            }
        });
    }
}