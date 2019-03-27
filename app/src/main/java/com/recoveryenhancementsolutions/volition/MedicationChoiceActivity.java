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

    final Button abstainButton = findViewById(R.id.abstain);
    final Button medicationButton = findViewById(R.id.medication);

    abstainButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        MedicationChoiceEntity med = new MedicationChoiceEntity();
        med.medication = "Abstain";
        db.medicationChoiceDAO().insertMedication(med);

        startActivity(new Intent(MedicationChoiceActivity.this, HomeActivity.class));

        //Toast.makeText(MedicationChoiceActivity.this,MedicationChoiceDAO.getMedication(),Toast.LENGTH_LONG).show();
      }
    });

    /*

     */
    medicationButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        MedicationChoiceEntity med = new MedicationChoiceEntity();
        med.medication = "Buprenorphine";
        db.medicationChoiceDAO().insertMedication(med);

        startActivity(new Intent(MedicationChoiceActivity.this, HomeActivity.class));

        //Toast.makeText(MedicationChoiceActivity.this,MedicationChoiceDAO.getMedication(),Toast.LENGTH_LONG).show();
      }
    });
  }

  private VolitionDatabase db = VolitionDatabase.getDatabase(MedicationChoiceActivity.this);
}
