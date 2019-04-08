package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

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

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE){
      setContentView(R.layout.activity_activity_land);
      isPortrait=false;

    } else {
      setContentView(R.layout.activity_activity_port);
      isPortrait = true;
    }

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

    viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);

    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);




  }

  public void onCreateTest(final VolitionDatabase db){
    viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);
    viewModel.setTestDatabase(db);
    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);
  }



  /**
   * Observes the treatment plan table in the database. Replaces the local treatment plan with an
   * updated copy.
   */
  private Observer<TreatmentPlanEntity> treatmentPlanObserver = new Observer<TreatmentPlanEntity>(){
    @Override
    public void onChanged(final TreatmentPlanEntity newTreatmentPlanEntity){
      treatmentPlanEntity = newTreatmentPlanEntity;
      try {
        int TEAs = treatmentPlanEntity.getNumTreatmentEffectivenessAssessment();
         Log.d("ActivityActivty", "onChanged: TEA's are " + TEAs);
        if(TEAs > 0){
          if(isPortrait)
            findViewById(R.id.teaCompletedPortrait).setVisibility(View.VISIBLE);
          else
            findViewById(R.id.teaCompletedLandscape).setVisibility(View.VISIBLE);
        }
        else{
          if(isPortrait)
            findViewById(R.id.teaIncompletePortrait).setVisibility(View.VISIBLE);
          else
            findViewById(R.id.teaIncompleteLandscape).setVisibility(View.VISIBLE);
        }
      }
      catch (NullPointerException e){
        //do nothing since the default is to not display them anyway
      }



    }
  };
  private TreatmentPlanViewModel viewModel;
  /**
   * A treatment plan entity to handle updates to the database.
   */
  private TreatmentPlanEntity treatmentPlanEntity;
  private boolean isPortrait=false;

}

