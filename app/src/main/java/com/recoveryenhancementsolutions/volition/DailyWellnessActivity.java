package com.recoveryenhancementsolutions.volition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class DailyWellnessActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daily_wellness);

    final NumberPicker np = findViewById(R.id.daily_wellness_number_picker);

    np.setMinValue(1);
    np.setMaxValue(10);
  }
}
