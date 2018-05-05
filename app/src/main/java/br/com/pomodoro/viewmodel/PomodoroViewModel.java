package br.com.pomodoro.viewmodel;

import android.databinding.ObservableField;
import android.os.CountDownTimer;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.pomodoro.R;
import br.com.pomodoro.common.data.HistoricDataSource;
import br.com.pomodoro.common.viewmodel.CommonViewModel;
import br.com.pomodoro.interaction.PomodoroInteraction;
import br.com.pomodoro.modal.Historic;

import static br.com.pomodoro.util.StringUtil.ONE_SECOND;
import static br.com.pomodoro.util.StringUtil.TWENTY_FIVE_MINUTES;
import static br.com.pomodoro.util.StringUtil.calculateTimer;
import static br.com.pomodoro.util.StringUtil.calculateTimerFinish;

@Singleton
public class PomodoroViewModel extends CommonViewModel {

    public final ObservableField<String> mTimer = new ObservableField<>();
    public final ObservableField<Integer> mTimerColor = new ObservableField<>();

    private final HistoricDataSource historicDataSource;
    private PomodoroInteraction pomodoroInteraction;
    private boolean statuButtonStart = false;
    private static long millisUntilFinished;

    @Inject
    public PomodoroViewModel(HistoricDataSource historicDataSource) {
        this.historicDataSource = historicDataSource;
    }

    public void clickStartOrStop() {
        configureButtom();
        if (statuButtonStart) {
            mTimerColor.set(pomodoroInteraction.getResourceColor(R.color.colorAccent));
            timer.start();
        } else {
            mTimerColor.set(pomodoroInteraction.getResourceColor(R.color.colorPrimaryDark));
            saveHistoric(false);
            timer.cancel();
        }
    }

    public void setPomodoroInteraction(PomodoroInteraction pomodoroInteraction) {
        this.pomodoroInteraction = pomodoroInteraction;
        mTimer.set(calculateTimer(TWENTY_FIVE_MINUTES));
        mTimerColor.set(pomodoroInteraction.getResourceColor(R.color.colorAccent));
    }

    private final CountDownTimer timer = new CountDownTimer(TWENTY_FIVE_MINUTES, ONE_SECOND) {

        @Override
        public void onTick(long millisUntilFinished) {
            PomodoroViewModel.millisUntilFinished = millisUntilFinished;
            mTimer.set(calculateTimer(millisUntilFinished));
        }

        @Override
        public void onFinish() {
            configureButtom();
            mTimerColor.set(pomodoroInteraction.getResourceColor(R.color.colorPrimaryDark));
            pomodoroInteraction.sendNotification();
            saveHistoric(true);

        }
    };

    private void configureButtom() {
        statuButtonStart = !statuButtonStart;
        pomodoroInteraction.togglePlay();
    }

    private void saveHistoric(boolean finish) {
        if (!finish) {
            historicDataSource.insert(new Historic(calculateTimerFinish(millisUntilFinished)));
        } else {
            historicDataSource.insert(new Historic(calculateTimerFinish(ONE_SECOND)));
        }
        pomodoroInteraction.notificationHistoric();
    }

}
