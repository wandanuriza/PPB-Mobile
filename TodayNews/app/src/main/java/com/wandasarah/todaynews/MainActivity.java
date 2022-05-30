package com.wandasarah.todaynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.iammert.library.readablebottombar.ReadableBottomBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar readableBottomBar;

    String API_KEY = "8372ecb6783543d3a7d2bb38d4f5777e";
    Adapter adapter;
    ArrayList<Model> modelArrayList;

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
                        fragmentTransaction.replace(R.id.content,new ScienceFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1 :
                        fragmentTransaction.replace(R.id.content,new SportsFragment());
                        fragmentTransaction.commit();
                        break;

                    case 2 :

                        fragmentTransaction.replace(R.id.content,new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case 3 :
                        fragmentTransaction.replace(R.id.content,new HealthFragment());
                        fragmentTransaction.commit();
                        break;

                    case 4 :
                        fragmentTransaction.replace(R.id.content,new TechnologyFragment());
                        fragmentTransaction.commit();
                        break;
                }
            }
        });
    }


    public void LoadJson(final String keyword) {


        //instansiasi ApiInterface
        ApiInterface apiInterface = ApiUtilities.getApiInterface();

        String country = "id";

        Call<MainNews> call;
        if (keyword.length() > 0){
            call = apiInterface.getNewsSearch(keyword, API_KEY);
        } else {
            call = apiInterface.getNews(country,100,API_KEY);
        }

        call.enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    if (!modelArrayList.isEmpty()){
                        modelArrayList.clear();
                    }
                }

                modelArrayList = response.body().getArticles();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest  News...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 2){
                    LoadJson(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LoadJson(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }
}