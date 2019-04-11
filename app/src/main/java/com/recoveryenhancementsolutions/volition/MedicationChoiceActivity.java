package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * UI Activity that allows the user to select a medication or abstain.
 */
public class MedicationChoiceActivity extends AppCompatActivity {

  /**
   * OnCreate method that initializes objects and the screen to be used in the onClick methods.
   *
   * @param savedInstanceState Saved Instance state of the phone
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medication_choice);

    final Button abstainButton = findViewById(R.id.abstain);
    final Button medicationButton = findViewById(R.id.medication);
    final MedicationChoiceViewModel mViewModel = new MedicationChoiceViewModel(getApplication());
    final MedicationChoiceEntity medication = new MedicationChoiceEntity();

    // Button that allows the user to choose to abstain from medication.
    // When clicked, this will send the string "Abstain" to the Medication Choice table.
    abstainButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        medication.medication = "Abstain";
        mViewModel.insertMedication(medication);
        startActivity(new Intent(MedicationChoiceActivity.this, TreatmentPlanActivity.class));
      }
    });

    // Button that allows the user to choose to take them medication.
    // When clicked, this will send the string "Buprenorphine" to the Medication Choice table.
    medicationButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        medication.medication = "Buprenorphine";
        mViewModel.insertMedication(medication);
        startActivity(new Intent(MedicationChoiceActivity.this, MedicationDosageActivity.class));
      }
    });
  }
}
