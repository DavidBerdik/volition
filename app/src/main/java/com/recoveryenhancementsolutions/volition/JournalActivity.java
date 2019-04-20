package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

/**
 * THIS IS A DUMMY CLASS
 */
public class JournalActivity extends AppCompatActivity {

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
    setContentView(R.layout.activity_journal);
  }
  public static int numberCompleted;
}
