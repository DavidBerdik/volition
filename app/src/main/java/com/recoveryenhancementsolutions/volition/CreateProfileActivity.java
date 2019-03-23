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
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * Class for running activity_create_profile.xml
 * Which includes two pop-up calendars
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
