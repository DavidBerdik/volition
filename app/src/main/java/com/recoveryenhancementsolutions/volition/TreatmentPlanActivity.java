package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Button subReadResponceJournalButton = findViewById(R.id.subReadResponceJournalButton);
    Button addReadResponceJournalButton = findViewById(R.id.addReadResponceJournalButton);
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
    subReadResponceJournalButton.setOnClickListener(this);
    addReadResponceJournalButton.setOnClickListener(this);
    updateButton.setOnClickListener(this);
    finishButton.setOnClickListener(this);

    //Sets initial treatmentPlanEntity values (for update use)
    treatmentPlanEntity = new TreatmentPlanEntity();

    //initializes values for observer tests.
    medObserved = false;
    questionnaireObserved = false;

    //Initializes observers
    viewModel.getMedicationChoiceEntity().observe(this, medObserver);
    viewModel.getQuestionnaireEntity().observe(this, questionnaireObserver);
    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);
  }

  /**
   * Test Database
   */
  public void onCreateTest(final VolitionDatabase db) {
    final TreatmentPlanViewModel treatmentPlanViewModel = ViewModelProviders.of(this)
        .get(TreatmentPlanViewModel.class);
    treatmentPlanViewModel.setTestDatabase(db);
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
    public void onChanged(final TreatmentPlanEntity treatmentPlanEntity) {
      Context context = getApplicationContext();
      CharSequence msg = "Your Treatment Plan was Saved!";
      int dur = Toast.LENGTH_SHORT;
      Toast toast = Toast.makeText(context, msg, dur);
      toast.show();

      try {
        if (treatmentPlanEntity.getMedManagementFrequency().equals("MONTHLY")) {
          medManagementDescView.setText("Medication Management per Month");
        } else {
          medManagementDescView.setText("Medication Management per Week");
        }

        if (treatmentPlanEntity.getOutcomeMeasureFrequency().equals("WEEKLY")) {
          outcomeMeasureDescView.setText("Outcome Measures per Week");
        } else {
          outcomeMeasureDescView.setText("Outcome Measures per Day");
        }
      } catch (NullPointerException e) {
        Log.e("TreatmentPlanActivity", Log.getStackTraceString(e));
      }
    }
  };

  /**
   * Observes the medication choice table in the database. Generates a treatment plan if the
   * questionnaire has already been loaded as well.
   */
  private Observer<MedicationChoiceEntity> medObserver = new Observer<MedicationChoiceEntity>() {
    @Override
    public void onChanged(final MedicationChoiceEntity medicationChoiceEntity) {
      try {
        medicationChoice = medicationChoiceEntity.medication;
        medObserved = true;
        if (questionnaireObserved) {
          generateTreatmentPlan();
        }
      } catch (NullPointerException e) {
        Log.e("TREATMENTPLANACTIVITY", Log.getStackTraceString(e));
      }
    }
  };

  /**
   * Observes the questionnaire table in the database. Generates a treatment plan if the medication
   * choice has already been loaded as well.
   */
  private Observer<QuestionnaireEntity> questionnaireObserver = new Observer<QuestionnaireEntity>() {
    @Override
    public void onChanged(@Nullable QuestionnaireEntity questionnaireEntity) {
      try {
        severityLevel = questionnaireEntity.getSeverityLevel();
        questionnaireObserved = true;
        if (medObserved) {
          generateTreatmentPlan();
        }
      } catch (NullPointerException e) {
        Log.e("TREATMENTPLANACTIVITY", Log.getStackTraceString(e));
      }
    }
  };

  /**
   * Method runs when the refresh button in the xml files is clicked.
   */
  private void onUpdateButtonClicked() {
    if (treatmentPlanLoaded) {
      viewModel.updateTreatmentPlan(treatmentPlanEntity);
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
      counselingView.setText(Integer.toString(num));
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
      counselingView.setText(Integer.toString(num));
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
      medManagementView.setText(Integer.toString(num));
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
      medManagementView.setText(Integer.toString(num));
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
      supportMeetingView.setText(Integer.toString(num));
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
      supportMeetingView.setText(Integer.toString(num));
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
      outcomeMeasureView.setText(Integer.toString(num));
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
      outcomeMeasureView.setText(Integer.toString(num));
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
      lessonView.setText(Integer.toString(num));
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
      lessonView.setText(Integer.toString(num));
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
      treatmentEffectiveView.setText(Integer.toString(num));
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
      treatmentEffectiveView.setText(Integer.toString(num));
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
      timeTrackingView.setText(Integer.toString(num));
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
      timeTrackingView.setText(Integer.toString(num));
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
      readingResponseView.setText(Integer.toString(num));
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
      readingResponseView.setText(Integer.toString(num));
    }
  }

  /**
   * Generates a new treatmentPlan.
   */
  private void generateTreatmentPlan() {

    //A new treatmentPlanEntity to add to the database
    TreatmentPlanEntity newTreatmentPlan = new TreatmentPlanEntity();
    if (severityLevel.equals("MILD")) { //There is no mild Buprenorphine plan currently
      newTreatmentPlan.setNumCounseling(1);
      newTreatmentPlan.setNumSupportMeeting(1);
      newTreatmentPlan.setNumLessons(1);
      newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
      newTreatmentPlan.setNumOutcomeMeasures(1);
      newTreatmentPlan.setNumTimeTracking(1);
      newTreatmentPlan.setNumReadingResponse(1);
      newTreatmentPlan.setNumMedManagement(0);
      newTreatmentPlan.setMedManagementMonthly();
      newTreatmentPlan.setOutcomeMeasureWeekly();
    } else if (severityLevel.equals("MODERATE")) {
      newTreatmentPlan.setNumCounseling(3);
      newTreatmentPlan.setNumSupportMeeting(3);
      newTreatmentPlan.setNumLessons(2);
      newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
      newTreatmentPlan.setNumOutcomeMeasures(3);
      newTreatmentPlan.setNumTimeTracking(2);
      newTreatmentPlan.setNumReadingResponse(2);
      newTreatmentPlan.setMedManagementMonthly();
      newTreatmentPlan.setOutcomeMeasureDaily();

      //handles differences in treatment plans
      if (medicationChoice.equals("ABSTAIN")) {
        newTreatmentPlan.setNumMedManagement(0);
      } else {
        newTreatmentPlan.setNumMedManagement(2);
      }
    } else { //Severe severity level
      newTreatmentPlan.setNumCounseling(5);
      newTreatmentPlan.setNumSupportMeeting(5);
      newTreatmentPlan.setNumLessons(3);
      newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
      newTreatmentPlan.setNumOutcomeMeasures(5);
      newTreatmentPlan.setNumTimeTracking(5);
      newTreatmentPlan.setNumReadingResponse(3);
      newTreatmentPlan.setMedManagementWeekly();
      newTreatmentPlan.setOutcomeMeasureDaily();

      //handles differences in treatment plans
      if (medicationChoice.equals("ABSTAIN")) {
        newTreatmentPlan.setNumMedManagement(0);
      } else {
        newTreatmentPlan.setNumMedManagement(1);
      }
    }

    counselingView.setText(Integer.toString(newTreatmentPlan.getNumCounseling()));
    medManagementView.setText(Integer.toString(newTreatmentPlan.getNumMedManagement()));
    supportMeetingView.setText(Integer.toString(newTreatmentPlan.getNumSupportMeeting()));
    lessonView.setText(Integer.toString(newTreatmentPlan.getNumLessons()));
    treatmentEffectiveView
        .setText(Integer.toString(newTreatmentPlan.getNumTreatmentEffectivenessAssessment()));
    outcomeMeasureView.setText(Integer.toString(newTreatmentPlan.getNumOutcomeMeasures()));
    timeTrackingView.setText(Integer.toString(newTreatmentPlan.getNumTimeTracking()));
    readingResponseView.setText(Integer.toString(newTreatmentPlan.getNumReadingResponse()));

    treatmentPlanEntity = newTreatmentPlan;
    treatmentPlanLoaded = true;
    viewModel.insertTreatmentPlan(newTreatmentPlan);
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
   * A live data object storing the medication choice entity from the database.
   */
  private LiveData<MedicationChoiceEntity> medicationChoiceEntity;

  /**
   * A live data object storing the questionnaire from the database.
   */
  private LiveData<QuestionnaireEntity> questionnaireEntity;

  /**
   * A boolean tracking if the medicaton choice has loaded in.
   */
  private boolean medObserved;

  /**
   * A boolean tracking if the questionnaire has been loaded in.
   */
  private boolean questionnaireObserved;

  /**
   * A boolean to check if the treatment plan has been generated.
   */
  private boolean treatmentPlanLoaded;

  /**
   * A treatment plan entity to handle updates to the database.
   */
  private TreatmentPlanEntity treatmentPlanEntity;

  /**
   * A String representing the user's severityLevel
   */
  private String severityLevel;

  /**
   * A String representing the user's medication Choice
   */
  private String medicationChoice;
}

