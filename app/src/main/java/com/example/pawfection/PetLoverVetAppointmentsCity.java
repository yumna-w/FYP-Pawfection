package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsCityBinding;

public class PetLoverVetAppointmentsCity extends PetLoverNavigationDrawerBase {

    ActivityPetLoverVetAppointmentsCityBinding activityPetLoverVetAppointmentsCityBinding;
    private Button petLoverVetAppointmentsCityButtonSubmit;
    private ImageView petLoverVetAppointmentsCityImageViewPaw;
    private TextView petLoverVetAppointmentsCityTextView;
    private TextView petLoverVetAppointmentsCityTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverVetAppointmentsCityBinding = ActivityPetLoverVetAppointmentsCityBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverVetAppointmentsCityBinding.getRoot());

        String[] city = new String[] {"Karachi", "Lahore", "Islamabad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item_vet_appointments_city,
                city
        );

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.petLoverVetAppointmentsCityAutoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

        petLoverVetAppointmentsCityImageViewPaw = findViewById(R.id.petLoverLostPetAlertImageViewPaw);
        petLoverVetAppointmentsCityTextView = findViewById(R.id.petLoverVetAppointmentsCityTextView);
        petLoverVetAppointmentsCityTextView2 = findViewById(R.id.petLoverVetAppointmentsCityTextView2);
        petLoverVetAppointmentsCityButtonSubmit = findViewById(R.id.petLoverVetAppointmentsCityButtonSubmit);

        petLoverVetAppointmentsCityButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(PetLoverVetAppointmentsCity.this,PetLoverVetAppointmentsVetsList.class);
                Pair[] pairs = new Pair[3];

                pairs[0] = new Pair<View,String>(petLoverVetAppointmentsCityImageViewPaw, "mainLogo");
                pairs[1] = new Pair<View,String>(petLoverVetAppointmentsCityTextView, "loginText");
                pairs[2] = new Pair<View,String>(petLoverVetAppointmentsCityTextView2, "loginText2");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PetLoverVetAppointmentsCity.this,pairs);
                startActivity(intent, options.toBundle());*/

                Intent intent = new Intent(PetLoverVetAppointmentsCity.this,PetLoverVetAppointmentsVetsList.class);
                startActivity(intent);
            }
        });

    }
}