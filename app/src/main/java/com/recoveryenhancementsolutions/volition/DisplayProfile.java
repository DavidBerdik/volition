package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Class for running only display_profile.xml
 */
public class DisplayProfile extends AppCompatActivity {
  TextView name, DOB, Gender, TypeOfPerson,DrugOfChoice,Disorder,CleanDate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.display_profile);


    name = (TextView) findViewById(R.id.recorded_name);
    DOB = (TextView) findViewById(R.id.recorded_dob);
    Gender = (TextView) findViewById(R.id.recorded_gender);
    TypeOfPerson = (TextView) findViewById(R.id.recorded_person);
    DrugOfChoice = (TextView) findViewById(R.id.recorded_drug);
    Disorder = (TextView) findViewById(R.id.recorded_type);
    CleanDate = (TextView) findViewById(R.id.recorded_date);

    name.setText(getIntent().getStringExtra("name"));
    DOB.setText(getIntent().getStringExtra("Date of Birth"));
    Gender.setText(getIntent().getStringExtra("Gender"));
    TypeOfPerson.setText(getIntent().getStringExtra("Type of Person"));
    DrugOfChoice.setText(getIntent().getStringExtra("Drug of Choice"));
    Disorder.setText(getIntent().getStringExtra("Disorder"));
    CleanDate.setText(getIntent().getStringExtra("CleanDate"));




  }

}
