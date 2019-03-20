package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * Class manages relationship between the TreatmentPlanActivity and the Volition Database.
 */
public class TreatmentPlanViewModel extends AndroidViewModel {
    //MUST CHANGE MED MANAGEMENT TO WEEKLY INSTEAD OF MONTHLY
    //MUST HANDLE CHANGING FROM WEEKLY TO DAILY WITH OUTCOME MEASURES
    //MUST CLARIFY DEFINITIONS OF EVENTS

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

        db = VolitionDatabase.getDatabase(this.getApplication());

        if (db.treatmentPlanDao().getNumTreatmentPlans() == 0) {
            generateTreatmentPlan();
        }

        treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Refreshes the data loaded from the database.
     */
    public void refreshDb() {
        treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan();
    }

    /**
     * Updates the database with the values of treatmentPlan.
     */
    public void updateDb() {
        db.treatmentPlanDao().updateTreatmentPlanEntity(treatmentPlan.getValue());
    }

    /**
     * Generates a new treatmentPlan.
     */
    private void generateTreatmentPlan() {
        int severityScore; //NEED TABLE FROM DIFFERENT SCRUM TEAM

        if (db.MedicationChoiceDao.getMedication().getValue().equals("abstain")) {
            //Less than mild severity
            if (severityScore < 2) {
                treatmentPlan.getValue().setNumCounseling(0);
                treatmentPlan.getValue().setNumSupportMeeting(0);
                treatmentPlan.getValue().setNumLessons(0);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(0);
                treatmentPlan.getValue().setNumOutcomeMeasures(0);
                treatmentPlan.getValue().setNumTimeTracking(0);
                treatmentPlan.getValue().setNumReadingResponse(0);
                treatmentPlan.getValue().setNumMedManagement(0);
            } else if (severityScore < 4) { //Mild Abstinence
                treatmentPlan.getValue().setNumCounseling(1);
                treatmentPlan.getValue().setNumSupportMeeting(1);
                treatmentPlan.getValue().setNumLessons(1);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.getValue().setNumOutcomeMeasures(1);
                treatmentPlan.getValue().setNumTimeTracking(1);
                treatmentPlan.getValue().setNumReadingResponse(1);
                treatmentPlan.getValue().setNumMedManagement(0);
            } else if (severityScore < 6) { //Moderate Abstinence
                treatmentPlan.getValue().setNumCounseling(3);
                treatmentPlan.getValue().setNumSupportMeeting(3);
                treatmentPlan.getValue().setNumLessons(2);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.getValue().setNumOutcomeMeasures(3);
                treatmentPlan.getValue().setNumTimeTracking(2);
                treatmentPlan.getValue().setNumReadingResponse(2);
                treatmentPlan.getValue().setNumMedManagement(0);
            } else { //Severe Abstinence
                treatmentPlan.getValue().setNumCounseling(5);
                treatmentPlan.getValue().setNumSupportMeeting(5);
                treatmentPlan.getValue().setNumLessons(3);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.getValue().setNumOutcomeMeasures(5);
                treatmentPlan.getValue().setNumTimeTracking(5);
                treatmentPlan.getValue().setNumReadingResponse(3);
                treatmentPlan.getValue().setNumMedManagement(0);
            }
        } else if (db.MedicationChoiceDao.getMedication().getValue().equals("buprenorphine")) {
            //Less than mild severity
            if (severityScore < 2) {
                treatmentPlan.getValue().setNumCounseling(0);
                treatmentPlan.getValue().setNumSupportMeeting(0);
                treatmentPlan.getValue().setNumLessons(0);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(0);
                treatmentPlan.getValue().setNumOutcomeMeasures(0);
                treatmentPlan.getValue().setNumTimeTracking(0);
                treatmentPlan.getValue().setNumReadingResponse(0);
                treatmentPlan.getValue().setNumMedManagement(0);
            } else if (severityScore < 4) { //Mild Buprenorphine
                treatmentPlan.getValue().setNumCounseling(1);
                treatmentPlan.getValue().setNumSupportMeeting(1);
                treatmentPlan.getValue().setNumLessons(1);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.getValue().setNumOutcomeMeasures(1);
                treatmentPlan.getValue().setNumTimeTracking(1);
                treatmentPlan.getValue().setNumReadingResponse(1);
                treatmentPlan.getValue().setNumMedManagement(1);
            } else if (severityScore < 6) { //Moderate Buprenorphine
                treatmentPlan.getValue().setNumCounseling(3);
                treatmentPlan.getValue().setNumSupportMeeting(3);
                treatmentPlan.getValue().setNumLessons(2);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.getValue().setNumOutcomeMeasures(3);
                treatmentPlan.getValue().setNumTimeTracking(2);
                treatmentPlan.getValue().setNumReadingResponse(2);
                treatmentPlan.getValue().setNumMedManagement(2);
            } else { //Severe Buprenorphine
                treatmentPlan.getValue().setNumCounseling(5);
                treatmentPlan.getValue().setNumSupportMeeting(5);
                treatmentPlan.getValue().setNumLessons(3);
                treatmentPlan.getValue().setNumTreatmentEffectivenessAssessment(1);
                treatmentPlan.getValue().setNumOutcomeMeasures(5);
                treatmentPlan.getValue().setNumTimeTracking(5);
                treatmentPlan.getValue().setNumReadingResponse(3);
                treatmentPlan.getValue().setNumMedManagement(1);
            }
        }
    }
}
