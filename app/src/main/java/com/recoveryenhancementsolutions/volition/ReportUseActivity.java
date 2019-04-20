package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;

import android.widget.DatePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

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

    //Pulls up a Date Picker for the user to select their date of last use
    useDate = Calendar.getInstance();
    useDateListener = new OnDateSetListener() {
      /**
       * Event handler for when a date of birth is chosen by the user.
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

        final String str = DateFormat.getDateInstance().format(useDate.getTime());
        ddViewModel.updateLastCleanDate(useDate, today);
        toast = Toast.makeText(getApplicationContext(), "Recorded " + str + " as day of last use",
            Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);

        //Only redirects if we are not in a testing environment
        if (!inTest) {
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
   * Redirects to another screen TODO: Move the user to the Activity screen
   */
  private void redirect() {
    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
  }

  private DatePickerDialog.OnDateSetListener useDateListener;
  private DemographicDataViewModel ddViewModel;
  private Calendar today;
  private Calendar useDate;
  private Toast toast;
  public static int numberCompleted;
  private boolean inTest;
}
