package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class DemographicDataEntity {
    @PrimaryKey
    @NonNull
    public String patientName;

    public int age;
    public Date dateOfBirth;
    public String gender;
    public boolean isPersonInRecovery; //True - person in recovery FALSE - Family/Support

    //Booleans regarding the usage of a drug
    public boolean useHeroin;
    public boolean useOpiateOrSynth;
    public boolean  useAlcohol;
    public boolean useCrackOrCocaine;
    public boolean useMarijuana;
    public boolean useMethamphetamine;
    public boolean  useBenzo;
    public boolean useNonBeznoTrang;
    public boolean useBarbituresOrHypno;
    public boolean useInhalants;

    public String Other;
    public boolean disorderOpioid;
    public boolean disorderAlcohol;
    public Date lastClean;

}
