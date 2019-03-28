package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Calendar;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */
public class CreateProfileActivity extends AppCompatActivity {


  /**
   * All of these public methods take in the current view @c
   * Then they check if the Corresponding RadioButton has been selected
   * If the RadioButton has been selected the corresponding field in the database is set to true
   */
  public void addSupportListener(View c) {
    radioSupport = (RadioButton) findViewById(R.id.radioSupport);
    if (radioSupport.isChecked()) {
      data.setPersonInRecovery(false);//set to false because not in Recovery
    }
  }

  public void addClientListener(View c) {
    radioClient = (RadioButton) findViewById(R.id.radioClient);
    if (radioClient.isChecked()) {
      data.setPersonInRecovery(true);
    }
  }
  public void addHeroinListener(View c) {
    radioHeroin = (RadioButton) findViewById(R.id.radioHeroin);
    if (radioHeroin.isChecked()) {
      data.setUseHeroin(true);
    }
  }

  public void addMarijuanaListener(View c) {
    radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
    if (radioMarijuana.isChecked()) {
      data.setUseMarijuana(true);
    }
  }
  public void addOpiatesListener(View c) {
    radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
    if (radioOpiates.isChecked()) {
      data.setUseOpiateOrSynth(true);
    }
  }

  public void addAlocholListener(View c) {
    radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
    if (radioAlcohol.isChecked()) {
      data.setUseAlcohol(true);
    }
  }

  public void addCocaineListener(View c) {
    radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
    if (radioCocaine.isChecked()) {
      data.setUseCrackOrCocaine(true);
    }
  }

  public void addMethListener(View c) {
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    if (radioMeth.isChecked()) {
      data.setUseMethamphetamine(true);
    }
  }

  public void addBenListener(View c) {
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    if (radioBen.isChecked()) {
      data.setUseBenzo(true);
    }
  }

  public void addTranqListener(View c) {
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    if (radioTranquilizers.isChecked()) {
      data.setUseNonBeznoTrang(true);
    }
  }
  public void addSedativesListener(View c) {
    radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
    if (radioSedatives.isChecked()) {
      data.setUseOpiateOrSynth(true);
    }
  }

  public void addInhalantsListener(View c) {
    radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
    if (radioInhalants.isChecked()) {
      data.setUseInhalants(true);
    }
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);

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

  }
  private TextView mTextMessage;
  private RadioButton radioSupport;
  private RadioButton radioClient;
  private RadioButton radioHeroin;
  private RadioButton radioOpiates;
  private RadioButton radioAlcohol;
  private RadioButton radioCocaine;
  private RadioButton radioMarijuana;
  private RadioButton radioMeth;
  private RadioButton radioBen;
  private RadioButton radioTranquilizers;
  private RadioButton radioSedatives;
  private RadioButton radioInhalants;
  private DemographicDataEntity data = new DemographicDataEntity();

}
