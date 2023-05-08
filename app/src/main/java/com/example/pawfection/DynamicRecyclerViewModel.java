package com.example.pawfection;

public class DynamicRecyclerViewModel {

    String petName;
    String lastSeen;
    String contactNumber;

    public DynamicRecyclerViewModel(String petName, String lastSeen, String contactNumber) {
        this.petName = petName;
        this.lastSeen = lastSeen;
        this.contactNumber = contactNumber;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
