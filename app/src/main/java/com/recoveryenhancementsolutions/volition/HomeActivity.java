package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Date;

/**
 * The HomeActivity that contains functionality and interactions relevant to the activity_home
 * document. Displays a generic welcoming message to the client as well as the number of days that
 * they have been clean. Includes a navigation menu at the bottom.
 */
public class HomeActivity extends AppCompatActivity {

  /**
   * Retrieves the text stored in daysCleanMessage. Only needed for testing.
   *
   * @return A String object containing the text inside daysCleanMessage.
   */
  public String getDaysCleanText() {
    return daysCleanMessage.getText().toString();
  }

  /**
   * Recreates the observer but using a testing database. Should only be used for testing.
   *
   * @param db A VolitionDatabase test object.
   */
  public void onCreateTest(final VolitionDatabase db) {
    final DemographicDataViewModel demographicDataViewModel = ViewModelProviders.of(this)
        .get(DemographicDataViewModel.class);
    demographicDataViewModel.setTestDatabase(db);
    demographicDataViewModel.getLastCleanDate().observe(this, dateObserver);
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    buttonTestItem = findViewById(R.id.buttonTestItem);
    daysCleanMessage = findViewById(R.id.clean);

    final DemographicDataViewModel demographicDataViewModel = ViewModelProviders.of(this)
        .get(DemographicDataViewModel.class);
    demographicDataViewModel.getLastCleanDate().observe(this, dateObserver);

    final BottomNavigationView navigation = findViewById(R.id.menubar);
    navigation.setSelectedItemId(R.id.core_navigation_home);
    navigation.setOnNavigationItemSelectedListener(navigationListener);
  }

  private Observer<Date> dateObserver = new Observer<Date>() {
    @Override
    public void onChanged(final Date date) {
      // We should only have a NullPointerException if nothing is entered into the DB yet.
      // If this is the case, have an empty days clean String.
      try {
        final int days = DateConverter.daysBetween(date.getTime(), new Date().getTime());
        daysCleanMessage.setText(R.string.home_clean);
        daysCleanMessage.append(" " + days);
      } catch (NullPointerException e) {
        daysCleanMessage.setText(R.string.home_clean);
      }
    }
  };

  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.core_navigation_home:
          buttonTestItem.setText(R.string.core_navigation_home);
          return true;
        case R.id.core_navigation_activity:
          buttonTestItem.setText(R.string.core_navigation_activity);
          return true;
        case R.id.core_navigation_plan:
          buttonTestItem.setText(R.string.core_navigation_plan);
          return true;
      }
      return false;
    }
  };

  private TextView buttonTestItem;
  private TextView daysCleanMessage;
}