package com.carrot.trucoder.MainAcitivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carrot.trucoder.Collection.FriendList;
import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.R;
import com.carrot.trucoder.RecyclerViewAdapter.FriendRecycleAdapter;
import com.carrot.trucoder.ViewModel.ApiRequestViewModel;
import com.carrot.trucoder.ViewModel.DatabaseViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class DashboardFragment extends Fragment {

    private DatabaseViewModel databaseViewModel;
    private RecyclerView recyclerView;
    private FriendRecycleAdapter adapter;
    private EditText searchfriend_et;
    private CircularProgressButton searchfriend_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ApiRequestViewModel apiRequestViewModel = new ViewModelProvider(ViewModelStore::new).get(ApiRequestViewModel.class);

        databaseViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(DatabaseViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        searchfriend_btn = root.findViewById(R.id.frag_frnd_search_btn);
        searchfriend_et = root.findViewById(R.id.frag_frnd_search_et);
        recyclerView = root.findViewById(R.id.frag_frnd_recycleview);
        adapter = new FriendRecycleAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        searchfriend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchfriend_btn.startAnimation();
                String handle = searchfriend_et.getText().toString().trim();
                if (handle.equals("")) {
                    searchfriend_btn.revertAnimation();
                    Toast.makeText(getContext(), "User handle cannot be left blank", Toast.LENGTH_SHORT).show();

                } else {
                    apiRequestViewModel.getNewFriend(handle, requireActivity().getApplication());

                }
            }
        });

        databaseViewModel.getFriendListLive().observe(getViewLifecycleOwner(), new Observer<List<FriendList>>() {
            @Override
            public void onChanged(List<FriendList> friendLists) {
                adapter.setList(friendLists, getContext());
                searchfriend_btn.stopAnimation();
                searchfriend_btn.revertAnimation();
            }
        });
        return root;
    }
}