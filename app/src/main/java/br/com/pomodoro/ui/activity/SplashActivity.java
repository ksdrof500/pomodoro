package br.com.pomodoro.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.pomodoro.R;
import br.com.pomodoro.interaction.SplashNavigation;
import br.com.pomodoro.viewmodel.SplashViewModel;

public class SplashActivity  extends AppCompatActivity implements SplashNavigation {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        new SplashViewModel(this, getResources().getInteger(R.integer.splash_time_out));
    }

    public void moveForward(Activity activity) {
        startActivity(new Intent(SplashActivity.this, activity.getClass()));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
