package com.recoveryenhancementsolutions.volition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class PlanNoteView extends DialogFragment {

  /**
   * Create a PlanNoteView with a proper Bundle.
   * @param day A locale-correct string representation of the viewing date without the time.
   * @param notes Expected to be compiled by DateView.  The message is set to this.
   * @return The instance of PlanNoteView.
   */
  public static PlanNoteView create(String day, String notes) {
    final PlanNoteView view = new PlanNoteView();
    final Bundle b = new Bundle();

    b.putString("day", day);
    b.putString("notes", notes);
    view.setArguments(b);

    return view;
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    final String notes = getArguments().getString("notes");

    builder.setTitle(getArguments().getString(("day")));
    if (notes == null || notes.isEmpty()) {
      builder.setMessage(getString(R.string.plan_note_view_no_notes));
    } else {
      builder.setMessage(notes);
    }
    builder.setPositiveButton(getString(R.string.plan_note_view_done), null);

    return builder.create();
  }

  public void show(FragmentManager manager) {
    show(manager, "plan-note-view");
  }
}