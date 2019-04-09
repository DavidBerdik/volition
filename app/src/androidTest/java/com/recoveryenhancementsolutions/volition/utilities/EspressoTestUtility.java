package com.recoveryenhancementsolutions.volition.utilities;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

import android.app.Activity;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import java.util.Collection;

/**
 * Utility testing class that allows Espresso to retrieve the class of the currently "running" and front Activity.
 */
public class EspressoTestUtility {

  /**
   * Returns the class of the current activity.
   *
   * @return The Activity.
   */
  public static Activity getCurrentActivity() {
    /* Acquired from: https://stackoverflow.com/a/53023272, little modifications made. */

    final Activity[] currentActivity = {null};

    getInstrumentation().runOnMainSync(new Runnable() {
      public void run() {
        final Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
            .getActivitiesInStage(RESUMED);
        if (resumedActivities.iterator().hasNext()) {
          currentActivity[0] = (Activity) resumedActivities.iterator().next();
        }
      }
    });
    return currentActivity[0];
  }

}
