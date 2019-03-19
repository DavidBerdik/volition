package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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
   * Method runs when add button is pressed to add a counseling session.
   */
  private void onAddCounselButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumCounseling(num);
  }

  /**
   * Method runs when sub button is pressed to subtract a counseling session.
   */
  private void onSubCounselButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumCounseling(num);
  }

  /**
   * Method runs when add button is pressed to add medication management.
   */
  private void onAddMedManagementButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumMedManagement(num);
  }

  /**
   * Method runs when sub button os pressed to subtract from medication management.
   */
  private void onSubMedMangaementButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumMedManagement(num);
  }

  /**
   * Method runs when add button is pressed to add a support group meeting.
   */
  private void onAddSupportGroupMeetingButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumSupportMeeting(num);
  }

  /**
   * Method runs when sub button is pressed to subtract a support group meeting.
   */
  private void onSubSupportGroupButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumSupportMeeting(num);
  }

  /**
   * Method runs when add button is pressed to add an outcome measurement.
   */
  private void onAddOutcomeMeasureButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumOutcomeMeasures(num);
  }

  /**
   * Method runs when sub button is pressed to subtract an outcome measurement.
   */
  private void onSubOutcomeMeasureButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumOutcomeMeasures(num);
  }

  /**
   * Method runs when add button is pressed to add a lesson plan.
   */
  private void onAddLessonPlannerButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumLessons(num);
  }

  /**
   * Method runs when sub button is pressed to subtrct a lesson plan.
   */
  private void onSubLessonPlannerButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumLessons(num);
  }

  /**
   * Method runs when add button is pressed to add a treatment plan effectiveness assessment.
   */
  private void onAddTreatmentEffectivnessAssessmentButtonClicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(num);
  }

  /**
   * Method runs when sub button is pressed to subtract a treatment plan effectiveness assessment.
   */
  private void onSubTreatmentEffectivnessAssessmentButtonCLicked(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(num);
  }

  /**
   * Method runs when add button is pressed to add clean time tracking.
   */
  private void onAddCleanTimeTrackingButton(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumTimeTracking(num);
  }

  /**
   * Method runs when sub button is pressed to subtract clean time tracking.
   */
  private void onSubCleanTimeTrackingButton(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumTimeTracking(num);
  }

  /**
   * Method runs when add button is pressed to add read response.
   */
  private void onAddReadResponseButton(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s) + 1;
    viewModel.treatmentPlan.getValue().setNumReadingResponse(num);
  }

  /**
   * Method runs when sub button is pressed to subtract from add read response.
   */
  private void onSubReadResponseButton(){
    String s = counselingView.getText().toString();
    int num = Integer.parseInt(s);
    if(num - 1 < 0) {
      num = 0;
    }
    else{
      num--;
    }
    viewModel.treatmentPlan.getValue().setNumReadingResponse(num);
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

