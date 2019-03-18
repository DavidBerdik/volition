package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Class for running only activity_display_profile.xml
 */
public class DisplayProfileActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_profile);
  }

}
