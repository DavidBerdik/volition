package com.recoveryenhancementsolutions.volition;

    import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
    import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

    import android.arch.lifecycle.LiveData;
    import android.arch.persistence.room.Dao;
    import android.arch.persistence.room.Insert;
    import android.arch.persistence.room.Query;
    import android.arch.persistence.room.Update;
/**
 * DAO for interacting with the TEAEntity
 */
@Dao
public interface TeaDAO {
  /**
   * Inserts a TEA into the database.
   *
   * @param teaEntity a TEAEntity object containing the object to be inserted.
   */
  @Insert(onConflict = IGNORE)
  void insertTEA(final TEAEntity teaEntity);
  /**
   * Updates the TEA in the database.
   *
   * @param teaEntity a TEAEntity object containing the object to be inserted.
   */
  @Update(onConflict = REPLACE)
  void updateTEA(final TEAEntity teaEntity);
  /**
   * Retrieves all given answers from the database.
   *
   * @return a LiveData object containing an object of responses
   */
  @Query("SELECT * FROM TEAEntity")
  LiveData<TEAEntity> getTEA();
}
