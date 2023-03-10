package com.example.pawfection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class VetNavigationDrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout vetDrawerLayout;

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
        return false;
    }

    protected void allocateActivityTitle(String titleString) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}