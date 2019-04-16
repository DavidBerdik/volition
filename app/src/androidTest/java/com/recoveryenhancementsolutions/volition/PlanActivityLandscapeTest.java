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
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
/**
 * Unit test for all the UI elements in the Plan Activity screen in landscape mode
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlanActivityLandscapeTest {

    @Rule
    public ActivityTestRule<PlanActivity> mActivityTestRule = new ActivityTestRule<>(PlanActivity.class);
    //tests textviews in landscape mode
    @Test
    public void planActivityLandscapeTest() {
        //taps rightmost textview
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textview_day_1), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_7),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                6)),
                                1)));
        appCompatTextView.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.textview_day_2), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_6),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                5)),
                                1)));
        appCompatTextView2.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.textview_day_5), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView3.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.textview_day_3), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_5),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                4)),
                                1)));
        appCompatTextView4.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.textview_day_4), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_4),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                3)),
                                1)));
        appCompatTextView5.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.textview_day_7), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_1),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1)));
        appCompatTextView6.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView7 = onView(
                allOf(withId(R.id.textview_day_5), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_3),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                1)));
        appCompatTextView7.perform(scrollTo(), click());
        //taps textview to the left of the previous textview
        ViewInteraction appCompatTextView8 = onView(
                allOf(withId(R.id.textview_day_6), withText("Visit 1 Visit 1"),
                        childAtPosition(
                                allOf(withId(R.id.vertical_layout_2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1)));
        appCompatTextView8.perform(scrollTo(), click());
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
