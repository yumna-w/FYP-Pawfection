package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityPetLoverEditProfileBinding;

public class PetLoverEditProfile extends PetLoverNavigationDrawerBase {

    ActivityPetLoverEditProfileBinding activityPetLoverEditProfileBinding;
    RecyclerView petLoverEditProfileRecyclerViewCurrentPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverEditProfileBinding = ActivityPetLoverEditProfileBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverEditProfileBinding.getRoot());

        petLoverEditProfileRecyclerViewCurrentPets = findViewById(R.id.petLoverEditProfileRecyclerViewCurrentPets);
        petLoverEditProfileRecyclerViewCurrentPets.setHasFixedSize(true);
        petLoverEditProfileRecyclerViewCurrentPets.setLayoutManager(new LinearLayoutManager(this));

        PetItem[] petItems = new PetItem[]{
                new PetItem("Tom", "Dog"),
                new PetItem("Mithu", "Bird"),
        };

        PetItemAdapter petItemAdapter = new PetItemAdapter(petItems,PetLoverEditProfile.this);
        petLoverEditProfileRecyclerViewCurrentPets.setAdapter(petItemAdapter);
    }
}