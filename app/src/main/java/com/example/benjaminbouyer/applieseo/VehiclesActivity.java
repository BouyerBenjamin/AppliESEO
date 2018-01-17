package com.example.benjaminbouyer.applieseo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.benjaminbouyer.applieseo.models.Vehicle;
import com.example.benjaminbouyer.applieseo.models.SWModelList;
import com.example.benjaminbouyer.applieseo.sw.StarWarsApi;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class VehiclesActivity extends AppCompatActivity {


    public static Intent getStartIntent(final Context ctx) {
        return new Intent(ctx, VehiclesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        setTitle(R.string.vehicle_activity_name);

        final ArrayList<Vehicle> listOfVehicle = new ArrayList<Vehicle>();

        final VehiclesAdapter vehiclesAdapter = new VehiclesAdapter(this, listOfVehicle, deviceSelectedListener);


        ListView list = (ListView) findViewById(R.id.listVehicles);
        list.setAdapter(vehiclesAdapter);
        /** list.setOnItemClickListener();*/

        StarWarsApi.init();
        StarWarsApi.getApi().getAllVehicles(1, new Callback<SWModelList<Vehicle>>() {
            @Override
            public void success(SWModelList<Vehicle> vehicleSWModelList, Response response) {
                listOfVehicle.addAll(vehicleSWModelList.results);

                for (int i = 0; i < listOfVehicle.size(); i++) {
                    vehiclesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("No character..");
            }
        });

    }

    private final VehiclesAdapter.OnDeviceSelectedListener deviceSelectedListener = new VehiclesAdapter.OnDeviceSelectedListener() {
        @Override
        public void handle(final Vehicle vehicle) {
            Toast.makeText(VehiclesActivity.this, "Name: " + vehicle.name + " Pilot(s): " + vehicle.pilotsUrls + " Film(s): " + vehicle.filmsUrls, Toast.LENGTH_SHORT).show();
            //finish();
        }
    };
}
