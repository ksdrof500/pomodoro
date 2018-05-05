package br.com.pomodoro.inject.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import br.com.pomodoro.common.data.HistoricDao;
import br.com.pomodoro.common.data.HistoricDataBase;
import br.com.pomodoro.common.data.HistoricDataSource;
import br.com.pomodoro.common.data.HistoricRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Created by filipenunes on 04/20/18.
 */
@Module
public class RoomModule {

    private final HistoricDataBase historicDataBase;

    public RoomModule(Application application) {
        historicDataBase = Room.databaseBuilder(application, HistoricDataBase.class, "historic-db")
                .allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    HistoricDataBase providesRoomDatabase() {
        return historicDataBase;
    }

    @Singleton
    @Provides
    HistoricDao providesHistoric(HistoricDataBase historicDataBase) {
        return historicDataBase.historicDao();
    }

    @Singleton
    @Provides
    HistoricRepository historicRepository(HistoricDao historicDao) {
        return new HistoricDataSource(historicDao);
    }
}
