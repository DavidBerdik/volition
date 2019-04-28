package com.recoveryenhancementsolutions.volition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
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
    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.plan_spinner_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
  }

  /**
   * Restores the CoreNavigationHandler to it's default state for this page.
   */
  @Override
  public void onResume() {
    super.onResume();
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_plan);
  }

  @SuppressLint("SetTextI18n")
  public void fillViews() {
    String sampletext = "Visit 1 Visit 1";

    final HorizontalScrollView hscroller = findViewById(R.id.hscroller);
    hscroller.post(new Runnable() {
      public void run() {
        hscroller.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
      }
    });
    TextView textView = (TextView) findViewById(R.id.textview_day_1);
    textView.setText(sampletext);
    textView = findViewById(R.id.textview_day_2);
    textView.setText(sampletext);
    textView = findViewById(R.id.textview_day_3);
    textView.setText(sampletext);
    textView = findViewById(R.id.textview_day_4);
    textView.setText(sampletext);
    textView = findViewById(R.id.textview_day_5);
    textView.setText(sampletext);
    textView = findViewById(R.id.textview_day_6);
    textView.setText(sampletext);
    textView = findViewById(R.id.textview_day_7);
    textView.setText(sampletext);
    textView = findViewById(R.id.day_of_week_1);
    textView.setText("S");
    textView = findViewById(R.id.day_of_week_2);
    textView.setText("S");
    textView = findViewById(R.id.day_of_week_3);
    textView.setText("F");
    textView = findViewById(R.id.day_of_week_4);
    textView.setText("T");
    textView = findViewById(R.id.day_of_week_5);
    textView.setText("W");
    textView = findViewById(R.id.day_of_week_6);
    textView.setText("T");
    textView = findViewById(R.id.day_of_week_7);
    textView.setText("M");


  }

  /**
   * Darkens selected day's textview and changes center_day_textview to corresponding day
   *
   * @param textview_day_1 The View to be modified by clickDay1.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay1(View textview_day_1) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Sunday");
    dayNo = 1;
    textView = findViewById(R.id.textview_day_1);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Darkens selected day's textview and changes center_day_textview to corresponding day
   *
   * @param textview_day_2 The View to be modified by clickDay2.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay2(View textview_day_2) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Saturday");
    dayNo = 2;
    textView = findViewById(R.id.textview_day_2);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Darkens selected day's textview and changes center_day_textview to corresponding day
   *
   * @param textview_day_3 The View to be modified by clickDay3.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay3(View textview_day_3) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Friday");
    dayNo = 3;
    textView = findViewById(R.id.textview_day_3);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Function called when Thursday's textview is clicked.
   *
   * @param textview_day_4 The View to be modified by clickDay4.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay4(View textview_day_4) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Thursday");
    dayNo = 4;
    textView = findViewById(R.id.textview_day_4);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Darkens selected day's textview and changes center_day_textview to corresponding day
   *
   * @param textview_day_5 The View to be modified by clickDay5.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay5(View textview_day_5) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Wednesday");
    dayNo = 5;
    textView = findViewById(R.id.textview_day_5);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Darkens selected day's textview and changes center_day_textview to corresponding day
   *
   * @param textview_day_6 The View to be modified by clickDay6.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay6(View textview_day_6) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Tuesday");
    dayNo = 6;
    textView = findViewById(R.id.textview_day_6);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Darkens selected day's textview and changes center_day_textview to corresponding day
   *
   * @param textview_day_7 The View to be modified by clickDay7.
   */
  @SuppressLint("SetTextI18n")
  public void clickDay7(View textview_day_7) {
    TextView textView = findViewById(R.id.center_day_textview);
    textView.setText("Monday");
    dayNo = 7;
    textView = findViewById(R.id.textview_day_7);
    resetColors();
    textView.setBackgroundResource(R.color.res_green);
  }

  /**
   * Places the user's entered notes in the textview of the selected day
   *
   * @param track_button The View to be modified.
   */
  public void trackClick(View track_button) {
    //change different different textview dependng on what center-day
    EditText notes_edittext = (EditText) findViewById(R.id.notes_edittext);
    if (dayNo == 1) {
      TextView textView = findViewById(R.id.textview_day_1);
      textView.setText(notes_edittext.getText().toString());
    } else if (dayNo == 2) {
      TextView textView = findViewById(R.id.textview_day_2);
      textView.setText(notes_edittext.getText().toString());
    } else if (dayNo == 3) {
      TextView textView = findViewById(R.id.textview_day_3);
      textView.setText(notes_edittext.getText().toString());
    } else if (dayNo == 4) {
      TextView textView = findViewById(R.id.textview_day_4);
      textView.setText(notes_edittext.getText().toString());
    } else if (dayNo == 5) {
      TextView textView = findViewById(R.id.textview_day_5);
      textView.setText(notes_edittext.getText().toString());
    } else if (dayNo == 6) {
      TextView textView = findViewById(R.id.textview_day_6);
      textView.setText(notes_edittext.getText().toString());
    } else if (dayNo == 7) {
      TextView textView = findViewById(R.id.textview_day_7);
      textView.setText(notes_edittext.getText().toString());
    }

  }

  /**
   * Resets all day's textviews to light green. Called before darkening selected textview so only
   * one textview is dark at once.
   */
  public void resetColors() {
    TextView textView = findViewById(R.id.textview_day_1);
    textView.setBackgroundResource(R.color.res_green_transparent);
    textView = findViewById(R.id.textview_day_2);
    textView.setBackgroundResource(R.color.res_green_transparent);
    textView = findViewById(R.id.textview_day_3);
    textView.setBackgroundResource(R.color.res_green_transparent);
    textView = findViewById(R.id.textview_day_4);
    textView.setBackgroundResource(R.color.res_green_transparent);
    textView = findViewById(R.id.textview_day_5);
    textView.setBackgroundResource(R.color.res_green_transparent);
    textView = findViewById(R.id.textview_day_6);
    textView.setBackgroundResource(R.color.res_green_transparent);
    textView = findViewById(R.id.textview_day_7);
    textView.setBackgroundResource(R.color.res_green_transparent);
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

  /**
   * Gets the content string for the calendar day at the given position.
   * @param at Offset from today.  1 would be yesterday.
   * @return String containing list of activities for that day.
   */
  protected String getCalendarBuffer(final int at) {
    return dateViews.get(at).content.getText().toString();
  }

  /**
   * Gets the notes string for all activities on the calendar day at the given position.
   * @param at Offset from today.  1 would be yesterday.
   * @return String containing list of all notes for that day.
   */
  protected String getNotesBuffer(final int at) {
    return dateViews.get(at).notes;
  }

  protected boolean didActivitiesLoad(final int at) {
    return dateViews.get(at).isLoaded();
  }

  /**
   * Change what set of days are being displayed.
   *
   * @param by How many days to add to the rightmost day.  For example, 7 would load the next week.
   */
  protected void cycle(final int by) {
    for (final DateView dv : dateViews) {
      dv.unobserve(this);
    }

    final Calendar rightmost = dateViews.get(0).day;
    rightmost.add(Calendar.DAY_OF_MONTH, by);

    for (final DateView dv : dateViews) {
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

  private final OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
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

    private boolean isLoaded() {
      return loaded;
    }

    private void observe(final LifecycleOwner owner) {
      data = actViewModel.getActivitiesByDate(day.getTime());
      data.observe(owner, this);
      notes = null;
      loaded = false;
    }

    private void unobserve(final LifecycleOwner owner) {
      if (data != null) {
        data.removeObservers(owner);
        data = null;
        notes = null;
      }
    }

    @Override
    public void onChanged(@NonNull final List<UserActivityEntity> activities) {
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
      loaded = true;
    }

    private final TextView title;
    private final TextView content;
    private Calendar day;
    private LiveData<List<UserActivityEntity>> data;
    private String notes;
    private boolean loaded;
  }

  private final ArrayList<DateView> dateViews = new ArrayList<>();
  private BottomNavigationView bottomNavigationView;
  private UserActivityViewModel actViewModel;
  private int dayNo;
}