package br.com.pomodoro.viewmodel;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import br.com.pomodoro.R;
import br.com.pomodoro.modal.Historic;
import br.com.pomodoro.util.StringUtil;

import static br.com.pomodoro.util.StringUtil.TWENTY_FIVE;

/**
 * Created by filipenunes on 04/20/18.
 */
public class HistoricItemViewModel extends BaseObservable {

    public final ObservableField<String> timer;
    public final ObservableField<String> status;
    public final ObservableField<String> lastTimer;
    public final ObservableField<String> day;
    private final Activity activity;

    public HistoricItemViewModel(Activity activity) {
        timer = new ObservableField<>();
        lastTimer = new ObservableField<>();
        status = new ObservableField<>();
        day = new ObservableField<>();
        this.activity = activity;
    }

    public void setContents(Historic historic, boolean newDay) {
        if (newDay) {
            day.set(StringUtil.calculateBetweenDates(historic.getDate(), activity));
        }
        timer.set(historic.getTimer());
        status.set(historic.getTimer().equals(TWENTY_FIVE) ? activity.getResources().getString(R.string.finish)
                :  activity.getResources().getString(R.string.stop));
        lastTimer.set(StringUtil.calculateBetweenHour(historic.getHour(), activity));
    }

}

