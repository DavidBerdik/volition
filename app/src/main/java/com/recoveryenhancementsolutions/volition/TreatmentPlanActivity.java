package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        treatmentPlan = viewModel.getTreatmentPlan();

        counselingView = findViewById(R.id.counselingView);
        medManagementView = findViewById(R.id.medManagementView);
        supportMeetingView = findViewById(R.id.supportMeetingView);
        lessonView = findViewById(R.id.lessonView);
        treatmentEffectiveView = findViewById(R.id.treatmentEffectiveView);
        outcomeMeasureView = findViewById(R.id.outcomeMeasureView);
        timeTrackingView = findViewById(R.id.timeTrackingView);
        readingResponseView = findViewById(R.id.readingResponseView);

        /**
         * Button Identifiers
         */

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


        /**
         * Button onClickListeners
         */

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
     * onClick Listener class using a switch statement to dictate proper
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subCounselButton:
                onSubCleanTimeTrackingButton();
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
        }
    }

    /**
     * Method runs when the refresh button in the xml files is clicked.
     */
    private void onUpdateButtonClicked() {
    }

    /**
     * Method runs when add button is pressed to add a counseling session.
     */
    private void onAddCounselButtonClicked() {
        String s = counselingView.getText().toString();
        int num = Integer.parseInt(s) + 1;
        viewModel.treatmentPlan.setNumCounseling(num);
    }

    /**
     * Method runs when sub button is pressed to subtract a counseling session.
     */
    private void onSubCounselButtonClicked() {
        final String s = counselingView.getText().toString();
        int num = Integer.parseInt(s);
        if (num - 1 < 0) {
            num = 0;
        } else {
            num--;
        }
        viewModel.treatmentPlan.setNumCounseling(num);
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

