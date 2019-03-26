package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        String sampletext = "Visit 1 Visit 1";
        TextView textView = (TextView) findViewById(R.id.textview_day_1);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_2);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_3);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_4);
        textView.setText(sampletext);
        textView = findViewById(R.id.day_of_week_1);
        textView.setText("M");
        textView = findViewById(R.id.day_of_week_2);
        textView.setText("T");
        textView = findViewById(R.id.day_of_week_3);
        textView.setText("W");
        textView = findViewById(R.id.day_of_week_4);
        textView.setText("T");

    }

}
