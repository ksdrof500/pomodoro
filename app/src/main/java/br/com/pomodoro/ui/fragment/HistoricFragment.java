package br.com.pomodoro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import br.com.pomodoro.PomodoroApp;
import br.com.pomodoro.R;
import br.com.pomodoro.databinding.HistoricFragmentBinding;
import br.com.pomodoro.interaction.HistoricInteraction;
import br.com.pomodoro.modal.Historic;
import br.com.pomodoro.ui.adapter.ItemHistoricListAdapter;
import br.com.pomodoro.viewmodel.HistoricViewModel;

public class HistoricFragment extends Fragment implements HistoricInteraction {

    @Inject
    HistoricViewModel viewModel;

    private ItemHistoricListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemHistoricListAdapter(getActivity());
        PomodoroApp.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.historic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HistoricFragmentBinding binding = HistoricFragmentBinding.bind(view);
        viewModel.setHistoricInteraction(this);
        binding.setViewModel(viewModel);
        bindAdapter(binding.itemList);
        viewModel.loadItems();
    }

    @Override
    public void displayItems(List<Historic> items) {
        adapter.setItems(items);
    }

    private void bindAdapter(RecyclerView binding) {
        RecyclerView.LayoutManager horizontalManager = new LinearLayoutManager(getContext(),
                OrientationHelper.VERTICAL, false);

        binding.setLayoutManager(horizontalManager);
        binding.setAdapter(adapter);
    }

}