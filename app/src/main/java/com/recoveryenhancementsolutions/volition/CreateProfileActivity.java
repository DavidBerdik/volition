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

/**
 * Class for running activity_create_profile.xml
 * Which includes two pop-up calendars
 */
public class CreateProfileActivity extends AppCompatActivity {

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

  }

}
