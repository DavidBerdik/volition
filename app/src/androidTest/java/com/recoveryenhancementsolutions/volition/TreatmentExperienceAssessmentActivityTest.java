package com.recoveryenhancementsolutions.volition;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import com.recoveryenhancementsolutions.volition.utilities.NumberPickerTestUtility;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;


    @RunWith(AndroidJUnit4.class)
    public class TreatmentExperienceAssessmentActivityTest {

        @Rule
        public ActivityTestRule<TreatmentExperienceAssessmentActivity> activityTestRule = new ActivityTestRule<>(
                TreatmentExperienceAssessmentActivity.class);

        /**
         * Tests the functionality of the NumberPicker and its live updates.
         */
        @Test
        public void treatmentExperienceActivityTest() {
            // Confirm the default page setting.
            assertEquals("Your choice: 5 (Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to ten and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 10, activityTestRule);
            assertEquals("Your choice: 10 (Much Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to one and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 1, activityTestRule);
            assertEquals("Your choice: 1 (None or not much)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to six and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 6, activityTestRule);
            assertEquals("Your choice: 6 (Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to seven and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 7, activityTestRule);
            assertEquals("Your choice: 7 (Much Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to two and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 2, activityTestRule);
            assertEquals("Your choice: 2 (None or not much)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to eight and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 8, activityTestRule);
            assertEquals("Your choice: 8 (Much Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to three and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 3, activityTestRule);
            assertEquals("Your choice: 3 (None or not much)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to four and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 4, activityTestRule);
            assertEquals("Your choice: 4 (Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Scroll to nine and test.
            NumberPickerTestUtility.selectValue(R.id.tea_number_picker, 9, activityTestRule);
            assertEquals("Your choice: 9 (Much Better)",
                    activityTestRule.getActivity().getTeaResultsText());

            // Click to the next assessment question.
            onView(withId(R.id.submit_button_tea)).perform(click());
            assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
                    TreatmentExperienceAssessmentActivity.class.getName());
            onView(withId(R.id.submit_button_tea)).perform(click());
            assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
                    TreatmentExperienceAssessmentActivity.class.getName());
            onView(withId(R.id.submit_button_tea)).perform(click());
            assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
                    TreatmentExperienceAssessmentActivity.class.getName());
            onView(withId(R.id.submit_button_tea)).perform(click());

            // Allow the slower devices/emulators to update.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Check that we're on the TreatmentExperienceAssessmentRemarksActivity class.
            assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
                    TreatmentExperienceAssessmentRemarksActivity.class.getName());
        }

}