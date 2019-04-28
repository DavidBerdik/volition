package com.recoveryenhancementsolutions.volition;

import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.content.Context;


public class FocusListener implements OnFocusChangeListener {

  /**
   *After clicking enter the keyboard will go away
   **/
  public void onFocusChange(final View v, final boolean hasFocus) {

    if (v.getId() == R.id.name && !hasFocus) {
      final InputMethodManager imm = (InputMethodManager) v.getContext()
          .getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
  }

}
