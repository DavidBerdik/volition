package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
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
import com.recoveryenhancementsolutions.volition.views.CreateProfileActivity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Auto-generated test. Tests activity_create_profile.xml
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateProfileTest {

  @Rule
  public ActivityTestRule<CreateProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      CreateProfileActivity.class);

  @Test
  public void createProfileTest() {
    ViewInteraction appCompatEditText = onView(
        allOf(withId(R.id.name),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                0)));
    appCompatEditText.perform(scrollTo(), replaceText("Name"), closeSoftKeyboard());

    ViewInteraction appCompatEditText2 = onView(
        allOf(withId(R.id.name), withText("Name"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                0)));
    appCompatEditText2.perform(pressImeActionButton());

    ViewInteraction appCompatEditText3 = onView(
        allOf(withId(R.id.date_of_birth),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                1)));
    appCompatEditText3.perform(scrollTo(), replaceText("9/9/89"), closeSoftKeyboard());

    ViewInteraction appCompatSpinner = onView(
        allOf(withId(R.id.gender_spinner),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                2)));
    appCompatSpinner.perform(scrollTo(), click());

    DataInteraction appCompatTextView = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(3);
    appCompatTextView.perform(click());

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
        allOf(withId(R.id.radioOpiates), withText("Other Opiates and Synthetics"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                1)));
    appCompatRadioButton2.perform(scrollTo(), click());

    ViewInteraction appCompatRadioButton3 = onView(
        allOf(withId(R.id.radioOther), withText("Other"),
            childAtPosition(
                allOf(withId(R.id.drug_selection),
                    childAtPosition(
                        withId(R.id.RelativeLayout01),
                        6)),
                10)));
    appCompatRadioButton3.perform(scrollTo(), click());

    ViewInteraction appCompatEditText4 = onView(
        allOf(withId(R.id.enter_other),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                7)));
    appCompatEditText4.perform(scrollTo(), replaceText("Other drug"), closeSoftKeyboard());

    ViewInteraction appCompatSpinner2 = onView(
        allOf(withId(R.id.use_type_spinner),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                9)));
    appCompatSpinner2.perform(scrollTo(), click());

    DataInteraction appCompatTextView2 = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(1);
    appCompatTextView2.perform(click());

    ViewInteraction appCompatSpinner3 = onView(
        allOf(withId(R.id.use_type_spinner),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                9)));
    appCompatSpinner3.perform(scrollTo(), click());

    DataInteraction appCompatTextView3 = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
            0))
        .atPosition(2);
    appCompatTextView3.perform(click());

    ViewInteraction appCompatEditText5 = onView(
        allOf(withId(R.id.clean_date),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                10)));
    appCompatEditText5.perform(scrollTo(), replaceText("1/1/19"), closeSoftKeyboard());

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.record_button), withText("Record Answers"),
            childAtPosition(
                allOf(withId(R.id.RelativeLayout01),
                    childAtPosition(
                        withId(R.id.LinearLayout01),
                        0)),
                11)));
    appCompatButton.perform(scrollTo(), click());
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
