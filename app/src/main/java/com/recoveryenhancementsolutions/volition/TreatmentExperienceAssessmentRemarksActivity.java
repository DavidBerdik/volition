package com.recoveryenhancementsolutions.volition;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


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

    context = this;
  }

  private final View.OnClickListener submitRemarksClickListener = new View.OnClickListener() {

    @Override
    public void onClick(final View v) {
      final String remarksTxt = remarks.getText().toString();
      TreatmentExperienceAssessmentViewModel
          .addTreatmentExperienceAssessment(db, teaAnswers, remarksTxt);
      startActivity(new Intent(context, HomeActivity.class));
    }
  };

  private VolitionDatabase db;
  private Context context;
  private EditText remarks;
  private ArrayList<Integer> teaAnswers;

}
