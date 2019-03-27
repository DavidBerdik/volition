package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * ViewModel for the "View Severity Level" activity.
 */
public class ViewSeverityLevelViewModel extends AndroidViewModel {

  public final LiveData<String> severity;
  public final LiveData<String> totalYes;

  /**
   * Constructor for the "View Severity Level" ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public ViewSeverityLevelViewModel(Application application) {
    super(application);
    mDb = VolitionDatabase.getDatabase(this.getApplication());

    // Books is a LiveData object so updates are observed.
    severity = mDb.questionnaireModel().findSeverityLevelString();
    totalYes= mDb.questionnaireModel().findTotalYes();
  }
  private VolitionDatabase mDb;
}
