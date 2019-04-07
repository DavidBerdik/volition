package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * UI activity that allows the user to choose between different daily activities.
 */
public class ActivityActivity extends AppCompatActivity {

  /**
   * OnCreate method that initializes objects and the screen to be used in the onClick methods.
   *
   * @param savedInstanceState Saved instance state of the phone
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_choice);

    final Button teaButton = findViewById(R.id.TEA);
    final Button lessonButton = findViewById(R.id.Lesson);
    final Button journalButton = findViewById(R.id.Journal);
    final Button eduButton = findViewById(R.id.Edu_);
    final Button wellnessButton = findViewById(R.id.DailyWellness);
    final Button cleanButton = findViewById(R.id.CleanTracker);

    //When clicked, this button will take the user to the TEA Activity
    teaButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(
            new Intent(ActivityActivity.this, TreatmentAssessmentActivity.class));
      }
    });

    //When clicked, this button will take the user to the Lesson Activity
    lessonButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, LessonActivity.class));
      }
    });

    //When clicked, this button will take the user to the Journal Activity
    journalButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, JournalActivity.class));
      }
    });

    //When clicked, this button will take the user to EDU Activity
    eduButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, EDUActivity.class));
      }
    });

    //When clicked, this button will take the user to the Daily Wellness Activity
    wellnessButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, DailyWellnessActivity.class));
      }
    });

    //When clicked, this button will take the user to the Report Use Activity
    cleanButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, ReportUseActivity.class));
      }
    });
  }
}

