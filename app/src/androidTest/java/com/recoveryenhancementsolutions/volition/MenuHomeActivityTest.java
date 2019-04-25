package com.recoveryenhancementsolutions.volition;


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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MenuHomeActivityTest {

  @Rule
  public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  @Test
  public void menuHomeActivityTest() {
    /**
     * Opens the menu navigation drawer when clicking on the hamburger icon in the top left corner. Once the drawer is open
     * the Edit Profile option is selected. This action closes the drawer because no functionality is added to the buttons
     * in the drawer menu yet.
     */
    ViewInteraction appCompatImageButton = onView(
        allOf(withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.support.design.widget.AppBarLayout")),
                        0)),
                1),
            isDisplayed()));
    appCompatImageButton.perform(click());

    ViewInteraction navigationMenuItemView = onView(
        allOf(childAtPosition(
            allOf(withId(R.id.design_navigation_view),
                childAtPosition(
                    withId(R.id.nav_view),
                    0)),
            1),
            isDisplayed()));
    navigationMenuItemView.perform(click());
    /**
     * Opens the menu navigation drawer when clicking on the hamburger icon in the top left corner. Once the drawer is open
     * the Edit Treatment Plan option is selected. This action closes the drawer because no functionality is added to the
     * buttons in the drawer menu yet.
     */
    ViewInteraction appCompatImageButton2 = onView(
        allOf(withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.support.design.widget.AppBarLayout")),
                        0)),
                1),
            isDisplayed()));
    appCompatImageButton2.perform(click());

    ViewInteraction navigationMenuItemView2 = onView(
        allOf(childAtPosition(
            allOf(withId(R.id.design_navigation_view),
                childAtPosition(
                    withId(R.id.nav_view),
                    0)),
            2),
            isDisplayed()));
    navigationMenuItemView2.perform(click());
    /**
     * Opens the menu navigation drawer when clicking on the hamburger icon in the top left corner. Once the drawer is open
     * the View Classification option is selected. This action closes the drawer because no functionality is added to the
     * buttons in the drawer menu yet.
     */
    ViewInteraction appCompatImageButton3 = onView(
        allOf(withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.support.design.widget.AppBarLayout")),
                        0)),
                1),
            isDisplayed()));
    appCompatImageButton3.perform(click());

    ViewInteraction navigationMenuItemView3 = onView(
        allOf(childAtPosition(
            allOf(withId(R.id.design_navigation_view),
                childAtPosition(
                    withId(R.id.nav_view),
                    0)),
            3),
            isDisplayed()));
    navigationMenuItemView3.perform(click());
    /**
     * Opens the menu navigation drawer when clicking on the hamburger icon in the top left corner. Once the drawer is open
     * the Retake Questionnaire option is selected. This action closes the drawer because no functionality is added to the
     * buttons in the drawer menu yet.
     */
    ViewInteraction appCompatImageButton4 = onView(
        allOf(withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.support.design.widget.AppBarLayout")),
                        0)),
                1),
            isDisplayed()));
    appCompatImageButton4.perform(click());

    ViewInteraction navigationMenuItemView4 = onView(
        allOf(childAtPosition(
            allOf(withId(R.id.design_navigation_view),
                childAtPosition(
                    withId(R.id.nav_view),
                    0)),
            4),
            isDisplayed()));
    navigationMenuItemView4.perform(click());
    /**
     * Opens the menu navigation drawer when clicking on the hamburger icon in the top left corner. Once the drawer is open
     * the View Clinical Overview option is selected. This action closes the drawer because no functionality is added to the
     * buttons in the drawer menu yet.
     */
    ViewInteraction appCompatImageButton5 = onView(
        allOf(withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.support.design.widget.AppBarLayout")),
                        0)),
                1),
            isDisplayed()));
    appCompatImageButton5.perform(click());

    ViewInteraction navigationMenuItemView5 = onView(
        allOf(childAtPosition(
            allOf(withId(R.id.design_navigation_view),
                childAtPosition(
                    withId(R.id.nav_view),
                    0)),
            5),
            isDisplayed()));
    navigationMenuItemView5.perform(click());
    /**
     * Opens the menu navigation drawer when clicking on the hamburger icon in the top left corner. Once the drawer is open
     * the right hand side of the screen is clicked to close the drawer.
     */
    ViewInteraction appCompatImageButton6 = onView(
        allOf(withContentDescription("Open navigation drawer"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.support.design.widget.AppBarLayout")),
                        0)),
                1),
            isDisplayed()));
    appCompatImageButton6.perform(click());
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
