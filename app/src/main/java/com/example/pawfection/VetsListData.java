package com.example.pawfection;

public class VetsListData {

    private static VetsListData instance;
    private VetItem[] vetItems;
    private int vet_user_id_pointer;

    private VetsListData() {

    }

    public static VetsListData getInstance() {
        if (instance == null) {
            instance = new VetsListData();
        }
        return instance;
    }

    public VetItem[] getVetItems() {
        return vetItems;
    }

    public void setVetItems(VetItem[] vetItems) {
        this.vetItems = vetItems;
    }

    public VetItem getVetItemByUserId(int users_id) {
        if (vetItems != null) {
            for (VetItem vetItem : vetItems) {
                if (vetItem.getUsers_id() == users_id) {
                    return vetItem;
                }
            }
        }
        return null; // Return null if no match is found
    }

    public int getVet_user_id_pointer() {
        return vet_user_id_pointer;
    }

    public void setVet_user_id_pointer(int vet_user_id_pointer) {
        this.vet_user_id_pointer = vet_user_id_pointer;
    }
}
