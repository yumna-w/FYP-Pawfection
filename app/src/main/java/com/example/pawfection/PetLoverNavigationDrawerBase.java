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

import com.google.android.material.navigation.NavigationView;

public class PetLoverNavigationDrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

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
            Intent intent = new Intent(getApplicationContext(), PetLoverVetAppointmentsCity.class);
            startActivity(intent);
        }
        else if (item.getTitle().toString().equals("Shop")) {
            Intent intent = new Intent(getApplicationContext(), PetLoverShopCategories.class);
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