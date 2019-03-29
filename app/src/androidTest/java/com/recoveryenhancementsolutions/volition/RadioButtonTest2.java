package com.recoveryenhancementsolutions.volition;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.recoveryenhancementsolutions.volition.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RadioButtonTest2 {

  @Rule
  public ActivityTestRule<CreateProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      CreateProfileActivity.class);

  @Test
  public void createProfileActivityTest2temp() {
    ViewInteraction appCompatRadioButton = onView(
        allOf(withId(R.id.radioClient), withText("Person in Recovery/Seeking Recovery"),
            childAtPosition(
                allOf(withId(R.id.user_type),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        4)),
                1)));
    appCompatRadioButton.perform(scrollTo(), click());

    ViewInteraction appCompatRadioButton2 = onView(
        allOf(withId(R.id.radioSedatives), withText("Barbiturates, Sedatives, or Hypnotics"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                8)));
    appCompatRadioButton2.perform(scrollTo(), click());

    ViewInteraction radioButton = onView(
        allOf(withId(R.id.radioInhalants),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                9),
            isDisplayed()));
    radioButton.check(matches(isDisplayed()));

    ViewInteraction button = onView(
        allOf(withId(R.id.record_button),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                11)));
    button.perform(scrollTo());

    ViewInteraction button2 = onView(
        allOf(withId(R.id.record_button),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                11),
            isDisplayed()));
    button2.check(matches(isDisplayed()));
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