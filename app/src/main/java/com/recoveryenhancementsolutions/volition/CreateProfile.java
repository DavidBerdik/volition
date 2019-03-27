package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class for running only create_profile.xml
 */
public class CreateProfile extends AppCompatActivity {
  Spinner spinner, spin2;
  EditText editText, editText2, editText3,editText4;
  Button send;

  String myFormat = "MM/dd/yy";
  SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

  boolean checked = false;
  RadioGroup rg, rg2;
  String CleanDateResult = "";
  String BirthdayResult ="";
  RadioButton rb, rb2;
  private boolean isPersonInRecovery;
  private boolean useHeroin;
  private boolean useOpiateOrSynth;
  private boolean useAlcohol;
  private boolean useCrackOrCocaine;
  private boolean useMarijuana;
  private boolean useMethamphetamine;
  private boolean useBenzo;
  private boolean useNonBeznoTrang;
  private boolean useBarbituresOrHypno;
  private boolean useInhalants;
  private boolean disorderOpioid;
  private boolean disorderAlcohol;
  int flag = 0; //flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, flag10, flag11,flag12;
  private RadioButton radioSupport, radioClient, radioHeroin, radioOpiates, radioAlcohol, radioCocaine, radioMarijuana, radioMeth, radioBen, radioTranquilizers, radioSedatives, radioInhalants;
  ArrayAdapter<CharSequence> adapter,adapter2;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_profile);




    final DemographicDataEntity patient = new DemographicDataEntity();
    final DemographicDataDAO demographicDataDAO;

    VolitionDatabase db = VolitionDatabase.getDatabase(this.getApplication());
    final Context context = getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
            .allowMainThreadQueries().build();
    demographicDataDAO = db.demographicDataDAO();

    editText = (EditText) findViewById(R.id.name);
    editText2 = (EditText) findViewById(R.id.date_of_birth);
    editText3 = (EditText) findViewById(R.id.enter_other);
    editText4 = (EditText) findViewById(R.id.clean_date);
    final Calendar calendar = Calendar.getInstance();

    rg = findViewById(R.id.user_type);
    rg2 = findViewById(R.id.drug_selection);

    spinner = (Spinner)findViewById(R.id.gender_spinner);
    adapter = ArrayAdapter.createFromResource(this,R.array.create_profile_gender_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    spin2 = (Spinner)findViewById(R.id.use_type_spinner);
    adapter2 = ArrayAdapter.createFromResource(this,R.array.create_profile_use_type_array, android.R.layout.simple_spinner_item);
    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spin2.setAdapter(adapter2);
    //spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //@Override
    // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    //  Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)Toast.LENGTH_LONG);
    // }
    //);
    send = (Button) findViewById(R.id.record_button);

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
      public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        BirthdayResult = calendar.get(Calendar.MONTH) +"-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR);
        editText2.setText(calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR));
      }
    };


    final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
      public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        CleanDateResult = calendar.get(Calendar.MONTH) +"-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR);
        editText4.setText(calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR));
      }
    };

    final EditText dateOfBirth = (EditText) findViewById(R.id.date_of_birth);
    dateOfBirth.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        DatePickerDialog pickDate = new DatePickerDialog(CreateProfile.this, date,
                2000, calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

       // BirthdayResult = pickDate.toString();

        //BirthdayResult = editText2.toString();
        pickDate.show();
      }

    });



    final EditText cleanDate = (EditText) findViewById(R.id.clean_date);
    cleanDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        DatePickerDialog pickDate2 = new DatePickerDialog(CreateProfile.this, date2,
                2019, calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

       // CleanDateResult = date2.toString();

        pickDate2.show();
      }

    });

    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //stuff to be passed here

        String checker = "";
        String name = editText.getText().toString();
        String DOB = editText2.getText().toString();
        String Gender = spinner.getSelectedItem().toString();
        String Subdisorder = spin2.getSelectedItem().toString();


        Toast toast = Toast.makeText(getApplicationContext(),
                "Bday " + BirthdayResult +  "   Clean   " + CleanDateResult,
                Toast.LENGTH_SHORT);
        toast.show();

        radioSupport = (RadioButton) findViewById(R.id.radioSupport);
        if(radioSupport.isChecked()) {
          flag = 1;
          isPersonInRecovery = true;

        }
        radioClient = (RadioButton) findViewById(R.id.radioClient);
        if(radioClient.isChecked()) {
          flag = 1;
          checked = true;
          isPersonInRecovery = false;

        }

        radioHeroin = (RadioButton) findViewById(R.id.radioHeroin);
        if(radioHeroin.isChecked()) {
          flag = 1;
          checked = true;
          useHeroin = true;
        }

        radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
        if(radioMarijuana.isChecked()) {
          flag = 1;
          checked = true;
          useMarijuana = true;
        }

        radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
        if(radioOpiates.isChecked()) {
          flag = 1;
          checked = true;
          useOpiateOrSynth = true;
        }

        radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
        if(radioAlcohol.isChecked())
        {
          flag = 1;
          checked = true;
          useAlcohol = true;
        }


        radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
        if(radioCocaine.isChecked()) {
          checked = true;
          useCrackOrCocaine = true;
          flag = 1;
        }

        radioMeth = (RadioButton) findViewById(R.id.radioMeth);
        if(radioMeth.isChecked()) {
          checked = true;
          useMethamphetamine = true;
          flag = 1;
        }

        radioBen = (RadioButton) findViewById(R.id.radioBen);
        if(radioBen.isChecked()) {
          flag = 1;
          useBenzo = true;
          checked = true;
        }
        radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
        if(radioTranquilizers.isChecked()) {
          flag = 1;
          useNonBeznoTrang = true;
          checked = true;
        }

        radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
        if(radioSedatives.isChecked()) {
          flag = 1;
          useBarbituresOrHypno = true;

          checked = true;
        }

        radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
        if(radioInhalants.isChecked()) {
          flag = 1;
          useInhalants = true;
          checked = true;
        }


        int rID = rg.getCheckedRadioButtonId();
        int rID2 = rg2.getCheckedRadioButtonId();

       rb = findViewById(rID);
       rb2 = findViewById(rID2);

        String whatareyou = rb.getText() + "";
        String whatdrug = rb2.getText()+"";


        if(whatdrug.equals("Other")){
          whatdrug = editText3.getText().toString();
        }

        String CleanDate2 = editText4.getText().toString();


        if((checker.equals(name)) || (checker.equals(BirthdayResult)) || (checker.equals(Gender)) || (checker.equals(Subdisorder)) || (checker.equals(CleanDateResult)) || checked == false) {

           toast = Toast.makeText(getApplicationContext(),
                  "Please enter all fields",
                  Toast.LENGTH_SHORT);
          toast.show();
        }

        else {
          Intent intent = new Intent(CreateProfile.this, DisplayProfile.class);
          //more stuff here
          intent.putExtra("Name", name);
          intent.putExtra("Date of Birth", BirthdayResult);
          intent.putExtra("Gender", Gender);
          intent.putExtra("Type of Person", whatareyou);
          intent.putExtra("Drug of Choice", whatdrug);
          intent.putExtra("Disorder", Subdisorder);
          intent.putExtra("CleanDate", CleanDateResult);


          patient.setDateOfBirth(BirthdayResult);
          patient.setPatientName("rahul");
         patient.setGender(Gender);
          patient.setLastClean(CleanDateResult);
          patient.setPersonInRecovery(isPersonInRecovery);
          patient.setUseHeroin(useHeroin);

          patient.setUseOpiateOrSynth(useOpiateOrSynth);
          patient.setUseAlcohol(useAlcohol);
          patient.setUseCrackOrCocaine(useCrackOrCocaine);
          patient.setUseMarijuana(useMarijuana);
          patient.setUseMethamphetamine(useMethamphetamine);
          patient.setUseBenzo(useBenzo);
          patient.setUseNonBeznoTrang(useNonBeznoTrang);

          patient.setUseInhalants(useInhalants);
          patient.setDisorderOpioid(disorderOpioid);
          patient.setDisorderAlcohol(disorderAlcohol);




          demographicDataDAO.insertDemographicInfo(patient);
          startActivity(intent);


        }



      }
    });

    }



  }

//}
