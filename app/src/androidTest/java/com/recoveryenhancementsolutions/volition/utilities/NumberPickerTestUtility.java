package com.recoveryenhancementsolutions.volition.utilities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.app.Activity;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.widget.NumberPicker;

/**
 * Allows Espresso to interact with the NumberPicker widget in Android.
 */
public class NumberPickerTestUtility {
  /* Method from: https://spin.atomicobject.com/2017/10/10/android-numberpicker-espresso/ */

  /**
   * Forced a NumberPicker with a specific Android ID to move to a targeted value.
   *
   * @param id The object ID of the NumbePicker.
   * @param target The target number to move to.
   * @param activityTestRule The controlling ActivityTestRule.
   */
  public static void selectValue(final int id, final int target,
      final ActivityTestRule activityTestRule) {
    final int ROWS_PER_SWIPE = 5;
    final NumberPicker np = activityTestRule.getActivity().findViewById(id);
    final ViewInteraction vi = onView(withId(id));

    while (target != np.getValue()) {
      final int delta = Math.abs(target - np.getValue());

      if (target < np.getValue()) {
        if (delta >= ROWS_PER_SWIPE) {
          vi.perform(new GeneralSwipeAction(Swipe.FAST, GeneralLocation.TOP_CENTER,
              GeneralLocation.BOTTOM_CENTER, Press.FINGER));
        } else {
          vi.perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.TOP_CENTER, Press.FINGER,
              InputDevice.SOURCE_TOUCHSCREEN, MotionEvent.TOOL_TYPE_FINGER));
        }
      } else {
        if (delta >= ROWS_PER_SWIPE) {
          vi.perform(new GeneralSwipeAction(Swipe.FAST, GeneralLocation.BOTTOM_CENTER,
              GeneralLocation.TOP_CENTER, Press.FINGER));
        } else {
          vi.perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.BOTTOM_CENTER, Press.FINGER,
              InputDevice.SOURCE_TOUCHSCREEN, MotionEvent.TOOL_TYPE_FINGER));
        }
      }

      SystemClock.sleep(50);
    }
  }

  /**
   * Forced a NumberPicker with a specific Android ID to move to a targeted value.
   *
   * @param id The object ID of the NumbePicker.
   * @param target The target number to move to.
   * @param activity A specific activity.
   */
  public static void selectValue(final int id, final int target,
      final Activity activity) {
    final int ROWS_PER_SWIPE = 5;
    final NumberPicker np = activity.findViewById(id);
    final ViewInteraction vi = onView(withId(id));

    while (target != np.getValue()) {
      final int delta = Math.abs(target - np.getValue());

      if (target < np.getValue()) {
        if (delta >= ROWS_PER_SWIPE) {
          vi.perform(new GeneralSwipeAction(Swipe.FAST, GeneralLocation.TOP_CENTER,
              GeneralLocation.BOTTOM_CENTER, Press.FINGER));
        } else {
          vi.perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.TOP_CENTER, Press.FINGER,
              InputDevice.SOURCE_TOUCHSCREEN, MotionEvent.TOOL_TYPE_FINGER));
        }
      } else {
        if (delta >= ROWS_PER_SWIPE) {
          vi.perform(new GeneralSwipeAction(Swipe.FAST, GeneralLocation.BOTTOM_CENTER,
              GeneralLocation.TOP_CENTER, Press.FINGER));
        } else {
          vi.perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.BOTTOM_CENTER, Press.FINGER,
              InputDevice.SOURCE_TOUCHSCREEN, MotionEvent.TOOL_TYPE_FINGER));
        }
      }

      SystemClock.sleep(50);
    }
  }
}
