package com.recoveryenhancementsolutions.volition;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
        Spinner spinner = (Spinner) findViewById(R.id.clinicalSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.months_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(onItemSelectedListener);

    }


    private Spinner.OnItemSelectedListener onItemSelectedListener = new Spinner.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            String option = parent.getItemAtPosition(pos).toString();
            switch (option) {
                case "January": displayMonth("01");
                    break;
                case "February": displayMonth("02");
                    break;
                case "March": displayMonth("03");
                    break;
                case "April": displayMonth("04");
                    break;
                case "May": displayMonth("05");
                    break;
                case "June": displayMonth("06");
                    break;
                case "July": displayMonth("07");
                    break;
                case "August": displayMonth("08");
                    break;
                case "September": displayMonth("09");
                    break;
                case "October": displayMonth("10");
                    break;
                case "November": displayMonth("11");
                    break;
                case "December": displayMonth("12");
                    break;
                case "Year to date": displayYear();
                default:
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    };

    private void displayMonth(String month){

    }

    private void displayYear() {

    }



    private Observer<String> nameObserver = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            nameBox.setText("Name: ");
            nameBox.append(s);
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
                daysCleanBox.setText(R.string.home_clean);
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

