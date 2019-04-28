package com.recoveryenhancementsolutions.volition;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
  public ActivityTestRule<ProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      ProfileActivity.class);

  /**
   * Test the use of the radio buttons on the Profile Activity.
   */
  @Test
  public void createProfileActivityTest2temp() {
    onView(withId(R.id.radioClient)).perform(scrollTo(), click());

    onView(withId(R.id.radioSedatives)).perform(scrollTo(), click());

    onView(withId(R.id.radioInhalants)).perform(scrollTo());

    onView(withId(R.id.radioInhalants)).check(matches(isDisplayed()));

    onView(withId(R.id.record_button)).perform(scrollTo());

    onView(withId(R.id.record_button)).check(matches(isDisplayed()));
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