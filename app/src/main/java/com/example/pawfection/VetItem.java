package com.example.pawfection;

public class VetItem {

    private String vetName;
    private String area;
    private String qualification;
    private String address;
    private int users_id;
    private int vet_clinic_id;
    private String days;

    public VetItem(int users_id, int vet_clinic_id, String qualification, String address, String area) {
        this.users_id = users_id;
        this.vet_clinic_id = vet_clinic_id;
        this.qualification = qualification;
        this.address = address;
        this.area = area;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getVet_clinic_id() {
        return vet_clinic_id;
    }

    public void setVet_clinic_id(int vet_clinic_id) {
        this.vet_clinic_id = vet_clinic_id;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
