package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
/**
 * The first test for the radio buttons on the UI
 */
public class RadioButtonTest1 {

  @Rule
  public ActivityTestRule<CreateProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      CreateProfileActivity.class);

  @Test
  /**
   * This radio button test checks for the presence of the Family or Support Person button, presses
   * it, and verifies the remainder radio buttons existence
   */
  public void radioButtonTest1() {
    ViewInteraction appCompatRadioButton = onView(
        allOf(withId(R.id.radioSupport), withText("Family or Support Person"),
            childAtPosition(
                allOf(withId(R.id.user_type),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        4)),
                0)));
    appCompatRadioButton.perform(scrollTo(), click());

    ViewInteraction radioButton = onView(
        allOf(withId(R.id.radioHeroin),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                0),
            isDisplayed()));
    radioButton.check(matches(isDisplayed()));

    ViewInteraction radioButton2 = onView(
        allOf(withId(R.id.radioOpiates),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                1),
            isDisplayed()));
    radioButton2.check(matches(isDisplayed()));

    ViewInteraction radioButton3 = onView(
        allOf(withId(R.id.radioAlcohol),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                2),
            isDisplayed()));
    radioButton3.check(matches(isDisplayed()));

    ViewInteraction radioButton4 = onView(
        allOf(withId(R.id.radioCocaine),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                3),
            isDisplayed()));
    radioButton4.check(matches(isDisplayed()));

    ViewInteraction radioButton5 = onView(
        allOf(withId(R.id.radioMarijuana),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                4),
            isDisplayed()));
    radioButton5.check(matches(isDisplayed()));

    ViewInteraction radioButton6 = onView(
        allOf(withId(R.id.radioMeth),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                5),
            isDisplayed()));
    radioButton6.check(matches(isDisplayed()));

    ViewInteraction radioButton7 = onView(
        allOf(withId(R.id.radioBen),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                6),
            isDisplayed()));
    radioButton7.check(matches(isDisplayed()));

    ViewInteraction radioButton8 = onView(
        allOf(withId(R.id.radioTranquilizers),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                7),
            isDisplayed()));
    radioButton8.check(matches(isDisplayed()));

    ViewInteraction radioButton9 = onView(
        allOf(withId(R.id.radioTranquilizers),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                7),
            isDisplayed()));
    radioButton9.check(matches(isDisplayed()));
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
