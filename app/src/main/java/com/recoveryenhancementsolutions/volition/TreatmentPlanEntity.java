package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the User Treatment Plan.
 */
@Entity
public class TreatmentPlanEntity {
    /**
     * Returns the ID of this treatmentPlan.
     *
     * @return the treatment plan's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the number of counseling sessions the user should attend.
     *
     * @return The number of counseling sessions the user should attend.
     */
    public int getNumCounseling() {
        return numCounseling;
    }

    /**
     * Returns the number of times the user should do a medManagement.
     *
     * @return The number of times the user should do a medManagement.
     */
    public int getNumMedManagement() {
        return numMedManagement;
    }

    /**
     * Returns the number of times the user should attend support meetings.
     *
     * @return The number of times the user should attend support meetings.
     */
    public int getNumSupportMeeting() {
        return numSupportMeeting;
    }

    /**
     * Returns the number of times the user should attend lessons.
     *
     * @return The number of times the user should attend lessons.
     */
    public int getNumLessons() {
        return numLessons;
    }

    /**
     * Returns the number of times the user should take a treatment plan effectiveness assessment.
     *
     * @return The number of times the user should take a treatment plan effectiveness assessment.
     */
    public int getNumTreatmentEffectivenessAssessment() {
        return numTreatmentEffectivenessAssessment;
    }

    /**
     * Returns the number of times the user should do outcome measures.
     *
     * @return The number of times the user should do outcome measures.
     */
    public int getNumOutcomeMeasures() {
        return numOutcomeMeasures;
    }

    /**
     * Returns the number of times the user should do time tracking.
     *
     * @return The number of times the user should do time tracking.
     */
    public int getNumTimeTracking() {
        return numTimeTracking;
    }

    /**
     * Returns the number of times the user should do a reading response.
     *
     * @return The number of times the user should do a reading response.
     */
    public int getNumReadingResponse() {
        return numReadingResponse;
    }

    /**
     * Returns the current frequency for Medication Management.
     *
     * @return A string representing the frequency for Medication Management in the form of "WEEKLY"
     * or "MONTHLY".
     */
    public String getMedManagementFrequency() {
        return medManagementFrequency;
    }

    /**
     * Returns the current frequency for Outcome Measures.
     *
     * @return A string representing the frequency for Outcome Measures in the form of "WEEKLY" or
     * "DAILY".
     */
    public String getOutcomeMeasureFrequency() {
        return outcomeMeasureFrequency;
    }

    /**
     * Sets the number of times the user should attend counseling meetings per week.
     *
     * @param numCounseling The number of times the user should attend counseling meetings per week.
     */
    public void setNumCounseling(int numCounseling) {
        this.numCounseling = numCounseling;
    }

    /**
     * Sets the number of times the user should do a Medication Management.
     *
     * @param numMedManagement The number of times the user should do a Medication Management.
     */
    public void setNumMedManagement(int numMedManagement) {
        this.numMedManagement = numMedManagement;
    }

    /**
     * Sets the number of times the user should attend support meetings per week.
     *
     * @param numSupportMeeting The number of times the user should attend support meetings per week.
     */
    public void setNumSupportMeeting(int numSupportMeeting) {
        this.numSupportMeeting = numSupportMeeting;
    }

    /**
     * Sets the number of times the user should do lessons.
     *
     * @param numLessons The number of times the user should do lessons.
     */
    public void setNumLessons(int numLessons) {
        this.numLessons = numLessons;
    }

    /**
     * Sets the number of times the user should take a treatment plan effectiveness assessment per
     * week.
     *
     * @param numTreatmentEffectivenessAssessment The number of times the user should take a
     *                                            treatment plan effectiveness assessment.
     */
    public void setNumTreatmentEffectivenessAssessment(int numTreatmentEffectivenessAssessment) {
        this.numTreatmentEffectivenessAssessment = numTreatmentEffectivenessAssessment;
    }

