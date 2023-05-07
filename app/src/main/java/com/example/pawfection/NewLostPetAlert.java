package com.example.pawfection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pawfection.databinding.ActivityNewLostPetAlertBinding;

public class NewLostPetAlert extends PetLoverNavigationDrawerBase {

    ActivityNewLostPetAlertBinding activityNewLostPetAlertBinding;
    private Button newLostPetAlertButtonSelectFromGallery;
    private ImageView newLostPetAlertImageViewPet;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewLostPetAlertBinding = ActivityNewLostPetAlertBinding.inflate(getLayoutInflater());
        setContentView(activityNewLostPetAlertBinding.getRoot());

        newLostPetAlertButtonSelectFromGallery = findViewById(R.id.newLostPetAlertButtonSelectFromGallery);
        newLostPetAlertImageViewPet = findViewById(R.id.newLostPetAlertImageViewPet);

        newLostPetAlertButtonSelectFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                newLostPetAlertImageViewPet.setImageURI(data.getData());
            }
        }
    }
}