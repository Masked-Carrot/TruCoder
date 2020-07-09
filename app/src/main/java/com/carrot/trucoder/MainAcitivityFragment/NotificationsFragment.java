package com.carrot.trucoder.MainAcitivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.R;
import com.carrot.trucoder.RecyclerViewAdapter.ConstestAdapter;
import com.carrot.trucoder.ViewModel.ApiRequestViewModel;
import com.carrot.trucoder.ViewModel.DatabaseViewModel;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private ConstestAdapter constestAdapter;
    private RecyclerView recyclerView;
    private DatabaseViewModel databaseViewModel;
    private ApiRequestViewModel apiRequestViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        constestAdapter = new ConstestAdapter();
        recyclerView = root.findViewById(R.id.contest_recycle);
        recyclerView.setAdapter(constestAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));


        apiRequestViewModel = new ViewModelProvider(ViewModelStore::new).get(ApiRequestViewModel.class);
        databaseViewModel = new ViewModelProvider(this,  new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(DatabaseViewModel.class);
        return root;
    }
}