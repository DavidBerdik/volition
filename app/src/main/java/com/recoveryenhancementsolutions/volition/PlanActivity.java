package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Loads the activities for each day and displays them to the user.
 */
public class PlanActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_plan_land);
    } else {
      setContentView(R.layout.activity_plan_port);
    }

    // Init navbar.
    bottomNavigationView = findViewById(R.id.core_navigation);
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_plan);
    CoreNavigationHandler.link(bottomNavigationView, this, 3);

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

    //fills spinner
    Spinner spinner = findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.plan_spinner_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);


    findViewById(R.id.track_button).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Spinner actSpinner = findViewById(R.id.spinner);
        String activity = actSpinner.getSelectedItem().toString();
        EditText notesEditText = findViewById(R.id.notes_edittext);
        String notes = notesEditText.getText().toString();
        actViewModel.insertActivity(new Date(), activity, notes);
      }
    });
  }

  /**
   * Gets the notes string for all activities on the calendar day at the given position.
   * @param at Offset from today.  1 would be yesterday.
   * @return String containing list of all notes for that day.
   */
  protected String getNotesBuffer(final int at) {
    return dateViews.get(at).notes;
  }

  /**
   * Restores the CoreNavigationHandler to it's default state for this page.
   */
  @Override
  public void onResume() {
    super.onResume();
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_plan);
  }

  protected UserActivityViewModel getViewModel() {
    return actViewModel;
  }

  /**
   * Sets observers for all dates currently being displayed.
   */
  private void subscribeUIActivities() {
    for (final DateView dv : dateViews) {
      dv.observe(this);
    }
  }

  private void scrollRight() {
    // Scroll calendar to the right to show today.
    final HorizontalScrollView calScroller = findViewById(R.id.hscroller);
    calScroller.post(new Runnable() {
      @Override
      public void run() {
        calScroller.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
      }
    });
  }


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
     * Change the day associated with these labels.  Updates the title label to reflect the day.
     *
     * @param day The new day to associate with.
     */
    private void setDay(final Calendar day) {
      final Calendar dayClone = (Calendar) day.clone();

      this.day = dayClone;

      content.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          final String dayString = DateFormat.getDateInstance(DateFormat.FULL,
              Locale.getDefault()).format(dayClone.getTime());
          PlanNoteView.create(dayString, notes).show(getSupportFragmentManager());
        }
      });

      if (title != null) {
        String str = day.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT,
            Locale.getDefault()).charAt(0) + "";
        title.setText(str);
      }
    }

    private void observe(final LifecycleOwner owner) {
      data = actViewModel.getActivitiesByDate(day.getTime());
      data.observe(owner, this);
      notes = null;
    }

    @Override
    public void onChanged(final List<UserActivityEntity> activities) {
      final StringBuilder calBuffer = new StringBuilder();   // Only stores activity name.
      final StringBuilder notesBuffer = new StringBuilder(); // Stores name & notes.

      // Compiles a list of activity descriptions for a specific date.
      // The notes string is also updated with these activities.

      for (int i = 0; i < activities.size(); ++i) {
        calBuffer.append(activities.get(i).getDesc());

        notesBuffer.append(activities.get(i).getDesc());
        notesBuffer.append(":\n");
        notesBuffer.append(activities.get(i).getNotes());

        if (i < activities.size() - 1) {
          calBuffer.append('\n');
          notesBuffer.append("\n\n");
        }
      }

      content.setText(calBuffer);
      notes = notesBuffer.toString();
    }

    private final TextView title;
    private final TextView content;
    private Calendar day;
    private LiveData<List<UserActivityEntity>> data;
    private String notes;
  }

  private final ArrayList<DateView> dateViews = new ArrayList<>();
  private BottomNavigationView bottomNavigationView;
  private UserActivityViewModel actViewModel;
}