package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * ViewModel for the "View Severity Level" activity.
 */
public class ViewSeverityLevelViewModel extends AndroidViewModel {

  /**
   * Constructor for the "View Severity Level" ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public ViewSeverityLevelViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  public LiveData<String> getSeverity() {
    return db.questionnaireModel().findSeverityLevelString();
  }

  public LiveData<String> getTotalYesAnswers() {
    return db.questionnaireModel().findTotalYes();
  }

  private VolitionDatabase db;
}
