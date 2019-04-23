package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.ArrayList;

public class TreatmentExperienceAssessmentActivity extends AppCompatActivity {

  public static int answerCounter = 0;

  public static ArrayList<Integer> teaAnswers = new ArrayList<>();
  public static ArrayList<TextView> questionsForTea = new ArrayList<>();
  public static ArrayList<TextView> headersForTea = new ArrayList<>();
  public static int numberCompleted;
  public TextView qOne;
  public TextView qTwo;
  public TextView qThree;
  public TextView qFour;
  public TextView substance;
  public TextView health;
  public TextView lifestyle;
  public TextView community;


  public String getTeaResultsText() {
    return tea_results.getText().toString();
  }

  /**
   * The method onCreate will initialize the Activity with the view of the treatment_experience_assessment_activity
   * xml. The Text View for every question is created with the opacity for each question and is
   * initially set to 0. Question one's opacity says at the default value of 100 for the initial
   * view to begin the tea. There will be a scroll with 10 number options for the tea.
   *
   * @param savedInstanceState stores the saved state in order to recreate the activity.
   */

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_treatment_experience_assessment);

    final Button submit = findViewById(R.id.submit_button_tea);

    submit.setOnClickListener(submitClickListener);

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

    final BottomNavigationView bottomNavigationView = findViewById(R.id.activity_back_navigation);

  }

  /**
   * When the submit button is clicked, it goes to the set answer for tea and passes the rating
   *
   */

  private final View.OnClickListener submitClickListener = new View.OnClickListener() {

    @Override
    public void onClick(final View v) {
      setAnswerForTea(rating);
    }
  };

  /**
   * This method displays what the user chooses on the scroll bar with rating attached
   * @param rating passes what the user chooses on the scroll bar
   * @return
   */

  private String getTeaString(final int rating) {
    lastKnownValue = rating;
    if((lastKnownValue >= 1) && (lastKnownValue <= 3)) {
      return "Your choice: " + lastKnownValue + " (" + outputs[0] + ")";
    }
    else if((lastKnownValue >= 4) && (lastKnownValue <= 6)) {
      return "Your choice: " + lastKnownValue + " (" + outputs[1] + ")";
    }
    else if((lastKnownValue >= 7) && (lastKnownValue <= 10)) {
      return "Your choice: " + lastKnownValue + " (" + outputs[2] + ")";
    }
    else
      return "";

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
   * Displays the new question. Passes a bundle of the tea answers arraylist to the remarks screen.
   * @param rating
   */
  private void setAnswerForTea(int rating) {
    if (answerCounter < 3) {
      teaAnswers.set(answerCounter, rating);
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
      teaAnswers.set(answerCounter, rating);
      Intent i = new Intent(this, TreatmentExperienceAssessmentRemarksActivity.class);
      Bundle bundle = new Bundle();
      bundle.putIntegerArrayList("ANSWERS", teaAnswers);
      i.putExtras(bundle);
      startActivity(i);
    }
  }

  private VolitionDatabase db;
  private TextView tea_results;
  private String[] outputs = {"None or not much", "Better", "Much Better"};
  private int rating;
  private static int lastKnownValue = -1;



}
