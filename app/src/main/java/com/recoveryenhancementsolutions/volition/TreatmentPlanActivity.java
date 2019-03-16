package com.recoveryenhancementsolutions.volition;

import android.widget.TextView;

/**
 * Treatment Plan Activity is called when the user selects the option to view their treatment plan.
 * Class reads the treatment plan from the database and displays it on the screen for the user.
 */
public class TreatmentPlanActivity {
  private TextView treatmentPlanView;

  private void onCreate(){
    treatmentPlanView = findViewById(R.id.treatmentPlanTextView);
  }
}
