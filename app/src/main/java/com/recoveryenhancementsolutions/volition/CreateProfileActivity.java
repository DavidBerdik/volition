package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.view.View.OnFocusChangeListener;


/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */

public class CreateProfileActivity extends AppCompatActivity implements OnItemSelectedListener {


  /**
   * Checking the selected gender and UseDisorder, if selected, adds to the database
   */
  public void onItemSelected(AdapterView<?> parent, View view,
      int pos, long id) {

    if (parent.getId() == R.id.gender_spinner) {
      if (pos == 0 && spinnerCount > 1) {
        onNothingSelected(parent);
      }
      String gender = (String) parent.getItemAtPosition(pos);
      data.setGender(gender);
    }

    if (parent.getId() == R.id.use_type_spinner) {
      String useType = (String) parent.getItemAtPosition(pos);
      if (useType.contains("Alcohol")) {
        data.setDisorderAlcohol(true);
      }
      if (useType.contains("Opioid")) {
        data.setDisorderOpioid(true);

      }
    }

    spinnerCount++;
  }

  /**
   * Lets user know to select a gender
   */
  public void onNothingSelected(AdapterView<?> parent) {
    Toast toast = Toast.makeText(getApplicationContext(), "Please select a gender and a Use Type",
        Toast.LENGTH_SHORT);
    toast.show();
  }

  /*
   * All of these public methods take in the current view @c
   * Then they check if the Corresponding RadioButton has been selected
   * If the RadioButton has been selected the corresponding field in the database is set to true
   */
  public void addSupportListener() {
    radioSupport = findViewById(R.id.radioSupport);
    radioSupport.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setPersonInRecovery(false);
        }
      }
    });
  }

  public void addClientListener() {
    radioClient = findViewById(R.id.radioClient);
    radioClient.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setPersonInRecovery(true);
        }
      }
    });
  }

  public void addHeroinListener() {
    radioHeroin = findViewById(R.id.radioHeroin);
    radioHeroin.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseHeroin(true);
        }
      }
    });
  }

  public void addOpiatesListener() {
    radioOpiates = findViewById(R.id.radioOpiates);
    radioOpiates.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseOpiateOrSynth(true);
        }
      }
    });
  }

  public void addAlocholListener() {
    radioAlcohol = findViewById(R.id.radioAlcohol);
    radioAlcohol.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseAlcohol(true);
        }
      }
    });
  }

  public void addCocaineListener() {
    radioCocaine = findViewById(R.id.radioCocaine);
    radioCocaine.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseCrackOrCocaine(true);
        }
      }
    });
  }

  public void addMarijuanaListener() {
    radioMarijuana = findViewById(R.id.radioMarijuana);
    radioMarijuana.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseMarijuana(true);
        }
      }
    });
  }

  public void addMethListener() {
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    radioMeth.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseMethamphetamine(true);
        }
      }
    });
  }

  public void addBenListener() {
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    radioBen.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseBenzo(true);
        }
      }
    });
  }

  public void addTranqListener() {
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    radioTranquilizers.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseNonBeznoTrang(true);
        }
      }
    });
  }

  public void addSedativesListener() {
    radioSedatives = findViewById(R.id.radioSedatives);
    radioSedatives.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseBarbituresOrHypno(true);
        }
      }
    });
  }

  public void addInhanlentsListener() {
    radioInhalants = findViewById(R.id.radioInhalants);
    radioInhalants.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseInhalants(true);
        }
      }
    });
  }

  /*
   *Adds the listeners to the corresponding RadioButtons and Spinners
   * Sets the buttons and Spinners to the corresponding ID's
   * Also sets the FocusChange for the Name entry
   */
  public void addAllListeners() {
    radioSupport = findViewById(R.id.radioSupport);
    radioClient = findViewById(R.id.radioClient);
    radioHeroin = findViewById(R.id.radioHeroin);
    radioInhalants = findViewById(R.id.radioInhalants);
    radioSedatives = findViewById(R.id.radioSedatives);
    radioTranquilizers = findViewById(R.id.radioTranquilizers);
    radioBen = findViewById(R.id.radioBen);
    radioMeth = findViewById(R.id.radioMeth);
    radioMarijuana = findViewById(R.id.radioMarijuana);
    radioAlcohol = findViewById(R.id.radioAlcohol);
    radioCocaine = findViewById(R.id.radioCocaine);
    radioOpiates = findViewById(R.id.radioOpiates);
    addSupportListener();
    addClientListener();
    addAlocholListener();
    addBenListener();
    addCocaineListener();
    addHeroinListener();
    addInhanlentsListener();
    addMarijuanaListener();
    addMethListener();
    addOpiatesListener();
    addSedativesListener();
    addTranqListener();

    genderSpinner = findViewById(R.id.gender_spinner);
    useTypeSpinner = findViewById(R.id.use_type_spinner);

    genderSpinner.setOnItemSelectedListener(this);
    useTypeSpinner.setOnItemSelectedListener(this);

    name = findViewById(R.id.name);
    OnFocusChangeListener ofcListener = new FocusListener();
    name.setOnFocusChangeListener(ofcListener);
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    setContentView(R.layout.activity_create_profile);

    final DemographicDataViewModel demogDataViewModel = ViewModelProviders.of(this)
        .get(DemographicDataViewModel.class);
    addAllListeners();

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
      public void onClick(final View v) {
        data.setPatientName(name.getText().toString());

        demogDataViewModel.insertDemographicData(data);
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
  }

  private DemographicDataEntity data = new DemographicDataEntity();
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
  private Spinner genderSpinner;
  private Spinner useTypeSpinner;
  private int spinnerCount = 0;
  private EditText name;
}