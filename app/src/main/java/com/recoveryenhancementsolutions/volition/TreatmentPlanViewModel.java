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
    private VolitionDatabase db;

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

        db =VolitionDatabase.getDatabase(this.getApplication());

        if(db.treatmentPlanDao().getNumTreatmentPlans() == 0){
            generateTreatmentPlan();
        }

        treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Refreshes the data loaded from the database.
     */
    public void refreshDb(){
        treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Updates the database with the values of treatmentPlan.
     */
    public void updateDb(){
        db.treatmentPlanDao().updateTreatmentPlanEntity(treatmentPlan.getValue());
    }

    /**
     * Generates a new treatmentPlan.
     */
    private void generateTreatmentPlan(){

    }
}
