package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

/**
 * UI Activity that allows the user to select dosage for medication selection
 */
public class MedicationDosageActivity extends AppCompatActivity {

  /**
   * OnCreate method that initializes objects and the screen to be used in the onClick methods.
   *
   * @param savedInstanceState Saved Instance state of the phone
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dosage_choice);

    final Button confirmButton = findViewById(R.id.confirmDosage);
    final MedicationDosageViewModel mViewModel = new MedicationDosageViewModel(getApplication());
    final MedicationDosageEntity dose = new MedicationDosageEntity();
    final Spinner dosageSpinner = findViewById(R.id.dosage_spinner);

    // Button click will send the integer to be stored in the database and call the next activity

    confirmButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        dose.dosage = Integer.parseInt(dosageSpinner.getSelectedItem().toString());
        mViewModel.insertDosage(dose);
        startActivity(new Intent(MedicationDosageActivity.this, HomeActivity.class));
      }
    });
  }
}
