package com.example.pawfection;

public class PetItem {

    private String petName;
    private String petAnimaType;

    public PetItem(String petName, String petAnimaType) {
        this.petName = petName;
        this.petAnimaType = petAnimaType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAnimaType() {
        return petAnimaType;
    }

    public void setPetAnimaType(String petAnimaType) {
        this.petAnimaType = petAnimaType;
    }
}
