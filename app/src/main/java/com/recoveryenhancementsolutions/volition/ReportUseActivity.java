package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;

import android.widget.DatePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The ReportUseActivity that contains functionality and interactions relevant to the
 * activity_report_use document. Displays a very simple question to the client and two buttons, yes
 * or no respectively. Also includes a navigation menu at the bottom as seen in the example page
 * image provided.
 */
public class ReportUseActivity extends AppCompatActivity {

  /**
   * Prepares the ActivityNavigationHandler object.
   */
  @Override
  public void onResume() {
    super.onResume();

    final BottomNavigationView bottomNavigationView = findViewById(R.id.activity_back_navigation);
    ActivityNavigationHandler.link(bottomNavigationView, this);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report_use);

    //Initializing ViewModel
    ddViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);

    today = Calendar.getInstance();
    inTest = false;

    final Button yesButton = findViewById(R.id.report_use_yes);
    yesButton.setOnClickListener(yesButtonListener);

    final Button noButton = findViewById(R.id.report_use_no);
    noButton.setOnClickListener(noButtonListener);


    //Observer that will retrieve the previously stored date of last use
    ddViewModel.getLastCleanDate().observe(this, dateObserver);

    //Date Picker for the user to select their date of last use
    useDate = Calendar.getInstance();
    useDateListener = new OnDateSetListener() {
      /**
       * Event handler for when the user must select their date of last use
       * @param view DatePicker object
       * @param year Year chosen by user
       * @param month Month chosen by user
       * @param day Day chosen by user
       */
      @Override
      public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        useDate.set(Calendar.YEAR, year);
        useDate.set(Calendar.MONTH, month);
        useDate.set(Calendar.DAY_OF_MONTH, day);
        ready = false;
        int days = DateConverter.daysBetween(useDate.getTime(), today.getTime());
        Log.e("RepUseActivity", "Attempted use date: " +useDate.getTime().toString());
        //Checks if the date selected is after the current date
        if (days < 0){
          toast = Toast.makeText(getApplicationContext(),
              "ERROR: Invalid date selected",
              Toast.LENGTH_LONG);
          toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
          toast.show();
        }
        else {
          Log.e("RepUseActivity", "Date: " +prevUseDate);
          days = DateConverter.daysBetween(prevUseDate.getTime(), useDate.getTime().getTime());
          //Check to see if the date selected is the same as or before the date already stored
          if (days < 0) {
            toast = Toast.makeText(getApplicationContext(),
                "ERROR: Date selected comes before the previous date entered",
                Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
            toast.show();
          }
          //The date selected is valid
          else {
            ready = true;
            ddViewModel.updateLastCleanDate(useDate, today);
          }
        }

        //Only redirects if we are not in a testing environment & the date chosen was valid
        if (!inTest && ready) {
          final String str = DateFormat.getDateInstance().format(useDate.getTime());
          toast = Toast.makeText(getApplicationContext(), "Recorded " + str + " as day of last use",
              Toast.LENGTH_LONG);
          toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
          redirect();
          toast.show();
        }
      }
    };
  }

  /**
   * Sets the activity as a test to prevent redirecting to other Activities (TEST METHOD)
   *
   * @param b True = in test, false = not in test
   */
  protected void setTestEnvironment(final boolean b) {
    inTest = b;
  }

  /**
   * Returns the ViewModel (TEST METHOD)
   *
   * @return the DemographicDataViewModel
   */
  protected DemographicDataViewModel getViewModel() {
    return ddViewModel;
  }

  /**
   * Sets the ViewModel to work with a test database (TEST METHOD)
   *
   * @param db the test database
   */
  protected void setTestDatabase(final VolitionDatabase db) {
    ddViewModel.setTestDatabase(db);
    ddViewModel.getLastCleanDate().observe(this, dateObserver);
  }

  /**
   * Listener method for the Yes button
   */
  private OnClickListener yesButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      final DatePickerDialog pickDate = new DatePickerDialog(ReportUseActivity.this,
          useDateListener, useDate.get(Calendar.YEAR), useDate.get(Calendar.MONTH),
          useDate.get(Calendar.DAY_OF_MONTH));
      pickDate.show();
    }
  };

  /**
   * Listener method for the No button
   */
  private OnClickListener noButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      ddViewModel.updateLastReportDate(today);
      toast = Toast
          .makeText(getApplicationContext(), "Recorded 'No' for the day", Toast.LENGTH_LONG);
      toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);

      //Only redirects if we are not in a testing environment
      if (!inTest) {
        redirect();
        toast.show();
      }
    }
  };

  /**
   * Redirects to another screen
   */
  private void redirect() {
    startActivity(new Intent(getApplicationContext(), ActivityActivity.class));
  }


  /**
   * Observer for retrieving the "last clean" Date
   */
  private Observer<Date> dateObserver = new Observer<Date>() {
    @Override
    public void onChanged(final Date date) {
      try {
        prevUseDate = date;
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    }
  };

  protected Date prevUseDate;

  private DatePickerDialog.OnDateSetListener useDateListener;
  private DemographicDataViewModel ddViewModel;
  private Calendar today;
  private Calendar useDate;
  private Toast toast;
  public static int numberCompleted;
  private boolean inTest;
  private boolean ready;
}
