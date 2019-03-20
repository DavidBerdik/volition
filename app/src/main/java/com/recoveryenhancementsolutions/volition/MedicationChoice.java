package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MedicationChoice extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medication_choice);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Button abstainB = findViewById(R.id.abstain);

    Button medicationB = findViewById(R.id.medication);

    //FloatingActionButton fab = findViewById(R.id.fab);

    abstainB.setOnClickListener(new OnClickListener() {
      @Override
        public void onClick(View v) {
        startActivity(new Intent(MedicationChoice.this, TreatmentPlanActivity.class));
        Toast.makeText(MedicationChoice.this,"DataSaved",Toast.LENGTH_LONG).show();
      }
    });

    medicationB.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
          startActivity(new Intent(MedicationChoice.this, TreatmentPlanActivity.class));
        }
    });

    //fab.setOnClickListener(new View.OnClickListener() {
      //public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //  .setAction("Action", null).show();
  }

}
