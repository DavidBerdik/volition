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
 * This is a test for the medication dosage activity
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MedicationDosageActivityTest {

  /**
   * Create object for testing
   */
  @Rule
  public ActivityTestRule<MedicationDosageActivity> mActivityTestRule = new ActivityTestRule<>(
      MedicationDosageActivity.class);

  /**
   * Creates the actual test for the dosage activity
   */
  @Test
  public void medicationDosageActivityTest() {
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
        .atPosition(1);
    appCompatTextView.perform(click());

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.confirmDosage), withText("Confirm"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    appCompatButton.perform(click());
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
