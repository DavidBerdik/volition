package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionnaireActivity extends AppCompatActivity {

  public static int answerCounter = 0;
  public static int yesAnswers = 0;
  public static int noAnswers = 0;
  public static int severityLevel = 0;

  public static Boolean qOneAnswer;
  public static Boolean qTwoAnswer;
  public static Boolean qThreeAnswer;
  public static Boolean qFourAnswer;
  public static Boolean qFiveAnswer;
  public static Boolean qSixAnswer;
  public static Boolean qSevenAnswer;
  public static Boolean qEightAnswer;
  public static Boolean qNineAnswer;
  public static Boolean qTenAnswer;
  public static Boolean qElevenAnswer;
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
    yesButton.setOnClickListener(yesClickListener);
    noButton.setOnClickListener(noClickListener);

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

    severityResult.setTextColor(severityResult.getTextColors().withAlpha(0));
  }

  private final View.OnClickListener yesClickListener = new View.OnClickListener() {
    /**
     *  The onClick method for the Yes button event listener will increment the answerCounter to
     *  keep track of which question the App user is on in the questionnaire. The variable
     *  yesAnswers is incremented each time the event is called for the end of the questionnaire
     *  determine the severity level.
     *
     *  The answerCounter is used in the if conditional statement. As the App user takes the
     *  questionnaire the opacity for the question just answered is set to 0 and the opacity for
     *  the Next question is set to 100 and made visible.
     *
     *   Once the user answers question eleven the severity level is calculated by subtracting the
     *   the No answers from the Yes Answers.
     *
     * @param v takes the view during onClick event.
     */
    @Override
    public void onClick(final View v) {
      answerCounter++;
      yesAnswers++;

      if (answerCounter == 1) {
        qOne.setTextColor(qOne.getTextColors().withAlpha(0));
        qTwo.setTextColor(qTwo.getTextColors().withAlpha(100));
        qOneAnswer = true;
      }
      else if (answerCounter == 2) {
        qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
        qThree.setTextColor(qThree.getTextColors().withAlpha(100));
        qTwoAnswer = true;
      }
      else if (answerCounter == 3) {
        qThree.setTextColor(qThree.getTextColors().withAlpha(0));
        qFour.setTextColor(qFour.getTextColors().withAlpha(100));
        qThreeAnswer = true;
      }
      else if (answerCounter == 4) {
        qFour.setTextColor(qFour.getTextColors().withAlpha(0));
        qFive.setTextColor(qFive.getTextColors().withAlpha(100));
        qFourAnswer = true;
      }
      else if (answerCounter == 5) {
        qFive.setTextColor(qFive.getTextColors().withAlpha(0));
        qSix.setTextColor(qSix.getTextColors().withAlpha(100));
        qFiveAnswer = true;
      }
      else if (answerCounter == 6) {
        qSix.setTextColor(qSix.getTextColors().withAlpha(0));
        qSeven.setTextColor(qSeven.getTextColors().withAlpha(100));
        qSixAnswer = true;
      }
      else if (answerCounter == 7) {
        qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
        qEight.setTextColor(qEight.getTextColors().withAlpha(100));
        qSevenAnswer = true;
      }
      else if (answerCounter == 8) {
        qEight.setTextColor(qEight.getTextColors().withAlpha(0));
        qNine.setTextColor(qNine.getTextColors().withAlpha(100));
        qEightAnswer = true;
      }
      else if (answerCounter == 9) {
        qNine.setTextColor(qNine.getTextColors().withAlpha(0));
        qTen.setTextColor(qTen.getTextColors().withAlpha(100));
        qNineAnswer = true;
      }
      else if (answerCounter == 10) {
        qTen.setTextColor(qTen.getTextColors().withAlpha(0));
        qEleven.setTextColor(qEleven.getTextColors().withAlpha(100));
        qTenAnswer = true;
      }
      else if (answerCounter == 11) {
        qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
        qElevenAnswer = true;
        severityResult.setTextColor(severityResult.getTextColors().withAlpha(100));

        severityLevel = yesAnswers - noAnswers;

        if (yesAnswers <= 3) {
          severityString = "Mild";
        }
        else if (yesAnswers <= 5) {
          severityString = "Moderate";
        }
        else {
          severityString = "Severe";
        }

        QuestionnaireActivityViewModel.populateAsync(db);
        startActivity(new Intent(QuestionnaireActivity.this, ViewSeverityLevelActivity.class));
      }
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
      answerCounter++;
      noAnswers++;

      if (answerCounter == 1) {
        qOne.setTextColor(qOne.getTextColors().withAlpha(0));
        qTwo.setTextColor(qTwo.getTextColors().withAlpha(100));
        qOneAnswer = false;
      }
      else if (answerCounter == 2) {
        qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
        qThree.setTextColor(qThree.getTextColors().withAlpha(100));
        qTwoAnswer = false;
      }
      else if (answerCounter == 3) {
        qThree.setTextColor(qThree.getTextColors().withAlpha(0));
        qFour.setTextColor(qFour.getTextColors().withAlpha(100));
        qThreeAnswer = false;
      }
      else if (answerCounter == 4) {
        qFour.setTextColor(qFour.getTextColors().withAlpha(0));
        qFive.setTextColor(qFive.getTextColors().withAlpha(100));
        qFourAnswer = false;
      }
      else if (answerCounter == 5) {
        qFive.setTextColor(qFive.getTextColors().withAlpha(0));
        qSix.setTextColor(qSix.getTextColors().withAlpha(100));
        qFiveAnswer = false;
      }
      else if (answerCounter == 6) {
        qSix.setTextColor(qSix.getTextColors().withAlpha(0));
        qSeven.setTextColor(qSeven.getTextColors().withAlpha(100));
        qSixAnswer = false;
      }
      else if (answerCounter == 7) {
        qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
        qEight.setTextColor(qEight.getTextColors().withAlpha(100));
        qSevenAnswer = false;
      }
      else if (answerCounter == 8) {
        qEight.setTextColor(qEight.getTextColors().withAlpha(0));
        qNine.setTextColor(qNine.getTextColors().withAlpha(100));
        qEightAnswer = false;
      }
      else if (answerCounter == 9) {
        qNine.setTextColor(qNine.getTextColors().withAlpha(0));
        qTen.setTextColor(qTen.getTextColors().withAlpha(100));
        qNineAnswer = false;
      }
      else if (answerCounter == 10) {
        qTen.setTextColor(qTen.getTextColors().withAlpha(0));
        qEleven.setTextColor(qEleven.getTextColors().withAlpha(100));
        qTenAnswer = false;
      }
      else if (answerCounter == 11) {

        qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
        qElevenAnswer = false;
        severityResult.setTextColor(severityResult.getTextColors().withAlpha(100));

        severityLevel = yesAnswers - noAnswers;

        if (yesAnswers <= 3) {
          severityString = "Mild";
        }
        else if (yesAnswers <= 5) {
          severityString = "Moderate";
        }
        else {
          severityString = "Severe";
        }

        QuestionnaireActivityViewModel.populateAsync(db);
        startActivity(new Intent(QuestionnaireActivity.this, ViewSeverityLevelActivity.class));
      }
    }
  };

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
