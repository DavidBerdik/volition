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
  TextView name, DOB, TypeOfPerson,DrugOfChoice,Disorder,CleanDate;

  //gspin.setSelection();
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);
   // DOB = (TextView) findViewById(R.id.date_of_birth);
    //checkIfMod();

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
        Toast.makeText(getApplicationContext(), "vlaue is "+(DateFormat.getDateInstance().format(dobCalendar.getTime())), Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(CreateProfileActivity.this, AdminMenuActivity.class);
        startActivity(intent);
      }

    });


  }
  public void checkIfMod() {
    int flag = getIntent().getIntExtra("flag", 0);
    if (flag != 0) {

      final Calendar dobCalendar = Calendar.getInstance();
      Spinner Gender = (Spinner) findViewById(R.id.gender_spinner);
      ArrayAdapter gspin = (ArrayAdapter) Gender.getAdapter();
      int spinnerPosition = gspin.getPosition(Gender);
      name = (TextView) findViewById(R.id.name);
      // DOB = (TextView) findViewById(R.id.date_of_birth);
      // Gender = (Spinner) findViewById(R.id.gender_spinner);
      // dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
      Gender.setSelection(spinnerPosition);
      //String DOB = getIntent().getStringExtra("DOB");

      int byear = getIntent().getIntExtra("BYear", 0);
      int bmonth = getIntent().getIntExtra("BMonth", 0);
      int bday = getIntent().getIntExtra("BDay", 0);
      Toast.makeText(getApplicationContext(), "vlaue is " + byear + " " + bmonth + " " + bday,
          Toast.LENGTH_LONG).show();
      //dobCalendar = (Calendar)getIntent().getSerializableExtra("datebirth");
      //dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
      if (byear != 0) {
        dobCalendar.set(Calendar.YEAR, byear);
        dobCalendar.set(Calendar.MONTH, bmonth);
        dobCalendar.set(Calendar.DAY_OF_MONTH, bday);
        final EditText dob = findViewById(R.id.date_of_birth);
        dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
      }

      final Calendar cleanDateCalendar = Calendar.getInstance();
      int Cyear = getIntent().getIntExtra("CYear", 0);
      int Cmonth = getIntent().getIntExtra("CMonth", 0);
      int Cday = getIntent().getIntExtra("CDay", 0);
      Toast.makeText(getApplicationContext(), "clean date is " + Cyear + " " + Cmonth + " " + Cday,
          Toast.LENGTH_LONG).show();
      if (byear != 0) {
        cleanDateCalendar.set(Calendar.YEAR, Cyear);
        cleanDateCalendar.set(Calendar.MONTH, Cmonth);
        cleanDateCalendar.set(Calendar.DAY_OF_MONTH, Cday);
        final EditText cleanDate = findViewById(R.id.clean_date);
        cleanDate.setText(DateFormat.getDateInstance().format(cleanDateCalendar.getTime()));
      }

      final Spinner spinner = findViewById(R.id.gender_spinner);

      String gender = getIntent().getStringExtra("gender");

      if(gender.equals("Male"))
      {
        spinner.setSelection(2);
      }
      if(gender.equals("Female"))
      {
        spinner.setSelection(1);
      }
      if(gender.equals("Other"))
      {
        spinner.setSelection(3);
      }


      boolean Support = getIntent().getBooleanExtra("family", false);
      if(Support)
      {
        RadioButton rdb = findViewById(R.id.radioSupport);
        rdb.setChecked(true);
      }
      boolean Client = getIntent().getBooleanExtra("recovery", false);
      if(Client)
      {
        RadioButton rdb = findViewById(R.id.radioClient);
        rdb.setChecked(true);
      }
      boolean heroin = getIntent().getBooleanExtra("heroin", false);
      if (heroin) {
        RadioButton rdb = findViewById(R.id.radioHeroin);
        rdb.setChecked(true);
      }
      boolean Opiate = getIntent().getBooleanExtra("Opiate", false);
      if (Opiate) {
        RadioButton rdb = findViewById(R.id.radioOpiates);
        rdb.setChecked(true);
      }
      boolean crackorcocaine = getIntent().getBooleanExtra("CrackCocaine", false);
      if (crackorcocaine) {
        RadioButton rdb = findViewById(R.id.radioCocaine);
        rdb.setChecked(true);
      }
      boolean marajuana = getIntent().getBooleanExtra("Marajuana", false);
      if (marajuana) {
        RadioButton rdb = findViewById(R.id.radioMarijuana);
        rdb.setChecked(true);
      }
      boolean meth = getIntent().getBooleanExtra("Meth", false);
      if (meth) {
        RadioButton rdb = findViewById(R.id.radioMeth);
        rdb.setChecked(true);
      }
      boolean benzo = getIntent().getBooleanExtra("benzo", false);
      if (benzo) {
        RadioButton rdb = findViewById(R.id.radioBen);
        rdb.setChecked(true);
      }
      boolean Nonbenzo = getIntent().getBooleanExtra("NonBenzo", false);
      if (Nonbenzo) {
        RadioButton rdb = findViewById(R.id.radioTranquilizers);
        rdb.setChecked(true);
      }
      boolean barb = getIntent().getBooleanExtra("Barb", false);
      if (barb) {
        RadioButton rdb = findViewById(R.id.radioSedatives);
        rdb.setChecked(true);
      }
      boolean inhalant = getIntent().getBooleanExtra("Inhalant", false);
      if (inhalant) {
        RadioButton rdb = findViewById(R.id.radioInhalants);
        rdb.setChecked(true);
      }
      //String useother = getIntent().getStringExtra("useother");
      //boolean alcoholDisorder = getIntent().getBooleanExtra("AlcoholDisorder", false);
      boolean alcohol = getIntent().getBooleanExtra("Alcohol", false);
      if (alcohol) {
        RadioButton rdb = findViewById(R.id.radioAlcohol);
        rdb.setChecked(true);
      }
      //boolean Opioddisorder = getIntent().getBooleanExtra("Opiod", false);

      boolean other = getIntent().getBooleanExtra("useother", false);
      if (other) {
        RadioButton rdb = findViewById(R.id.radioOther);
        String condition = getIntent().getStringExtra("condition");
        final EditText OtherCondition = findViewById(R.id.enter_other);
        OtherCondition.setText(condition);
        rdb.setChecked(true);
      }

      final Spinner DisorderSpinner = (Spinner) findViewById(R.id.use_type_spinner);
      boolean AlcoholDisorder = getIntent().getBooleanExtra("alcoholDisorder", false);
      if (AlcoholDisorder) {
        DisorderSpinner.setSelection(2);
      }
      boolean OpioidDisorder = getIntent().getBooleanExtra("Opioddisorder", false);
      if (OpioidDisorder) {
        DisorderSpinner.setSelection(1);
      }


/*i
    if(DOB/
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
      Date date = sdf.parse(DOB);
      dobCalendar.setTime(date);
      dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
    }catch(java.text.ParseException e) {
      e.printStackTrace();
    }
*/
      //TypeOfPerson = (TextView) findViewById(R.id.recorded_person);
      //DrugOfChoice = (TextView) findViewById(R.id.recorded_drug);
      //Disorder = (TextView) findViewById(R.id.recorded_type);
      //CleanDate = (TextView) findViewById(R.id.clean_date);

      name.setText(getIntent().getStringExtra("name"));
      //dob.setText(getIntent().getStringExtra("DOB"));
      // Toast.makeText(getApplicationContext(), "vlaue is "+(DateFormat.getDateInstance().format(dobCalendar.getTime())), Toast.LENGTH_LONG).show();
      //Gender.setText(getIntent().getStringExtra("DOB"));
      //CleanDate.setText(getIntent().getStringExtra("CleanDate"));
    }
  }
}