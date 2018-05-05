package br.com.pomodoro.ui.fragment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.pomodoro.PomodoroApp;
import br.com.pomodoro.R;
import br.com.pomodoro.databinding.PomorodoFragmentBinding;
import br.com.pomodoro.interaction.PomodoroInteraction;
import br.com.pomodoro.ui.activity.MainActivity;
import br.com.pomodoro.viewmodel.HistoricViewModel;
import br.com.pomodoro.viewmodel.PomodoroViewModel;

public class PomodoroFragment extends Fragment implements PomodoroInteraction {


    private PomorodoFragmentBinding binding;
    private static final int VIBRATE = 500;

    @Inject
    PomodoroViewModel pomodoroViewModel;
    @Inject
    HistoricViewModel historicViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PomodoroApp.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pomorodo_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = PomorodoFragmentBinding.bind(view);
        pomodoroViewModel.setPomodoroInteraction(this);
        binding.setPomodoroVM(pomodoroViewModel);
    }

    @Override
    public void togglePlay() {
        binding.fab.toggle();
    }

    @Override
    public void notificationHistoric() {
        historicViewModel.loadItems();
    }

    @Override
    public void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getString(R.string.description_notification))
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(getActivity(), MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setLights(Color.RED, VIBRATE, VIBRATE);
        long[] pattern = {VIBRATE,VIBRATE,VIBRATE,VIBRATE};
        mBuilder.setVibrate(pattern);
        NotificationManager mNotificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public int getResourceColor(int resource) {
        return getResources().getColor(resource);
    }
}
