package com.example.benjaminbouyer.applieseo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ListView;

import com.example.benjaminbouyer.applieseo.models.People;
import com.example.benjaminbouyer.applieseo.models.SWModelList;
import com.example.benjaminbouyer.applieseo.models.Starship;
import com.example.benjaminbouyer.applieseo.sw.StarWarsApi;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StarshipsActivity extends AppCompatActivity {

    public static Intent getStartIntent(final Context ctx) {
        return new Intent(ctx, StarshipsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships);
        setTitle(R.string.starship_activity_name);

        final ArrayList<Starship> listOfStarship = new ArrayList<Starship>();

        final StarshipsAdapter starshipAdapter = new StarshipsAdapter(this, listOfStarship, deviceSelectedListener);


        final ListView list = (ListView) findViewById(R.id.listStarship);
        list.setAdapter(starshipAdapter);
        /** list.setOnItemClickListener();*/

        StarWarsApi.init();
        StarWarsApi.getApi().getAllStarships(1, new Callback<SWModelList<Starship>>() {
            @Override
            public void success(SWModelList<Starship> starshipSWModelList, Response response) {
                listOfStarship.addAll(starshipSWModelList.results);

                for (int i = 0; i < listOfStarship.size(); i++) {
                    starshipAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("No character..");
            }
        });

    }

    private final StarshipsAdapter.OnDeviceSelectedListener deviceSelectedListener = new StarshipsAdapter.OnDeviceSelectedListener() {
        @Override
        public void handle(final Starship starship) {
            Toast.makeText(StarshipsActivity.this,"Name: " + starship.name + " Passengers: " + starship.passengers + " Film(s): " + starship.filmsUrls ,Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}