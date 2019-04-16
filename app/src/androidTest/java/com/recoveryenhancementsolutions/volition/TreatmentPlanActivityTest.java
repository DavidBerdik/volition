package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Calendar;
import java.util.Date;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test for TreatmentPlan Activity.
 */
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanActivityTest {

  @Rule
  public final ActivityTestRule<TreatmentPlanActivity> activityTestRule = new ActivityTestRule<>(
      TreatmentPlanActivity.class);

  /**
   * Loads the ViewModel and sets it to use a temporary database for testing
   */
  @Before
  public void loadViewModel() {
    //Set the ViewModel to use a test database instead of the app's real database
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();


    activityTestRule.getActivity().onCreateTest(db);

    //Fill in treatment plan
    TreatmentPlanEntity newTreatmentPlan = new TreatmentPlanEntity();
    newTreatmentPlan.setNumCounseling(3);
    newTreatmentPlan.setNumSupportMeeting(3);
    newTreatmentPlan.setNumLessons(2);
    newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
    newTreatmentPlan.setNumOutcomeMeasures(3);
    newTreatmentPlan.setNumTimeTracking(2);
    newTreatmentPlan.setNumReadingResponse(2);
    newTreatmentPlan.setMedManagementMonthly();
    newTreatmentPlan.setOutcomeMeasureDaily();
    newTreatmentPlan.setNumMedManagement(0);
    Date date = Calendar.getInstance().getTime();
    long time = date.getTime() - (1000 * 60 * 60 * 24);
    date.setTime(time);
    newTreatmentPlan.setCoolDownTime(2);
    newTreatmentPlan.setLastUpdate(date);

    TreatmentPlanViewModel.insertTreatmentPlan(newTreatmentPlan, db);

    // Allow the app time to update.
    try {
      Thread.sleep(3000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Closes the db after tests
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Checks that all buttons work properly and verifies the values of all TextViews.
   */
  @Test
  public void testTreatmentPlanViewModel() {

    ViewInteraction button = onView(
        allOf(withId(R.id.addCounselButton), isDisplayed()));
    button.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button2 = onView(
        allOf(withId(R.id.addMedManagementButton), isDisplayed()));
    button2.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button3 = onView(
        allOf(withId(R.id.addSupportGroupMeetingButton), isDisplayed()));
    button3.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button4 = onView(
        allOf(withId(R.id.addOutcomeMeasureButton), isDisplayed()));
    button4.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button5 = onView(
        allOf(withId(R.id.addLessonPlannerButton), isDisplayed()));
    button5.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button6 = onView(
        allOf(withId(R.id.addTreatmentEffectivnessAssessmentButton), isDisplayed()));
    button6.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button7 = onView(
        allOf(withId(R.id.addCleanTimeTrackingButton), isDisplayed()));
    button7.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button8 = onView(
        allOf(withId(R.id.addReadResponceJournalButton), isDisplayed()));
    button8.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button9 = onView(
        allOf(withId(R.id.subCounselButton), isDisplayed()));
    button9.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button10 = onView(
        allOf(withId(R.id.subMedMangaementButton), isDisplayed()));
    button10.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button11 = onView(
        allOf(withId(R.id.subSupportGroupButton), isDisplayed()));
    button11.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button12 = onView(
        allOf(withId(R.id.subOutcomeMeasureButton), isDisplayed()));
    button12.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button13 = onView(
        allOf(withId(R.id.subLessonPlannerButton), isDisplayed()));
    button13.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button14 = onView(
        allOf(withId(R.id.subTreatmentEffectivenessAssessmentButton), isDisplayed()));
    button14.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button15 = onView(
        allOf(withId(R.id.subCleanTimeTrackingButton), isDisplayed()));
    button15.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button16 = onView(
        allOf(withId(R.id.subReadResponceJournalButton), isDisplayed()));
    button16.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button17 = onView(
        allOf(withId(R.id.updateButton), isDisplayed()));
    button17.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction button18 = onView(
        allOf(withId(R.id.finishButton), isDisplayed()));
    button18.check(matches(isDisplayed()));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView = onView(
        allOf(withId(R.id.readingResponceJournal),
            withText("Read and Response Journal"), isDisplayed()));
    textView.check(matches(withText("Read and Response Journal")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView2 = onView(
        allOf(withId(R.id.cleanTimeTracking), withText("Clean Time Tracking"),
            isDisplayed()));
    textView2.check(matches(withText("Clean Time Tracking")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView3 = onView(
        allOf(withId(R.id.treatmentEffectivenessAssessment),
            withText("Treatment Plan Effectiveness Assessment"), isDisplayed()));
    textView3.check(matches(withText("Treatment Plan Effectiveness Assessment")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView4 = onView(
        allOf(withId(R.id.lessonPlanner), withText("Lesson Planner"), isDisplayed()));
    textView4.check(matches(withText("Lesson Planner")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView5 = onView(
        allOf(withId(R.id.outcomeMeasure), withText("Outcome Measures per Day"),
            isDisplayed()));
    textView5.check(matches(withText("Outcome Measures per Day")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView6 = onView(
        allOf(withId(R.id.supportGroupMeeting), withText("Support Meeting"),
            isDisplayed()));
    textView6.check(matches(withText("Support Meeting")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView7 = onView(
        allOf(withId(R.id.medManagement), withText("Medication Management per Month"),
            isDisplayed()));
    textView7.check(matches(withText("Medication Management per Month")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView8 = onView(
        allOf(withId(R.id.counseling), withText("Counseling"), isDisplayed()));
    textView8.check(matches(withText("Counseling")));

    // Allow the app time to update.
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    ViewInteraction textView9 = onView(
        allOf(withId(R.id.counselingView), withText("3"), isDisplayed()));
    textView9.check(matches(withText("3")));

    ViewInteraction textView10 = onView(
        allOf(withId(R.id.medManagementView), withText("0"), isDisplayed()));
    textView10.check(matches(withText("0")));

    ViewInteraction textView11 = onView(
        allOf(withId(R.id.supportMeetingView), withText("3"), isDisplayed()));
    textView11.check(matches(withText("3")));

    ViewInteraction textView12 = onView(
        allOf(withId(R.id.outcomeMeasureView), withText("3"), isDisplayed()));
    textView12.check(matches(withText("3")));

    ViewInteraction textView13 = onView(
        allOf(withId(R.id.lessonView), withText("2"), isDisplayed()));
    textView13.check(matches(withText("2")));

    ViewInteraction textView14 = onView(
        allOf(withId(R.id.treatmentEffectiveView), withText("1"), isDisplayed()));
    textView14.check(matches(withText("1")));

    ViewInteraction textView15 = onView(
        allOf(withId(R.id.timeTrackingView), withText("2"), isDisplayed()));
    textView15.check(matches(withText("2")));

    ViewInteraction textView16 = onView(
        allOf(withId(R.id.readingResponseView), withText("2"), isDisplayed()));
    textView16.check(matches(withText("2")));

    ViewInteraction textView17 = onView(
        allOf(withId(R.id.counselingView), withText("3"), isDisplayed()));
    textView17.check(matches(withText("3")));

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.addCounselButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                8)));
    appCompatButton.perform(scrollTo(), click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.addMedManagementButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                19)));
    appCompatButton2.perform(scrollTo(), click());

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.addSupportGroupMeetingButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                16)));
    appCompatButton3.perform(scrollTo(), click());

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.addOutcomeMeasureButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                32)));
    appCompatButton4.perform(scrollTo(), click());

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.addLessonPlannerButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                33)));
    appCompatButton5.perform(scrollTo(), click());

    ViewInteraction appCompatButton6 = onView(
        allOf(withId(R.id.addTreatmentEffectivnessAssessmentButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                31)));
    appCompatButton6.perform(scrollTo(), click());

    ViewInteraction appCompatButton7 = onView(
        allOf(withId(R.id.addCleanTimeTrackingButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                30)));
    appCompatButton7.perform(scrollTo(), click());

    ViewInteraction appCompatButton8 = onView(
        allOf(withId(R.id.addReadResponceJournalButton), withText("+"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                29)));
    appCompatButton8.perform(scrollTo(), click());

    ViewInteraction appCompatButton9 = onView(
        allOf(withId(R.id.subCounselButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                13)));
    appCompatButton9.perform(scrollTo(), click());

    ViewInteraction appCompatButton10 = onView(
        allOf(withId(R.id.subMedMangaementButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                12)));
    appCompatButton10.perform(scrollTo(), click());

    ViewInteraction appCompatButton11 = onView(
        allOf(withId(R.id.subSupportGroupButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                24)));
    appCompatButton11.perform(scrollTo(), click());

    ViewInteraction appCompatButton12 = onView(
        allOf(withId(R.id.subOutcomeMeasureButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                25)));
    appCompatButton12.perform(scrollTo(), click());

    ViewInteraction appCompatButton13 = onView(
        allOf(withId(R.id.subLessonPlannerButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                11)));
    appCompatButton13.perform(scrollTo(), click());

    ViewInteraction appCompatButton14 = onView(
        allOf(withId(R.id.subTreatmentEffectivenessAssessmentButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                26)));
    appCompatButton14.perform(scrollTo(), click());

    ViewInteraction appCompatButton15 = onView(
        allOf(withId(R.id.subCleanTimeTrackingButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                27)));
    appCompatButton15.perform(scrollTo(), click());

    ViewInteraction appCompatButton16 = onView(
        allOf(withId(R.id.subReadResponceJournalButton), withText("-"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                28)));
    appCompatButton16.perform(scrollTo(), click());

    ViewInteraction appCompatButton17 = onView(
        allOf(withId(R.id.updateButton), withText("update"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                1)));
    appCompatButton17.perform(scrollTo(), click());
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

  private VolitionDatabase db;
}