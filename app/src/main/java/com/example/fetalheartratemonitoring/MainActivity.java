package com.example.fetalheartratemonitoring;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
            case R.id.Mysavings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Mysavings_fragment()).commit();
                break;

            case R.id.Activity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Activity_fragment()).commit();
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
               exitMethod();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void exitMethod(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit The Activity");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("are you sure you want to Exit");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
      dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Mysavings_fragment()).commit();
            navigationView.setCheckedItem(R.id.Mysavings);
        }



    }
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




}
