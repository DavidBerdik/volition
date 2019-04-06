package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionnaireActivity extends AppCompatActivity {

    public static boolean prevAnswer = false;
    public static String severityString;

    /**
     * Changes the functionality of the phones back button so that it can no longer take you to
     * previous activities.  It also serves the same function as our own added back button
     */
    @Override
    public void onBackPressed() {
        if (qViewModel.getDisplayState() == 0) {
            super.onBackPressed();
        } else {
            if (prevAnswer) {
                qViewModel.setYesAnswers(qViewModel.getYesAnswers() - 1);
            }
            qViewModel.setDisplayState(qViewModel.getDisplayState() - 1);
            checkBackButton();
            if (qViewModel.getDisplayState() < 10) {
                findDisplayState();
            }
        }
    }

    /**
     * This method receives a boolean value from the on click listeners and will store that value in
     * an array list as well as checking the current display state and changing to adjust. when the
     * questionnaire is over it will assign and store values associated with the questionnaire
     *
     * @param value boolean representing the answer to previous question
     */
    public void storeOnclickQuestionnaire(boolean value) {

        prevAnswer = value;
        if (qViewModel.getDisplayState() < 10) {
            qViewModel.setQuestionnaireAnswers(value);

            qViewModel.setDisplayState(qViewModel.getDisplayState() + 1);
            findDisplayState();

        } else if (qViewModel.getDisplayState() == 10) {

            qViewModel.setQuestionnaireAnswers(value);

            if (qViewModel.getYesAnswers() <= 3) {
                severityString = "Mild";
            } else if (qViewModel.getYesAnswers() <= 5) {
                severityString = "Moderate";
            } else {
                severityString = "Severe";
            }
            QuestionnaireActivityViewModel.populateAsync(db);
            startActivity(new Intent(QuestionnaireActivity.this, ViewSeverityLevelActivity.class));
        }
    }


    /**
     * The method onCreate will initialize the Activity with the view of the questionnaire_activity
     * xml. The Text View for every question is created with the opacity for each question and is
     * initially set to 0. Question one's opacity says at the default value of 100 for the initial
     * view to begin the questionnaire. There are yes and No event listeners for the Yes and No button
     * clicks while taking the questionnaire
     *
     * @param savedInstanceState stores the saved state in order to recreate the activity.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        final Button yesButton = findViewById(R.id.YESbtn);
        final Button noButton = findViewById(R.id.NObtn);
        final Button backButton = findViewById(R.id.backButton);

        qViewModel = ViewModelProviders.of(this).get(QuestionnaireActivityViewModel.class);
        db = VolitionDatabase.getDatabase(this.getApplication());
        qViewModel.setDisplayState(qViewModel.getDisplayState());

        yesButton.setOnClickListener(yesClickListener);
        noButton.setOnClickListener(noClickListener);
        backButton.setOnClickListener(backClickListener);

        backButton.setEnabled(false);
        backButton.setAlpha(0);

        qOne = findViewById(R.id.questionOne);
        qTwo = findViewById(R.id.questionTwo);
        qThree = findViewById(R.id.questionThree);
        qFour = findViewById(R.id.questionFour);
        qFive = findViewById(R.id.questionFive);
        qSix = findViewById(R.id.questionSix);
        qSeven = findViewById(R.id.questionSeven);
        qEight = findViewById(R.id.questionEight);
        qNine = findViewById(R.id.questionNine);
        qTen = findViewById(R.id.questionTen);
        qEleven = findViewById(R.id.questionEleven);

        findDisplayState();

        qViewModel.fillQuestionnaireAnswers();

    }

    /**
     * To solve the issues caused by rotation this method is called onCreate and other situations that
     * require changing or setting display state which sets all displays to full opacity and then
     * checks which state is currently active in the view model and changes the opacity of that state
     */
    private void findDisplayState() {
        int state = qViewModel.getDisplayState();

        checkBackButton();

        qOne.setTextColor(qOne.getTextColors().withAlpha(0));
        qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
        qThree.setTextColor(qThree.getTextColors().withAlpha(0));
        qFour.setTextColor(qFour.getTextColors().withAlpha(0));
        qFive.setTextColor(qFive.getTextColors().withAlpha(0));
        qSix.setTextColor(qSix.getTextColors().withAlpha(0));
        qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
        qEight.setTextColor(qEight.getTextColors().withAlpha(0));
        qNine.setTextColor(qNine.getTextColors().withAlpha(0));
        qTen.setTextColor(qTen.getTextColors().withAlpha(0));
        qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
        if (state == 0) {
            qOne.setTextColor(qOne.getTextColors().withAlpha(255));
        } else if (state == 1) {
            qTwo.setTextColor(qTwo.getTextColors().withAlpha(255));
        } else if (state == 2) {
            qThree.setTextColor(qThree.getTextColors().withAlpha(255));
        } else if (state == 3) {
            qFour.setTextColor(qFour.getTextColors().withAlpha(255));
        } else if (state == 4) {
            qFive.setTextColor(qFive.getTextColors().withAlpha(255));
        } else if (state == 5) {
            qSix.setTextColor(qSix.getTextColors().withAlpha(255));
        } else if (state == 6) {
            qSeven.setTextColor(qSeven.getTextColors().withAlpha(255));
        } else if (state == 7) {
            qEight.setTextColor(qEight.getTextColors().withAlpha(255));
        } else if (state == 8) {
            qNine.setTextColor(qNine.getTextColors().withAlpha(255));
        } else if (state == 9) {
            qTen.setTextColor(qTen.getTextColors().withAlpha(255));
        } else {
            qEleven.setTextColor(qEleven.getTextColors().withAlpha(255));
        }

    }

    private final View.OnClickListener yesClickListener = new View.OnClickListener() {
        /**
         * The onClick method for the Yes button event listener will increment the answerCounter to
         * keep track of which question the App user is on in the questionnaire. The variable
         * yesAnswers is incremented each time the event is called for the end of the questionnaire
         * determine the severity level.
         *
         * The answerCounter is used in the if conditional statement. As the App user takes the
         * questionnaire the opacity for the question just answered is set to 0 and the opacity for
         * the Next question is set to 100 and made visible.
         *
         * Once the user answers question eleven the severity level is calculated by subtracting the
         * the No answers from the Yes Answers.
         *
         * @param v takes the view during onClick event.
         */
        @Override
        public void onClick(final View v) {
            qViewModel.setYesAnswers(qViewModel.getYesAnswers() + 1);
            storeOnclickQuestionnaire(true);
            checkBackButton();
        }
    };

    private final View.OnClickListener noClickListener = new View.OnClickListener() {
        /**
         *  The onClick method for the No button event listener will increment the answerCounter to
         *  keep track of which question the App user is on in the questionnaire. The variable
         *  noAnswers is incremented each time the event is called for the end of the questionnaire
         *  determine the severity level.
         *
         *  The answerCounter is used in the if conditional statement. As the App user takes the
         *  questionnaire the opacity for the question just answered is set to 0 and the opacity for
         *  the next question is set to 100 and made visible.
         *
         *  Once the user answers question eleven the severity level is calculated by subtracting the
         *  the No answers from the Yes Answers.
         *
         * @param v takes the view during the onClick event.
         */
        @Override
        public void onClick(final View v) {
            storeOnclickQuestionnaire(false);
            checkBackButton();
        }
    };
    private final View.OnClickListener backClickListener = new View.OnClickListener() {
        /**
         * The onClick method for the back button event listener will decrement the answerCounter to
         * keep track of which question the App user is on in the questionnaire. The variable
         * yesAnswers is decremented each time the event is called  and the last answer given was a yes for the end of the questionnaire
         * determine the severity level.
         *
         * @param v takes the view during onClick event.
         */
        @Override
        public void onClick(final View v) {

            if (prevAnswer) {
                qViewModel.setYesAnswers(qViewModel.getYesAnswers() - 1);
            }
            qViewModel.setDisplayState(qViewModel.getDisplayState() - 1);
            checkBackButton();
            if (qViewModel.getDisplayState() < 10) {
                findDisplayState();
            }
        }
    };


    /**
     * Method to activate back button after question one or deactivate it if it is on question one.
     */
    private void checkBackButton() {
        Button backButton = findViewById(R.id.backButton);
        if (qViewModel.getDisplayState() == 0) {
            backButton.setEnabled(false);
            backButton.setAlpha(0);
        } else {
            backButton.setEnabled(true);
            backButton.setAlpha(1);
        }

    }

    private QuestionnaireActivityViewModel qViewModel;
    private TextView qOne;
    private TextView qTwo;
    private TextView qThree;
    private TextView qFour;
    private TextView qFive;
    private TextView qSix;
    private TextView qSeven;
    private TextView qEight;
    private TextView qNine;
    private TextView qTen;
    private TextView qEleven;
    private VolitionDatabase db;
}
