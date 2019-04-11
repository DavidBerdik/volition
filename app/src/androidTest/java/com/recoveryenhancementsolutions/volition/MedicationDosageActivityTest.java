package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This tests if a dosage selection works after a medication is chosen
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MedicationDosageActivityTest {

  /**
   * Creates the test object
   */
  @Rule
  public ActivityTestRule<MedicationChoiceActivity> mActivityTestRule = new ActivityTestRule<>(
      MedicationChoiceActivity.class);

  /**
   * Creates the test for the activity
   * The test starts by choosing the medication as it is required for dosing. After choosing dosing
   * Then the test checks that the medication was entered correctly and then add the dose to the table
   */
  @Test
  public void medicationDosageActivityTest() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.medication), withText("Buprenorphine"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                0),
            isDisplayed()));
    appCompatButton.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction appCompatSpinner = onView(
        allOf(withId(R.id.dosage_spinner),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                0),
            isDisplayed()));
    appCompatSpinner.perform(click());

    DataInteraction appCompatTextView = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(3);
    appCompatTextView.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.confirmDosage), withText("Confirm"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    appCompatButton2.perform(click());


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
}
