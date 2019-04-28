package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Uses the automated Android Studio Espresso recorder to test the CoreNavigationHandler.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class CoreNavigationHandlerTest {

  @Rule
  public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * The test that checks for all buttons of CoreNavigationHandler to be working.
   */
  @Test
  public void coreNavigationHandlerTest() {
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), HomeActivity.class.getName());

    ViewInteraction bottomNavigationItemView = onView(
        allOf(withId(R.id.core_navigation_plan), withContentDescription("Plan"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), PlanActivity.class.getName());

    pressBack();

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), HomeActivity.class.getName());

    ViewInteraction bottomNavigationItemView2 = onView(
        allOf(withId(R.id.core_navigation_profile), withContentDescription("Profile"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                3),
            isDisplayed()));
    bottomNavigationItemView2.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), ProfileActivity.class.getName());

    pressBack();

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), HomeActivity.class.getName());

    ViewInteraction bottomNavigationItemView3 = onView(
        allOf(withId(R.id.core_navigation_activity), withContentDescription("Activity"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView3.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), ActivityActivity.class.getName());

    pressBack();

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(), HomeActivity.class.getName());
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
