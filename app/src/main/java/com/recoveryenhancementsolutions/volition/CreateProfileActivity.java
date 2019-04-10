package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
import android.widget.Button;
import android.content.Intent;

/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */
public class CreateProfileActivity extends AppCompatActivity {
  TextView name, DOB, TypeOfPerson,DrugOfChoice,Disorder,CleanDate;

  //gspin.setSelection();
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);
    checkIfMod();

    final Calendar dobCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener dateOfBirthListener = new OnDateSetListener() {
      /**
       * Event handler for when a date of birth is chosen by the user.
       * @param view DatePicker object
       * @param year Year chosen by user
       * @param month Month chosen by user
       * @param day Day chosen by user
       */
      @Override
      public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        dobCalendar.set(Calendar.YEAR, year);
        dobCalendar.set(Calendar.MONTH, month);
        dobCalendar.set(Calendar.DAY_OF_MONTH, day);
        final EditText dob = findViewById(R.id.date_of_birth);
        dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
      }
    };

    final Calendar cleanDateCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener cleanDateListener = new OnDateSetListener() {
      /**
       * Event handler for when a date of last use is chosen by the user.
       * @param view DatePicker object
       * @param year Year chosen by user
       * @param month Month chosen by user
       * @param day Day chosen by user
       */
      @Override
      public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        cleanDateCalendar.set(Calendar.YEAR, year);
        cleanDateCalendar.set(Calendar.MONTH, month);
        cleanDateCalendar.set(Calendar.DAY_OF_MONTH, day);
        final EditText cleanDate = findViewById(R.id.clean_date);
        cleanDate.setText(DateFormat.getDateInstance().format(cleanDateCalendar.getTime()));
      }
    };

    findViewById(R.id.date_of_birth).setOnClickListener(new OnClickListener() {
      /**
       * Event handler for triggering the display of the Date of Birth date picker when the user
       * taps on the "dateOfBirth" EditText object.
       * @param view EditText object
       */
      @Override
      public void onClick(final View view) {
        final DatePickerDialog pickDate = new DatePickerDialog(CreateProfileActivity.this,
            dateOfBirthListener, dobCalendar.get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH),
            dobCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    findViewById(R.id.clean_date).setOnClickListener(new OnClickListener() {
      /**
       * Event handler for triggering the display of the Last Use Date date picker when the user
       * taps on the "cleanDate" EditText object.
       * @param view EditText object
       */
      @Override
      public void onClick(final View view) {
        final DatePickerDialog pickDate = new DatePickerDialog(CreateProfileActivity.this,
            cleanDateListener, cleanDateCalendar.get(Calendar.YEAR),
            cleanDateCalendar.get(Calendar.MONTH), cleanDateCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    final Button send;
    send = findViewById(R.id.record_button);
    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick( final View v) {
        sendOff();
      }

      /**
       *Upon Clicking, "Record Answers" Birthday, name, gender, and CleanDate will be added to
       * the database. Only these four will be added from my method because Collin is handling
       * the outputs from the buttons and is adding them to the database according to his
       * latest commit on March 27, 2019. However for now it will be simply a button until
       * confirmation
       */
      private void sendOff() {
        //Intent goes to the next activity in the Work Flow.
        Intent intent = new Intent(CreateProfileActivity.this, AdminMenu.class);
        startActivity(intent);
      }

    });


  }
  public void checkIfMod() {
    Spinner Gender = (Spinner) findViewById(R.id.gender_spinner);
    ArrayAdapter gspin = (ArrayAdapter) Gender.getAdapter();
    int spinnerPosition = gspin.getPosition(Gender);
    name = (TextView) findViewById(R.id.name);
    DOB = (TextView) findViewById(R.id.date_of_birth);
   // Gender = (Spinner) findViewById(R.id.gender_spinner);

    Gender.setSelection(spinnerPosition);

    //TypeOfPerson = (TextView) findViewById(R.id.recorded_person);
    //DrugOfChoice = (TextView) findViewById(R.id.recorded_drug);
    //Disorder = (TextView) findViewById(R.id.recorded_type);
    //CleanDate = (TextView) findViewById(R.id.clean_date);

    name.setText(getIntent().getStringExtra("name"));
    DOB.setText(getIntent().getStringExtra("DOB"));
    //Gender.setText(getIntent().getStringExtra("DOB"));
    //CleanDate.setText(getIntent().getStringExtra("CleanDate"));
  }

}