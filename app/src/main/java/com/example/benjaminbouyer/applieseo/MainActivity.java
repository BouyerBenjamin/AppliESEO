package com.example.benjaminbouyer.applieseo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {


    private Button charactersButton;
    private Button vehiclesButton;
    private Button starshipsButton;
    private ImageView starWars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charactersButton = findViewById(R.id.charactersButton);
        vehiclesButton = findViewById(R.id.vehiclesButton);
        starshipsButton = findViewById(R.id.starshipsButton);
        starWars = findViewById(R.id.accueilImage);

        vehiclesButton.setOnClickListener(onVehiclesButtonClicked);
        charactersButton.setOnClickListener(onCharactersButtonClicked);
        starshipsButton.setOnClickListener(onStarshipsButtonClicked);
    }

    private final View.OnClickListener onCharactersButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            startActivity(CharactersActivity.getStartIntent(MainActivity.this));
        }
    };

    private final View.OnClickListener onVehiclesButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(VehiclesActivity.getStartIntent(MainActivity.this));
        }
    };


    private final View.OnClickListener onStarshipsButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(StarshipsActivity.getStartIntent(MainActivity.this));
        }
    };

}
