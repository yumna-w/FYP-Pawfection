package com.example.pawfection;

import static com.example.pawfection.Login.userID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityPetLoverEditProfileAddPetBinding;
import com.example.pawfection.databinding.ActivityPetLoverEditProfileBinding;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PetLoverEditProfileAddPet extends PetLoverNavigationDrawerBase {

    ActivityPetLoverEditProfileAddPetBinding activityPetLoverEditProfileAddPetBinding;
    TextInputLayout petLoverEditProfileAddPetTextInputLayoutPetName;
    TextInputLayout petLoverEditProfileAddPetTextInputLayoutType;
    TextInputLayout petLoverEditProfileAddPetTextInputLayoutAge;
    TextInputLayout petLoverEditProfileAddPetTextInputLayoutBreed;
    TextInputLayout petLoverEditProfileAddPetTextInputLayoutColour;
    String gender = "";
    RadioGroup petLoverEditProfileAddPetRadioGroup;
    Button petLoverEditProfileAddPetButtonSubmit;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverEditProfileAddPetBinding = ActivityPetLoverEditProfileAddPetBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverEditProfileAddPetBinding.getRoot());

        String[] animalType = new String[] {"Cat", "Dog", "Bird"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item_vet_appointments_city,
                animalType
        );

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.petLoverEditProfileAddPetAutoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

        petLoverEditProfileAddPetTextInputLayoutPetName = findViewById(R.id.petLoverEditProfileAddPetTextInputLayoutPetName);
        petLoverEditProfileAddPetTextInputLayoutType = findViewById(R.id.petLoverEditProfileAddPetTextInputLayoutType);
        petLoverEditProfileAddPetTextInputLayoutAge = findViewById(R.id.petLoverEditProfileAddPetTextInputLayoutAge);
        petLoverEditProfileAddPetTextInputLayoutBreed = findViewById(R.id.petLoverEditProfileAddPetTextInputLayoutBreed);
        petLoverEditProfileAddPetTextInputLayoutColour = findViewById(R.id.petLoverEditProfileAddPetTextInputLayoutColour);
        petLoverEditProfileAddPetRadioGroup = findViewById(R.id.petLoverEditProfileAddPetRadioGroup);
        petLoverEditProfileAddPetButtonSubmit = findViewById(R.id.petLoverEditProfileAddPetButtonSubmit);

        petLoverEditProfileAddPetRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.petLoverEditProfileAddPetRadioButtonMale:
                        gender = "Male";
                        break;
                    case R.id.petLoverEditProfileAddPetRadioButtonFemale:
                        gender = "Female";
                        break;
                }
            }
        });

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        petLoverEditProfileAddPetButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int users_id = userID;
                String petName = petLoverEditProfileAddPetTextInputLayoutPetName.getEditText().getText().toString();
                String type = petLoverEditProfileAddPetTextInputLayoutType.getEditText().getText().toString();
                int age = Integer.parseInt(petLoverEditProfileAddPetTextInputLayoutAge.getEditText().getText().toString());
                String breed = petLoverEditProfileAddPetTextInputLayoutBreed.getEditText().getText().toString();
                String color = petLoverEditProfileAddPetTextInputLayoutColour.getEditText().getText().toString();

                compositeDisposable.add(myAPI.createNewPet(users_id, petName, type, age, breed, color, gender)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Toast.makeText(PetLoverEditProfileAddPet.this,s, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(PetLoverEditProfileAddPet.this,PetLoverEditProfile.class);
                                startActivity(intent);
                            }
                        }));
            }
        });



    }
}