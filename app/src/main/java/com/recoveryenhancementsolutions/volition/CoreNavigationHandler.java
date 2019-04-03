package com.recoveryenhancementsolutions.volition;

import android.content.Context;
import android.content.Intent;
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

  public CoreNavigationHandler(final BottomNavigationView view, final Context context) {
    bottomNavigation = view;

    // Create an internal OnNavigationItemSelectedListener.
    // NOTE: Having it outside this method generated a local-use warning from Android Studio.
    bottomNavigation.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == bottomNavigation.getSelectedItemId()) {
          return false;
        }

        switch (item.getItemId()) {
          case R.id.core_navigation_home:
            context.startActivity(new Intent(context, HomeActivity.class));
            return true;
          case R.id.core_navigation_activity:
            context.startActivity(new Intent(context, ReportUseActivity.class));
            return true;
          case R.id.core_navigation_plan:
            context.startActivity(new Intent(context, PlanActivity.class));
            return true;
        }
        return false;
      }
    });
  }

  /**
   * Sets the selected item ID of the BottomNavigationView object.
   *
   * @param page A CoreNavigationPage enum representing the page to highlight.
   */
  public void setSelectedItem(CoreNavigationPage page) {
    switch (page) {
      case PAGE_HOME:
        this.bottomNavigation.setSelectedItemId(R.id.core_navigation_home);
        break;
      case PAGE_ACTIVITY:
        this.bottomNavigation.setSelectedItemId(R.id.core_navigation_activity);
        break;
      case PAGE_PLAN:
        this.bottomNavigation.setSelectedItemId(R.id.core_navigation_plan);
        break;
    }
  }

  private final BottomNavigationView bottomNavigation;
}
