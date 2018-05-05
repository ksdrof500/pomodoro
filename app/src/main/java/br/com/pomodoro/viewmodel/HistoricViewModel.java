package br.com.pomodoro.viewmodel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.pomodoro.common.data.HistoricDataSource;
import br.com.pomodoro.common.viewmodel.CommonViewModel;
import br.com.pomodoro.interaction.HistoricInteraction;
import br.com.pomodoro.modal.Historic;
import br.com.pomodoro.util.ListUtils;

/**
 * Created by filipenunes on 04/20/18.
 */
@Singleton
public class HistoricViewModel extends CommonViewModel {

    private HistoricInteraction historicInteraction;
    private final HistoricDataSource historicDataSource;

    @Inject
    public HistoricViewModel(HistoricDataSource historicDataSource) {
        this.historicDataSource = historicDataSource;
    }

    public void setHistoricInteraction(HistoricInteraction historicInteraction) {
        this.historicInteraction = historicInteraction;
    }

    public void loadItems() {
        displayProgress();
        List<Historic> historicList = historicDataSource.findAll();
        if (ListUtils.isEmpty(historicList)) {
            displayEmpty();
        } else {
            historicInteraction.displayItems(historicList);
            displayContent();
        }
    }

}

