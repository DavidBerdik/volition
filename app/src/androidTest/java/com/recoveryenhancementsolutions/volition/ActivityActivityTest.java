package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.internal.runner.junit4.statement.UiThreadStatement;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityActivityTest {

  @Rule
  public ActivityTestRule<ActivityActivity> activityTestRule = new ActivityTestRule<>(
      ActivityActivity.class);

  @Before
  public void initDB() {
    // Set the ViewModel to use a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    activityTestRule.getActivity().getViewModel().setTestDatabase(db);

    final Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.MILLISECOND, 0);

    for (String desc : userActivityDesc) {
      activityTestRule.getActivity().getViewModel().insertActivity(today.getTime(), desc);
      today.add(Calendar.DAY_OF_MONTH, -1);
    }

    try {
      UiThreadStatement.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          activityTestRule.getActivity().cycle(0);
        }
      });
    } catch (Throwable t) {
      throw new AssertionError("Could not prepare test: " + t.getMessage());
    }
  }

  /**
   * Verifies that each calendar day is displaying the correct activity.
   */
  @Test
  public void testCalendar() {
    for (int i = 0; i < activityTestRule.getActivity().getDayCount(); i++) {
      while (!activityTestRule.getActivity().didActivitiesLoad(i)) {
      }

      String value = activityTestRule.getActivity().getActivityBuffer(i);

      Log.i(logTag, "Label " + i + "; expect:\"" + userActivityDesc[i]
          + "\" got:\"" + value + '"');

      Assert.assertEquals(userActivityDesc[i], value);
    }
  }

  private final String logTag = "ActivityActivityTest";
  private final String[] userActivityDesc = {
      "Test 1",
      "Test 2",
      "Test 3",
      "Test 4",
      "Test 5",
      "Test 6",
      "Test 7"
  };
  private VolitionDatabase db;
}
