package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Loads the activities for each day and displays them to the user. Allows the user to go
 * back/forward by 4 days on the calendar.
 */
public class ActivityActivity extends AppCompatActivity {

  /**
   * Structure for storing a date's corresponding TextViews for the title and descriptions.
   */
  private class DateView implements Observer<List<UserActivityEntity>> {

    public final TextView title;
    public final TextView content;
    public LiveData<List<UserActivityEntity>> data;

    public DateView(Calendar day, TextView title, TextView content) {
      this.title = title;
      this.content = content;

      setDay(day);
    }

    /**
     * Change the day associated with these labels. Updates the title label to reflect the day.
     *
     * @param day The new day to associate with.
     */
    public void setDay(Calendar day) {
      this.day = (Calendar) day.clone();
      if (title != null) {
        title.setText(day.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT,
            getResources().getConfiguration().locale));
      }
    }

    public Calendar getDay() {
      return day;
    }

    @Override
    public void onChanged(@NonNull final List<UserActivityEntity> activities) {
      final StringBuilder activityBuffer = new StringBuilder();

      //Compiles a list of activity descriptions for a specific date
      for (int i = 0; i < activities.size(); ++i) {
        activityBuffer.append(activities.get(i).getDesc());
        if (i < activities.size() - 1) {
          activityBuffer.append('\n');
        }
      }

      content.setText(activityBuffer);
    }

    private Calendar day;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TEMP: Eventually make this R.layout.activity_activity
    setContentView(R.layout.activity_activity_testing);

    // Init navbar.
    final BottomNavigationView navigation = findViewById(R.id.menubar);
    navigation.setSelectedItemId(R.id.menubar_home);
    navigation.setOnNavigationItemSelectedListener(navigationListener);

    /*
    ((ImageButton) findViewById(R.id.button_next)).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        cycle(true);
      }
    });
    ((ImageButton) findViewById(R.id.button_previous)).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        cycle(false);
      }
    });
    */

    // Store today's date with a time of 0 for relative date calculation.
    final Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.MILLISECOND, 0);

    // Load the views of of the currently displayed dates.
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_1),
        (TextView) findViewById(R.id.textview_day_1)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_2),
        (TextView) findViewById(R.id.textview_day_2)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_3),
        (TextView) findViewById(R.id.textview_day_3)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_4),
        (TextView) findViewById(R.id.textview_day_4)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_4), // TEMP
        (TextView) findViewById(R.id.textview_day_5)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_4), // TEMP
        (TextView) findViewById(R.id.textview_day_6)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_4), // TEMP
        (TextView) findViewById(R.id.textview_day_7)));
    today.add(Calendar.DAY_OF_MONTH, -1);

    //Initializing ViewModel
    actViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);
    subscribeUIActivities();
  }

  protected UserActivityViewModel getViewModel() {
    return actViewModel;
  }

  /**
   * Retrieves activity descriptions for the current and previous dates
   */
  private void subscribeUIActivities() {
    for (DateView dv : dateViews) {
      dv.data = actViewModel.getActivitiesByDate(dv.day.getTime());
      dv.data.observe(this, dv);
    }
  }

  /**
   * Change what set of days are being displayed.
   *
   * @param forward If set, displays the next days on the calendar.  Otherwise, the previous days.
   */
  private void cycle(boolean forward) {
    for (DateView dv : dateViews) {
      if (dv.data != null) {
        dv.data.removeObservers(this);
      }
    }

    Calendar rightmost = dateViews.get(0).day;
    rightmost.add(Calendar.DAY_OF_MONTH, forward ? dateViews.size() : -dateViews.size());

    for (DateView dv : dateViews) {
      dv.setDay(rightmost);
      rightmost.add(Calendar.DAY_OF_MONTH, -1);
    }

    subscribeUIActivities();
  }

  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.menubar_home:
          Intent i = new Intent(getApplicationContext(), HomeActivity.class);
          startActivity(i);
          return true;
        case R.id.menubar_activity:
          return true;
        case R.id.menubar_plan:
          return true;
      }
      return false;
    }
  };

  private final ArrayList<DateView> dateViews = new ArrayList<DateView>();
  private UserActivityViewModel actViewModel;
}