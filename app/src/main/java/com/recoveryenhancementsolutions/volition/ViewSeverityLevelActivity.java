package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ViewSeverityLevelActivity extends AppCompatActivity {

  private TextView specifier;
  private TextView totalYesTv;

  /**
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_severitylevel);

    specifier = findViewById(R.id.specifier_tv);
    totalYesTv = findViewById(R.id.totalScore_tv);
    Button TPButton = findViewById(R.id.TPButton);

    // Get a reference to the ViewModel for this screen.
    mViewModel = ViewModelProviders.of(this).get(ViewSeverityLevelViewModel.class);
    observeTotalYes();
    // Update the UI whenever there's a change in the ViewModel's data.
    observeSeverityLevel();
  }

  private void observeTotalYes() {
    mViewModel.totalYes.observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String totalYes) {
        showTotalYesInUi(totalYes);
      }
    });
  }

  private void showTotalYesInUi(final @NonNull String totalYes) {
    StringBuilder sb = new StringBuilder();

    sb.append(totalYes);

    totalYesTv.setText(sb.toString());
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

    sb.append(severity);

    specifier.setText(sb.toString());
  }

  private ViewSeverityLevelViewModel mViewModel;


}


