package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Date;

/**
 * The starting Activity for the application. Allows the user to select a "Create Profile" button.
 */
public class StartupLandingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_startup_landing_land);
    } else {
      setContentView(R.layout.activity_startup_landing_port);
    }

    Log.e("Startup", "Running in the 90s");
    context = this;
    ddViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);
    ddViewModel.getLastCleanDate().observe(this, dateObserver);
    try{
      Thread.sleep(1000);
    }
    catch(InterruptedException e){
      Thread.currentThread().interrupt();
    }

    if (d != null){
      Log.e("Startup",ddViewModel.getLastCleanDate().getValue().toString());
      redirect();
    }
    else {

      final Button createProfileButton = findViewById(R.id.start_landing_button_create);
      createProfileButton.setOnClickListener(createProfileButtonListener);
    }
  }

  private Observer<Date> dateObserver = new Observer<Date>() {
    @Override
    public void onChanged(final Date date) {
      try {
        d = date;
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    }
  };
  private OnClickListener createProfileButtonListener = new OnClickListener() {

    @Override
    public void onClick(final View view) {
      startActivity(new Intent(context, ProfileActivity.class));
    }
  };

  /**
   * Redirects to the Home Activity
   */
  private void redirect(){
    startActivity(new Intent(context, HomeActivity.class));
  }
  private Context context;
  private DemographicDataViewModel ddViewModel;
  private Date d;
}
