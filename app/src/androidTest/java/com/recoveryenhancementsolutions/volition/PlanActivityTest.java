package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.internal.runner.junit4.statement.UiThreadStatement;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import java.util.Calendar;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class PlanActivityTest {

  @Rule
  public ActivityTestRule<PlanActivity> activityTestRule = new ActivityTestRule<>(
      PlanActivity.class);

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

    for (int i = 0; i < userActivityDesc.length; ++i) {
      activityTestRule.getActivity().getViewModel()
          .insertActivity(today.getTime(), userActivityDesc[i], userActivityNotes[i]);
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

      String value = activityTestRule.getActivity().getCalendarBuffer(i);

      Log.i(logTag, "Label " + i + "; expect:\"" + userActivityDesc[i]
          + "\" got:\"" + value + '"');

      Assert.assertEquals(userActivityDesc[i], value);
    }
  }

  /**
   * Verifies that opening a calendar day opens the notes for the correct day.
   */
  @Test
  public void testNoteView() {
    final int ids[] = {
        R.id.textview_day_1,
        R.id.textview_day_2,
        R.id.textview_day_3,
        R.id.textview_day_4,
        R.id.textview_day_5,
        R.id.textview_day_6,
        R.id.textview_day_7
    };

    for (int i = 0; i < ids.length; ++i) {
      Espresso.onView(ViewMatchers.withId(ids[i]))
          .perform(ViewActions.scrollTo(), ViewActions.click());
      Espresso.onView(ViewMatchers.withText(activityTestRule.getActivity().getNotesBuffer(i)))
          .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
      Espresso.pressBack();
    }
  }

  private final String logTag = "PlanActivityTest";
  private final String[] userActivityDesc = {
      "Test 1",
      "Test 2",
      "Test 3",
      "Test 4",
      "Test 5",
      "Test 6",
      "Test 7"
  };
  private final String[] userActivityNotes = {
      "Note 1",
      "Note 2",
      "Note 3",
      "Note 4",
      "Note 5",
      "Note 6",
      "Note 7"
  };
  private VolitionDatabase db;
}
