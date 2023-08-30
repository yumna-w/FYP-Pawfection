package com.example.pawfection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityVetAppointmentsSetUpFormBinding;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class VetAppointmentsSetUpForm extends VetNavigationDrawerBase {

    ActivityVetAppointmentsSetUpFormBinding activityVetAppointmentsSetUpFormBinding;
    TextInputLayout vetAppointmentsSetUpFormTextInputLayoutQualification;
    TextInputLayout vetAppointmentsSetUpFormTextInputLayoutAddress;
    TextInputLayout vetAppointmentsSetUpFormTextInputLayoutArea;
    Button vetAppointmentsSetUpFormButtonSubmit;
    CheckBox vetAppointmentsSetUpFormCheckBoxMonday;
    CheckBox vetAppointmentsSetUpFormCheckBoxTuesday;
    CheckBox vetAppointmentsSetUpFormCheckBoxWednesday;
    CheckBox vetAppointmentsSetUpFormCheckBoxThursday;
    CheckBox vetAppointmentsSetUpFormCheckBoxFriday;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVetAppointmentsSetUpFormBinding = ActivityVetAppointmentsSetUpFormBinding.inflate(getLayoutInflater());
        setContentView(activityVetAppointmentsSetUpFormBinding.getRoot());

        vetAppointmentsSetUpFormTextInputLayoutQualification = findViewById(R.id.vetAppointmentsSetUpFormTextInputLayoutQualification);
        vetAppointmentsSetUpFormTextInputLayoutAddress = findViewById(R.id.vetAppointmentsSetUpFormTextInputLayoutAddress);
        vetAppointmentsSetUpFormTextInputLayoutArea = findViewById(R.id.vetAppointmentsSetUpFormTextInputLayoutArea);
        vetAppointmentsSetUpFormButtonSubmit = findViewById(R.id.vetAppointmentsSetUpFormButtonSubmit);
        vetAppointmentsSetUpFormCheckBoxMonday = findViewById(R.id.vetAppointmentsSetUpFormCheckBoxMonday);
        vetAppointmentsSetUpFormCheckBoxTuesday = findViewById(R.id.vetAppointmentsSetUpFormCheckBoxTuesday);
        vetAppointmentsSetUpFormCheckBoxWednesday = findViewById(R.id.vetAppointmentsSetUpFormCheckBoxWednesday);
        vetAppointmentsSetUpFormCheckBoxThursday = findViewById(R.id.vetAppointmentsSetUpFormCheckBoxThursday);
        vetAppointmentsSetUpFormCheckBoxFriday = findViewById(R.id.vetAppointmentsSetUpFormCheckBoxFriday);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        vetAppointmentsSetUpFormButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int users_id = 76;
                //int users_id = UserData.getInstance().getUsers_id();
                String qualification = vetAppointmentsSetUpFormTextInputLayoutQualification.getEditText().getText().toString();
                String address = vetAppointmentsSetUpFormTextInputLayoutAddress.getEditText().getText().toString();
                String area = vetAppointmentsSetUpFormTextInputLayoutArea.getEditText().getText().toString();
                String days = "";

                if (vetAppointmentsSetUpFormCheckBoxMonday.isChecked()) {
                    days += "Monday,";
                }
                if (vetAppointmentsSetUpFormCheckBoxTuesday.isChecked()) {
                    days += "Tuesday,";
                }
                if (vetAppointmentsSetUpFormCheckBoxWednesday.isChecked()) {
                    days += "Wednesday,";
                }
                if (vetAppointmentsSetUpFormCheckBoxThursday.isChecked()) {
                    days += "Thursday,";
                }
                if (vetAppointmentsSetUpFormCheckBoxThursday.isChecked()){
                    days += "Friday,";
                }

                days = days.substring(0, days.length() - 1);

                compositeDisposable.add(myAPI.createVetClinic(users_id,qualification,address,area,days)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Intent intent = new Intent(VetAppointmentsSetUpForm.this,VetAppointmentsViewAppointments.class);
                                startActivity(intent);
                            }
                        }));
            }
        });
    }
}