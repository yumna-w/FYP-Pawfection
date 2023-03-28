package com.example.pawfection;

public class DynamicRecyclerViewModel {

    String petName;

    public DynamicRecyclerViewModel(String petName) {
        this.petName = petName;
    }

    public String getPetName() {
        return petName;
    }
}
