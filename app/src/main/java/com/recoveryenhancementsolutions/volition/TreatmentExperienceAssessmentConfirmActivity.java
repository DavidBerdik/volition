package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class TreatmentExperienceAssessmentConfirmActivity extends AppCompatActivity{
    /**
     * Shows the screen when the class is called.
     * @param savedInstanceState stores the saved state in order to recreate the activity.
     */
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_confirmation);
        final Button takeTeaButton = findViewById(R.id.button5);
        takeTeaButton.setOnClickListener(takeTeaClickListener);

    }

    /**
     * When the button is clicked, it will take the user to the Treatment Experience Assessment.
     */
    private final View.OnClickListener takeTeaClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            startActivity(new Intent(TreatmentExperienceAssessmentConfirmActivity.this, TreatmentExperienceAssessmentActivity.class));
        }



    };
}