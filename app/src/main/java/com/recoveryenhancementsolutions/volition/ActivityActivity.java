package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

/**
 * Skeleton activity, will be filled in by another group/task at a later date.
 */
public class ActivityActivity extends AppCompatActivity {

  /**
   * Restores the CoreNavigationHandler to it's default state for this page.
   */
  @Override
  public void onResume()
  {
    super.onResume();
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity);

    bottomNavigationView = findViewById(R.id.core_navigation);
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
    CoreNavigationHandler.link(bottomNavigationView, this);
  }

  private BottomNavigationView bottomNavigationView;
}
