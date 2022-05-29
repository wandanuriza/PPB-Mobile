package com.wandasarah.todaynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.iammert.library.readablebottombar.ReadableBottomBar;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar readableBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readableBottomBar = findViewById(R.id.readableBottomBar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,new HomeFragment());
        fragmentTransaction.commit();

        readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                switch (i){

                    case 0 :
                        fragmentTransaction.replace(R.id.content,new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1 :
                        fragmentTransaction.replace(R.id.content,new ScienceFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2 :
                        fragmentTransaction.replace(R.id.content,new SportsFragment());
                        fragmentTransaction.commit();
                        break;

                    case 3 :
                        fragmentTransaction.replace(R.id.content,new HealthFragment());
                        fragmentTransaction.commit();
                        break;

                    case 4 :
                        fragmentTransaction.replace(R.id.content,new EnterFragment());
                        fragmentTransaction.commit();
                        break;

                }
            }
        });
    }
}