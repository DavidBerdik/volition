package com.recoveryenhancementsolutions.volition;

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

  private int numCounseling;
  private int numMedManagement;
  private int numSupportMeeting;
  private int numLesson;
  private int numTreatmentEffective;
  private int numOutcomeMeasure;
  private int numTimeTrackingView;
  private int numReadingResponse;

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
  }

}
