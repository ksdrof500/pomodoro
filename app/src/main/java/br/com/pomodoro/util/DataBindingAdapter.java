package br.com.pomodoro.util;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filipenunes on 3/5/16.
 */
public abstract class DataBindingAdapter<T extends ViewDataBinding, I> extends RecyclerView.Adapter<BindingViewHolder<T>> {

    private final LayoutInflater inflater;
    protected final List<I> items;
    protected final Activity activity;

    protected DataBindingAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity);
        items = new ArrayList<>();
        this.activity = activity;
    }

    @NonNull
    @Override
    public BindingViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        T binding = DataBindingUtil.inflate(inflater, getItemResource(), parent, false);

        return new BindingViewHolder<>(binding);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<I> items) {

        this.items.clear();
        this.items.addAll(items);

        notifyDataSetChanged();
    }

    @LayoutRes
    protected abstract int getItemResource();
}
