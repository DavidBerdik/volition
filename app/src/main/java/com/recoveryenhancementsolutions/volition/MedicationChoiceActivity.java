package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MedicationChoiceActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medication_choice);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Button abstainB = findViewById(R.id.abstain);

    Button medicationB = findViewById(R.id.medication);

    //OnClick method for the bup button
    //when the button is clicked, the user's choice
    //of bup is sent to the database and stored,
    //and then the intent takes the user to the next page (treatmentPlan).
    abstainB.setOnClickListener(new OnClickListener() {
      @Override
        public void onClick(View v, final VolitionDatabase voldat) {

        MedicationChoiceActivity med = new MedicationChoiceActivity();
        med.medication = "abstain";
        voldat.medicationChoiceDAO().insertMedication(med);

        startActivity(new Intent(MedicationChoiceActivity.this, TreatmentPlanActivity.class));

        Toast.makeText(MedicationChoiceActivity.this,MedicationChoiceDAO.getMedication(),Toast.LENGTH_LONG).show();
      }
    });

    //OnClick method for the bup button
    //when the button is clicked, the user's choice
    //of bup is sent to the database and stored,
    //and then the intent takes the user to the next page (treatmentPlan).
    medicationB.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v, final VolitonDatabase voldat) {
        MedicationChoiceActivity med = new MedicationChoiceActivity();
        med.medication = "abstain";
        voldat.medicationChoiceDAO().insertMedication(med);

        startActivity(new Intent(MedicationChoiceActivity.this, TreatmentPlanActivity.class));

        Toast.makeText(MedicationChoiceActivity.this,"DataSaved",Toast.LENGTH_LONG).show();
        }
    });
  }

}
