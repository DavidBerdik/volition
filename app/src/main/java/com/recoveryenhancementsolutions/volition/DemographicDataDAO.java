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

    @Query("SELECT patientName FROM DemographicDataEntity WHERE fetchID = 1")
    String queryPatientName();
    @Query("SELECT age FROM DemographicDataEntity WHERE fetchID = 1")
    int queryPatientAge();
    @Query("SELECT dateOfBirth FROM DemographicDataEntity WHERE fetchID = 1")
    String queryDoB();
    @Query("SELECT gender FROM DemographicDataEntity WHERE fetchID = 1")
    String queryPatientGender();
    @Query("SELECT isPersonInRecovery FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsInRecovery();

    @Query("SELECT useHeroin FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingHeroin();
    @Query("SELECT useOpiateOrSynth FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingOpiateOrSynth();
    @Query("SELECT useAlcohol FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingAlcohol();
    @Query("SELECT useCrackOrCocaine FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingCrackOrCo();
    @Query("SELECT useMarijuana FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingMarijuana();
    @Query("SELECT useMethamphetamine FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingMeth();
    @Query("SELECT useBenzo FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingBenzo();
    @Query("SELECT useNonBeznoTrang FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingNonBenzoTranq();
    @Query("SELECT useBarbituresOrHypno FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingBarbOrHypno();
    @Query("SELECT useInhalants FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsUsingInhalants();

    @Query("SELECT Other FROM DemographicDataEntity WHERE fetchID = 1")
    String queryOtherUsedDrugs();

    @Query("SELECT disorderAlcohol FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsHavingAlcoholDisorder();
    @Query("SELECT disorderOpioid FROM DemographicDataEntity WHERE fetchID = 1")
    boolean queryIsHavingOpioidDisorder();
    @Query("SELECT lastClean FROM DemographicDataEntity WHERE fetchID = 1")
    String queryLastCleanDate();
}
