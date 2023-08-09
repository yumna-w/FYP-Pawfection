package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pawfection.databinding.ActivityPetLoverShopCategoriesBinding;

public class PetLoverShopCategories extends PetLoverNavigationDrawerBase {

    ActivityPetLoverShopCategoriesBinding activityPetLoverShopCategoriesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverShopCategoriesBinding = ActivityPetLoverShopCategoriesBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverShopCategoriesBinding.getRoot());
    }
}