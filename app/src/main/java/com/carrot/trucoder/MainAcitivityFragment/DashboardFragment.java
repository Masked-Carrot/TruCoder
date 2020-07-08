package com.carrot.trucoder.MainAcitivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DatabaseViewModel databaseViewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private FriendRecycleAdapter adapter;
    private EditText search ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ApiRequestViewModel apiRequestViewModel = new ViewModelProvider(ViewModelStore::new).get(ApiRequestViewModel.class);

        databaseViewModel =
                new ViewModelProvider(this , new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(DatabaseViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        search = root.findViewById(R.id.search_f);
        recyclerView = root.findViewById(R.id.friendRecycle);
        floatingActionButton = root.findViewById(R.id.add_friend);
        adapter = new FriendRecycleAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL,false));

        databaseViewModel.getAllFriends().observe(getViewLifecycleOwner(), new Observer<List<FriendList>>() {
            @Override
            public void onChanged(List<FriendList> list) {
                adapter.setList(list , getContext());
                /*
                StringBuilder sb = new StringBuilder();
                if(list != null){
                    for(int  i =0;i<list.size();i++)
                    {
                        String prefix = "";
                        for (FriendList friendList : list)
                        {
                            sb.append(prefix);
                            prefix = ";";
                            String h = friendList.getHandle().toString();
                            sb.append(h);
                        }
                        sb.deleteCharAt(sb.lastIndexOf(sb.toString()));
                    }

                }

                 */
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiRequestViewModel.getNewFriend(search.getText().toString() , getActivity().getApplication());
            }
        });

        return root;
    }
}