package br.com.pomodoro.inject.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.pomodoro.PomodoroApp;
import br.com.pomodoro.util.FontManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by filipenunes on 04/20/18.
 */
@Module
public class AppModule {

    private final PomodoroApp application;

    public AppModule(PomodoroApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public FontManager provideFontManager() {
        return new FontManager(application);
    }

}
