package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import java.util.Calendar;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateProfileActivityTest {

  @Rule
  public ActivityTestRule<ProfileActivity> activityTestRule = new ActivityTestRule<>(
      ProfileActivity.class);

  /**
   * Creates a temporary, in-memory database to use for testing the edit profile activity.
   */
  @Before
  public void createTestEnvironment() {
    // Set the activity to test mode.
    activityTestRule.getActivity().setTestMode(db);
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Performs tests that are related to the user interface in the "Create Profile" activity.
   */
  @Test
  public void createProfileUiTests() {
    // Test setting a name in the "Name" EditText.
    onView(withId(R.id.name)).perform(scrollTo(), click());

    // Test setting a date in the "Date of Birth" EditText. Note that this test enters a date
    // directly in the field and does not make use of the date picker.
    onView(withId(R.id.date_of_birth))
        .perform(scrollTo(), replaceText("Sep 9, 1989"), closeSoftKeyboard());

    // Test opening the gender dropdown menu.
    onView(withId(R.id.gender_spinner)).perform(scrollTo(), click());

    // Test selecting the "Other" option from the gender dropdown menu.
    DataInteraction appCompatTextView = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(3);
    appCompatTextView.perform(click());

    // Test selecting the role of the person completing the form. In this test, the
    // "Person in Recovery/Seeking Recovery" option is chosen.
    onView(allOf(withId(R.id.radioClient), withText("Person in Recovery/Seeking Recovery")))
        .perform(scrollTo(), click());

    // Test selecting the drug of choice. In this test, the "Other Opiates and Synthetics" option
    // is chosen.
    onView(allOf(withId(R.id.radioOpiates), withText("Other Opiates and Synthetics")))
        .perform(scrollTo(), click());

    // Test changing the drug of choice selection to "Other."
    onView(allOf(withId(R.id.radioOther), withText("Other"))).perform(scrollTo(), click());

    // Test setting the name in the custom other drug EditText to "Other drug".
    onView(withId(R.id.enter_other))
        .perform(scrollTo(), replaceText("Other drug"), closeSoftKeyboard());

    // Test opening the Substance Use Disorder dropdown menu.
    onView(withId(R.id.use_type_spinner)).perform(scrollTo(), click());

    // Test selecting "Opioid Use Disorder" from the Substance Use Disorder dropdown menu.
    DataInteraction appCompatTextView2 = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(1);
    appCompatTextView2.perform(click());

    // Test reopening the Substance Use Disorder dropdown menu.
    onView(withId(R.id.use_type_spinner)).perform(scrollTo(), click());

    // Test selecting "Alcohol Use Disorder" from the Substance Use Disorder dropdown menu.
    DataInteraction appCompatTextView3 = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(2);
    appCompatTextView3.perform(click());

    // Test setting a date in the "Date of Last Use" EditText. Note that this test enters
    // a date directly in the field and does not make use of the date picker.
    onView(withId(R.id.clean_date))
        .perform(scrollTo(), replaceText("Jan 1, 2019"), closeSoftKeyboard());

    // Test tapping on the "Record Answers" button.
    onView(withId(R.id.record_button)).perform(scrollTo(), click());
  }

  /**
   * Performs tests that are related to the date pickers in the "Create Profile" activity.
   */
  @Test
  public void createProfileActivityDatePickerTests() {
    // Test opening the Date of Birth date picker
    onView(withId(R.id.date_of_birth)).perform(scrollTo(), click());

    // Test opening of year selection in the Date of Birth date picker
    ViewInteraction appCompatTextView = onView(
        allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")),
            withText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.LinearLayout")),
                    0),
                0),
            isDisplayed()));
    appCompatTextView.perform(click());

    // Test year scrolling and choosing in the Date of Birth date picker
    DataInteraction appCompatTextView2 = onData(anything())
        .inAdapterView(allOf(withClassName(is("android.widget.YearPickerView")),
            childAtPosition(
                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                1)))
        .atPosition(96);
    appCompatTextView2.perform(scrollTo(), click());

    // Test 3 uses of the "Next Month" button in the Date of Birth date picker
    for (int x = 0; x < 3; x++) {
      ViewInteraction appCompatImageButton = onView(
          allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")),
              withContentDescription("Next month"),
              childAtPosition(
                  allOf(withClassName(is("android.widget.DayPickerView")),
                      childAtPosition(
                          withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                          0)),
                  2)));
      appCompatImageButton.perform(scrollTo(), click());
    }

    // Test clicking the "OK" button in the Date of Birth date picker
    ViewInteraction appCompatButton = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton.perform(scrollTo(), click());

    // Test opening the Last Date Used date picker
    onView(withId(R.id.clean_date)).perform(scrollTo(), click());

    // Test 5 uses of the "Previous Month" button in the Last Date Used date picker
    for (int x = 0; x < 5; x++) {
      ViewInteraction appCompatImageButton4 = onView(
          allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")),
              withContentDescription("Previous month"),
              childAtPosition(
                  allOf(withClassName(is("android.widget.DayPickerView")),
                      childAtPosition(
                          withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                          0)),
                  1)));
      appCompatImageButton4.perform(scrollTo(), click());
    }

    // Test clicking the "OK" button in the Last Date Used date picker
    ViewInteraction appCompatButton2 = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton2.perform(scrollTo(), click());

    // Test setting the "Date of Birth" EditText to January 19, 2038
    onView(withId(R.id.date_of_birth)).perform(scrollTo(), click());
    onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
        .perform(PickerActions.setDate(2038, 1, 19));
    onView(withId(android.R.id.button1)).perform(click());
    onView(withId(R.id.date_of_birth)).check(matches(withText("Jan 19, 2038")));

    // Test setting the "Last Date Used" EditText to March 14, 2015
    onView(withId(R.id.clean_date)).perform(scrollTo(), click());
    onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
        .perform(PickerActions.setDate(2015, 3, 14));
    onView(withId(android.R.id.button1)).perform(click());
    onView(withId(R.id.clean_date)).check(matches(withText("Mar 14, 2015")));

    //Testing if the button calls QuestionnaireActivity
    onView(allOf(withId(R.id.record_button), withText("Record Answers")))
        .perform(scrollTo(), click());
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

  final private VolitionDatabase db = Room
      .inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), VolitionDatabase.class)
      .allowMainThreadQueries().build();

}