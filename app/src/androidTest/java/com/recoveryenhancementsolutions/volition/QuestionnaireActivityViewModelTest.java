package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * This class will run test on the QuestionnaireActivtyViewModel. It will check to see that the
 * answers are stored properly into the database.
 */
public class QuestionnaireActivityViewModelTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Creates a  temporary test database.
   */
  @Before
  public void loadViewModel() {
    // Load the ViewModel
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
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
   * Performs test for inserting questionnaire answers into test database.
   */

  @Test
  public void testQuestionnaireActivityViewModel() {
    setTestArray();
    viewModel.addQuestionnaire(db, questionnaireAnswers, 0, "Mild");
  }

  /**
   * Fills array to send to view model
   */
  public void setTestArray() {
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
  }

  private ArrayList<Boolean> questionnaireAnswers = new ArrayList<>();
  private QuestionnaireActivityViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "QuestionnaireActivityViewModelTest";
}