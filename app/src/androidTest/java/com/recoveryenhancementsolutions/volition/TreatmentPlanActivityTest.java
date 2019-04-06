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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanActivityTest {

    @Rule
    public ActivityTestRule<TreatmentPlanActivity> mActivityTestRule = new ActivityTestRule<>(TreatmentPlanActivity.class);

    /**
     * Test to ensure all data displayed on screen is accurate with database
     */
    @Test
    public void treatmentPlanActivityTest() {
        /**
         * Verifies textview for couselingview
         */
        ViewInteraction textView = onView(allOf(withId(R.id.counselingView), withText("3"),isDisplayed()));
        textView.check(matches(withText("3")));

        /**
         * Verifies textview for medManagementView
         */
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.medManagementView), withText("2"),isDisplayed()));
        textView2.check(matches(withText("2")));

        /**
         * Verifies textview for supportMeetingView
         */
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.supportMeetingView), withText("3"),isDisplayed()));
        textView3.check(matches(withText("3")));

        /**
         * Verifies textview for outcomeMeasureView
         */
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.outcomeMeasureView), withText("3"),isDisplayed()));
        textView4.check(matches(withText("3")));

        /**
         * Verifies textview for lessonView
         */
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.lessonView), withText("2"),isDisplayed()));
        textView5.check(matches(withText("2")));

        /**
         * Verifies textview for treatmentEffectivenessView
         */
        ViewInteraction textView6 = onView(
                allOf(withId(R.id.treatmentEffectiveView), withText("1"),isDisplayed()));
        textView6.check(matches(withText("1")));

        /**
         * Verifies textview for timeTrackingView
         */
        ViewInteraction textView7 = onView(
                allOf(withId(R.id.timeTrackingView), withText("2"),isDisplayed()));
        textView7.check(matches(withText("2")));

        /**
         * Verifies textview for readingResponceView
         */
        ViewInteraction textView8 = onView(
                allOf(withId(R.id.readingResponseView), withText("2"),isDisplayed()));
        textView8.check(matches(withText("2")));
    }
}
