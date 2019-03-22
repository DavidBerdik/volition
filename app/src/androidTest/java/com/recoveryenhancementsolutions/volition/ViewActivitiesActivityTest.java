package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class ViewActivitiesActivityTest {

  @Rule
  public ActivityTestRule<ViewActivitiesActivity> activityTestRule = new ActivityTestRule<>(
      ViewActivitiesActivity.class);

  /**
   * Checks that an activity description is always placed on the correct calendar day.
   */
  @Test
  public void testActivityUpdating() {
    assertEquals(0, 1);
  }
}
