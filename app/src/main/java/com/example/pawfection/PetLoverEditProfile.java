package com.example.pawfection;

import static com.example.pawfection.Login.userID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.example.pawfection.databinding.ActivityPetLoverEditProfileBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PetLoverEditProfile extends PetLoverNavigationDrawerBase {

    ActivityPetLoverEditProfileBinding activityPetLoverEditProfileBinding;
    RecyclerView petLoverEditProfileRecyclerViewCurrentPets;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    JSONArray jsonArray;

    Button petLoverEditProfileButtonAddPet;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPetLoverEditProfileBinding = ActivityPetLoverEditProfileBinding.inflate(getLayoutInflater());
        setContentView(activityPetLoverEditProfileBinding.getRoot());

        petLoverEditProfileRecyclerViewCurrentPets = findViewById(R.id.petLoverEditProfileRecyclerViewCurrentPets);
        petLoverEditProfileRecyclerViewCurrentPets.setHasFixedSize(true);
        petLoverEditProfileRecyclerViewCurrentPets.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        ArrayList<PetItem> temp = new ArrayList<>();

        compositeDisposable.add(myAPI.getPets(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
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

                                                PetItemAdapter petItemAdapter = new PetItemAdapter(petItems,PetLoverEditProfile.this);
                                                petLoverEditProfileRecyclerViewCurrentPets.setAdapter(petItemAdapter);
                                            }
                                    }
                                }));
                    }
                }));

        petLoverEditProfileButtonAddPet = findViewById(R.id.petLoverEditProfileButtonAddPet);

        petLoverEditProfileButtonAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetLoverEditProfile.this,PetLoverEditProfileAddPet.class);
                startActivity(intent);
            }
        });

    }

    public void onResume() {
        super.onResume();

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        ArrayList<PetItem> temp = new ArrayList<>();

        compositeDisposable.add(myAPI.getPets(userID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
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

                                            PetItemAdapter petItemAdapter = new PetItemAdapter(petItems,PetLoverEditProfile.this);
                                            petLoverEditProfileRecyclerViewCurrentPets.setAdapter(petItemAdapter);
                                        }
                                    }
                                }));
                    }
                }));

    }
}