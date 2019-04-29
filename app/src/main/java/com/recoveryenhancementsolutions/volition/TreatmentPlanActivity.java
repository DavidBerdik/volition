package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

/**
 * Treatment Plan Activity is called when the user selects the option to view their treatment plan.
 * Class reads the treatment plan from the database and displays it on the screen for the user.
 */
public class TreatmentPlanActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plan);
    viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);
    counselingView = findViewById(R.id.counselingView);
    medManagementView = findViewById(R.id.medManagementView);
    supportMeetingView = findViewById(R.id.supportMeetingView);
    lessonView = findViewById(R.id.lessonView);
    treatmentEffectiveView = findViewById(R.id.treatmentEffectiveView);
    outcomeMeasureView = findViewById(R.id.outcomeMeasureView);
    timeTrackingView = findViewById(R.id.timeTrackingView);
    readingResponseView = findViewById(R.id.readingResponseView);
    medManagementDescView = findViewById(R.id.medManagement);
    outcomeMeasureDescView = findViewById(R.id.outcomeMeasure);

    //Sets button identifiers
    Button subCounselButton = findViewById(R.id.subCounselButton);
    Button addCounselButton = findViewById(R.id.addCounselButton);
    Button subMedManagementButton = findViewById(R.id.subMedMangaementButton);
    Button addMedManagementButton = findViewById(R.id.addMedManagementButton);
    Button subSupportGroupButton = findViewById(R.id.subSupportGroupButton);
    Button addSupportGroupButton = findViewById(R.id.addSupportGroupMeetingButton);
    Button subOutcomeMeasureButton = findViewById(R.id.subOutcomeMeasureButton);
    Button addOutcomeMeasureButton = findViewById(R.id.addOutcomeMeasureButton);
    Button subLessonPlannerButton = findViewById(R.id.subLessonPlannerButton);
    Button addLessonPlannerButton = findViewById(R.id.addLessonPlannerButton);
    Button subTreatmentEffectivenessAssessmentButton = findViewById
        (R.id.subTreatmentEffectivenessAssessmentButton);
    Button addTreatmentEffectivenessAssessmentButton = findViewById
        (R.id.addTreatmentEffectivnessAssessmentButton);
    Button subCleanTimeTrackingButton = findViewById(R.id.subCleanTimeTrackingButton);
    Button addCleanTimeTrackingButton = findViewById(R.id.addCleanTimeTrackingButton);
    Button subReadResponseJournalButton = findViewById(R.id.subReadResponceJournalButton);
    Button addReadResponseJournalButton = findViewById(R.id.addReadResponceJournalButton);
    Button updateButton = findViewById(R.id.updateButton);
    Button finishButton = findViewById(R.id.finishButton);

    //button onClickListeners
    subCounselButton.setOnClickListener(this);
    addCounselButton.setOnClickListener(this);
    subMedManagementButton.setOnClickListener(this);
    addMedManagementButton.setOnClickListener(this);
    subSupportGroupButton.setOnClickListener(this);
    addSupportGroupButton.setOnClickListener(this);
    subOutcomeMeasureButton.setOnClickListener(this);
    addOutcomeMeasureButton.setOnClickListener(this);
    subLessonPlannerButton.setOnClickListener(this);
    addLessonPlannerButton.setOnClickListener(this);
    subTreatmentEffectivenessAssessmentButton.setOnClickListener(this);
    addTreatmentEffectivenessAssessmentButton.setOnClickListener(this);
    subCleanTimeTrackingButton.setOnClickListener(this);
    addCleanTimeTrackingButton.setOnClickListener(this);
    subReadResponseJournalButton.setOnClickListener(this);
    addReadResponseJournalButton.setOnClickListener(this);
    updateButton.setOnClickListener(this);
    finishButton.setOnClickListener(this);

    //Sets initial treatmentPlanEntity values (for update use)
    treatmentPlanEntity = new TreatmentPlanEntity();

    //Initializes observer
    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);
  }

  /**
   * Test Database
   */
  public void onCreateTest(final VolitionDatabase db) {
    final TreatmentPlanViewModel treatmentPlanViewModel = ViewModelProviders.of(this)
        .get(TreatmentPlanViewModel.class);
    treatmentPlanViewModel.setTestDatabase(db);

    //Initializes observers. Note during tests this triggers 3 additional toasts that do not appear
    //during regular initialization
    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);
  }

  /**
   * onClick Listener class using a switch statement to dictate proper
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.subCounselButton:
        onSubCounselButtonClicked();
        break;

      case R.id.addCounselButton:
        onAddCounselButtonClicked();
        break;

      case R.id.subMedMangaementButton:
        onSubMedMangaementButtonClicked();
        break;

      case R.id.addMedManagementButton:
        onAddMedManagementButtonClicked();
        break;

      case R.id.subSupportGroupButton:
        onSubSupportGroupButtonClicked();
        break;

      case R.id.addSupportGroupMeetingButton:
        onAddSupportGroupMeetingButtonClicked();
        break;

      case R.id.subOutcomeMeasureButton:
        onSubOutcomeMeasureButtonClicked();
        break;

      case R.id.addOutcomeMeasureButton:
        onAddOutcomeMeasureButtonClicked();
        break;

      case R.id.subLessonPlannerButton:
        onSubLessonPlannerButtonClicked();
        break;

      case R.id.addLessonPlannerButton:
        onAddLessonPlannerButtonClicked();
        break;

      case R.id.subTreatmentEffectivenessAssessmentButton:
        onSubTreatmentEffectivnessAssessmentButtonCLicked();
        break;

      case R.id.addTreatmentEffectivnessAssessmentButton:
        onAddTreatmentEffectivnessAssessmentButtonClicked();
        break;

      case R.id.subCleanTimeTrackingButton:
        onSubCleanTimeTrackingButton();
        break;

      case R.id.addCleanTimeTrackingButton:
        onAddCleanTimeTrackingButton();
        break;

      case R.id.subReadResponceJournalButton:
        onSubReadResponseButton();
        break;

      case R.id.addReadResponceJournalButton:
        onAddReadResponseButton();
        break;

      case R.id.updateButton:
        onUpdateButtonClicked();
        break;

      case R.id.finishButton:
        onFinishButtonClicked();
        break;
    }
  }

  /**
   * Observes the treatment plan table in the database. Generates a toast if any updates/changes are
   * made to the treatment plan table.
   */
  private Observer<TreatmentPlanEntity> treatmentPlanObserver = new Observer<TreatmentPlanEntity>() {
    @Override
    public void onChanged(final TreatmentPlanEntity newTreatmentPlanEntity) {
      try {
        treatmentPlanEntity = newTreatmentPlanEntity;

        Context context = getApplicationContext();
        CharSequence msg = "Your Treatment Plan was Updated!";
        int dur = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, msg, dur);
        toast.show();
        String s;

        if (newTreatmentPlanEntity.getMedManagementFrequency().equals("MONTHLY")) {
          s = "Medication Management per Month";
          medManagementDescView.setText(s);
        } else {
          s = "Medication Management per Week";
          medManagementDescView.setText(s);
        }

        if (newTreatmentPlanEntity.getOutcomeMeasureFrequency().equals("WEEKLY")) {
          s = "Outcome Measures per Week";
          outcomeMeasureDescView.setText(s);
        } else {
          s = "Outcome Measures per Day";
          outcomeMeasureDescView.setText(s);
        }

        s = "" + treatmentPlanEntity.getNumCounseling();
        counselingView.setText(s);
        s = "" + treatmentPlanEntity.getNumMedManagement();
        medManagementView.setText(s);
        s = "" + treatmentPlanEntity.getNumSupportMeeting();
        supportMeetingView.setText(s);
        s = "" + treatmentPlanEntity.getNumLessons();
        lessonView.setText(s);
        s = "" + treatmentPlanEntity.getNumTreatmentEffectivenessAssessment();
        treatmentEffectiveView.setText(s);
        s = "" + treatmentPlanEntity.getNumOutcomeMeasures();
        outcomeMeasureView.setText(s);
        s = "" + treatmentPlanEntity.getNumTimeTracking();
        timeTrackingView.setText(s);
        s = "" + treatmentPlanEntity.getNumReadingResponse();
        readingResponseView.setText(s);

        treatmentPlanLoaded = true;
      } catch (NullPointerException e) {
        Log.e(TAG, Log.getStackTraceString(e));
      }
    }
  };

  /**
   * Method runs when the refresh button in the xml files is clicked.
   */
  private void onUpdateButtonClicked() {
    try {
      Date startDate = treatmentPlanEntity.getLastUpdate();
      int hours = DateConverter
          .hoursBetween(startDate, Calendar.getInstance().getTime());

      //Checks if the cool down time has been reached
      if (treatmentPlanLoaded) {
        if (hours < treatmentPlanEntity.getCoolDownTime()) {
          Context context = getApplicationContext();
          CharSequence msg =
              "Try again in " + (treatmentPlanEntity.getCoolDownTime() - hours) + " hours!";
          int dur = Toast.LENGTH_SHORT;
          Toast toast = Toast.makeText(context, msg, dur);
          toast.show();
        } else {
          treatmentPlanEntity.setLastUpdate(Calendar.getInstance().getTime());
          viewModel.updateTreatmentPlan(treatmentPlanEntity);
        }
      }
    } catch (NullPointerException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  /**
   * Method runs when finish button is clicked. Sends an intent to the home activity.
   */
  private void onFinishButtonClicked() {
    Intent intent = new Intent(this, HomeActivity.class);
    startActivity(intent);
  }

  /**
   * Method runs when add button is pressed to add a counseling session.
   */
  private void onAddCounselButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = counselingView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumCounseling(num);
      s = "" + num;
      counselingView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtract a counseling session.
   */
  private void onSubCounselButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = counselingView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumCounseling(num);
      s = "" + num;
      counselingView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add medication management.
   */
  private void onAddMedManagementButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = medManagementView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumMedManagement(num);
      s = "" + num;
      medManagementView.setText(s);
    }
  }

  /**
   * Method runs when sub button os pressed to subtract from medication management.
   */
  private void onSubMedMangaementButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = medManagementView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumMedManagement(num);
      s = "" + num;
      medManagementView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add a support group meeting.
   */
  private void onAddSupportGroupMeetingButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = supportMeetingView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumSupportMeeting(num);
      s = "" + num;
      supportMeetingView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtract a support group meeting.
   */
  private void onSubSupportGroupButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = supportMeetingView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumSupportMeeting(num);
      s = "" + num;
      supportMeetingView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add an outcome measurement.
   */
  private void onAddOutcomeMeasureButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = outcomeMeasureView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumOutcomeMeasures(num);
      s = "" + num;
      outcomeMeasureView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtract an outcome measurement.
   */
  private void onSubOutcomeMeasureButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = outcomeMeasureView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumOutcomeMeasures(num);
      s = "" + num;
      outcomeMeasureView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add a lesson plan.
   */
  private void onAddLessonPlannerButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = lessonView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumLessons(num);
      s = "" + num;
      lessonView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtrct a lesson plan.
   */
  private void onSubLessonPlannerButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = lessonView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumLessons(num);
      s = "" + num;
      lessonView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add a treatment plan effectiveness assessment.
   */
  private void onAddTreatmentEffectivnessAssessmentButtonClicked() {
    if (treatmentPlanLoaded) {
      String s = treatmentEffectiveView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(num);
      s = "" + num;
      treatmentEffectiveView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtract a treatment plan effectiveness assessment.
   */

  private void onSubTreatmentEffectivnessAssessmentButtonCLicked() {
    if (treatmentPlanLoaded) {
      String s = treatmentEffectiveView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(num);
      s = "" + num;
      treatmentEffectiveView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add clean time tracking.
   */
  private void onAddCleanTimeTrackingButton() {
    if (treatmentPlanLoaded) {
      String s = timeTrackingView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumTimeTracking(num);
      s = "" + num;
      timeTrackingView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtract clean time tracking.
   */
  private void onSubCleanTimeTrackingButton() {
    if (treatmentPlanLoaded) {
      String s = timeTrackingView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumTimeTracking(num);
      s = "" + num;
      timeTrackingView.setText(s);
    }
  }

  /**
   * Method runs when add button is pressed to add read response.
   */
  private void onAddReadResponseButton() {
    if (treatmentPlanLoaded) {
      String s = readingResponseView.getText().toString();
      int num = Integer.parseInt(s) + 1;
      treatmentPlanEntity.setNumReadingResponse(num);
      s = "" + num;
      readingResponseView.setText(s);
    }
  }

  /**
   * Method runs when sub button is pressed to subtract from add read response.
   */
  private void onSubReadResponseButton() {
    if (treatmentPlanLoaded) {
      String s = readingResponseView.getText().toString();
      int num = Integer.parseInt(s);
      if (num - 1 < 0) {
        num = 0;
      } else {
        num--;
      }
      treatmentPlanEntity.setNumReadingResponse(num);
      s = "" + num;
      readingResponseView.setText(s);
    }
  }


  /**
   * The view model used to access the database.
   */
  private TreatmentPlanViewModel viewModel;

  /**
   * The TextView displaying data representing the current value of numCounseling in the
   * treatmentPlanEntity.
   */
  private TextView counselingView;

  /**
   * The TextView displaying data representing the current value of numMedManagement in the
   * treatmentPlanEntity.
   */
  private TextView medManagementView;

  /**
   * The TextView displaying data representing the current value of numSupportMeeting in the
   * treatmentPlanEntity.
   */
  private TextView supportMeetingView;

  /**
   * The TextView displaying data representing the current value of numLessons in the
   * treatmentPlanEntity.
   */
  private TextView lessonView;

  /**
   * The TextView displaying data representing the current value of numTreatmentEffectivenessAssessment
   * in the treatmentPlanEntity.
   */
  private TextView treatmentEffectiveView;

  /**
   * The TextView displaying data representing the current value of numOutcomeMeasures in the
   * treatmentPlanEntity.
   */
  private TextView outcomeMeasureView;

  /**
   * The TextView displaying data representing the current value of numTimeTracking in the
   * treatmentPlanEntity.
   */
  private TextView timeTrackingView;

  /**
   * The TextView displaying data representing the current value of numReadingResponse in the
   * treatmentPlanEntity.
   */
  private TextView readingResponseView;

  /**
   * The TextView displaying the description for medication management.
   */
  private TextView medManagementDescView;

  /**
   * The TextView displaying the description for outcome measures.
   */
  private TextView outcomeMeasureDescView;

  /**
   * A boolean to check if the treatment plan has been generated.
   */
  private boolean treatmentPlanLoaded;

  /**
   * A treatment plan entity to handle updates to the database.
   */
  private TreatmentPlanEntity treatmentPlanEntity;

  /**
   * As string for logging purposes.
   */
  private String TAG = "TreatmentPlanActivityCaughtException";


}
