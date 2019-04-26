package com.recoveryenhancementsolutions.volition;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class ClinicalScreenActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_clinical_screen);
        DemographicDataViewModel demographicDataViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);
        demographicDataViewModel.returnName().observe(this,nameObserver);
        demographicDataViewModel.getLastCleanDate().observe(this, dateObserver);
        Spinner spinner = findViewById(R.id.clinicalSpinner);
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
                case "January": displayMonth(1);
                    break;
                case "February": displayMonth(2);
                    break;
                case "March": displayMonth(3);
                    break;
                case "April": displayMonth(4);
                    break;
                case "May": displayMonth(5);
                    break;
                case "June": displayMonth(6);
                    break;
                case "July": displayMonth(7);
                    break;
                case "August": displayMonth(8);
                    break;
                case "September": displayMonth(9);
                    break;
                case "October": displayMonth(10);
                    break;
                case "November": displayMonth(11);
                    break;
                case "December": displayMonth(12);
                    break;
                case "Year to Date": displayYear();
                default:
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    };

    private void displayMonth(int month){
        

    }

    private void displayYear() {
        UserActivityViewModel userActivityViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);
        userActivityViewModel.getAllActivities().observe(this, yearObserver);
    }


    private Observer<List<UserActivityEntity>> yearObserver = new Observer<List<UserActivityEntity>>() {
        @Override
        public void onChanged(final List<UserActivityEntity> s) {
            enterActivities = findViewById(R.id.listOfActivities);
            enterActivities.setText("Activities to date: \n");
            if(!s.isEmpty()) {
                for (int i = 0; i < s.size(); i++) {
                    enterActivities.append(s.get(i).toString());
                    enterActivities.append("\n");
                    Log.e("s: ", s.get(i).toString());
                }
            }
               else{enterActivities.setText(R.string.noActivities);
                   Log.e("s: ", s.toString()); }

        }
    };

    private Observer<String> nameObserver = new Observer<String>() {
        @Override
        public void onChanged(final String s) {
            enterName = findViewById(R.id.nameBox);
            enterName.setText(R.string.name);
            enterName.append(" " + s);
           // nameBox.setText("tester");
            Log.e("name ", s);
        }
    };


    private Observer<Date> dateObserver = new Observer<Date>() {
        @Override
        public void onChanged(final Date date) {
            enterDate = findViewById(R.id.dateBox);
            enterDaysClean = findViewById(R.id.cleanDaysBox);
            enterDate.setText(R.string.dateOfLastUse);
            enterDate.append(" " + date.toString());
                Log.e("date: ", date.toString());
                final int days = DateConverter.daysBetween(date.getTime(), new Date().getTime());
                final String newDays = Integer.toString(days);
                Log.e("days ", newDays);
            enterDaysClean.setText(R.string.home_clean);
            enterDaysClean.append(" " + newDays);

        }
    };

    public TextView enterName;
    public TextView enterDate;
    public TextView enterDaysClean;
    public TextView enterActivities;
}

