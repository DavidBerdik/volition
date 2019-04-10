package com.recoveryenhancementsolutions.volition;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;

public class PlanActivity extends AppCompatActivity {

    int dayNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_plan_land);
        }
        else {
            setContentView(R.layout.activity_plan_port);
        }
        fillViews();
    }

    public void fillViews()
    {
        String sampletext = "Visit 1 Visit 1";

        final HorizontalScrollView hscroller = findViewById(R.id.hscroller);
        hscroller.post(new Runnable() {
            public void run() {
                hscroller.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });
        TextView textView = (TextView) findViewById(R.id.textview_day_1);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_2);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_3);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_4);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_5);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_6);
        textView.setText(sampletext);
        textView = findViewById(R.id.textview_day_7);
        textView.setText(sampletext);
        textView = findViewById(R.id.day_of_week_1);
        textView.setText("S");
        textView = findViewById(R.id.day_of_week_2);
        textView.setText("S");
        textView = findViewById(R.id.day_of_week_3);
        textView.setText("F");
        textView = findViewById(R.id.day_of_week_4);
        textView.setText("T");
        textView = findViewById(R.id.day_of_week_5);
        textView.setText("W");
        textView = findViewById(R.id.day_of_week_6);
        textView.setText("T");
        textView = findViewById(R.id.day_of_week_7);
        textView.setText("M");

        //fills spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plan_spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //clicking the boxes below each day's initial updates the center day textview and change selected box's color
    public void dayClick1(View textview_day_1) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Sunday");
        dayNo=1;
        textView = findViewById(R.id.textview_day_1);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }
    public void dayClick2(View textview_day_2) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Saturday");
        dayNo=2;
        textView = findViewById(R.id.textview_day_2);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }
    public void dayClick3(View textview_day_3) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Friday");
        dayNo=3;
        textView = findViewById(R.id.textview_day_3);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }
    public void dayClick4(View textview_day_4) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Thursday");
        dayNo=4;
        textView = findViewById(R.id.textview_day_4);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }
    public void dayClick5(View textview_day_5) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Wednesday");
        dayNo=5;
        textView = findViewById(R.id.textview_day_5);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }
    public void dayClick6(View textview_day_6) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Tuesday");
        dayNo=6;
        textView = findViewById(R.id.textview_day_6);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }
    public void dayClick7(View textview_day_7) {
        TextView textView = findViewById(R.id.center_day_textview);
        textView.setText("Monday");
        dayNo=7;
        textView = findViewById(R.id.textview_day_7);
        resetColors();
        textView.setBackgroundResource(R.color.res_green);
    }

    //track button
    public void trackClick(View track_button) {
        //change different different textview dependng on what center-day
        EditText notes_edittext = (EditText) findViewById(R.id.notes_edittext);
        if (dayNo == 1){
            TextView textView = findViewById(R.id.textview_day_1);
            textView.setText(notes_edittext.getText().toString());
        }
        else if (dayNo == 2){
            TextView textView = findViewById(R.id.textview_day_2);
            textView.setText(notes_edittext.getText().toString());
        }
        else if (dayNo == 3){
            TextView textView = findViewById(R.id.textview_day_3);
            textView.setText(notes_edittext.getText().toString());
        }
        else if (dayNo == 4){
            TextView textView = findViewById(R.id.textview_day_4);
            textView.setText(notes_edittext.getText().toString());
        }
        else if (dayNo == 5){
            TextView textView = findViewById(R.id.textview_day_5);
            textView.setText(notes_edittext.getText().toString());
        }
        else if (dayNo == 6){
            TextView textView = findViewById(R.id.textview_day_6);
            textView.setText(notes_edittext.getText().toString());
        }
        else if (dayNo == 7){
            TextView textView = findViewById(R.id.textview_day_7);
            textView.setText(notes_edittext.getText().toString());
        }

    }

    //reset colors to original light green
    public void resetColors(){
        TextView textView = findViewById(R.id.textview_day_1);
        textView.setBackgroundResource(R.color.res_green_transparent);
        textView = findViewById(R.id.textview_day_2);
        textView.setBackgroundResource(R.color.res_green_transparent);
        textView = findViewById(R.id.textview_day_3);
        textView.setBackgroundResource(R.color.res_green_transparent);
        textView = findViewById(R.id.textview_day_4);
        textView.setBackgroundResource(R.color.res_green_transparent);
        textView = findViewById(R.id.textview_day_5);
        textView.setBackgroundResource(R.color.res_green_transparent);
        textView = findViewById(R.id.textview_day_6);
        textView.setBackgroundResource(R.color.res_green_transparent);
        textView = findViewById(R.id.textview_day_7);
        textView.setBackgroundResource(R.color.res_green_transparent);
    }

}
