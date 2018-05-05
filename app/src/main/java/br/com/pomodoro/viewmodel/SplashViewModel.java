package br.com.pomodoro.viewmodel;

import android.os.Handler;

import br.com.pomodoro.interaction.SplashNavigation;
import br.com.pomodoro.ui.activity.MainActivity;

public class SplashViewModel {

    private final SplashNavigation splashNavigation;

    public SplashViewModel(SplashNavigation splashNavigation, int timer) {
        this.splashNavigation = splashNavigation;
        startSplashTimeout(timer);
    }

    private void moveToNextTouched() {
        splashNavigation.moveForward(new MainActivity());
    }


    private void startSplashTimeout(int timer) {
        new Handler().postDelayed(this::moveToNextTouched, timer);
    }

}
