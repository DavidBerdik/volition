package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class for running activity_create_profile.xml
 * Which includes two pop-up calendars
 */
public class CreateProfile extends AppCompatActivity {

  /*
   *The four private variables below are global due to being used multiple times
   * Both Birthday Result and Clean Date Result are used in the calendar method
   * while the name and gender are used from whoever is making the method that outputs name
   * and gender.
   */
  private String BirthdayResult;
  private String CleanDateResult;
  private String name = "";
  private String Gender = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);
    final DemographicDataEntity patient = new DemographicDataEntity();
    final DemographicDataDAO demographicDataDAO;

    VolitionDatabase db = VolitionDatabase.getDatabase(this.getApplication());
    final Context context = getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
            .allowMainThreadQueries().build();
    demographicDataDAO = db.demographicDataDAO();
    final Button send;
    send = (Button) findViewById(R.id.record_button);

    final Calendar dobCalendar = Calendar.getInstance();
    final Calendar cleanDateCalendar = Calendar.getInstance();

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
        dob.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(dobCalendar.getTime()));
        dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
       BirthdayResult = dob.toString();
      }
    };

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
        cleanDate.setText(
                new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(cleanDateCalendar.getTime()));
        cleanDate.setText(DateFormat.getDateInstance().format(cleanDateCalendar.getTime()));
        CleanDateResult = cleanDate.toString();
      }
    };

    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        /*
         *Upon Clicking, "Record Answers" Birthday, name, gender, and CleanDate will be added to
         * the database. Only these four will be added from my method because Collin is handling
         * the outputs from the buttons and is adding them to the database according to his
         * latest commit on March 27, 2019.
         */
        sendOff();
        }

      public void sendOff(){
        //Intent goes to the next activity in the Work Flow after adding to the database.
        Intent intent = new Intent(CreateProfile.this, QuestionnaireActivity.class);
        patient.setDateOfBirth(BirthdayResult);
        patient.setPatientName(name);
        patient.setGender(Gender);
        patient.setLastClean(CleanDateResult);
        demographicDataDAO.insertDemographicInfo(patient);
        startActivity(intent);
       }

      });

    }

}






