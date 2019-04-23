package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
<<<<<<< HEAD
 * This class will run Espresso Test on the Questionnaire Activity
 * It tests the questionnaire activity. It goes to the questionnaire activity and tests the
 * yes and no buttons to answer each question.
 *  This class will run test on the QuestionnaireActivity to test that the buttons have proper
 *  functionality.
=======
 * This class will run test on the QuestionnaireActivity to test that the buttons have proper
 * functionality.
>>>>>>> ad84d8445f8e6353b29fb9c57415431e9a786277
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class QuestionnaireActivityTest {

  @Rule
  public ActivityTestRule<QuestionnaireActivity> activityTestRule = new ActivityTestRule<>(
      QuestionnaireActivity.class);

  /**
<<<<<<< HEAD
   * Performs test to check that the Questionnaire Activity run to go through each question the Yes and No buttons
=======
   * Loads the ViewModel and sets it to use a temporary, in-memory database for testing.
   */
  @Before
  public void loadViewModel() {
    // Load the ViewModel
    final QuestionnaireActivityViewModel viewModel = ViewModelProviders
        .of(activityTestRule.getActivity())
        .get(QuestionnaireActivityViewModel.class);

    // Set the ViewModel to use a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    viewModel.setTestDatabase(db);
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
>>>>>>> ad84d8445f8e6353b29fb9c57415431e9a786277
   * This espresso test will run through the QuestionnaireActivity. The Yes and No buttons are
   * tested to check the functionality for each question. At the last question the
   * QuestionnaireActivity will go to the ViewSeverityLevelActivity.
   */
  @Test
  public void questionnaireActivityTest2() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton2.perform(click());

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                14),
            isDisplayed()));
    appCompatButton3.perform(click());

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton4.perform(click());

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton5.perform(click());

    ViewInteraction appCompatButton6 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton6.perform(click());

    ViewInteraction appCompatButton7 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton7.perform(click());

    ViewInteraction appCompatButton8 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton8.perform(click());

    ViewInteraction appCompatButton9 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                14),
            isDisplayed()));
    appCompatButton9.perform(click());

    pressBack();

    ViewInteraction appCompatButton10 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton10.perform(click());

    ViewInteraction appCompatButton11 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                13),
            isDisplayed()));
    appCompatButton11.perform(click());

    ViewInteraction appCompatButton12 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                14),
            isDisplayed()));
    appCompatButton12.perform(click());
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
