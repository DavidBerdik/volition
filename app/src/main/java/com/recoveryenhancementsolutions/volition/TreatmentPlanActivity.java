package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Treatment Plan Activity is called when the user selects the option to view their treatment plan.
 * Class reads the treatment plan from the database and displays it on the screen for the user.
 */
public class TreatmentPlanActivity extends AppCompatActivity {
  private TextView mCounselingView;
  private TextView mMedManagementView;
  private TextView mSupportMeetingView;
  private TextView mLessonView;
  private TextView mTreatmentEffectiveView;
  private TextView mOutcomeMeasureView;
  private TextView mTimeTrackingView;
  private TextView mReadingResponseView;
  private VolitionDatabase mDb;
  private LiveData<TreatmentPlan> treatmentPlan;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plan);
    mCounselingView = findViewById(R.id.counselingView);
    mMedManagementView = findViewById(R.id.medManagementView);
    mSupportMeetingView = findViewById(R.id.supportMeetingView);
    mLessonView = findViewById(R.id.lessonView);
    mTreatmentEffectiveView = findViewById(R.id.treatmentEffectiveView);
    mOutcomeMeasureView = findViewById(R.id.outcomeMeasureView);
    mTimeTrackingView = findViewById(R.id.timeTrackingView);
    mReadingResponseView = findViewById(R.id.readingResponseView);

    mDb = VolitionDatabase.getDatabase(this.getApplication());
    treatmentPlan = mDb.treatmentPlanDao().loadTreatmentPlan();

    mCounselingView.setText(treatmentPlan.getValue().numCounseling);
    mMedManagementView.setText(treatmentPlan.getValue().numMedManagement);
    mSupportMeetingView.setText(treatmentPlan.getValue().numSupportMeeting);
    mLessonView.setText(treatmentPlan.getValue().numLessons);
    mTreatmentEffectiveView.setText(treatmentPlan.getValue().numTreatmentEffectivenessAssessment);
    mOutcomeMeasureView.setText(treatmentPlan.getValue().numOutcomeMeasures);
    mTimeTrackingView.setText(treatmentPlan.getValue().numTimeTracking);
    mReadingResponseView.setText(treatmentPlan.getValue().numReadingResponse);
  }

  private void onUpdateButtonClicked(){
    mDb.treatmentPlanDao().insertTreatmentPlan(treatmentPlan.getValue());
  }



}
