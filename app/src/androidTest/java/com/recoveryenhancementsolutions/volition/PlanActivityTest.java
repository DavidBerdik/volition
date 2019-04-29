package com.recoveryenhancementsolutions.volition;


import android.content.pm.ActivityInfo;
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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
/**
 * Unit test for all the UI elements in the plan screen
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlanActivityTest {

    @Rule
    public ActivityTestRule<PlanActivity> mActivityTestRule = new ActivityTestRule<>(PlanActivity.class);
    //Tests everything in the plan screen
    @Test
    public void planActivityTest() {

        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //tap textview on the far right of scrollview
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textview_day_1),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_7),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                6)),
                                1)));
        appCompatTextView.perform(scrollTo(), click());
        //tap done to close popup
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.plan_note_view_done), withText("Done"),
                        childAtPosition(
                                allOf(withId(R.id.instructions_view),
                                        childAtPosition(
                                                withId(R.id.ScrollView01),
                                                0)),
                                1)));
        appCompatButton.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.textview_day_2),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_6),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                5)),
                                1)));
        appCompatTextView2.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.textview_day_3),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_5),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                4)),
                                1)));
        appCompatTextView3.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.textview_day_4),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_4),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                3)),
                                1)));
        appCompatTextView4.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.textview_day_5),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView5.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.textview_day_6),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatTextView6.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView7 = onView(
                allOf(withId(R.id.textview_day_7),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_1),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1)));
        appCompatTextView7.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton.perform(scrollTo(), click());
        //opens spinner by tapping it then select 1 option
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());
        //opens spinner and select another option
        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView2.perform(click());
        //open spinner and select third option
        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView3.perform(click());
        //type in "test" into edittext and close the keyboard
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.notes_edittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());
        //press the Track button
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.track_button), withText("TRACK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());
        //sets orientation to landscape to test landscape UI
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //tap textview on the far right of scrollview
        ViewInteraction appCompatTextView8 = onView(
                allOf(withId(R.id.textview_day_1),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_7),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                6)),
                                1)));
        appCompatTextView8.perform(scrollTo(), click());
        //tap done to close popup
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.plan_note_view_done), withText("Done"),
                        childAtPosition(
                                allOf(withId(R.id.instructions_view),
                                        childAtPosition(
                                                withId(R.id.ScrollView01),
                                                0)),
                                1)));
        appCompatButton3.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView9 = onView(
                allOf(withId(R.id.textview_day_2),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_6),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                5)),
                                1)));
        appCompatTextView9.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton3.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView10 = onView(
                allOf(withId(R.id.textview_day_3),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_5),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                4)),
                                1)));
        appCompatTextView10.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton3.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView11 = onView(
                allOf(withId(R.id.textview_day_4),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_4),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                3)),
                                1)));
        appCompatTextView11.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton3.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView12 = onView(
                allOf(withId(R.id.textview_day_5),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView12.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton3.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView13 = onView(
                allOf(withId(R.id.textview_day_6),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatTextView13.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton3.perform(scrollTo(), click());
        //tap to open textview on the left of previous
        ViewInteraction appCompatTextView14 = onView(
                allOf(withId(R.id.textview_day_7),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_1),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1)));
        appCompatTextView14.perform(scrollTo(), click());
        //tap done to close popup
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner4 = onView(withId(R.id.spinner));
        appCompatSpinner4.perform(scrollTo(), click());
        //select first option from spinner
        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView4.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner5 = onView(withId(R.id.spinner));
        appCompatSpinner5.perform(scrollTo(), click());
        //select second option from spinner
        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView5.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner6 = onView(withId(R.id.spinner));
        appCompatSpinner6.perform(scrollTo(), click());
        //select third option from spinner
        DataInteraction appCompatCheckedTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView6.perform(scrollTo(), click());
        //opens edittext and type in "Test for Landscape" into the box and close keyboard
        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.notes_edittext), isDisplayed()));
        appCompatEditText2.perform(replaceText("Test For Landscape"), closeSoftKeyboard());
        //tap the track button
        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.track_button), withText("TRACK")));
        appCompatButton4.perform(scrollTo(), click());
        
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
