package com.example.pawfection;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsVetProfileBinding;

public class PetLoverVetAppointmentsVetProfile extends PetLoverNavigationDrawerBase {

    ActivityPetLoverVetAppointmentsVetProfileBinding activityPetLoverVetAppointmentsVetProfileBinding;
    Dialog vetProfileDialog;
    Button petLoverVetAppointmentsVetProfileButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverVetAppointmentsVetProfileBinding = ActivityPetLoverVetAppointmentsVetProfileBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverVetAppointmentsVetProfileBinding.getRoot());

        vetProfileDialog = new Dialog(PetLoverVetAppointmentsVetProfile.this);
        vetProfileDialog.setContentView(R.layout.dialog_vet_profile);
        vetProfileDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog_sign_up));
        vetProfileDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vetProfileDialog.setCancelable(false);

        Button dialogVetProfileButtonSubmit = vetProfileDialog.findViewById(R.id.vetProfileDialogButtonSubmit);
        dialogVetProfileButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vetProfileDialog.dismiss();
            }
        });

        petLoverVetAppointmentsVetProfileButtonSubmit = findViewById(R.id.petLoverVetAppointmentsVetProfileButtonSubmit);
        petLoverVetAppointmentsVetProfileButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vetProfileDialog.show();
            }
        });
    }
}