package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

public class ClinicalScreenActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameBox = findViewById(R.id.nameBox);
        dateBox = findViewById(R.id.dateBox);
        daysCleanBox = findViewById(R.id.cleanDaysBox);
        setContentView(R.layout.activity_clinical_screen);
        DemographicDataViewModel demographicDataViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);
        demographicDataViewModel.returnName().observe(this,nameObserver);
        demographicDataViewModel.getLastCleanDate().observe(this, dateObserver);


    }

    private Observer<String> nameObserver = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            nameBox.setText("Name: " + s);
        }
    };


    private Observer<Date> dateObserver = new Observer<Date>() {
        @Override
        public void onChanged(final Date date) {
            // We should only have a NullPointerException if nothing is entered into the DB yet.
            // If this is the case, have an empty days clean String.
            try {
                dateBox.setText("Date of last use: ");
                dateBox.append(date.toString());
                final int days = DateConverter.daysBetween(date.getTime(), new Date().getTime());
                daysCleanBox.setText("Number of days clean:" );
                daysCleanBox.append(" " + days);
            } catch (NullPointerException e) {
                daysCleanBox.setText(R.string.home_clean);
            }
        }
    };



    private TextView nameBox;
    private TextView dateBox;
    private TextView daysCleanBox;
}

