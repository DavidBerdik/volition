package com.recoveryenhancementsolutions.volition;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TreatmentExperienceAssessmentRemarksActivity extends AppCompatActivity {
    private EditText remarks;

    protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_treatment_experience_assessment_remarks);
      final Button submitButton = findViewById(R.id.submitButton);
      remarks = findViewById(R.id.remarksText);
      remarks.requestFocus();
      submitButton.setOnClickListener(submitRemarksClickListener);
    }


    private final View.OnClickListener submitRemarksClickListener = new View.OnClickListener() {

      @Override
      public void onClick(final View v) {
        String remarksTxt = remarks.getText().toString();
        startActivity(new Intent(TreatmentExperienceAssessmentRemarksActivity.this, HomeActivity.class));
      }
    };

}
