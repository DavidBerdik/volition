package com.recoveryenhancementsolutions.volition;

import static android.os.SystemClock.sleep;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


/**
 * UI Activity that allows the user to select a medication or abstain
 */
public class MedicationChoiceActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medication_choice);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final VolitionDatabase db = VolitionDatabase.getDatabase(this.getApplication());
    final Button abstainButton = findViewById(R.id.abstain);
    final Button medicationButton = findViewById(R.id.medication);
    final MedicationChoiceEntity med = new MedicationChoiceEntity();

    abstainButton.setOnClickListener(new OnClickListener() {
      @Override
      /**
       * onClick method that sends the choice 'abstain' to the database
       */
      public void onClick(View view) {
        med.medication = "Abstain";
        db.medicationChoiceDAO().insertMedication(med);
        startActivity(new Intent(MedicationChoiceActivity.this, HomeActivity.class));
        //this will really change to treatmentPlan.class, but for testing it goes to HomeActivity
      }
    });

    medicationButton.setOnClickListener(new OnClickListener() {
      @Override
      /**
       * onClick method that sends the choice 'Buprenorphine' to the database
       */
      public void onClick(View view) {
        med.medication = "Buprenorphine";
        db.medicationChoiceDAO().insertMedication(med);
        startActivity(new Intent(MedicationChoiceActivity.this, HomeActivity.class));
        //this will really change to treatmentPlan.class, but for testing it goes to HomeActivity
      }
    });
  }
}
