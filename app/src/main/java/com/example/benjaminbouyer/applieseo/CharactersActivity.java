package com.example.benjaminbouyer.applieseo;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjaminbouyer.applieseo.models.People;
import com.example.benjaminbouyer.applieseo.models.SWModelList;
import com.example.benjaminbouyer.applieseo.sw.StarWarsApi;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CharactersActivity extends AppCompatActivity {


    public static Intent getStartIntent(final Context ctx) {
        return new Intent(ctx, CharactersActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        setTitle(R.string.character_activity_name);

        final ArrayList<People> listOfPeople = new ArrayList<People>();

        final PeopleAdapter peopleAdapter = new PeopleAdapter(this,listOfPeople , deviceSelectedListener);


        ListView list = (ListView) findViewById(R.id.listCharacter);
        list.setAdapter(peopleAdapter);
       /** list.setOnItemClickListener();*/

        StarWarsApi.init();
        StarWarsApi.getApi().getAllPeople(2, new Callback<SWModelList<People>>() {
            @Override
            public void success(SWModelList<People> peopleSWModelList, Response response) {
                listOfPeople.addAll(peopleSWModelList.results);

                for(int i=0 ; i<listOfPeople.size(); i++){
                    peopleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("No character..");
            }
        });

    }

    private final PeopleAdapter.OnDeviceSelectedListener deviceSelectedListener = new PeopleAdapter.OnDeviceSelectedListener() {
        @Override
        public void handle(final People people) {
            Toast.makeText(CharactersActivity.this,"Name: " + people.name + " BirthYear: " + people.birthYear + " Film(s) " + people.filmsUrls,Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}
