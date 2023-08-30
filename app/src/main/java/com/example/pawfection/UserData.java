package com.example.pawfection;

public class UserData {

    private static UserData instance;
    private int users_id;
    private PetItem[] petItems;

    private UserData() {
        // Private constructor to prevent instantiation
    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setPetItems(PetItem[] petItems) {
        this.petItems = petItems;
    }

    public PetItem[] getPetItems() {
        return petItems;
    }
}
