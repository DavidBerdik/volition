package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Performs test on the ViewSeverityLevelViewModel in order to check the values for the Total score
 *  and Specifier values.
 */

public class ViewSeverityLevelViewModelTest {

    @Rule
    public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     * Creates temporary test database.
     */
    @Before
    public void loadViewModel() {
        // Load the ViewModel
        viewModel = ViewModelProviders.of(activityTestRule.getActivity()).get(ViewSeverityLevelViewModel.class);

        // Set the ViewModel to use a test database instead of the app's real database.
        final Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
                .allowMainThreadQueries().build();
        viewModel.getSeverity();
        viewModel.getTotalYesAnswers();
    }

    /**
     *  Performs test for getting the severity level for specifier and total yes answers for total
     *  score.
     */
    @Test
    public void testQuestionnaireActivityViewModel() {
        viewModel.getSeverity();
        viewModel.getTotalYesAnswers();

    }

    private ViewSeverityLevelViewModel viewModel;
    private QuestionnaireActivityViewModel qViewModel;
    private QuestionnaireDao questionnaireActivityDao;
    private VolitionDatabase db;
    private static final String TAG = "ViewSeverityLevelActivityViewModelTest";
}