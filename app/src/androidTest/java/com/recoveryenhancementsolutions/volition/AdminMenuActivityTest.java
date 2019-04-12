package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AdminMenuActivityTest {

  @Rule
  public ActivityTestRule<AdminMenu> activityTestRule = new ActivityTestRule<>(
      AdminMenu.class);

  @Test
  public void navigateToProfile() {
    onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
    onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.edit_profile));
  }

  @Test
  public void navigateEditTreatment() {
    onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
    onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.edit_treatment));
  }
  @Test
  public void navigateClassification() {
    onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
    onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.ques_class));
  }
  @Test
  public void navigateRetake() {
    onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
    onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.retake_ques));
  }
  @Test
  public void navigateOverview() {
    onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
    onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.clinical_overview));
  }
}
