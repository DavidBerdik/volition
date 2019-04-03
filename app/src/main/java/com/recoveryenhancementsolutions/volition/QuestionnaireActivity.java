package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class QuestionnaireActivity extends AppCompatActivity {

  public static int answerCounter = 0;
  public static int yesAnswers = 0;
  public static int noAnswers = 0;
  public static int severityLevel = 0;
  public static ArrayList<Boolean> questionnaireAnswers = new ArrayList<>();
  public static  ArrayList<TextView> questionsForQuestionnaire = new ArrayList<>();
  public static String severityString;

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

    ViewModelProviders.of(this).get(QuestionnaireActivityViewModel.class);
    severityResult = findViewById(R.id.severityResponse);
    db = VolitionDatabase.getDatabase(this.getApplication());
    final Button yesButton = findViewById(R.id.YESbtn);
    final Button noButton = findViewById(R.id.NObtn);
    final Button backButton = findViewById(R.id.backButton);
    yesButton.setOnClickListener(yesClickListener);
    noButton.setOnClickListener(noClickListener);
    backButton.setOnClickListener(backClickListener);

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

    questionsForQuestionnaire.add(qOne);
    questionsForQuestionnaire.add(qTwo);
    questionsForQuestionnaire.add(qThree);
    questionsForQuestionnaire.add(qFour);
    questionsForQuestionnaire.add(qFive);
    questionsForQuestionnaire.add(qSix);
    questionsForQuestionnaire.add(qSeven);
    questionsForQuestionnaire.add(qEight);
    questionsForQuestionnaire.add(qNine);
    questionsForQuestionnaire.add(qTen);
    questionsForQuestionnaire.add(qEleven);
    questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);
      questionnaireAnswers.add(false);



      severityResult.setTextColor(severityResult.getTextColors().withAlpha(0));
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
        storeOnclickQuestionnaire(true);
        yesAnswers++;

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
    }
  };
    private final View.OnClickListener backClickListener = new View.OnClickListener() {
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
           answerCounter--;


        }
    };

  public void storeOnclickQuestionnaire(boolean value) {

        if (answerCounter <10) {
            questionnaireAnswers.set(answerCounter, value);
            questionsForQuestionnaire.get(answerCounter).setTextColor(questionsForQuestionnaire.get(answerCounter).getTextColors().withAlpha(0));
            questionsForQuestionnaire.get(answerCounter+1).setTextColor(questionsForQuestionnaire.get(answerCounter+1).getTextColors().withAlpha(100));
            answerCounter++;

        }

        else if (answerCounter==10){
            questionnaireAnswers.set(answerCounter, value);
            questionsForQuestionnaire.get(answerCounter).setTextColor(questionsForQuestionnaire.get(answerCounter).getTextColors().withAlpha(0));
            severityLevel = yesAnswers - noAnswers;
            if (yesAnswers <= 3) {
                severityString = "Mild";
            } else if (yesAnswers <= 5) {
                severityString = "Moderate";
            } else {
                severityString = "Severe";
            }
            QuestionnaireActivityViewModel.populateAsync(db);
            startActivity(new Intent(QuestionnaireActivity.this, ViewSeverityLevelActivity.class));
        }
        return;
    }
  private TextView severityResult;
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
