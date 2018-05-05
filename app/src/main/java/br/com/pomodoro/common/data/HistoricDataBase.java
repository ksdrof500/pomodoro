package br.com.pomodoro.common.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.pomodoro.modal.Historic;


/**
 * Created by filipenunes on 04/20/18.
 */

@Database(entities = {Historic.class}, version = 1, exportSchema = false)
public abstract class HistoricDataBase extends RoomDatabase {

    public abstract HistoricDao historicDao();
}
