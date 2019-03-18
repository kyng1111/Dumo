package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    BottomNavigationView bottomNavigationView;
    Fragment fragment1;

    Sign_in_activity sign_in_activity = (Sign_in_activity)Sign_in_activity.Sign_in_activity;
    Sign_up_activity sign_up_activity = (Sign_up_activity)Sign_up_activity.Sign_up_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_in_activity.finish();
        sign_up_activity.finish();

        fragment1 = new FirstFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.fragment_place, fragment1);
        fragmentTransaction.commit();




        textView = (TextView)findViewById(R.id.helloworld);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    default:
                    case R.id.action_first:
                        fragment1 = new FirstFragment();
                        break;
                    case R.id.action_second:
                        fragment1 = new SecondFragment();
                        break;
                    case R.id.action_third:
                        fragment1 = new ThirdFragment();
                        break;
                    case R.id.action_fourth:
                        fragment1 = new FourthFragment();
                        break;
                    case R.id.action_fifth:
                        fragment1 = new FifthFragment();
                        break;
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.fragment_place, fragment1);
                fragmentTransaction.commit();

                return true;
            }
        });


    }
}
