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

  public void setSelectedItem(CoreNavigationPage page) {
    switch (page) {
      case PAGE_HOME:
        this.bottomNavigationView.setSelectedItemId(R.id.core_navigation_home);
      case PAGE_ACTIVITY:
        this.bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
      case PAGE_PLAN:
        this.bottomNavigationView.setSelectedItemId(R.id.core_navigation_plan);
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
