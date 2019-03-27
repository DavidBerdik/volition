package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
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
 * Class for running only create_profile.xml
 */
public class CreateProfile extends AppCompatActivity {
  private Button send;
  private String BirthdayResult;
  private String CleanDateResult;
  private RadioButton rb, rb2;
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
  String name = "";
  String Gender = "";
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
    send = (Button) findViewById(R.id.record_button);



    final Calendar dobCalendar = Calendar.getInstance();
    final EditText dateOfBirth = findViewById(R.id.date_of_birth);
    final Calendar cleanDateCalendar = Calendar.getInstance();
    final EditText cleanDate = findViewById(R.id.clean_date);

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
        //stuff to be passed here
        sendOff();
        }

      public void sendOff(){
        Intent intent = new Intent(CreateProfile.this, QuestionnaireActivity.class);

        patient.setDateOfBirth(BirthdayResult);
        patient.setPatientName(name);
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

      });

    }

    }





//}
