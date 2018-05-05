package br.com.pomodoro.common.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.pomodoro.modal.Historic;

/**
 * Created by filipenunes on 04/20/18.
 */
@Dao
public interface HistoricDao {

    @Query("SELECT * FROM Historic ORDER BY id DESC")
    List<Historic> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Historic historic);
}
