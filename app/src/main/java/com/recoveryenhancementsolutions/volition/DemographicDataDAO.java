package com.recoveryenhancementsolutions.volition;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface DemographicDataDAO {
    @Insert
    void insertDemographicInfo(DemographicDataEntity demographicDataEntity);
    @Update
    void updateDemographicInfo(DemographicDataEntity demographicDataEntity);


    /*
    Other components of the volition application may need to access the data of the patient.
    Rather than having to access the object created and use the get method on them, since this
    will be a table of one, they can call the generic query on this class to receive it which
    should allow for  easier access of this information for the other functions of this application
    */

    String genericQuery = "FROM DemographicDataEntity WHERE fetchID = 1";// commonly used component of query

    @Query("SELECT patientName "+genericQuery)
    String queryPatientName();
    @Query("SELECT age "+genericQuery)
    int queryPatientAge();
    @Query("SELECT dateOfBirth "+genericQuery)
    String queryDoB();
    @Query("SELECT gender "+genericQuery)
    String queryPatientGender();
    @Query("SELECT isPersonInRecovery "+genericQuery)
    boolean queryIsInRecovery();

    @Query("SELECT useHeroin "+genericQuery)
    boolean queryIsUsingHeroin();
    @Query("SELECT useOpiateOrSynth "+genericQuery)
    boolean queryIsUsingOpiateOrSynth();
    @Query("SELECT useAlcohol "+genericQuery)
    boolean queryIsUsingAlcohol();
    @Query("SELECT useCrackOrCocaine "+genericQuery)
    boolean queryIsUsingCrackOrCo();
    @Query("SELECT useMarijuana "+genericQuery)
    boolean queryIsUsingMarijuana();
    @Query("SELECT useMethamphetamine "+genericQuery)
    boolean queryIsUsingMeth();
    @Query("SELECT useBenzo "+genericQuery)
    boolean queryIsUsingBenzo();
    @Query("SELECT useNonBeznoTrang "+genericQuery)
    boolean queryIsUsingNonBenzoTranq();
    @Query("SELECT useBarbituresOrHypno "+genericQuery)
    boolean queryIsUsingBarbOrHypno();
    @Query("SELECT useInhalants "+genericQuery)
    boolean queryIsUsingInhalants();

    @Query("SELECT Other "+genericQuery)
    String queryOtherUsedDrugs();

    @Query("SELECT disorderAlcohol "+genericQuery)
    boolean queryIsHavingAlcoholDisorder();
    @Query("SELECT disorderOpioid "+genericQuery)
    boolean queryIsHavingOpioidDisorder();
    @Query("SELECT lastClean "+genericQuery)
    String queryLastCleanDate();
}