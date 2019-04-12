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
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;

/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */
public class CreateProfileActivity extends AppCompatActivity {

  TextView name;
  final Spinner spinner = findViewById(R.id.gender_spinner);

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
        Toast.makeText(getApplicationContext(),
            "vlaue is " + (DateFormat.getDateInstance().format(dobCalendar.getTime())),
            Toast.LENGTH_LONG).show();
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
    int flag = getIntent().getIntExtra("flag", 0);
    if (flag != 0) {

      final Calendar dobCalendar = Calendar.getInstance();
      Spinner Gender = findViewById(R.id.gender_spinner);
      ArrayAdapter gSpin = (ArrayAdapter) Gender.getAdapter();
      /*
       * Something will always be checked here because
       * the catch for not allowing the user to continue without filling in everything
       * would be included in the final product.
       */
      @SuppressWarnings("unchecked")
      int spinnerPosition = gSpin.getPosition(Gender);
      name = findViewById(R.id.name);
      Gender.setSelection(spinnerPosition);

      int bYear = getIntent().getIntExtra("bYear", 0);
      int bMonth = getIntent().getIntExtra("bMonth", 0);
      int bDay = getIntent().getIntExtra("bDay", 0);

      if (bYear != 0) {
        dobCalendar.set(Calendar.YEAR, bYear);
        dobCalendar.set(Calendar.MONTH, bMonth);
        dobCalendar.set(Calendar.DAY_OF_MONTH, bDay);
        final EditText dob = findViewById(R.id.date_of_birth);
        dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
      }

      final Calendar cleanDateCalendar = Calendar.getInstance();
      int cYear = getIntent().getIntExtra("cYear", 0);
      int cMonth = getIntent().getIntExtra("cMonth", 0);
      int cDay = getIntent().getIntExtra("cDay", 0);

      if (bYear != 0) {
        cleanDateCalendar.set(Calendar.YEAR, cYear);
        cleanDateCalendar.set(Calendar.MONTH, cMonth);
        cleanDateCalendar.set(Calendar.DAY_OF_MONTH, cDay);
        final EditText cleanDate = findViewById(R.id.clean_date);
        cleanDate.setText(DateFormat.getDateInstance().format(cleanDateCalendar.getTime()));
      }

      String gender = getIntent().getStringExtra("gender");
      if (gender.equals("Male")) {
        spinner.setSelection(2);
      }
      if (gender.equals("Female")) {
        spinner.setSelection(1);
      }
      if (gender.equals("Other")) {
        spinner.setSelection(3);
      }

      boolean support = getIntent().getBooleanExtra("family", false);
      if (support) {
        RadioButton rdb = findViewById(R.id.radioSupport);
        rdb.setChecked(true);
      }
      boolean client = getIntent().getBooleanExtra("recovery", false);
      if (client) {
        RadioButton rdb = findViewById(R.id.radioClient);
        rdb.setChecked(true);
      }
      boolean heroin = getIntent().getBooleanExtra("heroin", false);
      if (heroin) {
        RadioButton rdb = findViewById(R.id.radioHeroin);
        rdb.setChecked(true);
      }
      boolean opiate = getIntent().getBooleanExtra("opiate", false);
      if (opiate) {
        RadioButton rdb = findViewById(R.id.radioOpiates);
        rdb.setChecked(true);
      }
      boolean crackCocaine = getIntent().getBooleanExtra("CrackCocaine", false);
      if (crackCocaine) {
        RadioButton rdb = findViewById(R.id.radioCocaine);
        rdb.setChecked(true);
      }
      boolean marajuana = getIntent().getBooleanExtra("marajuana", false);
      if (marajuana) {
        RadioButton rdb = findViewById(R.id.radioMarijuana);
        rdb.setChecked(true);
      }
      boolean meth = getIntent().getBooleanExtra("meth", false);
      if (meth) {
        RadioButton rdb = findViewById(R.id.radioMeth);
        rdb.setChecked(true);
      }
      boolean benzo = getIntent().getBooleanExtra("benzo", false);
      if (benzo) {
        RadioButton rdb = findViewById(R.id.radioBen);
        rdb.setChecked(true);
      }
      boolean nonBenzo = getIntent().getBooleanExtra("nonBenzo", false);
      if (nonBenzo) {
        RadioButton rdb = findViewById(R.id.radioTranquilizers);
        rdb.setChecked(true);
      }
      boolean barb = getIntent().getBooleanExtra("barb", false);
      if (barb) {
        RadioButton rdb = findViewById(R.id.radioSedatives);
        rdb.setChecked(true);
      }
      boolean inhalant = getIntent().getBooleanExtra("inhalant", false);
      if (inhalant) {
        RadioButton rdb = findViewById(R.id.radioInhalants);
        rdb.setChecked(true);
      }
      boolean alcohol = getIntent().getBooleanExtra("alcohol", false);
      if (alcohol) {
        RadioButton rdb = findViewById(R.id.radioAlcohol);
        rdb.setChecked(true);
      }

      boolean other = getIntent().getBooleanExtra("useother", false);
      if (other) {
        RadioButton rdb = findViewById(R.id.radioOther);
        String condition = getIntent().getStringExtra("condition");
        final EditText OtherCondition = findViewById(R.id.enter_other);
        OtherCondition.setText(condition);
        rdb.setChecked(true);
      }
      final Spinner DisorderSpinner = findViewById(R.id.use_type_spinner);
      boolean alcoholDisorder = getIntent().getBooleanExtra("alcoholDisorder", false);
      if (alcoholDisorder) {
        DisorderSpinner.setSelection(2);
      }
      boolean opioidDisorder = getIntent().getBooleanExtra("opiodDisorder", false);
      if (opioidDisorder) {
        DisorderSpinner.setSelection(1);
      }
      name.setText(getIntent().getStringExtra("name"));
    }
  }
}