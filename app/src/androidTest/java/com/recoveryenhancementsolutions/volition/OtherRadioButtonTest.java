package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OtherRadioButtonTest {

  @Rule
  public ActivityTestRule<StartupLandingActivity> mActivityTestRule = new ActivityTestRule<>(
      StartupLandingActivity.class);

  @Test
  public void otherRadioButtonTest() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.start_landing_button_create), withText("Create Profile"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
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

    ViewInteraction appCompatRadioButton = onView(
        allOf(withId(R.id.radioOther), withText("Other"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        7)),
                10)));
    appCompatRadioButton.perform(scrollTo(), click());

    ViewInteraction appCompatRadioButton2 = onView(
        allOf(withId(R.id.radioOther), withText("Other"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        7)),
                10)));
    appCompatRadioButton2.perform(scrollTo(), click());

    ViewInteraction appCompatRadioButton3 = onView(
        allOf(withId(R.id.radioOther), withText("Other"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        7)),
                10)));
    appCompatRadioButton3.perform(scrollTo(), click());

    ViewInteraction appCompatEditText = onView(
        allOf(withId(R.id.enter_other),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                8)));
    appCompatEditText.perform(scrollTo(), replaceText("sfsfssf"), closeSoftKeyboard());

    ViewInteraction appCompatEditText2 = onView(
        allOf(withId(R.id.enter_other), withText("sfsfssf"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                8)));
    appCompatEditText2.perform(pressImeActionButton());
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
