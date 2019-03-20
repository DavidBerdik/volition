package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * Class manages relationship between the TreatmentPlanActivity and the Volition Database.
 */
public class TreatmentPlanViewModel extends AndroidViewModel {
    /**
     * Constructor method to initialize a new TreatmentPlanViewModel
     *
     * @param application The application creating the TreatmentPlanViewModel.
     */
    public TreatmentPlanViewModel(Application application) {
        super(application);

        db = VolitionDatabase.getDatabase(this.getApplication());

        if (db.treatmentPlanDao().getNumTreatmentPlans() == 0) {
            generateTreatmentPlan();
        }

        treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Updates the database with the values of treatmentPlan.
     */
    public void updateDb() {
        db.treatmentPlanDao().updateTreatmentPlanEntity(treatmentPlan.getValue());
    }

    /**
     * Live Data representing the treatment plan stored in the database.
     */
    public LiveData<TreatmentPlanEntity> treatmentPlan;

    /**
     * Generates a new treatmentPlan.
     */
    private void generateTreatmentPlan() {
        String severityLevel = db.questionnaireDao().findSeverityLevel();
        TreatmentPlanEntity treatmentPlan = new TreatmentPlanEntity();
        if (db.medicationChoiceDao().getMedication().getValue().equals("abstain")) {
            if (severityLevel.equals("MILD")) { //Mild Abstinence
                treatmentPlan.setNumCounseling(1);
                treatmentPlan.setNumSupportMeeting(1);
                treatmentPlan.setNumLessons(1);
                treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.setNumOutcomeMeasures(1);
                treatmentPlan.setNumTimeTracking(1);
                treatmentPlan.setNumReadingResponse(1);
                treatmentPlan.setNumMedManagement(0);
                treatmentPlan.setMedManagementMontly();
                treatmentPlan.setOutcomeMeasureWeekly();
            } else if (severityLevel.equals("MODERATE")) { //Moderate Abstinence
                treatmentPlan.setNumCounseling(3);
                treatmentPlan.setNumSupportMeeting(3);
                treatmentPlan.setNumLessons(2);
                treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.setNumOutcomeMeasures(3);
                treatmentPlan.setNumTimeTracking(2);
                treatmentPlan.setNumReadingResponse(2);
                treatmentPlan.setNumMedManagement(0);
                treatmentPlan.setMedManagementMontly();
                treatmentPlan.setOutcomeMeasureDaily();
            } else { //Severe Abstinence
                treatmentPlan.setNumCounseling(5);
                treatmentPlan.setNumSupportMeeting(5);
                treatmentPlan.setNumLessons(3);
                treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.setNumOutcomeMeasures(5);
                treatmentPlan.setNumTimeTracking(5);
                treatmentPlan.setNumReadingResponse(3);
                treatmentPlan.setNumMedManagement(0);
                treatmentPlan.setMedManagementWeekly();
                treatmentPlan.setOutcomeMeasureDaily();
            }
        } else if (db.medicationChoiceDao().getMedication().getValue().equals("buprenorphine")) {
            if (severityLevel.equals("MILD")) { //Mild Buprenorphine
                treatmentPlan.setNumCounseling(1);
                treatmentPlan.setNumSupportMeeting(1);
                treatmentPlan.setNumLessons(1);
                treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.setNumOutcomeMeasures(1);
                treatmentPlan.setNumTimeTracking(1);
                treatmentPlan.setNumReadingResponse(1);
                treatmentPlan.setNumMedManagement(1);
                treatmentPlan.setMedManagementMontly();
                treatmentPlan.setOutcomeMeasureWeekly();
            } else if (severityLevel.equals("MODERATE")) { //Moderate Buprenorphine
                treatmentPlan.setNumCounseling(3);
                treatmentPlan.setNumSupportMeeting(3);
                treatmentPlan.setNumLessons(2);
                treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.setNumOutcomeMeasures(3);
                treatmentPlan.setNumTimeTracking(2);
                treatmentPlan.setNumReadingResponse(2);
                treatmentPlan.setNumMedManagement(2);
                treatmentPlan.setMedManagementMontly();
                treatmentPlan.setOutcomeMeasureDaily();
            } else { //Severe Buprenorphine
                treatmentPlan.setNumCounseling(5);
                treatmentPlan.setNumSupportMeeting(5);
                treatmentPlan.setNumLessons(3);
                treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.setNumOutcomeMeasures(5);
                treatmentPlan.setNumTimeTracking(5);
                treatmentPlan.setNumReadingResponse(3);
                treatmentPlan.setNumMedManagement(1);
                treatmentPlan.setMedManagementWeekly();
                treatmentPlan.setOutcomeMeasureDaily();
            }
        }
        db.treatmentPlanDao().insertTreatmentPlanEntity(treatmentPlan);
    }

    /**
     * The apps loaded Database.
     */
    private VolitionDatabase db;

}
