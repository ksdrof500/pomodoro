package br.com.pomodoro;

import android.app.Application;

import br.com.pomodoro.inject.components.AppComponent;
import br.com.pomodoro.inject.components.DaggerAppComponent;
import br.com.pomodoro.inject.modules.AppModule;
import br.com.pomodoro.inject.modules.RoomModule;

public class PomodoroApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {

        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .roomModule(new RoomModule(this))
                    .build();
        }
    }


    public static AppComponent getAppComponent() {
        return component;
    }

}
