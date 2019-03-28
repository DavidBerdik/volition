package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * UI Activity that allows the user to select a medication or abstain
 */
public class MedicationChoiceActivity extends AppCompatActivity {

  public String medAnswer;
  private static VolitionDatabase db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medication_choice);

    final Button abstainButton = findViewById(R.id.abstain);
    final Button medicationButton = findViewById(R.id.medication);
    final MedicationChoiceViewModel mViewModel = new MedicationChoiceViewModel(getApplication());
    db = VolitionDatabase.getDatabase(this.getApplication());

    abstainButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        medAnswer = "Abstain";
        mViewModel.populateAsync(db);
        startActivity(new Intent(MedicationChoiceActivity.this, HomeActivity.class));
        //this will really change to treatmentPlan.class, but for testing it goes to HomeActivity
      }
    });

    medicationButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        medAnswer = "Buprenorphine";
        mViewModel.populateAsync(db);
        startActivity(new Intent(MedicationChoiceActivity.this, HomeActivity.class));
        //this will really change to treatmentPlan.class, but for testing it goes to HomeActivity
      }
    });
  }
}
