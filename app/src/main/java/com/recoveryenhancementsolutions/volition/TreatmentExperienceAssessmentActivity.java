package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class TreatmentExperienceAssessmentActivity extends AppCompatActivity {

    public static int numberCompleted;

    /**
     * Changes back button so that if you are past the first question it will return you 1 question
     * rather than returning to previous activity and if you are on the first question it will return
     * to previous activity
     */
    @Override
    public void onBackPressed() {
        if (teaView.getDisplayState() == 0) {
            super.onBackPressed();
        } else {
            if (teaView.getDisplayState() < 4) {
                teaView.setDisplayState(teaView.getDisplayState() - 1);
                findDisplayState();
            } else {
                remarksBack();
            }
        }
    }

    public String getTeaResultsText() {
        return tea_results.getText().toString();
    }

    protected TreatmentExperienceAssessmentViewModel getViewModel() {
        return teaView;
    }

    /**
     * The method onCreate will initialize the Activity with the view of the
     * treatment_experience_assessment_activity xml. The Text View for every question is created with
     * the opacity for each question and is initially set to 0. Question one's opacity says at the
     * default value of 100 for the initial view to begin the tea. There will be a scroll with 10
     * number options for the tea.
     *
     * @param savedInstanceState stores the saved state in order to recreate the activity.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_experience_assessment);

        final Button submit = findViewById(R.id.submit_button_tea);
        teaView = ViewModelProviders.of(this).get(TreatmentExperienceAssessmentViewModel.class);
        submit.setOnClickListener(submitClickListener);

        qs[0] = findViewById(R.id.questionOne);
        qs[1] = findViewById(R.id.questionTwo);
        qs[2] = findViewById(R.id.questionThree);
        qs[3] = findViewById(R.id.questionFour);

        title[0] = findViewById(R.id.healthText);
        title[1] = findViewById(R.id.lifestyleText);
        title[2] = findViewById(R.id.communityText);
        title[3] = findViewById(R.id.substanceText);

        if (teaView.getDisplayState() < 4) {
            findDisplayState();

            teaView.fillTeaAnswers();

            final NumberPicker np = findViewById(R.id.tea_number_picker);
            np.setMinValue(1);
            np.setMaxValue(10);
            np.setValue(5);
            rating = 5;
            np.setOnValueChangedListener(onValueChangeListener);

            if (lastKnownValue != -1) {
                np.setValue(lastKnownValue);
            }

            tea_results = findViewById(R.id.tea_results);
            tea_results.setText(getTeaString(np.getValue()));
        } else if (teaView.getDisplayState() == 4) {
            onRemarksCall();
        }

        final BottomNavigationView bottomNavigationView = findViewById(R.id.activity_back_navigation);

    }

    /**
     * When the button is clicked it passes the string and stores all answers into the database.
     */
    private final View.OnClickListener submitRemarksClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            final String remarksTxt = remarks.getText().toString();
            teaView.setRemarks(remarksTxt);
            teaView.insTEA();
            startActivity(new Intent(TreatmentExperienceAssessmentActivity.this,
                    HomeActivity.class));
        }
    };

    /**
     * When the submit button is clicked, it goes to the set answer for tea and passes the rating
     */
    private final View.OnClickListener submitClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            setAnswerForTea(rating);
        }
    };

    /**
     * This method displays what the user chooses on the scroll bar with rating attached
     *
     * @param rating passes what the user chooses on the scroll bar
     */
    private String getTeaString(final int rating) {
        lastKnownValue = rating;
        if ((lastKnownValue >= 1) && (lastKnownValue <= 3)) {
            return "Your choice: " + lastKnownValue + " (" + outputs[0] + ")";
        } else if ((lastKnownValue >= 4) && (lastKnownValue <= 6)) {
            return "Your choice: " + lastKnownValue + " (" + outputs[1] + ")";
        } else if ((lastKnownValue >= 7) && (lastKnownValue <= 10)) {
            return "Your choice: " + lastKnownValue + " (" + outputs[2] + ")";
        } else {
            return "";
        }
    }

    /**
     * This method is called to reset the content view onto the old xml after it has been set to the
     * remarks xml.  This is only called in the onBackPressed method when the content view is on the
     * remarks xml.
     */
    private void remarksBack() {
        setContentView(R.layout.activity_treatment_experience_assessment);

        final Button submit = findViewById(R.id.submit_button_tea);
        submit.setOnClickListener(submitClickListener);
        qs[0] = findViewById(R.id.questionOne);
        qs[1] = findViewById(R.id.questionTwo);
        qs[2] = findViewById(R.id.questionThree);
        qs[3] = findViewById(R.id.questionFour);

        title[0] = findViewById(R.id.healthText);
        title[1] = findViewById(R.id.lifestyleText);
        title[2] = findViewById(R.id.communityText);
        title[3] = findViewById(R.id.substanceText);

        teaView.setDisplayState(teaView.getDisplayState() - 1);

        findDisplayState();

        final NumberPicker np = findViewById(R.id.tea_number_picker);
        np.setMinValue(1);
        np.setMaxValue(10);
        np.setValue(5);
        rating = 5;
        np.setOnValueChangedListener(onValueChangeListener);

        if (lastKnownValue != -1) {
            np.setValue(lastKnownValue);
        }

        tea_results = findViewById(R.id.tea_results);
        tea_results.setText(getTeaString(np.getValue()));
    }

    /**
     * This method finds out what question user is on to correct for back button presses and rotations
     * then changes textView opacity to make sure correct question is being viewed
     */
    private void findDisplayState() {
        int state = teaView.getDisplayState();

        for (TextView q : qs) {
            q.setTextColor(q.getTextColors().withAlpha(0));
        }
        qs[state].setTextColor(qs[state].getTextColors().withAlpha(255));

        for (TextView t : title) {
            t.setTextColor(t.getTextColors().withAlpha(0));
        }
        title[state].setTextColor(title[state].getTextColors().withAlpha(255));
    }

    /**
     * Changes the rating based on the choice.
     */
    private NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {

        @Override
        public void onValueChange(final NumberPicker np, final int oldVal, final int newVal) {
            tea_results.setText(getTeaString(np.getValue()));
            rating = newVal;
        }
    };

    /**
     * Method called to set content view to remarks screen whenever necessary
     */
    private void onRemarksCall() {
        setContentView(R.layout.activity_treatment_experience_assessment_remarks);
        final Button submitButton = findViewById(R.id.submitButton);
        remarks = findViewById(R.id.remarksText);
        remarks.requestFocus();
        submitButton.setOnClickListener(submitRemarksClickListener);
    }

    /**
     * Displays the new question. Sends intent to next part of activity.
     *
     * @param rating an int value representing a user entered value on how they feel
     *               for each question
     */
    private void setAnswerForTea(int rating) {
        if (teaView.getDisplayState() < 3) {
            teaView.setTeaAnswers(rating);
            teaView.setDisplayState(teaView.getDisplayState() + 1);
            findDisplayState();
        } else if (teaView.getDisplayState() == 3) {
            teaView.setTeaAnswers(rating);
            teaView.setDisplayState(teaView.getDisplayState() + 1);
            onRemarksCall();
        }
    }

    private TreatmentExperienceAssessmentViewModel teaView;
    private TextView tea_results;
    private String[] outputs = {"None or not much", "Better", "Much Better"};
    private int rating;
    private static int lastKnownValue = -1;
    private TextView[] qs = new TextView[4];
    private TextView[] title = new TextView[4];
    private EditText remarks;
}