package com.recoveryenhancementsolutions.volition;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

    context = this;

    final Button createProfileButton = findViewById(R.id.start_landing_button_create);
    createProfileButton.setOnClickListener(createProfileButtonListener);
  }

  private OnClickListener createProfileButtonListener = new OnClickListener() {

    @Override
    public void onClick(final View view) {
      startActivity(new Intent(context, CreateProfileActivity.class));
    }
  };

  private Context context;
}
