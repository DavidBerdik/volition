package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
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

@LargeTest
@RunWith(AndroidJUnit4.class)
/**
 * Test class to test the input validation in the ProfileActivity
 */
public class ProfileActivityValidationTest {

  @Rule
  public ActivityTestRule<ProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      ProfileActivity.class);

  @Test
  public void profileActivityValidationTest() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton.perform(scrollTo(), click());

    ViewInteraction appCompatEditText = onView(
        allOf(withId(R.id.name),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                1)));
    appCompatEditText.perform(scrollTo(), replaceText("jon"), closeSoftKeyboard());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton2.perform(scrollTo(), click());

    ViewInteraction appCompatEditText2 = onView(
        allOf(withId(R.id.date_of_birth),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                2)));
    appCompatEditText2.perform(scrollTo(), click());

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton3.perform(scrollTo(), click());

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton4.perform(scrollTo(), click());

    ViewInteraction appCompatEditText3 = onView(
        allOf(withId(R.id.clean_date),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                11)));
    appCompatEditText3.perform(scrollTo(), click());

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton5.perform(scrollTo(), click());

    ViewInteraction appCompatButton6 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton6.perform(scrollTo(), click());

    ViewInteraction appCompatSpinner = onView(
        allOf(withId(R.id.gender_spinner),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                3)));
    appCompatSpinner.perform(scrollTo(), click());

    DataInteraction appCompatTextView = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(1);
    appCompatTextView.perform(click());

    ViewInteraction appCompatButton7 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton7.perform(scrollTo(), click());

    ViewInteraction appCompatSpinner2 = onView(
        allOf(withId(R.id.use_type_spinner),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                10)));
    appCompatSpinner2.perform(scrollTo(), click());

    DataInteraction appCompatTextView2 = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(1);
    appCompatTextView2.perform(click());

    ViewInteraction appCompatButton8 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton8.perform(scrollTo(), click());

    ViewInteraction appCompatRadioButton = onView(
        allOf(withId(R.id.radioSupport), withText("Family or Support Person"),
            childAtPosition(
                allOf(withId(R.id.user_type),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        5)),
                0)));
    appCompatRadioButton.perform(scrollTo(), click());

    ViewInteraction appCompatButton9 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton9.perform(scrollTo(), click());

    ViewInteraction appCompatRadioButton2 = onView(
        allOf(withId(R.id.radioOther), withText("Other"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        7)),
                10)));
    appCompatRadioButton2.perform(scrollTo(), click());

    ViewInteraction appCompatButton10 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton10.perform(scrollTo(), click());

    ViewInteraction appCompatEditText4 = onView(
        allOf(withId(R.id.enter_other),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                8)));
    appCompatEditText4.perform(scrollTo(), click());

    ViewInteraction appCompatEditText5 = onView(
        allOf(withId(R.id.enter_other),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                8)));
    appCompatEditText5.perform(scrollTo(), replaceText("other"), closeSoftKeyboard());

    ViewInteraction appCompatButton11 = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                12)));
    appCompatButton11.perform(scrollTo(), click());
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
