package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.content.res.Configuration;
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

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_dosage_choice_land);
    } else {
      setContentView(R.layout.activity_dosage_choice_port);
    }

    final Button confirmButton = findViewById(R.id.confirmDosage);
    final MedicationChoiceViewModel mViewModel = new MedicationChoiceViewModel(getApplication());
    final MedicationChoiceEntity dose = new MedicationChoiceEntity();
    final Spinner dosageSpinner = findViewById(R.id.dosage_spinner);

    // Button click will send the integer to be stored in the database and call the next activity
    // This should call the treatment plan activity when it is ready
    confirmButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        dose.dosage = Integer.parseInt(dosageSpinner.getSelectedItem().toString());
        mViewModel.updateDosage(dose);
        startActivity(new Intent(MedicationDosageActivity.this, TreatmentPlanActivity.class));
      }
    });
  }
}
