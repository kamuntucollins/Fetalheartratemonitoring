package com.example.fetalheartratemonitoring;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.Activity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Activity_fragment()).commit();
                break;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Settings_fragment()).commit();
                break;
            case R.id.Mysavings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Mysavings_fragment()).commit();
                break;

            case R.id.Tips:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Tips_fragment()).commit();
                break;
            case R.id.Visit:
                Toast.makeText(this, "wait as we connect you to the internet!", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this, Visitweb.class);
                startActivity(myintent);
                break;
            case R.id.Exit:
                Toast.makeText(this, "wait for exit please!", Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Activity_fragment()).commit();
            navigationView.setCheckedItem(R.id.Activity);
        }



    }
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.majormenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int a = item.getItemId();
        switch (a) {
            case R.id.Start_Monitoring:
                Intent myintent1 = new Intent(MainActivity.this, Activity_process_monitoring.class);
                startActivity(myintent1);
                break;

            case R.id.Visit:
                Intent myintent2 = new Intent(MainActivity.this, Visitweb.class);
                startActivity(myintent2);
                break;
            case R.id.logout:
                Intent myintent3 = new Intent(MainActivity.this, Login.class);
                startActivity(myintent3);
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }



}
