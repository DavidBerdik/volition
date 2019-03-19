package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Treatment Plan Activity is called when the user selects the option to view their treatment plan.
 * Class reads the treatment plan from the database and displays it on the screen for the user.
 */
public class TreatmentPlanActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState){
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

    counselingView.setText(viewModel.treatmentPlan.getValue().getNumCounseling());
    medManagementView.setText(viewModel.treatmentPlan.getValue().getNumMedManagement());
    supportMeetingView.setText(viewModel.treatmentPlan.getValue().getNumSupportMeeting());
    lessonView.setText(viewModel.treatmentPlan.getValue().getNumLessons());
    treatmentEffectiveView.setText(viewModel.treatmentPlan.getValue().getNumTreatmentEffectivenessAssessment());
    outcomeMeasureView.setText(viewModel.treatmentPlan.getValue().getNumOutcomeMeasures());
    timeTrackingView.setText(viewModel.treatmentPlan.getValue().getNumTimeTracking());
    readingResponseView.setText(viewModel.treatmentPlan.getValue().getNumReadingResponse());
  }

  /**
   * Method runs when the refresh button in the xml files is clicked.
   */
  private void onUpdateButtonClicked(){
    viewModel.updateDb();
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
   * The TextView displaying data representing the current value of
   * numTreatmentEffectivenessAssessment in the treatmentPlanEntity.
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
}
