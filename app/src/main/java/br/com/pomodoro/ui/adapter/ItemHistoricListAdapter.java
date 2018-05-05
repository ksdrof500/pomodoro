package br.com.pomodoro.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;

import br.com.pomodoro.R;
import br.com.pomodoro.databinding.ItemHistoricBinding;
import br.com.pomodoro.modal.Historic;
import br.com.pomodoro.util.BindingViewHolder;
import br.com.pomodoro.util.DataBindingAdapter;
import br.com.pomodoro.viewmodel.HistoricItemViewModel;


/**
 * Created by filipenunes on 26/12/2017.
 */
public class ItemHistoricListAdapter extends DataBindingAdapter<ItemHistoricBinding, Historic> {

    public ItemHistoricListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected int getItemResource() {
        return R.layout.item_historic;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemHistoricBinding> holder, int position) {
        if (holder.binding.getItemViewModel() == null) {
            HistoricItemViewModel viewModel = new HistoricItemViewModel(activity);
            holder.binding.setItemViewModel(viewModel);
        }
        holder.binding.getItemViewModel().setContents(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
