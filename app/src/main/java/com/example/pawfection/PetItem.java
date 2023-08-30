package com.example.pawfection;

public class PetItem {

    private String petName;
    private String petAnimaType;
    private int pets_id;

    public PetItem(String petName, String petAnimaType, int pets_id) {
        this.petName = petName;
        this.petAnimaType = petAnimaType;
        this.pets_id = pets_id;
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

    public int getPetsID() {
        return pets_id;
    }

    public void setPetsID(int pets_id) {
        this.pets_id = pets_id;
    }
}
