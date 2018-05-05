package br.com.pomodoro.interaction;

/**
 * Created by filipenunes on 04/20/18.
 */
public interface PomodoroInteraction {
    void togglePlay();
    void notificationHistoric();
    void sendNotification();
    int getResourceColor(int resource);
}
