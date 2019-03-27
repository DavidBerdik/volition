package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

/**
 * Class for running only display_profile.xml
 */
public class DisplayProfile extends AppCompatActivity {
  TextView name, DOB, Gender, TypeOfPerson,DrugOfChoice,Disorder,CleanDate;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.display_profile);
    Button send;

    //
    //database insertion


    name = (TextView) findViewById(R.id.recorded_name);
    DOB = (TextView) findViewById(R.id.recorded_dob);
    Gender = (TextView) findViewById(R.id.recorded_gender);
    TypeOfPerson = (TextView) findViewById(R.id.recorded_person);
    DrugOfChoice = (TextView) findViewById(R.id.recorded_drug);
    Disorder = (TextView) findViewById(R.id.recorded_type);
    CleanDate = (TextView) findViewById(R.id.recorded_date);

    name.setText(getIntent().getStringExtra("Name"));
    DOB.setText(getIntent().getStringExtra("Date of Birth"));
    Gender.setText(getIntent().getStringExtra("Gender"));
    TypeOfPerson.setText(getIntent().getStringExtra("Type of Person"));
    DrugOfChoice.setText(getIntent().getStringExtra("Drug of Choice"));
    Disorder.setText(getIntent().getStringExtra("Disorder"));
    CleanDate.setText(getIntent().getStringExtra("CleanDate"));

    send = (Button) findViewById(R.id.button);
    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        Intent intent = new Intent(DisplayProfile.this, MainActivity.class);
        startActivity(intent);
      }
    });



  }

}
