package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * This class allows the user to enter remarks and passes them to the database.
 */

public class TreatmentExperienceAssessmentRemarksActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_treatment_experience_assessment_remarks);

    teaAnswers = getIntent().getExtras().getIntegerArrayList("ANSWERS");

    final Button submitButton = findViewById(R.id.submitButton);
    remarks = findViewById(R.id.remarksText);
    remarks.requestFocus();
    submitButton.setOnClickListener(submitRemarksClickListener);

    db = VolitionDatabase.getDatabase(this);
    teaViewModel = ViewModelProviders.of(this).get(TreatmentExperienceAssessmentViewModel.class);

    context = this;
  }

  /**
   * When the button is clicked it passes the string. 
   */

  private final View.OnClickListener submitRemarksClickListener = new View.OnClickListener() {

    @Override
    public void onClick(final View v) {
      final String remarksTxt = remarks.getText().toString();
      teaViewModel.addTreatmentExperienceAssessment(teaAnswers, remarksTxt);
      startActivity(new Intent(context, HomeActivity.class));
    }
  };

  private TreatmentExperienceAssessmentViewModel teaViewModel;
  private VolitionDatabase db;
  private Context context;
  private EditText remarks;
  private ArrayList<Integer> teaAnswers;

}
