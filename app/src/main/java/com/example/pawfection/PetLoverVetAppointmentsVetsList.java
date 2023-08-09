package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsCityBinding;
import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsVetsListBinding;

public class PetLoverVetAppointmentsVetsList extends PetLoverNavigationDrawerBase {

    ActivityPetLoverVetAppointmentsVetsListBinding activityPetLoverVetAppointmentsVetsListBinding;
    private RecyclerView petLoverVetAppointmentsVetsListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverVetAppointmentsVetsListBinding = ActivityPetLoverVetAppointmentsVetsListBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverVetAppointmentsVetsListBinding.getRoot());

        petLoverVetAppointmentsVetsListRecyclerView = findViewById(R.id.petLoverVetAppointmentsListRecyclerView);
        petLoverVetAppointmentsVetsListRecyclerView.setHasFixedSize(true);
        petLoverVetAppointmentsVetsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        VetItem[] vetItems = new VetItem[]{
                new VetItem("Dr Sarah", "Korangi"),
                new VetItem("Dr Ahmed", "F. B. Area"),
                new VetItem("Dr Khan", "DHA Phase 2 Extension"),
                new VetItem("Dr Imran", "North Nazimabad"),
                new VetItem("Dr Farah", "Clifton"),
        };

        VetItemAdapter vetItemAdapter = new VetItemAdapter(vetItems, PetLoverVetAppointmentsVetsList.this);
        petLoverVetAppointmentsVetsListRecyclerView.setAdapter(vetItemAdapter);



    }
}