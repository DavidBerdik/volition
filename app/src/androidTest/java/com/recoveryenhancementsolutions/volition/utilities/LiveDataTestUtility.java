package com.recoveryenhancementsolutions.volition.utilities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * A utility method for running on-device unit tests that involve LiveData objects.
 */
public class LiveDataTestUtility {

  /**
   * Extracts the object contained within a LiveData object.
   *
   * @param liveData The LiveData object to extract from.
   * @return The object contained within liveData.
   */
  public static <T> T getNestedLiveDataObj(final LiveData<T> liveData) throws InterruptedException {
    /* Acquired from https://stackoverflow.com/a/49693724/2941352
       Original code from https://github.com/googlesamples/android-architecture-components/blob/
        17c315a050745c61ae8e79000bc0251305bd148b/BasicSample/app/src/androidTest/java/com/example/
        android/persistence/LiveDataTestUtil.java */
    final Object[] objects = new Object[1];
    final CountDownLatch latch = new CountDownLatch(1);

    Observer observer = new Observer() {
      @Override
      public void onChanged(@Nullable Object o) {
        objects[0] = o;
        latch.countDown();
        liveData.removeObserver(this);
      }
    };
    liveData.observeForever(observer);
    latch.await(2, TimeUnit.SECONDS);
    return (T) objects[0];
  }
}
