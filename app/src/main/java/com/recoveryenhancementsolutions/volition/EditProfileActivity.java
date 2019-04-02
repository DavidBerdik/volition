package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Backend code for the "Edit Profile" activity.
 */
public class EditProfileActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Until Abdulloh and Fletcher finish the real activity XML for editing a profile, I am going
    // to use a temporary activity layout that is based on Stephanie's "Create Profile" activity.
    setContentView(R.layout.activity_edit_profile_temp);
    this.setTitle("Edit Profile - TEMPORARY");

    DemographicDataViewModel demogDataViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);

    final Calendar dobCalendar = Calendar.getInstance();

    final OnDateSetListener dateOfBirthListener = new OnDateSetListener() {
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

    final OnDateSetListener cleanDateListener = new OnDateSetListener() {
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
        final DatePickerDialog pickDate = new DatePickerDialog(EditProfileActivity.this,
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
        final DatePickerDialog pickDate = new DatePickerDialog(EditProfileActivity.this,
            cleanDateListener, cleanDateCalendar.get(Calendar.YEAR),
            cleanDateCalendar.get(Calendar.MONTH), cleanDateCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    final Button send;
    send = findViewById(R.id.record_button);
    send.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View v) {
        // This does not do anything for now.
      }

    });

    demogDataViewModel.getAllDemographicData().observe(this, new Observer<DemographicDataEntity>() {
      /**
       * Observes for retrieval of all demographic data from the database and displays the data in
       * the appropriate fields after it has been retrieved.
       * @param demographicDataEntity The DemographicDataEntity object
       */
      @Override
      public void onChanged(@Nullable DemographicDataEntity demographicDataEntity) {
        // Set the display of the patient's name.
        EditText name = findViewById(R.id.name);
        name.setText(demographicDataEntity.getPatientName());

        // Set the date of birth in the appropriate calendar and EditText field.
        dobCalendar.setTime(demographicDataEntity.getDateOfBirth());
        EditText dob = findViewById(R.id.date_of_birth);
        dob.setText(DateFormat.getDateInstance().format(demographicDataEntity.getDateOfBirth()));

        // Set the date of last use in the appropriate calendar and EditText field.
        cleanDateCalendar.setTime(demographicDataEntity.getLastClean());
        EditText cleanDate = findViewById(R.id.clean_date);
        cleanDate
            .setText(DateFormat.getDateInstance().format(demographicDataEntity.getLastClean()));
      }
    });

  }

}