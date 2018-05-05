package br.com.pomodoro.common.data;

import java.util.List;

import br.com.pomodoro.modal.Historic;


/**
 * Created by filipenunes on 04/20/18.
 */

public interface HistoricRepository {
    List<Historic> findAll();
    long insert(Historic historic);
}
