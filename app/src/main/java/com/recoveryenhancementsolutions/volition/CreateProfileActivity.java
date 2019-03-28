package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.Button;
import android.content.Intent;

/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */
public class CreateProfileActivity extends AppCompatActivity {

  /**
   * The two private variables below are global due to being used multiple times Was both Birthday
   * Result and Clean Date Result are used in the calendar method Was name and gender from whoever
   * is making the method that outputs name and gender but calendar fix causes test issues so to
   * avoid conflict I am not including.
   */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);

    final Calendar calendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
      }
    };

    final EditText dateOfBirth = (EditText) findViewById(R.id.date_of_birth);
    dateOfBirth.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        DatePickerDialog pickDate = new DatePickerDialog(CreateProfileActivity.this, date,
            2000, calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    final EditText cleanDate = (EditText) findViewById(R.id.clean_date);
    cleanDate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        DatePickerDialog pickDate = new DatePickerDialog(CreateProfileActivity.this, date,
            2019, calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    final Button send;
    send = (Button) findViewById(R.id.record_button);
    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sendOff();
      }

      /**
       *Upon Clicking, "Record Answers" Birthday, name, gender, and CleanDate will be added to
       * the database. Only these four will be added from my method because Collin is handling
       * the outputs from the buttons and is adding them to the database according to his
       * latest commit on March 27, 2019. However for now it will be simply a button until
       * confirmation
       */
      public void sendOff() {
        //Intent goes to the next activity in the Work Flow.
        Intent intent = new Intent(CreateProfileActivity.this, QuestionnaireActivity.class);
        startActivity(intent);
      }

    });

  }

}
