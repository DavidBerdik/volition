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
   * Assigns a BottomNavigationView object to the NavigationItemSelectedListener, provided a given
   * context to build new intents with.
   *
   * @param view A BottomNavigationView that should be represented by the core_navigation menu.
   * @param context The context of the parent activity that will be used to create new intents.
   */
  public static void link(final BottomNavigationView view, final Context context) {
    // Create an internal OnNavigationItemSelectedListener.
    // NOTE: Having it outside this method generated a local-use warning from Android Studio.
    view.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
        final int id = item.getItemId();

        // Prevent us from trying to restart the same activity we're already looking at.
        if (id == view.getSelectedItemId()) {
          return false;
        }

        final Intent destination = new Intent();

        switch (item.getItemId()) {
          case R.id.core_navigation_home:
            destination.setClass(context, HomeActivity.class);
            break;
          case R.id.core_navigation_activity:
            // TODO: Update to reflect proper activity once it is added.
            destination.setClass(context, ReportUseActivity.class);
            break;
          case R.id.core_navigation_plan:
            destination.setClass(context, PlanActivity.class);
            break;
        }

        destination.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(destination);
        return true;
      }
    });
  }
}
