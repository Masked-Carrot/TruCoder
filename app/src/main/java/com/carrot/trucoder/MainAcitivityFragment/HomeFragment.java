package com.carrot.trucoder.MainAcitivityFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import com.bumptech.glide.Glide;
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.Collection.UserRatingList;
import com.carrot.trucoder.Collection.UserRatingResponse;
import com.carrot.trucoder.R;
import com.carrot.trucoder.RecyclerViewAdapter.Home_adapter;
import com.carrot.trucoder.ViewModel.ApiRequestViewModel;
import com.carrot.trucoder.ViewModel.DatabaseViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class HomeFragment extends Fragment {

    private ApiRequestViewModel apiRequestViewModel;
    private TextView handle;
    private TextView ranktv;
    private TextView ratingtv;
    private ImageView profilepic;
    private TextView name;
    private ConstraintLayout layout;
    private LottieAnimationView lottieAnimationView;
    private LineChartView Chart;
    private Home_adapter adapter;
    private RecyclerView recyclerView;
    private ImageView color_Rating;
    private SharedPreferences sharedPreferences;
    private int flag = 0;
    private DatabaseViewModel databaseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        apiRequestViewModel = new ViewModelProvider(ViewModelStore::new).get(ApiRequestViewModel.class);
        databaseViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(DatabaseViewModel.class);

        sharedPreferences = requireActivity().getBaseContext().getSharedPreferences("SP" , Context.MODE_PRIVATE);
        String cfuser = sharedPreferences.getString("cfuser" , "");

        handle =  root.findViewById(R.id.userHandle);
        Chart = root.findViewById(R.id.user_rating_chart);
        recyclerView  = root.findViewById(R.id.home_recycler);
        lottieAnimationView = root.findViewById(R.id.loading_anim);
        layout = root.findViewById(R.id.home_frag_layout);
        ratingtv =  root.findViewById(R.id.rating);
        ranktv = root.findViewById(R.id.rating_text);
        color_Rating = root.findViewById(R.id.profile_pic_bound);
        color_Rating.setImageResource(R.color.colorPrimary);

        profilepic = root.findViewById(R.id.profile_pic);
        name = root.findViewById(R.id.name_home);
        adapter = new Home_adapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
        recyclerView.animate();

        Chart.setInteractive(false);
        Chart.setContainerScrollEnabled(false , null);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        name.setText(account.getGivenName());
        Glide.with(this).load(account.getPhotoUrl()).into(profilepic);


        apiRequestViewModel.getInfo(cfuser , requireActivity().getApplication());
        apiRequestViewModel.getRating(cfuser , requireActivity().getApplication());
        System.out.println(cfuser);



        databaseViewModel.getUserInfoListLiveData().observe(getViewLifecycleOwner(), new Observer<UserInfoList>() {
            @Override
            public void onChanged(UserInfoList userInfoList) {
                flag++;
                setTextView(userInfoList);
                layout.setVisibility(View.VISIBLE);
                lottieAnimationView.setVisibility(View.INVISIBLE);
                setRatingImage(Integer.parseInt(userInfoList.getRating()));
                if(flag == 2) {
                    layout.setVisibility(View.VISIBLE);
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                }
            }
        });

        databaseViewModel.getUserRatingListLiveData().observe(getViewLifecycleOwner(), new Observer<List<UserRatingList>>() {
            @Override
            public void onChanged(List<UserRatingList> lists) {
                UserRatingResponse userRatingResponse = new UserRatingResponse();
                userRatingResponse.setRatingList(lists);
                userRatingResponse.setStatus("ok");
                setGraph(userRatingResponse);
                adapter.setListList(lists);
                flag++;
                if(flag == 2) {
                    layout.setVisibility(View.VISIBLE);
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                }
            }
        });


        return root;
    }

    private void setTextView(UserInfoList userInfoList){
        if(userInfoList == null)
            return;
        ratingtv.setText(userInfoList.getRating());;
        flag++;
        handle.setText("@"+userInfoList.getHandle());
        ranktv.setText(userInfoList.getRank());
    }

    private void setGraph(UserRatingResponse userRatingResponse){
        if(userRatingResponse == null)
            return;
        flag++;
        List<UserRatingList> list = userRatingResponse.getRatingList();
        List<Float> list1 = new ArrayList<>();
        List<String> list2  = new ArrayList<>();
        int min = 10000;
        int max = -1;
        List<PointValue> values = new ArrayList<PointValue>();
        int i = Math.max(list.size() - 20, 0);
        for( ; i< list.size();i++) {
            values.add(new PointValue(i , list.get(i).getNewRating()));
            int rating = list.get(i).getNewRating();
            if(rating > max)
                max = rating;
            if(rating < min)
                min = rating;
        }

        Line line = new Line(values).setColor(Color.parseColor("#6200EE")).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        data.setAxisYLeft(Axis.generateAxisFromRange(((float) min), (float) max , (float)(max - min)/20));

        Chart.setLineChartData(data);
    }

    private void setRatingImage(int rating) {
            if(rating < 1200)
                color_Rating.setImageResource(R.color.newbie);
            else if(rating < 1400)
                color_Rating.setImageResource(R.color.pupil);
            else if(rating < 1600)
                color_Rating.setImageResource(R.color.specilist);
            else if(rating < 1900)
                color_Rating.setImageResource(R.color.expert);
            else if(rating < 2200)
                color_Rating.setImageResource(R.color.CandidateMaster);
            else if(rating < 2400)
                color_Rating.setImageResource(R.color.InternationalMaster);
            else if(rating < 2900)
                color_Rating.setImageResource(R.color.InternationalGrandmaster);
            else
                color_Rating.setImageResource(R.color.LegendaryGrandmaster);
    }
}