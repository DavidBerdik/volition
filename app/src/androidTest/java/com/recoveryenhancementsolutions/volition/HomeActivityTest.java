package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
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
public class HomeActivityTest {

  @Rule
  public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  @Test
  public void homeActivityTest2() {
    ViewInteraction bottomNavigationItemView = onView(
        allOf(withId(R.id.menubar_activity), withContentDescription("Activity"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.menubar),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView.perform(click());

    ViewInteraction textView = onView(
        allOf(withId(R.id.buttonTestItem), withText("Activity"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                2),
            isDisplayed()));
    textView.check(matches(withText("Activity")));

    ViewInteraction bottomNavigationItemView2 = onView(
        allOf(withId(R.id.menubar_plan), withContentDescription("Plan"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.menubar),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView2.perform(click());

    ViewInteraction textView2 = onView(
        allOf(withId(R.id.buttonTestItem), withText("Plan"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                2),
            isDisplayed()));
    textView2.check(matches(withText("Plan")));

    ViewInteraction bottomNavigationItemView3 = onView(
        allOf(withId(R.id.menubar_home), withContentDescription("Home"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.menubar),
                    0),
                0),
            isDisplayed()));
    bottomNavigationItemView3.perform(click());

    ViewInteraction textView3 = onView(
        allOf(withId(R.id.buttonTestItem), withText("Home"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                2),
            isDisplayed()));
    textView3.check(matches(withText("Home")));

    ViewInteraction bottomNavigationItemView4 = onView(
        allOf(withId(R.id.menubar_activity), withContentDescription("Activity"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.menubar),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView4.perform(click());

    ViewInteraction textView4 = onView(
        allOf(withId(R.id.buttonTestItem), withText("Activity"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                2),
            isDisplayed()));
    textView4.check(matches(withText("Activity")));
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
