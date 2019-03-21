package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewActivitiesActivity extends AppCompatActivity {

  /**
   * Structure for storing a date's corresponding TextViews for the title and descriptions.
   */
  private class DateView {

    public Calendar day;
    public TextView title;
    public TextView content;

    public DateView(Calendar day, TextView title, TextView content) {
      this.day = (Calendar)day.clone();
      this.title = title;
      this.content = content;
    }
  }

  private ArrayList<DateView> dateViews = new ArrayList<DateView>();

  private TextView mTextMessage;

  private UserActivityViewModel actViewModel;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_activities);

    //Initializing TextViews
    mTextMessage = (TextView) findViewById(R.id.message);

    //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    // Store today's date with a time of 0 for relative date calculation.
    Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.MILLISECOND, 0);

    // Load the views of of the currently displayed dates.
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_today),
        (TextView) findViewById(R.id.details_today)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_yesterday),
        (TextView) findViewById(R.id.details_yesterday)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_2_days),
        (TextView) findViewById(R.id.details_2_days)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_3_days),
        (TextView) findViewById(R.id.details_3_days)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_4_days),
        (TextView) findViewById(R.id.details_4_days)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_5_days),
        (TextView) findViewById(R.id.details_5_days)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.label_6_days),
        (TextView) findViewById(R.id.details_6_days)));

    //Initializing ViewModel
    actViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);
    subscribeUIActivities();
  }

  /**
   * Sets the activity text for the corresponding views on the calendar.
   *
   * @param day The day on the calendar to update.  Should be before or on the date the activity
   * loaded.
   * @param descs All the activities that were done on the given day.
   */
  private void updateDayActivities(final Calendar day, ArrayList<String> descs) {
    StringBuilder activityBuffer = new StringBuilder();
    Calendar dayNoTime; // Activity day with the time set to 0 for accurate millisecond difference.

    for (int i = 0; i < descs.size(); ++i) {
      activityBuffer.append(descs.get(i));
      if (i < descs.size() - 1) {
        activityBuffer.append('\n');
      }
    }

    dayNoTime = (Calendar) day.clone();
    dayNoTime.set(Calendar.HOUR_OF_DAY, 0);
    dayNoTime.set(Calendar.MINUTE, 0);
    dayNoTime.set(Calendar.SECOND, 0);
    dayNoTime.set(Calendar.MILLISECOND, 0);

    for (DateView d : dateViews) {
      if (d.day.equals(dayNoTime)) {
        d.content.setText(activityBuffer);
      }
    }
  }

  /**
   * Retrieves activity descriptions for the current and previous dates
   */
  private void subscribeUIActivities() {
    Date date = new Date();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_WEEK);

    //Separate Observers for each date
    for (int i = 0; i < 7; i++) {
      actViewModel.getActivitiesByDate(year, month, day)
          .observe(this, new Observer<List<UserActivityEntity>>() {
            @Override
            public void onChanged(@NonNull final List<UserActivityEntity> activities) {
              final ArrayList<String> activityList = new ArrayList<String>();
              //Compiles a list of activity descriptions for a specific date
              for (UserActivityEntity activity : activities) {
                activityList.add(activity.getDesc());
              }
              updateDayActivities(cal, activityList);
            }
          });
      //Decrements the current date and updates year, month, and day (for later Observers)
      cal.add(Calendar.DAY_OF_MONTH, -1);
      year = cal.get(Calendar.YEAR);
      month = cal.get(Calendar.MONTH);
      day = cal.get(Calendar.DAY_OF_WEEK);
    }
  }
}