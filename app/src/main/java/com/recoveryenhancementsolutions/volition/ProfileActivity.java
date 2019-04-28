package com.recoveryenhancementsolutions.volition;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for running activity_profile which includes two pop-up calendars
 */

public class ProfileActivity extends AppCompatActivity implements OnItemSelectedListener {

  /*
   *Makes AdminMenu
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_drawer_menu_options, menu);
    return true;
  }

  /*
   *Adds Functionality to AdminMenu
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    if(item.getItemId() == R.id.edit_profile){
      Intent profile = new Intent(this, ProfileActivity.class);
      startActivity(profile);
    }
    if(item.getItemId() == R.id.edit_treatment){
      Intent treatment = new Intent(this, TreatmentPlanActivity.class);
      startActivity(treatment);
    }
    if(item.getItemId() == R.id.classification){
      Intent classification = new Intent(this, ClassificationScreenActivity.class);
      startActivity(classification);
    }
    if(item.getItemId() == R.id.retake_questionnaire){
      Intent questionarre = new Intent(this, QuestionnaireActivity.class);
      startActivity(questionarre);
    }
   /* if(item.getItemId() == R.id.clinical_overview){
      Intent clinical = new Intent(this, ClinicalOverviewActivity.class);
      startActivity(clinical);
    }*/
    return true;
  }


  /**
   * Checking the selected gender and UseDisorder, if selected, adds to the database
   */
  public void onItemSelected(final AdapterView<?> parent, final View view,
      final int pos, final long id) {

    if (parent.getId() == R.id.gender_spinner) {
      if (pos == 0 && spinnerCount > 1) {
        onNothingSelected(parent);
      }
      final String gender = (String) parent.getItemAtPosition(pos);
      data.setGender(gender);
    }

    if (parent.getId() == R.id.use_type_spinner) {
      final String useType = (String) parent.getItemAtPosition(pos);
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
  public void onNothingSelected(final AdapterView<?> parent) {
    final Toast toast = Toast
        .makeText(getApplicationContext(), "Please select a gender and a Use Type",
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
          setAllDrugListenersFalse();
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
          setAllDrugListenersFalse();
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
          setAllDrugListenersFalse();
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
          setAllDrugListenersFalse();
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
          setAllDrugListenersFalse();
          data.setUseMarijuana(true);
        }
      }
    });
  }

  public void addMethListener() {
    radioMeth = findViewById(R.id.radioMeth);
    radioMeth.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          setAllDrugListenersFalse();
          data.setUseMethamphetamine(true);
        }
      }
    });
  }

  public void addBenListener() {
    radioBen = findViewById(R.id.radioBen);
    radioBen.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          setAllDrugListenersFalse();
          data.setUseBenzo(true);
        }
      }
    });
  }

  public void addTranqListener() {
    radioTranquilizers = findViewById(R.id.radioTranquilizers);
    radioTranquilizers.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          setAllDrugListenersFalse();
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
          setAllDrugListenersFalse();
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
          setAllDrugListenersFalse();
          data.setUseInhalants(true);
        }
      }
    });
  }

  public void addOtherListener() {
    radioOther = findViewById(R.id.radioOther);
    radioOther.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          setAllDrugListenersFalse();
          findViewById(R.id.enter_other).setVisibility(View.VISIBLE);
          findViewById(R.id.enter_other).requestFocus();
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
    radioOther = findViewById(R.id.radioOther);
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
    addOtherListener();

    final Spinner genderSpinner = findViewById(R.id.gender_spinner);
    final Spinner useTypeSpinner = findViewById(R.id.use_type_spinner);

    genderSpinner.setOnItemSelectedListener(this);
    useTypeSpinner.setOnItemSelectedListener(this);

    final EditText name = findViewById(R.id.name);
    OnFocusChangeListener ofcListener = new FocusListener();
    name.setOnFocusChangeListener(ofcListener);
  }

  /**
   * Depending on the source of the activity's launch request, determine where to send the user when
   * the back button is pressed.
   */
  @Override
  public void onBackPressed() {
    // Set "dest" equal to the ID passed via the intent. If nothing was passed, set it to 1.
    final int dest = getIntent().getIntExtra(BACK_DEST, 1);
    final Intent destination = new Intent();

    if (!editMode) {
      // Create an alert for people to confirm with the user their intent to back out.
      final Builder alert = new Builder(this)
          .setTitle(R.string.create_profile_back_out_title)
          .setMessage(R.string.create_profile_back_out_content)
          .setIcon(android.R.drawable.ic_dialog_alert);
      alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(final DialogInterface dialog, final int whichButton) {
          finish();
        }
      });
      alert.setNegativeButton(android.R.string.no, null);
      alert.show();
      return;
    } else if (dest == 1) {
      destination.setClass(this, HomeActivity.class);
    } else if (dest == 2) {
      destination.setClass(this, ActivityActivity.class);
    } else {
      destination.setClass(this, PlanActivity.class);
    }
    /*
    Set the core navigation's variable for tracking ProfileActivity's load source to 0 to indicate
    that it should be updated the next time that ProfileActivity is chosen from the navigation.
     */
    CoreNavigationHandler.profileActivityLoadSrc = 0;
    this.startActivity(destination);
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

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    setContentView(R.layout.activity_profile);
    findViewById(R.id.enter_other).setVisibility(View.GONE);
    bottomNavigationView = findViewById(R.id.core_navigation);

    /*
    If an edit mode intent was passed to this activity with a value of "true", set edit mode
    to true. Otherwise, set it to false.
     */
    final String EDIT_MODE = "editMode";
    editMode = getIntent().getBooleanExtra(EDIT_MODE, false);

     /*
    If the activity is in edit mode, change the title on the activity to "Edit Profile" and set the
    text on the record button to "Update Profile."
     */
    if (editMode) {
      this.setTitle("Edit Profile");
      final Button updateProfile = findViewById(R.id.record_button);
      updateProfile.setText(R.string.profile_update_profile);
    }

    // Retrieve the relevant ViewModel for interacting with the database.
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
        view.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
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
        view.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
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
        final DatePickerDialog pickDate = new DatePickerDialog(ProfileActivity.this,
            dateOfBirthListener, dobCalendar.get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH),
            dobCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.getDatePicker().setMaxDate(new Date().getTime());
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
        final DatePickerDialog pickDate = new DatePickerDialog(ProfileActivity.this,
            cleanDateListener, cleanDateCalendar.get(Calendar.YEAR),
            cleanDateCalendar.get(Calendar.MONTH), cleanDateCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.getDatePicker().setMaxDate(new Date().getTime());
        pickDate.show();
      }

    });

    final Button send;
    send = findViewById(R.id.record_button);
    send.setOnClickListener(new View.OnClickListener() {
      /**
       * When the "Record Answers" button is clicked, update the database and send the user to
       * the questionnaire.
       */
      @Override
      public void onClick(final View v) {
        /*
        Set the values for the patient's name, date of birth, other drug, and last use date fields
        and the insert the demographic data in to the database.
         */
        EditText name = findViewById(R.id.name);
        data.setPatientName(name.getText().toString());

        data.setDateOfBirth(dobCalendar.getTime());

        final EditText other = findViewById(R.id.enter_other);
        data.setUseOther(other.getText().toString());

        data.setLastClean(cleanDateCalendar.getTime());

        demogDataViewModel.insertDemographicData(data);

        findViewById(R.id.name).clearFocus();
        findViewById(R.id.date_of_birth).clearFocus();
        findViewById(R.id.clean_date).clearFocus();
        findViewById(R.id.gender_spinner).clearFocus();
        findViewById(R.id.use_type_spinner).clearFocus();
        findViewById(R.id.user_type).clearFocus();
        findViewById(R.id.drug_selection).clearFocus();
        findViewById(R.id.enter_other).clearFocus();

        //Intent goes to the next activity in the Work Flow.
        /*
        If the activity is in edit mode, send the user back to the previous activity and if the
        activity is not in edit mode, send the user to the questionnaire.
         */
        if (editMode) {
          onBackPressed();
        } else {
          final Spinner genderSpinner = findViewById(R.id.gender_spinner);
          final Spinner useDisorderSpinner = findViewById(R.id.use_type_spinner);
          final RadioGroup user_type_radio_group = findViewById(R.id.user_type);
          final RadioGroup drug_selection_radio_group = findViewById(R.id.drug_selection);
          final RadioButton radio_other = findViewById(R.id.radioOther);
          if (TextUtils.isEmpty(((EditText) findViewById(R.id.name)).getText())) {
            ((EditText) findViewById(R.id.name)).setError("Name is required! ");
            findViewById(R.id.name).setFocusable(true);
            findViewById(R.id.name).requestFocus();
            Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();
          } else if (TextUtils.isEmpty(((EditText) findViewById(R.id.date_of_birth)).getText())) {
            ((EditText) findViewById(R.id.name)).setError(null);
            findViewById(R.id.date_of_birth).setFocusableInTouchMode(true);
            findViewById(R.id.date_of_birth).requestFocus();
            ((EditText) findViewById(R.id.date_of_birth)).setError("Date of birth is required! ");
            Toast.makeText(getApplicationContext(), "Date of birth is required", Toast.LENGTH_SHORT)
                .show();
          } else if (TextUtils.isEmpty(((EditText) findViewById(R.id.clean_date)).getText())) {
            ((EditText) findViewById(R.id.date_of_birth)).setError(null);
            findViewById(R.id.clean_date).setFocusable(true);
            findViewById(R.id.clean_date).requestFocus();
            ((EditText) findViewById(R.id.clean_date)).setError("Clean date is required! ");
            Toast.makeText(getApplicationContext(), "Clean date is required", Toast.LENGTH_SHORT)
                .show();
          } else if (genderSpinner.getSelectedItem().toString().equals("Select Gender")) {
            ((EditText) findViewById(R.id.clean_date)).setError(null);
            findViewById(R.id.gender_spinner).setFocusableInTouchMode(true);
            findViewById(R.id.gender_spinner).requestFocus();
            TextView errorText = (TextView) genderSpinner.getSelectedView();
            errorText.setError("Gender required!");
            errorText.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(), "Gender required!", Toast.LENGTH_SHORT)
                .show();
          } else if (useDisorderSpinner.getSelectedItem().toString().equals("Select Type")) {
            TextView errorTextPrev = (TextView) genderSpinner.getSelectedView();
            errorTextPrev.setError(null);
            findViewById(R.id.use_type_spinner).setFocusableInTouchMode(true);
            findViewById(R.id.use_type_spinner).requestFocus();
            TextView errorText = (TextView) useDisorderSpinner.getSelectedView();
            errorText.setError("Use type required!");
            errorText.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(), "Use type required!", Toast.LENGTH_SHORT)
                .show();
          } else if (user_type_radio_group.getCheckedRadioButtonId() == -1) {
            TextView errorTextPrev = (TextView) useDisorderSpinner.getSelectedView();
            errorTextPrev.setError(null);
            findViewById(R.id.user_type).setFocusableInTouchMode(true);
            findViewById(R.id.user_type).requestFocus();
            TextView userType = findViewById(R.id.are_you);
            userType.setError("User type required!");
            Toast.makeText(getApplicationContext(), "User type required!", Toast.LENGTH_SHORT)
                .show();
          } else if (drug_selection_radio_group.getCheckedRadioButtonId() == -1) {
            TextView errorTextPrev = findViewById(R.id.are_you);
            errorTextPrev.setError(null);
            findViewById(R.id.drug_selection).setFocusableInTouchMode(true);
            findViewById(R.id.drug_selection).requestFocus();
            TextView userType = findViewById(R.id.drug_of_choice);
            userType.setError("Drug of choice required!");
            Toast.makeText(getApplicationContext(), "Drug of choice required!", Toast.LENGTH_SHORT)
                .show();
          } else if (radio_other.isChecked() && TextUtils
              .isEmpty(((EditText) findViewById(R.id.enter_other)).getText())) {
            ((EditText) findViewById(R.id.enter_other)).setError("Other drug is required! ");
            findViewById(R.id.enter_other).setFocusableInTouchMode(true);
            findViewById(R.id.enter_other).requestFocus();
            Toast.makeText(getApplicationContext(), "Other drug is required", Toast.LENGTH_SHORT)
                .show();
          } else {
            TextView errorTextPrev = findViewById(R.id.drug_of_choice);
            errorTextPrev.setError(null);
            @SuppressLint("CutPasteId") TextView errorTextPrev2 = findViewById(R.id.enter_other);
            errorTextPrev2.setError(null);
            startActivity(new Intent(ProfileActivity.this, QuestionnaireConfirmActivity.class));
          }
        }
      }
    });

    /*
    If the activity is in edit mode, then set the observer to wait for the existing demographic
    information to be retrieved from the database.
     */
    if (editMode) {
      demogDataViewModel.getAllDemographicData().observe(this, demographicDataEntityObserver);
      findViewById(R.id.enter_other).setVisibility(View.VISIBLE);
    }

  }

  /**
   * If the activity is not in edit mode, remove the core navigation menu from being displayed, and
   * if the core navigation menu is in edit mode, set it to the appropriate state for this
   * activity.
   */
  @Override
  protected void onResume() {
    super.onResume();
    if (editMode) {
      // Set the correct core navigation button on the menu and make it functional.
      bottomNavigationView.setSelectedItemId(R.id.core_navigation_profile);
      CoreNavigationHandler.link(bottomNavigationView, this, 4);
    } else {
      // Make the core navigation menu invisible and adjust the master layout's margins.
      bottomNavigationView.setVisibility(View.INVISIBLE);
      ScrollView scrollView = findViewById(R.id.scrollview_layout);
      ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) scrollView
          .getLayoutParams();
      params.bottomMargin = 0;
      scrollView.setLayoutParams(params);
    }
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

  /**
   * Sets all listeners to false so that drug choice stays consistent
   */
  private void setAllDrugListenersFalse(){
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
    radioOther = findViewById(R.id.radioOther);

    data.setUseHeroin(false);
    data.setUseInhalants(false);
    data.setUseBarbituresOrHypno(false);
    data.setUseNonBeznoTrang(false);
    data.setUseBenzo(false);
    data.setUseMethamphetamine(false);
    data.setUseMarijuana(false);
    data.setUseAlcohol(false);
    data.setUseCrackOrCocaine(false);
    data.setUseOpiateOrSynth(false);
  }

  private final DemographicDataEntity data = new DemographicDataEntity();
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
  private RadioButton radioOther;
  private int spinnerCount = 0;
  private boolean editMode;
  private final Calendar dobCalendar = Calendar.getInstance();
  private final Calendar cleanDateCalendar = Calendar.getInstance();
  private BottomNavigationView bottomNavigationView;
  private static final String BACK_DEST = "backDest";
}