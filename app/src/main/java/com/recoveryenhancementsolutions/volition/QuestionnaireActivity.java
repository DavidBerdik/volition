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
  public static boolean prevAnswer = false;
  public static int severityLevel = 0;
  public static ArrayList<Boolean> questionnaireAnswers = new ArrayList<>();
  public static ArrayList<TextView> questionsForQuestionnaire = new ArrayList<>();
  public static String severityString;

  /**
   * Changes the functionality of the phones back button so that it can no longer take you to
   * previous activities.  It also serves the same function as our own added back button
   */
  @Override
  public void onBackPressed() {
    if (qViewModel.getDisplayState() == 0) {

    } else {
      if (prevAnswer) {
        yesAnswers--;
      }
      qViewModel.setDisplayState(qViewModel.getDisplayState() - 1);
      checkBackButton();
      if (qViewModel.getDisplayState() < 10) {

        questionsForQuestionnaire.get(qViewModel.getDisplayState() + 1).setTextColor(
            questionsForQuestionnaire.get(qViewModel.getDisplayState() + 1).getTextColors()
                .withAlpha(0));
        questionsForQuestionnaire.get(qViewModel.getDisplayState()).setTextColor(
            questionsForQuestionnaire.get(qViewModel.getDisplayState()).getTextColors()
                .withAlpha(100));
      }
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
yesAnswers=0;

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_questionnaire);
    final Button yesButton = findViewById(R.id.YESbtn);
    final Button noButton = findViewById(R.id.NObtn);
    final Button backButton = findViewById(R.id.backButton);
    final Button confirmButton = findViewById(R.id.confirmButton);
    final Button nextButton = findViewById(R.id.nextButton);

    qViewModel = ViewModelProviders.of(this).get(QuestionnaireActivityViewModel.class);
    severityResult = findViewById(R.id.severityResponse);
    db = VolitionDatabase.getDatabase(this.getApplication());
    qViewModel.setDisplayState(0);

    yesButton.setOnClickListener(yesClickListener);
    noButton.setOnClickListener(noClickListener);
    backButton.setOnClickListener(backClickListener);
    confirmButton.setOnClickListener(confirmButtonListener);
    nextButton.setOnClickListener(nextButtonListener);

    confirmButton.setEnabled(false);
    confirmButton.setAlpha(0);
    backButton.setEnabled(false);
    backButton.setAlpha(0);
    nextButton.setEnabled(false);
    nextButton.setAlpha(0);

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
  private final View.OnClickListener nextButtonListener = new View.OnClickListener() {
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
      //storeOnclickQuestionnaire(false);
    }
  };
  private final View.OnClickListener confirmButtonListener = new View.OnClickListener() {
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
      //storeOnclickQuestionnaire(false);
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

      if (prevAnswer) {
        yesAnswers--;
      }
      qViewModel.setDisplayState(qViewModel.getDisplayState() - 1);
      checkBackButton();
      if (qViewModel.getDisplayState() < 10) {

        questionsForQuestionnaire.get(qViewModel.getDisplayState() + 1).setTextColor(
            questionsForQuestionnaire.get(qViewModel.getDisplayState() + 1).getTextColors()
                .withAlpha(0));
        questionsForQuestionnaire.get(qViewModel.getDisplayState()).setTextColor(
            questionsForQuestionnaire.get(qViewModel.getDisplayState()).getTextColors()
                .withAlpha(100));
      }


    }
  };

  public void storeOnclickQuestionnaire(boolean value) {
    //backButton.setEnabled(true);
    // backButton.setAlpha(1);
    prevAnswer = value;
    if (qViewModel.getDisplayState() < 10) {
      questionnaireAnswers.set(qViewModel.getDisplayState(), value);
      questionsForQuestionnaire.get(qViewModel.getDisplayState())
          .setTextColor(questionsForQuestionnaire.get(qViewModel.getDisplayState()).getTextColors()
              .withAlpha(0));
      questionsForQuestionnaire.get(qViewModel.getDisplayState() + 1).setTextColor(
          questionsForQuestionnaire.get(qViewModel.getDisplayState() + 1).getTextColors()
              .withAlpha(100));
      //answerCounter++;
      qViewModel.setDisplayState(qViewModel.getDisplayState() + 1);

    } else if (qViewModel.getDisplayState() == 10) {
      questionnaireAnswers.set(qViewModel.getDisplayState(), value);
      questionsForQuestionnaire.get(qViewModel.getDisplayState())
          .setTextColor(questionsForQuestionnaire.get(qViewModel.getDisplayState()).getTextColors()
              .withAlpha(0));
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

  /**
   * Method to activate back button after question one or deactivate it if it is on question one.
   */
  private void checkBackButton() {
    Button backButton = (Button) findViewById(R.id.backButton);
    if (qViewModel.getDisplayState() == 0) {
      backButton.setEnabled(false);
      backButton.setAlpha(0);
    } else {
      backButton.setEnabled(true);
      backButton.setAlpha(1);
    }

  }

  private QuestionnaireActivityViewModel qViewModel;
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
