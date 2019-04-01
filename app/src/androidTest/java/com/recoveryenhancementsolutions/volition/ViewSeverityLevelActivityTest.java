package com.recoveryenhancementsolutions.volition;


import static org.junit.Assert.assertEquals;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Performs a test on the ViewSeverityLevelActivity
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
/**
 * Tests bringing up activity and total score and specifier values.
 */
public class ViewSeverityLevelActivityTest {

  @Rule
  public ActivityTestRule<ViewSeverityLevelActivity> mActivityTestRule = new ActivityTestRule<>(
      ViewSeverityLevelActivity.class);

  @Test
  public void viewSeverityLevelActivityTest() {



  }
  private ViewSeverityLevelActivity viewSeverityLevelActivity;
  private String TAG = "ViewSeverityLevelActivityTest";

}
