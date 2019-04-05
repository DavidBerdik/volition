package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Loads the activities for each day and displays them to the user.
 */
public class PlanActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_plan_land);
    } else {
      setContentView(R.layout.activity_plan_port);
    }

    // Init navbar.
    final BottomNavigationView navigation = findViewById(R.id.core_navigation);
    navigation.setSelectedItemId(R.id.navigation_home);
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
        (TextView) findViewById(R.id.day_of_week_5),
        (TextView) findViewById(R.id.textview_day_5)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_6),
        (TextView) findViewById(R.id.textview_day_6)));
    today.add(Calendar.DAY_OF_MONTH, -1);
    dateViews.add(new DateView(
        today,
        (TextView) findViewById(R.id.day_of_week_7),
        (TextView) findViewById(R.id.textview_day_7)));
    today.add(Calendar.DAY_OF_MONTH, -1);

    //Initializing ViewModel
    actViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);
    subscribeUIActivities();
    scrollRight();
  }

  protected UserActivityViewModel getViewModel() {
    return actViewModel;
  }

  /**
   * @return The amount of days currently being displayed.
   */
  protected int getDayCount() {
    return dateViews.size();
  }

  protected String getActivityBuffer(final int at) {
    return dateViews.get(at).content.getText().toString();
  }

  protected boolean didActivitiesLoad(int at) {
    return dateViews.get(at).isLoaded();
  }

  /**
   * Change what set of days are being displayed.
   *
   * @param by How many days to add to the rightmost day.  For example, 7 would load the next week.
   */
  protected void cycle(final int by) {
    for (DateView dv : dateViews) {
      dv.unobserve(this);
    }

    final Calendar rightmost = dateViews.get(0).day;
    rightmost.add(Calendar.DAY_OF_MONTH, by);

    for (DateView dv : dateViews) {
      dv.setDay(rightmost);
      rightmost.add(Calendar.DAY_OF_MONTH, -1);
    }

    subscribeUIActivities();

    scrollRight();
  }

  /**
   * Change what set of days are being displayed.
   *
   * @param forward If set, displays the next days on the calendar.  Otherwise, the previous days.
   */
  protected void cycle(final boolean forward) {
    cycle(forward ? dateViews.size() : -dateViews.size());
  }

  /**
   * Sets observers for all dates currently being displayed.
   */
  private void subscribeUIActivities() {
    for (DateView dv : dateViews) {
      dv.observe(this);
    }
  }

  private void scrollRight() {
    // Scroll calendar to the right to show today.
    final HorizontalScrollView calScroller = (HorizontalScrollView) findViewById(R.id.hscroller);
    calScroller.post(new Runnable() {
      @Override
      public void run() {
        calScroller.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
      }
    });
  }

  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.core_navigation_home:
          Intent i = new Intent(getApplicationContext(), HomeActivity.class);
          startActivity(i);
          return true;
        case R.id.core_navigation_activity:
          return true;
        case R.id.core_navigation_plan:
          return true;
      }
      return false;
    }
  };

  /**
   * Structure for storing a date's corresponding TextViews for the title and descriptions.
   */
  private class DateView implements Observer<List<UserActivityEntity>> {

    public Calendar getDay() {
      return day;
    }

    private DateView(final Calendar day, final TextView title, final TextView content) {
      this.title = title;
      this.content = content;

      setDay(day);
    }

    /**
     * Change the day associated with these labels. Updates the title label to reflect the day.
     *
     * @param day The new day to associate with.
     */
    private void setDay(final Calendar day) {
      this.day = (Calendar) day.clone();
      if (title != null) {
        String str = day.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT,
            getResources().getConfiguration().locale).charAt(0) + "";
        title.setText(str);
      }
    }

    private void observe(final LifecycleOwner owner) {
      data = actViewModel.getActivitiesByDate(day.getTime());
      data.observe(owner, this);
      loaded = false;
    }

    private void unobserve(final LifecycleOwner owner) {
      if (data != null) {
        data.removeObservers(owner);
        data = null;
      }
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
      loaded = true;
    }

    public boolean isLoaded() {
      return loaded;
    }

    private final TextView title;
    private final TextView content;
    private Calendar day;
    private LiveData<List<UserActivityEntity>> data;
    private boolean loaded;
  }

  private final ArrayList<DateView> dateViews = new ArrayList<>();
  private UserActivityViewModel actViewModel;
}