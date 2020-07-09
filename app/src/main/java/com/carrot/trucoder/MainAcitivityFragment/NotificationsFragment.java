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

import com.airbnb.lottie.LottieAnimationView;
import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.R;
import com.carrot.trucoder.RecyclerViewAdapter.ConstestAdapter;
import com.carrot.trucoder.ViewModel.ApiRequestViewModel;
import com.carrot.trucoder.ViewModel.DatabaseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private ConstestAdapter constestAdapter;
    private RecyclerView recyclerView;
    private DatabaseViewModel databaseViewModel;
    private ApiRequestViewModel apiRequestViewModel;
    private LottieAnimationView animationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        constestAdapter = new ConstestAdapter();
        recyclerView = root.findViewById(R.id.contest_recycle);
        recyclerView.setAdapter(constestAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));

        animationView = root.findViewById(R.id.loading_anim_contest);
        apiRequestViewModel = new ViewModelProvider(ViewModelStore::new).get(ApiRequestViewModel.class);
        databaseViewModel = new ViewModelProvider(this,  new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(DatabaseViewModel.class);
        apiRequestViewModel.getContestList(requireActivity().getApplication()).observe(getViewLifecycleOwner(), new Observer<List<ContestList>>() {
            @Override
            public void onChanged(List<ContestList> temp) {
                if(temp != null){
                    List<ContestList> contestLists = new ArrayList<>();
                    for(int i= 0;i<temp.size();i++){
                        if(temp.get(i).getPhase().equals("BEFORE"))
                            contestLists.add(temp.get(i));
                        else
                            break;

                    }
                    animationView.setVisibility(View.INVISIBLE);
                    constestAdapter.setList(contestLists);
                }
            }
        });
        return root;
    }
}