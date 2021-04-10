package com.example.pixaflip;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.pixaflip.ShowStates.EXTRA_CONFIRMEDF;
import static com.example.pixaflip.ShowStates.EXTRA_CONFIRMEDI;
import static com.example.pixaflip.ShowStates.EXTRA_DEATHS;
import static com.example.pixaflip.ShowStates.EXTRA_DISCHARGED;
import static com.example.pixaflip.ShowStates.EXTRA_LOCATION;
import static com.example.pixaflip.ShowStates.EXTRA_TOTAL;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String Location = intent.getStringExtra(EXTRA_LOCATION);
        String ConfirmedI = intent.getStringExtra(EXTRA_CONFIRMEDI);
        String ConfirmedF=intent.getStringExtra(EXTRA_CONFIRMEDF);
        String Discharged=intent.getStringExtra(EXTRA_DISCHARGED);
        String Deaths=intent.getStringExtra(EXTRA_DEATHS);
        String Total=intent.getStringExtra(EXTRA_TOTAL);

        TextView textViewLocation = findViewById(R.id.Location);
        TextView textViewConfirmedI = findViewById(R.id.ConfirmedI);
        TextView textViewConfirmedF = findViewById(R.id.ConfirmedF);
        TextView textViewDischarged=findViewById(R.id.discharged);
        TextView textViewDeaths=findViewById(R.id.deaths);
        TextView textViewTotal=findViewById(R.id.total);

        textViewLocation.setText("Location:"+Location);
        textViewConfirmedI.setText("Confirmed Cases in India :" + ConfirmedI);
        textViewConfirmedF.setText("Confirmed Cases in Foreign :"+ConfirmedF);
        textViewDischarged.setText("Dischaged :" + Discharged);
        textViewDeaths.setText("Deaths :"+Deaths);
        textViewTotal.setText("Total Confirmed:" + Total);
    }
}