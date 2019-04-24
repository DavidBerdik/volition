package com.recoveryenhancementsolutions.volition;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.view.MenuItem;

/**
 * The ActivityNavigationHandler is a general purpose class intended to be used by multiple activities
 * provided they all feature the activity_back_navigation menu at the bottom of the device screen.
 */
public class ActivityNavigationHandler {

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
    view.setSelected(false);
    view.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
        context.startActivity(new Intent(context, ActivityActivity.class));
        return true;
      }
    });
  }
}
