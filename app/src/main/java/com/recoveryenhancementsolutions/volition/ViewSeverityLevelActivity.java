package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;




public class ViewSeverityLevelActivity extends AppCompatActivity {


  /**
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.severity_activity);
    mSeverityTextView = findViewById(R.id.severe_tv);

    // Get a reference to the ViewModel for this screen.
    mViewModel = ViewModelProviders.of(this).get(ViewSeverityLevelViewModel.class);

    // Update the UI whenever there's a change in the ViewModel's data.
    observeSeverityLevel();
  }

  /**
   *
   */
  private void observeSeverityLevel() {
    mViewModel.severity.observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String severity) {
        showSeverityLevelInUi(severity);
      }
    });
  }

  private void showSeverityLevelInUi(final @NonNull String severity) {
    StringBuilder sb = new StringBuilder();

    sb.append("Severity Level:");

    sb.append(severity);
    sb.append("\n");

    mSeverityTextView.setText(sb.toString());
  }
  private ViewSeverityLevelViewModel mViewModel;

  private TextView mSeverityTextView;
}


