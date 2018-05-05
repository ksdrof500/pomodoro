package br.com.pomodoro.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.pomodoro.R;
import br.com.pomodoro.databinding.ActivityMainBinding;
import br.com.pomodoro.ui.adapter.ViewPagerAdapter;
import br.com.pomodoro.ui.fragment.HistoricFragment;
import br.com.pomodoro.ui.fragment.PomodoroFragment;

public class MainActivity extends AppCompatActivity {

    private static final int EXIT = 2000;
    private long lastBackPressTime = 0;

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < (System.currentTimeMillis() - EXIT)) {
            Toast.makeText(this,
                    getString(R.string.backpress), Toast.LENGTH_LONG).show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        setupViewPager(binding.viewpager);
        binding.tabs.setupWithViewPager(binding.viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PomodoroFragment(), getString(R.string.title_new));
        adapter.addFragment(new HistoricFragment(), getString(R.string.title_historic));
        viewPager.setAdapter(adapter);
    }
}
