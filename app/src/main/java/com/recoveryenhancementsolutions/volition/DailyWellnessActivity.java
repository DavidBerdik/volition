package com.recoveryenhancementsolutions.volition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class DailyWellnessActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daily_wellness);

    final NumberPicker np = findViewById(R.id.daily_wellness_number_picker);
    np.setMinValue(1);
    np.setMaxValue(10);
    np.setValue(5);
    np.setOnValueChangedListener(onValueChangeListener);

    outputs = getResources().getStringArray(R.array.daily_wellness_result_strings);
    dailyWellnessResultsView = findViewById(R.id.daily_wellness_results);
    dailyWellnessResultsView.setText(getWellnessString(np.getValue()));
  }

  private String getWellnessString(final int rating) {
    return getResources().getString(R.string.daily_wellness_rating) + " " + outputs[rating - 1];
  }

  private OnValueChangeListener onValueChangeListener = new OnValueChangeListener() {

    @Override
    public void onValueChange(final NumberPicker np, final int oldVal, final int newVal) {
      dailyWellnessResultsView.setText(getWellnessString(np.getValue()));
    }
  };

  private TextView dailyWellnessResultsView;
  private String[] outputs;
}
