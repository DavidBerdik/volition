package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MedicationDosageActivityTest {

  /**
   * Creates tht test object
   */
  @Rule
  public ActivityTestRule<MedicationChoiceActivity> mActivityTestRule = new ActivityTestRule<>(
      MedicationChoiceActivity.class);

  /**
   * Tests that the buttons, text, and content all match what they are intended to say and do
   */
  @Test
  public void medicationDosageActivityTest() {
    onView(withId(R.id.textViewMed)).check(matches(isDisplayed()));
    onView(withId(R.id.textViewMed)).check(matches(withText("Would you like to take Buprenorphine or abstain?")));

    onView(withId(R.id.medication)).check(matches(isDisplayed()));

    onView(withId(R.id.abstain)).check(matches(isDisplayed()));

    onView(withId(R.id.medication)).perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    onView(withId(R.id.dosage_message)).check(matches(withText("You have chosen to take Buprenorphine. How many doses a day would you like to take?")));

    onView(withId(R.id.confirmDosage)).check(matches(isDisplayed()));

    onView(withId(R.id.dosage_spinner)).check(matches(isDisplayed()));

    onView(withId(R.id.dosage_spinner)).perform(click());

    DataInteraction appCompatTextView = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(3);
    appCompatTextView.perform(click());

    onView(withId(android.R.id.text1)).check(matches(withText("4")));

    onView(withId(R.id.confirmDosage)).perform(click());
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
