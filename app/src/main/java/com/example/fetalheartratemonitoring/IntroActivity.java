package com.example.fetalheartratemonitoring;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenpager;
    IntroViewPagerAdapter introViewPagerAdapter;
    Button btnnext;
    int position = 0;
    Button buttonGetStarted;
    Animation animation;

    TabLayout tabindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()) {
            Intent myintent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(myintent);
            finish();
        }

requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_intro);
        getSupportActionBar().hide();

        //initaialising the views
        tabindicator = findViewById(R.id.tab_indicator);

        //when the activity is about to be opened when to check if its open or not


        //fill the list screen
        final List<screenitem> mlist = new ArrayList<>();
        mlist.add(new screenitem("Fetal View", "It is vital to view and monitor the fetus at early stages,mid and at the end of the pregmancy period", R.mipmap.pregnant));
        mlist.add(new screenitem("View  Fetus", "You can view the fetus using various techniques,radiology is an essential element that helps us to register looks onto nody figures", R.mipmap.unborn));
        mlist.add(new screenitem("Documents", "Remember to save your measurements in my savings which can be accessed  on the top left           ", R.mipmap.doc));

        //setting up the viewpager

        screenpager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mlist);
        screenpager.setAdapter(introViewPagerAdapter);

        //settings up the tab indicators
        tabindicator.setupWithViewPager(screenpager);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animate);

        btnnext = findViewById(R.id.btn_next);
        buttonGetStarted = findViewById(R.id.btn_get_started);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenpager.getCurrentItem();
                if (position < mlist.size()) {
                    position++;
                    screenpager.setCurrentItem(position);
                }
                if (position == mlist.size() - 1) {
                    //when we reach the last screen
                    //TODO:LOAD THE GET START BUTTON SCREEN AND HIDE THE INDICATOR and next button
                    loadLastScreen();

                }

            }
        });


        //tablayout add change listener
        tabindicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mlist.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //getstarted buttonclick to open new activity
        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening main activity

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //set aboolean variable to storage to check if the intro screen has been visited
                //use of shared preferences to this part

                savePrefsData();
                finish();

            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref=getApplicationContext().getSharedPreferences("MYPREFERENCES",MODE_PRIVATE);
        Boolean isIntroActivityopenedBefore=pref.getBoolean("is_the_intro_slide_opened",false);
        return  isIntroActivityopenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MYPREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("is_the_intro_slide_opened", true);
        editor.commit();
    }

    private void loadLastScreen() {

        btnnext.setVisibility(View.INVISIBLE);
        buttonGetStarted.setVisibility(View.VISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);

//TODO:adding and and animation to the getstarted button

        //adding the button animation
        buttonGetStarted.setAnimation(animation);
    }
}
