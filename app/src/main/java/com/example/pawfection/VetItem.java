package com.example.pawfection;

public class VetItem {

    private String vetName;
    private String vetArea;

    public VetItem(String vetName, String vetArea) {
        this.vetName = vetName;
        this.vetArea = vetArea;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getVetArea() {
        return vetArea;
    }

    public void setVetArea(String vetArea) {
        this.vetArea = vetArea;
    }
}
