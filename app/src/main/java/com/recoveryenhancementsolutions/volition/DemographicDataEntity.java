package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class DemographicDataEntity {
    @PrimaryKey
    @NonNull
    private String patientName;

    private int age;
    private Date dateOfBirth;
    private String gender;
    private boolean isPersonInRecovery; //True - person in recovery FALSE - Family/Support

    //Booleans regarding the usage of a drug
    private boolean useHeroin;
    private boolean useOpiateOrSynth;
    private boolean useAlcohol;
    private boolean useCrackOrCocaine;
    private boolean useMarijuana;
    private boolean useMethamphetamine;
    private boolean useBenzo;
    private boolean useNonBeznoTrang;
    private boolean useBarbituresOrHypno;
    private boolean useInhalants;

    private String Other;
    private boolean disorderOpioid;
    private boolean disorderAlcohol;
    private Date lastClean;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isPersonInRecovery() {
        return isPersonInRecovery;
    }

    public void setPersonInRecovery(boolean personInRecovery) {
        isPersonInRecovery = personInRecovery;
    }

    public boolean isUseHeroin() {
        return useHeroin;
    }

    public void setUseHeroin(boolean useHeroin) {
        this.useHeroin = useHeroin;
    }

    public boolean isUseOpiateOrSynth() {
        return useOpiateOrSynth;
    }

    public void setUseOpiateOrSynth(boolean useOpiateOrSynth) {
        this.useOpiateOrSynth = useOpiateOrSynth;
    }

    public boolean isUseAlcohol() {
        return useAlcohol;
    }

    public void setUseAlcohol(boolean useAlcohol) {
        this.useAlcohol = useAlcohol;
    }

    public boolean isUseCrackOrCocaine() {
        return useCrackOrCocaine;
    }

    public void setUseCrackOrCocaine(boolean useCrackOrCocaine) {
        this.useCrackOrCocaine = useCrackOrCocaine;
    }

    public boolean isUseMarijuana() {
        return useMarijuana;
    }

    public void setUseMarijuana(boolean useMarijuana) {
        this.useMarijuana = useMarijuana;
    }

    public boolean isUseMethamphetamine() {
        return useMethamphetamine;
    }

    public void setUseMethamphetamine(boolean useMethamphetamine) {
        this.useMethamphetamine = useMethamphetamine;
    }

    public boolean isUseBenzo() {
        return useBenzo;
    }

    public void setUseBenzo(boolean useBenzo) {
        this.useBenzo = useBenzo;
    }

    public boolean isUseNonBeznoTrang() {
        return useNonBeznoTrang;
    }

    public void setUseNonBeznoTrang(boolean useNonBeznoTrang) {
        this.useNonBeznoTrang = useNonBeznoTrang;
    }

    public boolean isUseBarbituresOrHypno() {
        return useBarbituresOrHypno;
    }

    public void setUseBarbituresOrHypno(boolean useBarbituresOrHypno) {
        this.useBarbituresOrHypno = useBarbituresOrHypno;
    }

    public boolean isUseInhalants() {
        return useInhalants;
    }

    public void setUseInhalants(boolean useInhalants) {
        this.useInhalants = useInhalants;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public boolean isDisorderOpioid() {
        return disorderOpioid;
    }

    public void setDisorderOpioid(boolean disorderOpioid) {
        this.disorderOpioid = disorderOpioid;
    }

    public boolean isDisorderAlcohol() {
        return disorderAlcohol;
    }

    public void setDisorderAlcohol(boolean disorderAlcohol) {
        this.disorderAlcohol = disorderAlcohol;
    }

    public Date getLastClean() {
        return lastClean;
    }

    public void setLastClean(Date lastClean) {
        this.lastClean = lastClean;
    }
}
