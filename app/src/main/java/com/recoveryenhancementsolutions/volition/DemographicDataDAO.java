package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface DemographicDataDAO {
    @Insert
    void insertActivity(DemographicDataEntity demographicDataEntity);
}