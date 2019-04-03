package com.recoveryenhancementsolutions.volition;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.view.MenuItem;

public class CoreNavigationHandler {

  public enum CoreNavigationPage {
    PAGE_HOME,
    PAGE_ACTIVITY,
    PAGE_PLAN
  }

  public CoreNavigationHandler(final BottomNavigationView bottomNavigationView) {
    this.bottomNavigationView = bottomNavigationView;
    this.bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
  }

  /**
   * Sets the selected item ID of the BottomNavigationView object.
   *
   * @param page A CoreNavigationPage enum representing the page to highlight.
   */
  public void setSelectedItem(CoreNavigationPage page) {
    switch (page) {
      case PAGE_HOME:
        this.bottomNavigationView.setSelectedItemId(R.id.core_navigation_home);
        break;
      case PAGE_ACTIVITY:
        this.bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
        break;
      case PAGE_PLAN:
        this.bottomNavigationView.setSelectedItemId(R.id.core_navigation_plan);
        break;
    }
  }

  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.core_navigation_home:
          return true;
        case R.id.core_navigation_activity:
          return true;
        case R.id.core_navigation_plan:
          return true;
      }
      return false;
    }
  };

  private final BottomNavigationView bottomNavigationView;
}
