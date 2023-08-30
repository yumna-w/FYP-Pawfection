package com.example.pawfection;

import static com.example.pawfection.Login.userID;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsVetProfileBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PetLoverVetAppointmentsVetProfile extends PetLoverNavigationDrawerBase {

    ActivityPetLoverVetAppointmentsVetProfileBinding activityPetLoverVetAppointmentsVetProfileBinding;
    Dialog vetProfileDialog;
    Button petLoverVetAppointmentsVetProfileButtonSubmit;
    TextView petLoverVetAppointmentsVetProfileTextViewVetName;
    TextView petLoverVetAppointmentsVetProfileQualification;
    TextView petLoverVetAppointmentsVetProfileTextViewAddress;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    JSONArray jsonArray;
    AutoCompleteTextView petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentDay;
    AutoCompleteTextView petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverVetAppointmentsVetProfileBinding = ActivityPetLoverVetAppointmentsVetProfileBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverVetAppointmentsVetProfileBinding.getRoot());

        petLoverVetAppointmentsVetProfileTextViewVetName = findViewById(R.id.petLoverVetAppointmentsVetProfileTextViewVetName);
        petLoverVetAppointmentsVetProfileQualification = findViewById(R.id.petLoverVetAppointmentsVetProfileQualification);
        petLoverVetAppointmentsVetProfileTextViewAddress = findViewById(R.id.petLoverVetAppointmentsVetProfileTextViewAddress);

        VetItem vet = VetsListData.getInstance().getVetItemByUserId(VetsListData.getInstance().getVet_user_id_pointer());

        petLoverVetAppointmentsVetProfileTextViewVetName.setText("Dr " + vet.getVetName());
        petLoverVetAppointmentsVetProfileQualification.setText(vet.getQualification());
        petLoverVetAppointmentsVetProfileTextViewAddress.setText(vet.getAddress());

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

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        ArrayList<PetItem> temp = new ArrayList<>();
        petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentPet = findViewById(R.id.petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentPet);

        compositeDisposable.add(myAPI.getPets(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        if (!s.contains("False")) {
                            jsonArray = new JSONArray(s);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = null;
                                jsonObject = jsonArray.getJSONObject(i);
                                int pets_id = Integer.parseInt(jsonObject.getString("id"));
                                String petName = jsonObject.getString("petName");
                                String type = jsonObject.getString("type");

                                PetItem tempPet = new PetItem(petName, type, pets_id);
                                temp.add(tempPet);
                            }

                            PetItem[] petItems = temp.toArray(new PetItem[0]);
                            UserData.getInstance().setPetItems(petItems);

                            String[] pets = new String[petItems.length];

                            for (int i = 0; i < petItems.length;i++) {
                                pets[i] = petItems[i].getPetName();
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    PetLoverVetAppointmentsVetProfile.this,
                                    R.layout.drop_down_item_vet_appointments_city,
                                    pets
                            );


                            petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentPet.setAdapter(adapter);

                        }
                    }
                }));

        int vet_id = vet.getUsers_id();
        petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentDay = findViewById(R.id.petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentDay);

        compositeDisposable.add(myAPI.getDays(vet_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        if (!s.contains("False")) {
                            jsonArray = new JSONArray(s);
                            String[] days = new String[jsonArray.length()];

                            for(int i = 0;i < jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                days[i] = jsonObject.getString("day");
                                DayOfWeek dayOfWeek = DayOfWeek.valueOf(days[i].toUpperCase());
                                LocalDate localDate = getFutureDateOfDay(dayOfWeek);

                                // Define the desired format

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                    String formattedDate = localDate.format(formatter);
                                    System.out.println(formattedDate);
                                }
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    PetLoverVetAppointmentsVetProfile.this,
                                    R.layout.drop_down_item_vet_appointments_city,
                                    days
                            );


                            petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentDay.setAdapter(adapter);
                        }

                        else {
                            System.out.println("TEST HAS FAILED............................................");
                        }
                    }
                }));





    }

    private Boolean checkIfEmpty() {
        String pet = petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentPet.getText().toString();
        String day = petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentDay.getText().toString();

        if (pet.isEmpty()) {
            petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentPet.setError("Pet is not selected.");
            return false;
        }
        else if (day.isEmpty()) {
            petLoverVetAppointmentsVetProfileAutoCompleteTextViewAppointmentDay.setError("Day is not selected.");
            return false;
        }
        else {
            return true;
        }
    }

    public static LocalDate getFutureDateOfDay(DayOfWeek dayOfWeek) {
        LocalDate futureDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            DayOfWeek currentDay = today.getDayOfWeek();

            // Calculate the difference in days between today's day and the target day
            int dayDifference = (dayOfWeek.getValue() - currentDay.getValue() + 7) % 7;

            // Calculate the future date based on the difference
            futureDate = today.plusDays(dayDifference);


        }
        return futureDate;
    }
}