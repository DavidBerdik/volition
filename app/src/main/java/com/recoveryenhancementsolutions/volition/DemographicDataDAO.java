package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * DAO for interacting with the DemographicDataEntity. Skeleton file, should be replaced by another
 * group's full task later.
 */
@Dao
public interface DemographicDataDAO {

  /**
   * Inserts a new DemographicDataEntity into the database.
   *
   * @param demographicDataEntity An object representing the DemographicDataEntity.
   */
  @Insert
  void insertDemographicInfo(DemographicDataEntity demographicDataEntity);

  /**
   * Updates the DemographicDataEntity inside the database.
   *
   * @param demographicDataEntity An object representing the DemographicDataEntity.
   */
  @Update
  void updateDemographicInfo(DemographicDataEntity demographicDataEntity);

  /**
   * Retrieves the last date of being clean from the database.
   *
   * @return A String object representing the last date of being clean.
   */
  @Query("SELECT lastClean FROM DemographicDataEntity LIMIT 1")
  String queryLastCleanDate();
}
