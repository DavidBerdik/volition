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
import static android.support.test.espresso.Espresso.pressBack;
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
 * Unit test for all the UI elements in the Plan Activity screen in portrait mode
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlanActivityTest {

    @Rule
    public ActivityTestRule<PlanActivity> mActivityTestRule = new ActivityTestRule<>(PlanActivity.class);
        //tests elements in the UI such as the textviews, scrollviews, and drop down menu
    @Test
    public void planActivityPortraitTest() {
        //make sure test starts with portrait layout
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //tests by tapping textview that is on the far right of the horizontal scrollview
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textview_day_1), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_7),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                6)),
                                1)));
        appCompatTextView.perform(scrollTo(), click());
        //tests by tapping textview to the left of the previous textview
        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.textview_day_2), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_6),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                5)),
                                1)));
        appCompatTextView2.perform(scrollTo(), click());
        //tests by tapping textview to the left of the previous textview
        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.textview_day_3), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_5),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                4)),
                                1)));
        appCompatTextView3.perform(scrollTo(), click());
        //tests by tapping textview to the left of the previous textview
        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.textview_day_4), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_4),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                3)),
                                1)));
        appCompatTextView4.perform(scrollTo(), click());
        //tests by tapping textview to the left of the previous textview
        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.textview_day_5), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView5.perform(scrollTo(), click());
        //tests by tapping textview to the left of the previous textview
        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.textview_day_6), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatTextView6.perform(scrollTo(), click());
        //tests by tapping textview to the left of the previous textview
        ViewInteraction appCompatTextView7 = onView(
                allOf(withId(R.id.textview_day_7), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_1),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1)));
        appCompatTextView7.perform(scrollTo(), click());
        //opens spinner and select an option
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());
        //open spinner and select another option from the spinner
        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());
        //opens keyboard by tapping on edittext box and add some text
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.notes_edittext),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(click());
        appCompatEditText.perform(replaceText("abcd"), closeSoftKeyboard());
        //press the button "TRACK"
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.track_button), withText("TRACK"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());
        //change orientation of the app to landscape to test landscape layout
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //taps rightmost textview
        ViewInteraction appCompatTextView8 = onView(
                allOf(withId(R.id.textview_day_1), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_7),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                6)),
                                1)));
        appCompatTextView8.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView9 = onView(
                allOf(withId(R.id.textview_day_2), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_6),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                5)),
                                1)));
        appCompatTextView9.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView10 = onView(
                allOf(withId(R.id.textview_day_5), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView10.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView11 = onView(
                allOf(withId(R.id.textview_day_3), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_5),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                4)),
                                1)));
        appCompatTextView11.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView12 = onView(
                allOf(withId(R.id.textview_day_4), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_4),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                3)),
                                1)));
        appCompatTextView12.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView13 = onView(
                allOf(withId(R.id.textview_day_7), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_1),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1)));
        appCompatTextView13.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView14 = onView(
                allOf(withId(R.id.textview_day_5), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView14.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView15 = onView(
                allOf(withId(R.id.textview_day_6), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatTextView15.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.track_button), withText("TRACK")));
        appCompatButton2.perform(scrollTo(), click());
        //open spinner
        ViewInteraction appCompatSpinner3 = onView(withId(R.id.spinner));
        appCompatSpinner3.perform(scrollTo(), click());
        //select first option from spinner
        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView3.perform(scrollTo(), click());

        //open spinner
        ViewInteraction appCompatSpinner4 = onView(withId(R.id.spinner));
        appCompatSpinner4.perform(scrollTo(), click());
        //select second option from spinner
        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView4.perform(scrollTo(), click());

        //open spinner
        ViewInteraction appCompatSpinner5 = onView(withId(R.id.spinner));
        appCompatSpinner5.perform(scrollTo(), click());
        //select third option from spinner
        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView5.perform(scrollTo(), click());
        //type into edittext some text
        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.notes_edittext), isDisplayed()));
        appCompatEditText2.perform(replaceText("dcba"), closeSoftKeyboard());

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
