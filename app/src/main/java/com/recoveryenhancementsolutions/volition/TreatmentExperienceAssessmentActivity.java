package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class TreatmentExperienceAssessmentActivity extends AppCompatActivity {

  public static int answerCounter = 0;

  public static ArrayList<Integer> teaAnswers = new ArrayList<>();
  public static ArrayList<TextView> questionsForTea = new ArrayList<>();
  public static ArrayList<TextView> headersForTea = new ArrayList<>();
  public TextView qOne;
  public TextView qTwo;
  public TextView qThree;
  public TextView qFour;
  public TextView substance;
  public TextView health;
  public TextView lifestyle;
  public TextView community;


  /**
   * The method onCreate will initialize the Activity with the view of the treatment_experience_assessment_activity
   * xml. The Text View for every question is created with the opacity for each question and is
   * initially set to 0. Question one's opacity says at the default value of 100 for the initial
   * view to begin the questionnaire. There will be a scroll with 10 number options for the tea.
   *
   * @param savedInstanceState stores the saved state in order to recreate the activity.
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_treatment_experience_assessment);

    final Button oneButton = findViewById(R.id.button1);
    final Button twoButton = findViewById(R.id.button2);
    final Button threeButton = findViewById(R.id.button3);
    final Button fourButton = findViewById(R.id.button4);
    final Button fiveButton = findViewById(R.id.button5);
    final Button sixButton = findViewById(R.id.button6);
    final Button sevenButton = findViewById(R.id.button7);
    final Button eightButton = findViewById(R.id.button8);
    final Button nineButton = findViewById(R.id.button9);
    final Button tenButton = findViewById(R.id.button10);

    oneButton.setOnClickListener(oneClickListener);
    twoButton.setOnClickListener(twoClickListener);
    threeButton.setOnClickListener(twoClickListener);
    fourButton.setOnClickListener(twoClickListener);
    fiveButton.setOnClickListener(twoClickListener);
    sixButton.setOnClickListener(twoClickListener);
    sevenButton.setOnClickListener(twoClickListener);
    eightButton.setOnClickListener(twoClickListener);
    nineButton.setOnClickListener(twoClickListener);
    tenButton.setOnClickListener(twoClickListener);

    qOne = findViewById(R.id.questionOne);
    qTwo = findViewById(R.id.questionTwo);
    qThree = findViewById(R.id.questionThree);
    qFour = findViewById(R.id.questionFour);

    health = findViewById(R.id.healthText);
    lifestyle = findViewById(R.id.lifestyleText);
    community = findViewById(R.id.communityText);
    substance = findViewById(R.id.substanceText);

    qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
    qThree.setTextColor(qThree.getTextColors().withAlpha(0));
    qFour.setTextColor(qFour.getTextColors().withAlpha(0));

    health.setTextColor(qTwo.getTextColors().withAlpha(0));
    lifestyle.setTextColor(qTwo.getTextColors().withAlpha(0));
    community.setTextColor(qTwo.getTextColors().withAlpha(0));

    questionsForTea.add(qOne);
    questionsForTea.add(qTwo);
    questionsForTea.add(qThree);
    questionsForTea.add(qFour);

    headersForTea.add(substance);
    headersForTea.add(health);
    headersForTea.add(lifestyle);
    headersForTea.add(community);

    teaAnswers.add(0);
    teaAnswers.add(0);
    teaAnswers.add(0);
    teaAnswers.add(0);

  }

  private final View.OnClickListener oneClickListener = new View.OnClickListener() {

    @Override
    public void onClick(final View v) {

      setAnswerForTea(1);
    }
  };

  private final View.OnClickListener twoClickListener = new View.OnClickListener() {

    @Override
    public void onClick(final View v) {

      setAnswerForTea(2);
    }
  };


  public void setAnswerForTea(int value) {
    if (answerCounter < 3) {
      teaAnswers.set(answerCounter, value);
      questionsForTea.get(answerCounter)
          .setTextColor(questionsForTea.get(answerCounter).getTextColors().withAlpha(0));
      headersForTea.get(answerCounter)
          .setTextColor(headersForTea.get(answerCounter).getTextColors().withAlpha(0));
      questionsForTea.get(answerCounter + 1)
          .setTextColor(questionsForTea.get(answerCounter + 1).getTextColors().withAlpha(255));
      headersForTea.get(answerCounter + 1)
          .setTextColor(headersForTea.get(answerCounter).getTextColors().withAlpha(255));
      answerCounter++;
    } else if (answerCounter == 3) {
      teaAnswers.set(answerCounter, value);
      Intent i = new Intent(this, TreatmentExperienceAssessmentRemarksActivity.class);
      Bundle bundle = new Bundle();
      bundle.putIntegerArrayList("ANSWERS", teaAnswers);
      i.putExtras(bundle);
      startActivity(i);
    }
  }


  private VolitionDatabase db;

}
