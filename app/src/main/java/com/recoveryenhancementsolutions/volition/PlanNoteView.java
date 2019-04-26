package com.recoveryenhancementsolutions.volition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Displays all notes for the selected day in a scroll view.
 */
public class PlanNoteView extends DialogFragment {

  public static final String TAG = "plan-note-view";

  /**
   * Create a PlanNoteView with a proper Bundle.
   *
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
    final LayoutInflater inflater = requireActivity().getLayoutInflater();
    final View view = inflater.inflate(R.layout.activity_plan_note_view, null);
    final TextView content = (TextView) view.findViewById(R.id.plan_note_view_text);
    final String notes = getArguments().getString("notes");

    builder.setView(view);

    builder.setTitle(getArguments().getString(("day")));

    if (notes == null || notes.isEmpty()) {
      content.setText(getString(R.string.plan_note_view_no_notes));
    } else {
      content.setText(notes);
    }

    view.findViewById(R.id.plan_note_view_done).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        dismiss();
      }
    });

    return builder.create();
  }

  /**
   * Calls show with the proper tag string.
   */
  public void show(FragmentManager manager) {
    show(manager, TAG);
  }
}
