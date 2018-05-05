package br.com.pomodoro.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import br.com.pomodoro.PomodoroApp;


/**
 * Created by filipenunes on 2/3/16.
 */
public final class BindAdapters {

    @BindingAdapter("font")
    public static void setTypeFace(TextView view, String font) {
        FontManager fontManager = PomodoroApp.getAppComponent().getFontManager();
        view.setTypeface(fontManager.get(font));
    }

    @BindingAdapter("visible")
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
