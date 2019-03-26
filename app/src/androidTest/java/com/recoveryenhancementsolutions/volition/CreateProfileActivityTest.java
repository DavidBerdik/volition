package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
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
import java.util.Calendar;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateProfileActivityTest {

  @Rule
  public ActivityTestRule<CreateProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      CreateProfileActivity.class);

  @Test
  public void createProfileActivityTest() {
    // Test opening the Date of Birth date picker
    ViewInteraction appCompatEditText = onView(
        allOf(withId(R.id.date_of_birth),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                1)));
    appCompatEditText.perform(scrollTo(), click());

    // Test opening of year selection in the Date of Birth date picker
    ViewInteraction appCompatTextView = onView(
        allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")),
            withText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.LinearLayout")),
                    0),
                0),
            isDisplayed()));
    appCompatTextView.perform(click());

    // Test year scrolling and choosing in the Date of Birth date picker
    DataInteraction appCompatTextView2 = onData(anything())
        .inAdapterView(allOf(withClassName(is("android.widget.YearPickerView")),
            childAtPosition(
                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                1)))
        .atPosition(96);
    appCompatTextView2.perform(scrollTo(), click());

    // Test 3 uses of the "Next Month" button in the Date of Birth date picker
    for (int x = 0; x < 3; x++) {
      ViewInteraction appCompatImageButton = onView(
          allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")),
              withContentDescription("Next month"),
              childAtPosition(
                  allOf(withClassName(is("android.widget.DayPickerView")),
                      childAtPosition(
                          withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                          0)),
                  2)));
      appCompatImageButton.perform(scrollTo(), click());
    }

    // Test clicking the "OK" button in the Date of Birth date picker
    ViewInteraction appCompatButton = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton.perform(scrollTo(), click());

    // Test opening the Last Date Clean date picker
    ViewInteraction appCompatEditText2 = onView(
        allOf(withId(R.id.clean_date),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                10)));
    appCompatEditText2.perform(scrollTo(), click());

    // Test 5 uses of the "Previous Month" button in the Last Date Clean date picker
    for (int x = 0; x < 5; x++) {
      ViewInteraction appCompatImageButton4 = onView(
          allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")),
              withContentDescription("Previous month"),
              childAtPosition(
                  allOf(withClassName(is("android.widget.DayPickerView")),
                      childAtPosition(
                          withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                          0)),
                  1)));
      appCompatImageButton4.perform(scrollTo(), click());
    }

    // Test clicking the "OK" button in the Last Date Clean date picker
    ViewInteraction appCompatButton2 = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton2.perform(scrollTo(), click());
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
