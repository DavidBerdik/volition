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
import android.widget.RadioButton;
import android.widget.Spinner;
import java.text.DateFormat;
import java.util.Calendar;
import android.content.Intent;

/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */
public class CreateProfileActivity extends AppCompatActivity {

  final Calendar dobCalendar = Calendar.getInstance();
  final Calendar cleanDateCalendar = Calendar.getInstance();
  boolean editMode;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);

    /*
    If an "editMode" intent was passed to this activity with a value of "true", set "editMode"
    to true. Otherwise, set it to false.
     */
    editMode = getIntent().getBooleanExtra("editMode", false);

     /*
    If the activity is in edit mode, change the title on the activity to "Edit Profile" and set the
    text on the record button to "Update Profile."
     */
    if (editMode) {
      this.setTitle("Edit Profile");
      final Button updateProfile = findViewById(R.id.record_button);
      updateProfile.setText(R.string.edit_profile_update_profile);
    }

    // Retrieve the relevant ViewModel for interacting with the database.
    final DemographicDataViewModel demogDataViewModel = ViewModelProviders.of(this)
        .get(DemographicDataViewModel.class);

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
      public void onClick(final View v) {
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
        Intent intent = new Intent(CreateProfileActivity.this, QuestionnaireActivity.class);
        startActivity(intent);
      }

    });

    /*
    If the activity is in edit mode, then set the observer to wait for the existing demographic
    information to be retrieved from the database.
     */
    if (editMode) {
      demogDataViewModel.getAllDemographicData().observe(this, demographicDataEntityObserver);
    }

  }

  /**
   * Sets the database component of the activity to use a test database and modifies the observer to
   * use the in-memory database instead.
   *
   * @param db Database to use for testing.
   */
  public void setTestMode(final VolitionDatabase db) {
    final DemographicDataViewModel demogDataViewModel = ViewModelProviders.of(this)
        .get(DemographicDataViewModel.class);
    demogDataViewModel.setTestDatabase(db);
    demogDataViewModel.getAllDemographicData().observe(this, demographicDataEntityObserver);
  }

  /**
   * Observer for retrieving all demographic data for the user.
   */
  private Observer<DemographicDataEntity> demographicDataEntityObserver = new Observer<DemographicDataEntity>() {
    @Override
    public void onChanged(@Nullable final DemographicDataEntity demographicDataEntity) {
      // Only try to set the value of each field if "demographicDataEntity" is not null.
      if (demographicDataEntity != null) {
        // Set the display of the patient's name.
        final EditText name = findViewById(R.id.name);
        name.setText(demographicDataEntity.getPatientName());

        // Set the date of birth in the appropriate calendar and EditText field.
        dobCalendar.setTime(demographicDataEntity.getDateOfBirth());
        final EditText dob = findViewById(R.id.date_of_birth);
        dob.setText(DateFormat.getDateInstance().format(demographicDataEntity.getDateOfBirth()));

        // Set the gender in the gender spinner.
        final Spinner gender = findViewById(R.id.gender_spinner);
        for (int x = 0; x < gender.getAdapter().getCount(); x++) {
          if (gender.getAdapter().getItem(x).toString()
              .contains(demographicDataEntity.getGender())) {
            gender.setSelection(x);
          }
        }

        // Set the "type" of the person.
        RadioButton personType;
        if (demographicDataEntity.isPersonInRecovery()) {
          personType = findViewById(R.id.radioClient);
        } else {
          personType = findViewById(R.id.radioSupport);
        }
        personType.toggle();

        // Set the drug of choice.
        final RadioButton drugOfChoice;
        if (demographicDataEntity.isUseHeroin()) {
          drugOfChoice = findViewById(R.id.radioHeroin);
        } else if (demographicDataEntity.isUseOpiateOrSynth()) {
          drugOfChoice = findViewById(R.id.radioOpiates);
        } else if (demographicDataEntity.isUseAlcohol()) {
          drugOfChoice = findViewById(R.id.radioAlcohol);
        } else if (demographicDataEntity.isUseCrackOrCocaine()) {
          drugOfChoice = findViewById(R.id.radioCocaine);
        } else if (demographicDataEntity.isUseMarijuana()) {
          drugOfChoice = findViewById(R.id.radioMarijuana);
        } else if (demographicDataEntity.isUseMethamphetamine()) {
          drugOfChoice = findViewById(R.id.radioMeth);
        } else if (demographicDataEntity.isUseBenzo()) {
          drugOfChoice = findViewById(R.id.radioBen);
        } else if (demographicDataEntity.isUseNonBeznoTrang()) {
          drugOfChoice = findViewById(R.id.radioTranquilizers);
        } else if (demographicDataEntity.isUseBarbituresOrHypno()) {
          drugOfChoice = findViewById(R.id.radioSedatives);
        } else if (demographicDataEntity.isUseInhalants()) {
          drugOfChoice = findViewById(R.id.radioInhalants);
        } else {
          drugOfChoice = findViewById(R.id.radioOther);
        }
        drugOfChoice.toggle();

        // If the "Other" drug of choice option was chosen, set the "Other Drug" EditText field.
        if (demographicDataEntity.getUseOther() != null && !demographicDataEntity.getUseOther()
            .equals("")) {
          final EditText otherDrug = findViewById(R.id.enter_other);
          otherDrug.setText(demographicDataEntity.getUseOther());
        }

        // Set the substance use disorder type.
        final Spinner disorderType = findViewById(R.id.use_type_spinner);
        if (demographicDataEntity.isDisorderOpioid()) {
          disorderType.setSelection(1);
        } else {
          disorderType.setSelection(2);
        }

        // Set the date of last use in the appropriate calendar and EditText field.
        cleanDateCalendar.setTime(demographicDataEntity.getLastClean());
        final EditText cleanDate = findViewById(R.id.clean_date);
        cleanDate
            .setText(DateFormat.getDateInstance().format(demographicDataEntity.getLastClean()));
      }
    }
  };

}