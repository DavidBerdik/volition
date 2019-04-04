package com.recoveryenhancementsolutions.volition;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.view.MenuItem;

/**
 * The CoreNavigationHandler is a general purpose class intended to be used by multiple activites
 * provided they all feature the core_navigation menu at the bottom of the device screen.
 */
public class CoreNavigationHandler {

  /**
   * Pages that can be represented by the CoreNavigationHandler.
   */
  public enum CoreNavigationPage {
    /**
     * Represents the HomeActivity.java class.
     */
    PAGE_HOME,

    /**
     * Represents the ActivityActivity.java class.
     */
    PAGE_ACTIVITY,

    /**
     * Represents the PlanActivity.java class.
     */
    PAGE_PLAN
  }

  /**
   * Constructor for the CoreNavigationHandler.
   *
   * @param view A BottomNavigationView that should be represented by the core_navigation menu.
   * @param context The context of the parent activity that will be used to create new intents.
   */
  public CoreNavigationHandler(final BottomNavigationView view, final Context context) {
    bottomNavigation = view;

    // Create an internal OnNavigationItemSelectedListener.
    // NOTE: Having it outside this method generated a local-use warning from Android Studio.
    bottomNavigation.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
        int id = item.getItemId();

        // Prevent us from trying to restart the same activity we're already looking at.
        if (id == bottomNavigation.getSelectedItemId()) {
          return false;
        }

        switch (item.getItemId()) {
          case R.id.core_navigation_home:
            context.startActivity(new Intent(context, HomeActivity.class));
          case R.id.core_navigation_activity:
            // TODO: Update to reflect proper activity once it is added.
            context.startActivity(new Intent(context, ReportUseActivity.class));
          case R.id.core_navigation_plan:
            context.startActivity(new Intent(context, PlanActivity.class));
        }

        return true;
      }
    });
  }

  /**
   * Sets the selected item ID of the BottomNavigationView object. This will also disable that
   * specific buttom from being pressed again until changed off.
   *
   * @param page A CoreNavigationPage enum representing the page to highlight.
   */
  public void setFocusedItem(CoreNavigationPage page) {
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
