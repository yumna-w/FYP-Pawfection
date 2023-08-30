package com.example.pawfection;

import static com.example.pawfection.Login.userID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsCityBinding;
import com.example.pawfection.databinding.ActivityPetLoverVetAppointmentsVetsListBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PetLoverVetAppointmentsVetsList extends PetLoverNavigationDrawerBase {

    ActivityPetLoverVetAppointmentsVetsListBinding activityPetLoverVetAppointmentsVetsListBinding;
    private RecyclerView petLoverVetAppointmentsVetsListRecyclerView;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    JSONArray jsonArray, jsonArray2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverVetAppointmentsVetsListBinding = ActivityPetLoverVetAppointmentsVetsListBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverVetAppointmentsVetsListBinding.getRoot());

        petLoverVetAppointmentsVetsListRecyclerView = findViewById(R.id.petLoverVetAppointmentsListRecyclerView);
        petLoverVetAppointmentsVetsListRecyclerView.setHasFixedSize(true);
        petLoverVetAppointmentsVetsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        ArrayList<VetItem> temp = new ArrayList<>();

        String fullName;

        compositeDisposable.add(myAPI.getVets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        if (!s.contains("False")) {
                            jsonArray = new JSONArray(s);
                            AtomicInteger fetchedVetNames = new AtomicInteger(0);
                            int totalVetItems = jsonArray.length(); // Total number of vet items


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = null;
                                jsonObject = jsonArray.getJSONObject(i);
                                int vet_clinic_id = Integer.parseInt(jsonObject.getString("id"));
                                int users_id = Integer.parseInt(jsonObject.getString("users_id"));
                                String qualification = jsonObject.getString("qualification");
                                String address = jsonObject.getString("address");
                                String area = jsonObject.getString("area");

                                VetItem tempVet = new VetItem(users_id, vet_clinic_id, qualification, address, area);

                                compositeDisposable.add(myAPI.getName(users_id)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<String>() {
                                            @Override
                                            public void accept(String s) throws Throwable {
                                                if (!s.contains("False")) {
                                                    jsonArray2 = new JSONArray(s);
                                                    JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
                                                    String fullName = jsonObject2.getString("fullName");
                                                    tempVet.setVetName(fullName);
                                                    System.out.println(tempVet.getVetName());
                                                    int fetchedCount = fetchedVetNames.incrementAndGet(); // Increment and get the new value
                                                    if (fetchedCount == jsonArray.length()) {
                                                        // All vet names fetched, update RecyclerView
                                                        VetItem[] vetItems = temp.toArray(new VetItem[0]);

                                                        VetItemAdapter vetItemAdapter = new VetItemAdapter(vetItems,PetLoverVetAppointmentsVetsList.this);
                                                        petLoverVetAppointmentsVetsListRecyclerView.setAdapter(vetItemAdapter);

                                                        VetsListData.getInstance().setVetItems(vetItems);
                                                    }
                                                }
                                            }
                                        }));


                                temp.add(tempVet);
                            }


                        }
                    }
                }));




    }
}