    /**
     * Sets the number of outcome measures the user should take.
     *
     * @param numOutcomeMeasures The number of outcome measures the user should take.
     */
    public void setNumOutcomeMeasures(int numOutcomeMeasures) {
        this.numOutcomeMeasures = numOutcomeMeasures;
    }

    /**
     * Sets the number of times the user should record time tracking.
     *
     * @param numTimeTracking The number of times the user should record time tracking.
     */
    public void setNumTimeTracking(int numTimeTracking) {
        this.numTimeTracking = numTimeTracking;
    }

    /**
     * Sets the number of time the user should do a reading response.
     *
     * @param numReadingResponse The number of time the user should do a reading response.
     */
    public void setNumReadingResponse(int numReadingResponse) {
        this.numReadingResponse = numReadingResponse;
    }

    /**
     * Sets the activity's ID. Since the activity ID is an auto-increment value set by the database,
     * use of this setter is discouraged since the value set will be changed.
     *
     * @param id The ID of the activity.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Manually sets the value of frequency for medManagementFrequency.  Values for this field must
     * be either "WEEKLY" or "MONTHLY". Because of this, use of the setMedManagementWeekly() or
     * setMedManagementMonthly() are encouraged to be used instead.
     *
     * @param frequency The string value of the frequency Medication management should be done.
     */
    public void setMedManagementFrequency(String frequency) {
        this.medManagementFrequency = frequency;
    }

    /**
     * Manually sets the value of frequency for outcomeMeasureFrequency. Values for this field must
     * be either "WEEKLY" or "DAILY". Because of this, use of the setOutcomeMeasureWeekly() or
     * setOutcomeMeasureMonthly() are encouraged to be used instead.
     *
     * @param frequency The string value of the frequency outcome measures should be done.
     */
    public void setOutcomeMeasureFrequency(String frequency) {
        this.outcomeMeasureFrequency = frequency;
    }

    /**
     * Sets the frequency for Medication Management to "WEEKLY".
     */
    public void setMedManagementWeekly() {
        this.medManagementFrequency = "WEEKLY";
    }

    /**
     * Sets the frequency for Medication Management to "MONTHLY".
     */
    public void setMedManagementMonthly() {
        this.medManagementFrequency = "MONTHLY";
    }

    /**
     * Sets the frequency for Outcome Measures to "WEEKLY".
     */
    public void setOutcomeMeasureWeekly() {
        this.outcomeMeasureFrequency = "WEEKLY";
    }

    /**
     * Sets the frequency for Outcome Measures to "DAILY".
     */
    public void setOutcomeMeasureDaily() {
        this.outcomeMeasureFrequency = "DAILY";
    }

    /**
     * Stores the ID for this treatment plan.
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;  //used to manage the treatment plan and prevent duplicates.

    /**
     * Stores the number of times the user should go to counseling.
     */
    @NonNull
    private int numCounseling;

    /**
     * Stores the number of times the user should do a medManagement.
     */
    @NonNull
    private int numMedManagement;

    /**
     * Stores the number of times the user should attend a support meeting.
     */
    @NonNull
    private int numSupportMeeting;

    /**
     * Stores the number of times the user should attend lessons.
     */
    @NonNull
    private int numLessons;

    /**
     * Stores the number of times the user should take a treatment plan effectiveness assessment.
     */
    @NonNull
    private int numTreatmentEffectivenessAssessment;

    /**
     * Stores the number of times the user should record outcome measures.
     */
    @NonNull
    private int numOutcomeMeasures;

    /**
     * Stores the number of times the user should record time tracking.
     */
    @NonNull
    private int numTimeTracking;

    /**
     * Stores the number of times the user should take a reading response.
     */
    @NonNull
    private int numReadingResponse;

    /**
     * The frequency in which Medication Management should be done.
     */
    private String medManagementFrequency;

    /**
     * The frequency in which Outcome Measures should be done.
     */
    private String outcomeMeasureFrequency;
}