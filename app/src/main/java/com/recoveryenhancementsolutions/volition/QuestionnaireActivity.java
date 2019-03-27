package com.recoveryenhancementsolutions.volition;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class QuestionnaireActivity extends AppCompatActivity {

  private QuestionnaireActivityViewModel mViewModel;

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


  private static VolitionDatabase mDb;

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
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_questionnaire);
    // mViewModel = ViewModelProviders.of(this).get(QuestionnaireActivity.class);
    mViewModel = ViewModelProviders.of(this).get(QuestionnaireActivityViewModel.class);
    final TextView qOne = (TextView) findViewById(R.id.questionOne);
    final TextView qTwo = (TextView) findViewById(R.id.questionTwo);
    final TextView qThree = (TextView) findViewById(R.id.questionThree);
    final TextView qFour = (TextView) findViewById(R.id.questionFour);
    final TextView qFive = (TextView) findViewById(R.id.questionFive);
    final TextView qSix = (TextView) findViewById(R.id.questionSix);
    final TextView qSeven = (TextView) findViewById(R.id.questionSeven);
    final TextView qEight = (TextView) findViewById(R.id.questionEight);
    final TextView qNine = (TextView) findViewById(R.id.questionNine);
    final TextView qTen = (TextView) findViewById(R.id.questionTen);
    final TextView qEleven = (TextView) findViewById(R.id.questionEleven);

    final TextView severityResult = (TextView) findViewById(R.id.severityResponse);
    mDb = VolitionDatabase.getDatabase(this.getApplication());
    Button YESbtn = (Button) findViewById(R.id.YESbtn);
    Button NObtn = (Button) findViewById(R.id.NObtn);

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


    /*private void insertQuestionOne() {
      mViewModel.questionnaire.observe(this, new Observer<List<QuestionnaireActivityEntity>>() {
        @Override
        public void onChanged(@Nullable List<QuestionnaireActivityEntity> questionnaireActivityEntities) {
          saveStatus(questionnaireActivityEntities); //Need to pass proper arguments
        }
      });
    } */

    YESbtn.setOnClickListener(new View.OnClickListener() {

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
      public void onClick(View v) {
        answerCounter++;
        yesAnswers++;

        if (answerCounter == 1) {
          qOne.setTextColor(qOne.getTextColors().withAlpha(0));
          qTwo.setTextColor(qTwo.getTextColors().withAlpha(100));
          qOneAnswer = true;
        }

        if (answerCounter == 2) {
          qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
          qThree.setTextColor(qThree.getTextColors().withAlpha(100));
          qTwoAnswer = true;
        }

        if (answerCounter == 3) {
          qThree.setTextColor(qThree.getTextColors().withAlpha(0));
          qFour.setTextColor(qFour.getTextColors().withAlpha(100));
          qThreeAnswer = true;
        }

        if (answerCounter == 4) {
          qFour.setTextColor(qFour.getTextColors().withAlpha(0));
          qFive.setTextColor(qFive.getTextColors().withAlpha(100));
          qFourAnswer = true;
        }

        if (answerCounter == 5) {
          qFive.setTextColor(qFive.getTextColors().withAlpha(0));
          qSix.setTextColor(qSix.getTextColors().withAlpha(100));
          qFiveAnswer = true;
        }

        if (answerCounter == 6) {
          qSix.setTextColor(qSix.getTextColors().withAlpha(0));
          qSeven.setTextColor(qSeven.getTextColors().withAlpha(100));
          qSixAnswer = true;
        }

        if (answerCounter == 7) {
          qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
          qEight.setTextColor(qEight.getTextColors().withAlpha(100));
          qSevenAnswer = true;
        }

        if (answerCounter == 8) {
          qEight.setTextColor(qEight.getTextColors().withAlpha(0));
          qNine.setTextColor(qNine.getTextColors().withAlpha(100));
          qEightAnswer = true;
        }

        if (answerCounter == 9) {
          qNine.setTextColor(qNine.getTextColors().withAlpha(0));
          qTen.setTextColor(qTen.getTextColors().withAlpha(100));
          qNineAnswer = true;
        }

        if (answerCounter == 10) {
          qTen.setTextColor(qTen.getTextColors().withAlpha(0));
          qEleven.setTextColor(qEleven.getTextColors().withAlpha(100));
          qTenAnswer = true;
        }

        if (answerCounter == 11) {
          qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
          qElevenAnswer = true;
          severityResult.setTextColor(severityResult.getTextColors().withAlpha(100));

          severityLevel = yesAnswers - noAnswers;

          if (yesAnswers <= 3) {
            severityString = "Mild";
          }

          if (yesAnswers > 3 && yesAnswers <= 5) {
            severityString = "Moderate";
          }

          if (yesAnswers >= 6) {
            severityString = "Severe";
          }
          mViewModel.populateAsync(mDb);
          startActivity(new Intent(QuestionnaireActivity.this, ViewSeverityLevelActivity.class));
        }

      }
    });

    NObtn.setOnClickListener(new View.OnClickListener() {

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
      public void onClick(View v) {
        answerCounter++;
        noAnswers++;

        if (answerCounter == 1) {
          qOne.setTextColor(qOne.getTextColors().withAlpha(0));
          qTwo.setTextColor(qTwo.getTextColors().withAlpha(100));
          qOneAnswer = false;
        }

        if (answerCounter == 2) {
          qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
          qThree.setTextColor(qThree.getTextColors().withAlpha(100));
          qTwoAnswer = false;
        }

        if (answerCounter == 3) {
          qThree.setTextColor(qThree.getTextColors().withAlpha(0));
          qFour.setTextColor(qFour.getTextColors().withAlpha(100));
          qThreeAnswer = false;
        }

        if (answerCounter == 4) {
          qFour.setTextColor(qFour.getTextColors().withAlpha(0));
          qFive.setTextColor(qFive.getTextColors().withAlpha(100));
          qFourAnswer = false;
        }

        if (answerCounter == 5) {
          qFive.setTextColor(qFive.getTextColors().withAlpha(0));
          qSix.setTextColor(qSix.getTextColors().withAlpha(100));
          qFiveAnswer = false;
        }

        if (answerCounter == 6) {
          qSix.setTextColor(qSix.getTextColors().withAlpha(0));
          qSeven.setTextColor(qSeven.getTextColors().withAlpha(100));
          qSixAnswer = false;
        }

        if (answerCounter == 7) {
          qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
          qEight.setTextColor(qEight.getTextColors().withAlpha(100));
          qSevenAnswer = false;
        }

        if (answerCounter == 8) {
          qEight.setTextColor(qEight.getTextColors().withAlpha(0));
          qNine.setTextColor(qNine.getTextColors().withAlpha(100));
          qEightAnswer = false;
        }

        if (answerCounter == 9) {
          qNine.setTextColor(qNine.getTextColors().withAlpha(0));
          qTen.setTextColor(qTen.getTextColors().withAlpha(100));
          qNineAnswer = false;
        }

        if (answerCounter == 10) {
          qTen.setTextColor(qTen.getTextColors().withAlpha(0));
          qEleven.setTextColor(qEleven.getTextColors().withAlpha(100));
          qTenAnswer = false;
        }

        if (answerCounter == 11) {

          qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
          qElevenAnswer = false;
          severityResult.setTextColor(severityResult.getTextColors().withAlpha(100));

          severityLevel = yesAnswers - noAnswers;

          if (yesAnswers <= 3) {
            severityString = "Mild";
          }

          if (yesAnswers > 3 && yesAnswers <= 5) {
            severityString = "Moderate";
          }

          if (yesAnswers >= 6) {
            severityString = "Severe";
          }

          mViewModel.populateAsync(mDb);
          startActivity(new Intent(QuestionnaireActivity.this, ViewSeverityLevelActivity.class));
        }

      }

    });

  }
}
