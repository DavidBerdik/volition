package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.recoveryenhancementsolutions.volition.R.id;

/**
 * UI activity that allows the user to choose between different daily activities.
 */
public class ActivityActivity extends AppCompatActivity {

  /**
   * Recreates the observer but using a testing database. Should only be used for testing.
   *
   * @param db A VolitionDatabase test object.
   */
  public void onCreateTest(final VolitionDatabase db) {
    viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);
    viewModel.setTestDatabase(db);
    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);
  }

  /**
   * Restores the CoreNavigationHandler to it's default state for this page.
   */
  @Override
  public void onResume() {
    super.onResume();
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
  }

  /*
   *Makes AdminMenu
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_admin_menu_drawer, menu);
    return true;
  }

  /*
   *Adds Functionality to AdminMenu
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    if(item.getItemId() == R.id.edit_profile){
      Intent profile = new Intent(this, ProfileActivity.class);
      startActivity(profile);
    }
    if(item.getItemId() == R.id.edit_treatment){
      Intent treatment = new Intent(this, TreatmentPlanActivity.class);
      startActivity(treatment);
    }
    if(item.getItemId() == R.id.classification){
      Intent classification = new Intent(this, ClassificationScreenActivity.class);
      startActivity(classification);
    }
    if(item.getItemId() == R.id.retake_ques){
      Intent questionarre = new Intent(this, QuestionnaireActivity.class);
      startActivity(questionarre);
    }
    if(item.getItemId() == R.id.clinical_overview){
      Intent clinical = new Intent(this, ClinicalOverviewActivity.class);
      startActivity(clinical);
    }
    return true;
  }




  /**
   * OnCreate method that initializes objects and the screen to be used in the onClick methods.
   *
   * @param savedInstanceState Saved instance state of the phone
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_activity_land);
      isPortrait = false;

    } else {
      setContentView(R.layout.activity_activity_port);
      isPortrait = true;
    }

    final Button teaButton = findViewById(R.id.TEA);
    final Button lessonButton = findViewById(R.id.Lesson);
    final Button journalButton = findViewById(R.id.Journal);
    final Button eduButton = findViewById(R.id.Edu);
    final Button wellnessButton = findViewById(R.id.DailyWellness);
    final Button cleanButton = findViewById(R.id.CleanTracker);

    //When clicked, this button will take the user to the TEA Activity
    teaButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(
            new Intent(ActivityActivity.this, TreatmentExperienceAssessmentActivity.class));
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

    bottomNavigationView = findViewById(R.id.core_navigation);
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
    CoreNavigationHandler.link(bottomNavigationView, this, 2);

  }


  /**
   * Observes the treatment plan table in the database. Replaces the local treatment plan with an
   * updated copy. Compare number of times an activity needs to be completed as per the treatment
   * plan vs the number of times the user has completed the activity. Update UI with markers
   * accordingly.
   */
  private final Observer<TreatmentPlanEntity> treatmentPlanObserver = new Observer<TreatmentPlanEntity>() {
    @Override
    public void onChanged(final TreatmentPlanEntity newTreatmentPlanEntity) {
      /*
        A treatment plan entity to handle updates to the database.
       */
      try {
        //Once/if EDU has a corresponding database element, we must add it here and add if logic

        final int numberOfTeasFromPlan = newTreatmentPlanEntity
            .getNumTreatmentEffectivenessAssessment();
        final int numberOfLessonsFromPlan = newTreatmentPlanEntity.getNumLessons();
        final int numberOfReportUseFromPlan = newTreatmentPlanEntity.getNumTimeTracking();
        final int numberOfJournalsFromPlan = newTreatmentPlanEntity.getNumReadingResponse();
        final int numberOfDailyWellnessFromPlan = newTreatmentPlanEntity.getNumOutcomeMeasures();
        // TODO: 4/16/2019 This is the incorrect way to get these values, they must be retrieved from the database in the future when the activity classes are completed
        final int numberOfUserTeasCompleted = TreatmentExperienceAssessmentActivity.numberCompleted;
        final int numberOfUserLessonsCompleted = LessonActivity.numberCompleted;
        final int numberOfUserReportUseCompleted = ReportUseActivity.numberCompleted;
        final int numberOfUserJournalsCompleted = JournalActivity.numberCompleted;
        final int numberOfUserDailyWellnessCompleted = DailyWellnessActivity.numberCompleted;
        if (numberOfUserTeasCompleted >= numberOfTeasFromPlan) {
          if (isPortrait) {
            findViewById(R.id.teaCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.teaCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(R.id.teaIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.teaIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
        if (numberOfUserLessonsCompleted >= numberOfLessonsFromPlan) {
          if (isPortrait) {
            findViewById(R.id.lessonCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.lessonCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(R.id.lessonIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.lessonIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
        if (numberOfUserReportUseCompleted >= numberOfReportUseFromPlan) {
          if (isPortrait) {
            findViewById(R.id.cleanTrackerCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(id.cleanTrackerCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(R.id.cleanTrackerIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(id.cleanTrackerIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
        if (numberOfUserJournalsCompleted >= numberOfJournalsFromPlan) {
          if (isPortrait) {
            findViewById(id.JournalCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(id.JournalCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(id.jouranlIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(id.JournalIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
        if (numberOfUserDailyWellnessCompleted >= numberOfDailyWellnessFromPlan) {
          if (isPortrait) {
            findViewById(id.dailyWellnessCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(id.dailyWellnessCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(id.dailyWellnessIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(id.dailyWellnessIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
      } catch (final NullPointerException e) {
        Log.d("Activity Activity", "onChanged: " + Log.getStackTraceString(e));
      }


    }
  };

  private TreatmentPlanViewModel viewModel;
  private boolean isPortrait = false;
  private BottomNavigationView bottomNavigationView;
}
