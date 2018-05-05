package br.com.pomodoro.common.data;

import java.util.List;

import javax.inject.Inject;

import br.com.pomodoro.modal.Historic;


/**
 * Created by filipenunes on 04/20/18.
 */

public class HistoricDataSource implements HistoricRepository {

    private final HistoricDao historicDao;

    @Inject
    public HistoricDataSource(HistoricDao historicDao) {
        this.historicDao = historicDao;
    }


    @Override
    public List<Historic> findAll() {
        return historicDao.findAll();
    }

    @Override
    public long insert(Historic historic) {
        return historicDao.insert(historic);
    }
}