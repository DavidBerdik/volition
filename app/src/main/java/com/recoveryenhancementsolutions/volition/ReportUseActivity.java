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
   * Returns a private integer value related to the most recently clicked item. Used for testing.
   *
   * @return Integer value representing the most recently clicked item. 0 means Nothing, 1 means
   * Yes, and 2 means No.
   */
  public int getLastClickedItem() {
    return lastClickedItem;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_report_use);

    //Initializing ViewModel
    ddViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);
    today = Calendar.getInstance();

    lastClickedItem = 0;
    inTest = false;
    final Button yesButton = findViewById(R.id.report_use_yes);
    yesButton.setOnClickListener(yesButtonListener);

    final Button noButton = findViewById(R.id.report_use_no);
    noButton.setOnClickListener(noButtonListener);

    final BottomNavigationView navigation = findViewById(R.id.menubar);
    navigation.getMenu().getItem(1).setChecked(false);
    navigation.setOnNavigationItemSelectedListener(navigationListener);

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
        toast = Toast.makeText(getApplicationContext(), "Recorded " + str +" as day of last use", Toast.LENGTH_LONG);
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
      lastClickedItem = 1;
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
      lastClickedItem = 2;
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
    intent = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(intent);
  }

  //TODO: Uncomment as more Activities are added to the dev branch
  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.core_navigation_home:
          intent = new Intent(getApplicationContext(), HomeActivity.class);
          startActivity(intent);
          return true;
        case R.id.core_navigation_activity:
          //intent = new Intent(getApplicationContext(), ActivityActivity.class);
          //startActivity(intent);
          return true;
        case R.id.core_navigation_plan:
          //intent = new Intent(getApplicationContext(), PlanActivity.class);
          //startActivity(intent);
          return true;
      }
      return false;
    }
  };

  private DatePickerDialog.OnDateSetListener useDateListener;
  private DemographicDataViewModel ddViewModel;
  private Calendar today;
  private Calendar useDate;
  private Toast toast;
  private Intent intent;
  private int lastClickedItem;
  private boolean inTest;
}