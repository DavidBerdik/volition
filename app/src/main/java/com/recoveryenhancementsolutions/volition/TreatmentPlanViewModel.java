package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * Class manages relationship between the TreatmentPlanActivity and the Volition Database.
 */
public class TreatmentPlanViewModel extends AndroidViewModel {

    /**
     * The apps loaded Database.
     */
    private VolitionDatabase mDb;

    /**
     * Live Data representing the treatment plan stored in the database.
     */
    public LiveData<TreatmentPlanEntity> treatmentPlan;

    /**
     * Constructor method to initialize a new TreatmentPlanViewModel
     *
     * @param application The application creating the TreatmentPlanViewModel.
     */
    public TreatmentPlanViewModel(Application application) {
        super(application);

        mDb =VolitionDatabase.getDatabase(this.getApplication());
        treatmentPlan = mDb.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Refreshes the data loaded from the database.
     */
    public void refreshDb(){
        treatmentPlan = mDb.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Updates the database with the values of treatmentPlan.
     */
    public void updateDb(){
        mDb.treatmentPlanDao().updateTreatmentPlanEntity(treatmentPlan.getValue());
    }
}
