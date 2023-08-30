package com.example.pawfection;

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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class VetNavigationDrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout vetDrawerLayout;
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    JSONArray jsonArray;

    @Override
    public void setContentView(View view) {
        vetDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_vet_navigation_drawer_base, null);
        FrameLayout vetContainer = vetDrawerLayout.findViewById(R.id.activityContainer);
        vetContainer.addView(view);
        super.setContentView(vetDrawerLayout);

        Toolbar vetToolbar = vetDrawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(vetToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView vetNavigationView = vetDrawerLayout.findViewById(R.id.nav_view_vet);
        vetNavigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle vetToggle = new ActionBarDrawerToggle(this, vetDrawerLayout, vetToolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        vetDrawerLayout.addDrawerListener(vetToggle);
        vetToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getTitle().toString().equals("Lost Pet Alert")) {
            Intent intent = new Intent(getApplicationContext(), VetLostPetAlert.class);
            startActivity(intent);
        }
        else if (item.getTitle().toString().equals("Log out")) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            Toast.makeText(VetNavigationDrawerBase.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().toString().equals("Appointments")) {
            Retrofit retrofit = RetrofitClient.getInstance();
            myAPI = retrofit.create(NodeJS.class);

            compositeDisposable.add(myAPI.checkSetUpForm(UserData.getInstance().getUsers_id())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Throwable {
                            Intent intent;
                            if (s.contains("False")) {
                                intent = new Intent(getApplicationContext(), VetAppointmentsSetUpReminder.class);
                            }
                            else {
                                intent = new Intent(getApplicationContext(), VetAppointmentsViewAppointments.class);
                            }
                            startActivity(intent);
                        }
                    }));
        }
        return false;
    }

    protected void allocateActivityTitle(String titleString) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}