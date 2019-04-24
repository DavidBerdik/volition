package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Shows a screen that will explain the direction then take the user to the questionnaire.
 */

public class QuestionnaireConfirmActivity extends AppCompatActivity {

  /**
   * Shows the screen when the class is called.
   *
   * @param savedInstanceState stores the saved state in order to recreate the activity.
   */
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_questionnaire_confirmation);
    final Button takeQuestionnaireButton = findViewById(R.id.takeQuestionnaire);
    takeQuestionnaireButton.setOnClickListener(takeQClickListener);
  }

  /**
   * When the button is clicked, it will take the user to the questionnaire.
   */
  private final View.OnClickListener takeQClickListener = new View.OnClickListener() {

    @Override
    public void onClick(final View v) {
      startActivity(new Intent(QuestionnaireConfirmActivity.this, QuestionnaireActivity.class));
    }
  };
}
