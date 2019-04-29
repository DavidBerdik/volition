package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import com.recoveryenhancementsolutions.volition.utilities.NumberPickerTestUtility;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class TreatmentExperienceAssessmentActivityTest {

  @Rule
  public ActivityTestRule<TreatmentExperienceAssessmentActivity> activityTestRule = new ActivityTestRule<>(
      TreatmentExperienceAssessmentActivity.class);

  @Before
  public void initDB() {
    //Set the ViewModel to use a test database instead of the app's real database
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();
    //VolitionDatabase.setTestDatabase(InstrumentationRegistry.getTargetContext());
    activityTestRule.getActivity().getViewModel().setTestDatabase(db);
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Tests the functionality of the NumberPicker and its live updates. Tests the submit button
   * functionality. Runs Test for the remarks activity and the plan screen to show the TEA has been
   * completed.
   */
  @Test
  public void treatmentExperienceActivityTest() {
    // Confirm the default page setting.
    assertEquals("Your choice: 5 (Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to ten and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 10, activityTestRule);
    assertEquals("Your choice: 10 (Much Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to one and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 1, activityTestRule);
    assertEquals("Your choice: 1 (None or not much)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to six and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 6, activityTestRule);
    assertEquals("Your choice: 6 (Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to seven and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 7, activityTestRule);
    assertEquals("Your choice: 7 (Much Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to two and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 2, activityTestRule);
    assertEquals("Your choice: 2 (None or not much)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to eight and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 8, activityTestRule);
    assertEquals("Your choice: 8 (Much Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to three and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 3, activityTestRule);
    assertEquals("Your choice: 3 (None or not much)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to four and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 4, activityTestRule);
    assertEquals("Your choice: 4 (Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Scroll to nine and test.
    NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 9, activityTestRule);
    assertEquals("Your choice: 9 (Much Better)",
        activityTestRule.getActivity().getTeaResultsText());

    // Click to the next assessment question.
    onView(withId(R.id.submit_button_tea)).perform(click());
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        TreatmentExperienceAssessmentActivity.class.getName());
    onView(withId(R.id.submit_button_tea)).perform(click());
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        TreatmentExperienceAssessmentActivity.class.getName());
    onView(withId(R.id.submit_button_tea)).perform(click());
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        TreatmentExperienceAssessmentActivity.class.getName());
    onView(withId(R.id.submit_button_tea)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Insert text into the remarks section.
    ViewInteraction appCompatEditText = onView(
        allOf(withId(R.id.remarksText),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                3),
            isDisplayed()));
    appCompatEditText.perform(replaceText("THIS IS A TEST!"));
    //Test submit button.
    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.submitButton), withText("Submit"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                4),
            isDisplayed()));
    appCompatButton5.perform(click());
    /*
    The following code is commented out because it will not succeed in most cases but it is important
    that this part of the code is tested so i will now explain how to test it manually.
    To test this you can run the actual TEA activity then navigate to the plan screen and it should
    show TEA Completed in the current day.  The reason this does not work is because this test stores
    to a test database but the plan activity when it runs will run from the actual database therefore
    it will not read TEA Completed.
     */
   /* // Navigate to plan screen.
    ViewInteraction bottomNavigationItemView = onView(
        allOf(withId(R.id.core_navigation_plan), withContentDescription("Plan"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView.perform(click());
    //Test to see that the plan screen shows that the TEA has been completed.
    ViewInteraction textView = onView(
        allOf(withId(R.id.textview_day_1), withText("TEA completed"),
            childAtPosition(
                allOf(withId(R.id.vertical_layout_7),
                    childAtPosition(
                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                        6)),
                1),
            isDisplayed()));
    textView.check(matches(withText("TEA completed")));
    //while(true) {}*/
  }

  private static Matcher<View> childAtPosition(
      final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
            && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };


  }

  private VolitionDatabase db;
}