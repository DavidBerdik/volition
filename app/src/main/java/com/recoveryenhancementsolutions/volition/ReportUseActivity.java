package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
    final Button yesButton = findViewById(R.id.report_use_yes);
    yesButton.setOnClickListener(yesButtonListener);

    final Button noButton = findViewById(R.id.report_use_no);
    noButton.setOnClickListener(noButtonListener);

    final BottomNavigationView navigation = findViewById(R.id.menubar);
    navigation.getMenu().getItem(1).setChecked(false);
    navigation.setOnNavigationItemSelectedListener(navigationListener);
  }

  private OnClickListener yesButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      lastClickedItem = 1;
      ddViewModel.updateLastCleanDate(today, today);
      redirect();
    }
  };

  private OnClickListener noButtonListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      lastClickedItem = 2;
      ddViewModel.updateLastReportDate(today);
      redirect();
    }
  };

  //TODO: Move the user to the Activity page
  private void redirect(){
    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(i);
  }

  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.core_navigation_home:
          return true;
        case R.id.core_navigation_activity:
          return true;
        case R.id.core_navigation_plan:
          return true;
      }
      return false;
    }
  };

  private int lastClickedItem;
  private Calendar today;
  private DemographicDataViewModel ddViewModel;
}