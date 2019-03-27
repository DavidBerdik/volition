package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;




public class ViewSeverityLevelActivity extends AppCompatActivity {

  final TextView specifier = (TextView) findViewById(R.id.specifier_tv);
  final TextView totalYesTv = (TextView) findViewById(R.id.totalScore_tv);
  /**
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_severitylevel);


    observeTotalYes();


    // Get a reference to the ViewModel for this screen.
    mViewModel = ViewModelProviders.of(this).get(ViewSeverityLevelViewModel.class);

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

    //sb.append("Severity Level:");

    totalYesTv.append(totalYes);
    //sb.append("\n");

    //mViewModel.setText(sb.toString());
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

    //sb.append("Severity Level:");

    specifier.append(severity);
    //sb.append("\n");

  //mViewModel.setText(specifier.toString());
  }
  private ViewSeverityLevelViewModel mViewModel;

  private TextView mSeverityTextView;
}


