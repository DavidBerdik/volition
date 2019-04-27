package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

/**
 * Allows users to report on their daily wellness with a rating from 0 through 10. Users are
 * provided instant feedback by a TextView at the bottom of the screen with Verbose output of their
 * current selection.
 */
public class DailyWellnessActivity extends AppCompatActivity {

  /**
   * Retrieves the text stored in dailyWellnessResultsView. Only needed for testing.
   *
   * @return A String object containing the text inside dailyWellnessResultsView.
   */
  public String getDailyWellnessResultsText() {
    return dailyWellnessResultsView.getText().toString();
  }

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

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_daily_wellness_land);
    } else {
      setContentView(R.layout.activity_daily_wellness_port);
    }

    userActivityViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);

    final NumberPicker np = findViewById(R.id.daily_wellness_number_picker);
    np.setMinValue(1);
    np.setMaxValue(10);
    np.setValue(5);
    np.setOnValueChangedListener(onValueChangeListener);

    if (lastKnownValue != -1) {
      np.setValue(lastKnownValue);
    }

    outputs = getResources().getStringArray(R.array.daily_wellness_result_strings);
    dailyWellnessResultsView = findViewById(R.id.daily_wellness_results);
    dailyWellnessResultsView.setText(getWellnessString(np.getValue()));

    final Button button = findViewById(R.id.daily_wellness_button);
    button.setOnClickListener(submitButtonListener);
  }

  private String getWellnessString(final int rating) {
    lastKnownValue = rating;
    return getResources().getString(R.string.daily_wellness_rating) + " " + outputs[rating - 1];
  }

  private OnValueChangeListener onValueChangeListener = new OnValueChangeListener() {

    @Override
    public void onValueChange(final NumberPicker np, final int oldVal, final int newVal) {
      dailyWellnessResultsView.setText(getWellnessString(np.getValue()));
    }
  };


  private OnClickListener submitButtonListener = new OnClickListener() {
    @Override
    public void onClick(final View v) {
      final int rating = lastKnownValue;

      final UserActivityEntity entity = new UserActivityEntity();
      entity.setDate(new Date());
      entity.setDesc(String.format(getString(R.string.daily_wellness_activity_desc), rating));

      userActivityViewModel.insertActivity(entity);

      final Toast toast = Toast
          .makeText(getApplicationContext(), R.string.daily_wellness_toast, Toast.LENGTH_LONG);
      toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
      toast.show();
    }
  };

  private UserActivityViewModel userActivityViewModel;
  private TextView dailyWellnessResultsView;
  private String[] outputs;
  private static int lastKnownValue = -1;
  public static int numberCompleted;
}
