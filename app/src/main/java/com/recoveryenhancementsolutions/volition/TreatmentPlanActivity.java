package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Treatment Plan Activity is called when the user selects the option to view their treatment plan.
 * Class reads the treatment plan from the database and displays it on the screen for the user.
 */
public class TreatmentPlanActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plan);
    //  viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);
    //  treatmentPlan = viewModel.getTreatmentPlan();

    counselingView = findViewById(R.id.counselingView);
    medManagementView = findViewById(R.id.medManagementView);
    supportMeetingView = findViewById(R.id.supportMeetingView);
    lessonView = findViewById(R.id.lessonView);
    treatmentEffectiveView = findViewById(R.id.treatmentEffectiveView);
    outcomeMeasureView = findViewById(R.id.outcomeMeasureView);
    timeTrackingView = findViewById(R.id.timeTrackingView);
    readingResponseView = findViewById(R.id.readingResponseView);

    //initializes values for observer tests.
    medObserved = false;
    questionnaireObserved = false;

    /**
     * Calls used for database entry when applicable
     */

    // counselingView.setText(treatmentPlan.getValue().getNumCounseling());
    // medManagementView.setText(treatmentPlan.getValue().getNumMedManagement());
    // supportMeetingView.setText(treatmentPlan.getValue().getNumSupportMeeting());
    // lessonView.setText(treatmentPlan.getValue().getNumLessons());
    // treatmentEffectiveView.setText(treatmentPlan.getValue().getNumTreatmentEffectivenessAssessment());
    // outcomeMeasureView.setText(treatmentPlan.getValue().getNumOutcomeMeasures());
    // timeTrackingView.setText(treatmentPlan.getValue().getNumTimeTracking());
    // readingResponseView.setText(treatmentPlan.getValue().getNumReadingResponse());

    /**
     * Assertion of values for testing purposes
     */
    counselingView.setText("3");
    medManagementView.setText("2");
    supportMeetingView.setText("3");
    lessonView.setText("2");
    treatmentEffectiveView.setText("1");
    outcomeMeasureView.setText("3");
    timeTrackingView.setText("2");
    readingResponseView.setText("2");
  }

  /**
   * Observes the medication choice table in the database. Generates a treatment plan if the
   * questionnaire has already been loaded as well.
   */
  private Observer<MedicationChoiceEntity> medObserver = new Observer<MedicationChoiceEntity>() {
    @Override
    public void onChanged(final MedicationChoiceEntity medicationChoiceEntity) {
      medObserved = true;
      if (questionnaireObserved) {
        generateTreatmentPlan();
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
      questionnaireObserved = true;
      if (medObserved) {
        generateTreatmentPlan();
      }
    }
  };

  /**
   * Method runs when the refresh button in the xml files is clicked.
   */
  private void onUpdateButtonClicked() {
    viewModel.updateDb(treatmentPlan.getValue());
  }

  /**
   * Method runs when add button is pressed to add a counseling session.
   */
  private void onAddCounselButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    treatmentPlan.getValue().setNumCounseling(num);
  }

  /**
   * Method runs when sub button is pressed to subtract a counseling session.
   */
  private void onSubCounselButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    treatmentPlan.getValue().setNumCounseling(num);
  }

  /**
   * Method runs when add button is pressed to add medication management.
   */
  private void onAddMedManagementButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumMedManagement(num);
  }

  /**
   * Method runs when sub button os pressed to subtract from medication management.
   */
  private void onSubMedMangaementButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumMedManagement(num);
  }

  /**
   * Method runs when add button is pressed to add a support group meeting.
   */
  private void onAddSupportGroupMeetingButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumSupportMeeting(num);
  }

  /**
   * Method runs when sub button is pressed to subtract a support group meeting.
   */
  private void onSubSupportGroupButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumSupportMeeting(num);
  }

  /**
   * Method runs when add button is pressed to add an outcome measurement.
   */
  private void onAddOutcomeMeasureButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumOutcomeMeasures(num);
  }

  /**
   * Method runs when sub button is pressed to subtract an outcome measurement.
   */
  private void onSubOutcomeMeasureButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumOutcomeMeasures(num);
  }

  /**
   * Method runs when add button is pressed to add a lesson plan.
   */
  private void onAddLessonPlannerButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumLessons(num);
  }

  /**
   * Method runs when sub button is pressed to subtrct a lesson plan.
   */
  private void onSubLessonPlannerButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumLessons(num);
  }

  /**
   * Method runs when add button is pressed to add a treatment plan effectiveness assessment.
   */
  private void onAddTreatmentEffectivnessAssessmentButtonClicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumTreatmentEffectivenessAssessment(num);
  }

  /**
   * Method runs when sub button is pressed to subtract a treatment plan effectiveness assessment.
   */
  private void onSubTreatmentEffectivnessAssessmentButtonCLicked() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumTreatmentEffectivenessAssessment(num);
  }

  /**
   * Method runs when add button is pressed to add clean time tracking.
   */
  private void onAddCleanTimeTrackingButton() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumTimeTracking(num);
  }

  /**
   * Method runs when sub button is pressed to subtract clean time tracking.
   */
  private void onSubCleanTimeTrackingButton() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumTimeTracking(num);
  }

  /**
   * Method runs when add button is pressed to add read response.
   */
  private void onAddReadResponseButton() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.setNumReadingResponse(num);
  }

  /**
   * Method runs when sub button is pressed to subtract from add read response.
   */
  private void onSubReadResponseButton() {
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if (num - 1 < 0) {
      num = 0;
    } else {
      num--;
    }
    viewModel.treatmentPlan.setNumReadingResponse(num);
  }

  /**
   * Generates a new treatmentPlan.
   */
  private void generateTreatmentPlan() {
    String medicationChoice = "", severityLevel = "";

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
    viewModel.insertDb(newTreatmentPlan);
  }

  /**
   * Treatment plan to display data from
   */
  private LiveData<TreatmentPlanEntity> treatmentPlan;

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
   * A live data object storing the medication choice entity from the database.
   */
  private LiveData<MedicationChoiceEntity> medicationChoiceEntity;


  /**
   * A live data object storing the questionnaire from the database.
   */
  private LiveData<QuestionnaireEntity> questionnaireEntity;

  /**
   * A live data object storing the treatment plan from the database.
   */
  private LiveData<TreatmentPlanEntity> treatmentPlan;

  /**
   * A boolean tracking if the medicaton choice has loaded in.
   */
  private boolean medObserved;

  /**
   * A boolean tracking if the questionnaire has been loaded in.
   */
  private boolean questionnaireObserved;

}

