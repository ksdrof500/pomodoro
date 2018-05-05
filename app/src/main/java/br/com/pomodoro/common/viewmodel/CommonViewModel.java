package br.com.pomodoro.common.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

/**
 * Created by filipenunes on 04/20/18.
 */
public abstract class CommonViewModel extends BaseObservable {

    private final ObservableBoolean showProgress = new ObservableBoolean(true);
    public final ObservableBoolean showContent = new ObservableBoolean();
    public final ObservableBoolean showEmpty = new ObservableBoolean();

    protected void displayContent() {
        showContent.set(true);
        showProgress.set(false);
        showEmpty.set(false);
    }

    protected void displayProgress() {
        showContent.set(false);
        showEmpty.set(false);
        showProgress.set(true);
    }

    protected void displayEmpty() {
        showContent.set(false);
        showProgress.set(false);
        showEmpty.set(true);
    }

}
