package br.com.pomodoro.inject.components;

import javax.inject.Singleton;

import br.com.pomodoro.inject.modules.AppModule;
import br.com.pomodoro.inject.modules.RoomModule;
import br.com.pomodoro.ui.fragment.HistoricFragment;
import br.com.pomodoro.ui.fragment.PomodoroFragment;
import br.com.pomodoro.util.FontManager;
import dagger.Component;

/**
 * Created by filipenunes on 04/20/18.
 */
@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface  AppComponent {

    void inject(HistoricFragment historicFragment);
    void inject(PomodoroFragment pomodoroFragment);

    FontManager getFontManager();
}
