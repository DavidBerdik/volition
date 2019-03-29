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

  /**
   * Retrieves the severity String from the ViewSeverityLevel database.
   *
   * @return A LiveData object containing a String to represent the severity.
   */
  public LiveData<String> getSeverity() {
    return db.questionnaireModel().findSeverityLevelString();
  }

  /**
   * Retrieves the total amount of yes answers from the ViewSeverityLevel database.
   *
   * @return A LiveData object containing a String to represent the total "Yes" responses.
   */
  public LiveData<String> getTotalYesAnswers() {
    return db.questionnaireModel().findTotalYes();
  }

  private VolitionDatabase db;
}
