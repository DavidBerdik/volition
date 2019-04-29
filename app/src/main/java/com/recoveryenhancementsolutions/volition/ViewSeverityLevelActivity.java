package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class accesses database for questionnaire and displays desired information, specifically the
 * severity level and total yes answers
 */
public class ViewSeverityLevelActivity extends AppCompatActivity {

  /**
   * Forcing a restart of the questionnaire
   */
  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

  /**
   * The onCreate method sets the view to the proper xml and accesses the view model for this
   * activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_severity_level);

    specifier = findViewById(R.id.specifier_tv);
    totalYesTv = findViewById(R.id.totalScore_tv);
    Button TPButton = findViewById(R.id.TPButton);

    // Get a reference to the ViewModel for this screen.
    viewSeverityViewModel = ViewModelProviders.of(this).get(ViewSeverityLevelViewModel.class);
    observeTotalYes();
    // Update the UI whenever there's a change in the ViewModel's data.
    observeSeverityLevel();
    TPButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(ViewSeverityLevelActivity.this, MedicationChoiceActivity.class));
      }
    });
  }

  /**
   * observeTotalYes creates an observer for the string value of total yes in the database and calls
   * a method to show it in UI
   */
  private void observeTotalYes() {
    viewSeverityViewModel.getTotalYesAnswers().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String totalYes) {
        showTotalYesInUi(totalYes);
      }
    });
  }

  /**
   * appends a string into the total yes text view and is entered by a string builder
   */
  private void showTotalYesInUi(final @Nullable String totalYes) {
    totalYesTv.setText(totalYes);
  }

  /**
   * observeSeverityLevel creates an observer for the string value of severity in the database and
   * calls a method to show it in UI
   */
  private void observeSeverityLevel() {
    viewSeverityViewModel.getSeverity().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String severity) {
        showSeverityLevelInUi(severity);
      }
    });
  }

  /**
   * appends a string into the specifier text view and is entered by a string builder
   */
  private void showSeverityLevelInUi(final @Nullable String severity) {
    specifier.setText(severity);
  }
  private ViewSeverityLevelViewModel viewSeverityViewModel;
  private TextView specifier;
  private TextView totalYesTv;
}
