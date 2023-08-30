package com.example.pawfection;

import static com.example.pawfection.Login.userID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pawfection.Retrofit.NodeJS;
import com.example.pawfection.Retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PetLoverNavigationDrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    JSONArray jsonArray;

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
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_pet_lover_navigation_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view_pet_lover);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().equals("Lost Pet Alert")) {
            Intent intent = new Intent(getApplicationContext(), PetLoverLostPetAlert.class);
            startActivity(intent);
        }
        else if (item.getTitle().toString().equals("Log out")) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            Toast.makeText(PetLoverNavigationDrawerBase.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().toString().equals("Vet Appointments")) {
            Retrofit retrofit = RetrofitClient.getInstance();
            myAPI = retrofit.create(NodeJS.class);

            compositeDisposable.add(myAPI.getPets(userID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Throwable {
                            Intent intent;
                            if (s.contains("False")) {
                                intent = new Intent(getApplicationContext(), PetLoverPetSetUpReminder.class);
                            }
                            else {
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

                                                                    UserData.getInstance().setPetItems(petItems);
                                                                }
                                                            }
                                                        }));
                                            }
                                        }));
                                intent = new Intent(getApplicationContext(), PetLoverVetAppointmentsVetsList.class);
                            }
                            startActivity(intent);
                        }
                    }));
        }
        else if (item.getTitle().toString().equals("Shop")) {
            Intent intent = new Intent(getApplicationContext(), PetLoverShopCategories.class);
            startActivity(intent);
        }
        else if (item.getTitle().toString().equals("Edit Profile")) {
            Intent intent = new Intent(getApplicationContext(), PetLoverEditProfile.class);
            startActivity(intent);
        }
        return false;
    }

    protected void allocateActivityTitle(String titleString) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}