package com.carrot.trucoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.Retrofit.CodeApi;
import com.carrot.trucoder.Retrofit.RetrofitService;
import com.carrot.trucoder.ViewModel.ApiRequestViewModel;
import com.carrot.trucoder.ViewModel.FirebaseDatabaseViewModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDeatails extends AppCompatActivity {

    private EditText truusertv;
    private EditText codeforcestv;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reference = db.collection("TRUUSER>codeforces>test01");
    private FirebaseDatabaseViewModel firebaseDatabaseViewModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_deatails);
        firebaseDatabaseViewModel = new ViewModelProvider(this , new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(FirebaseDatabaseViewModel.class);
        codeforcestv = findViewById(R.id.codeforcesusername);
        CircularProgressButton save = findViewById(R.id.save_of_userDetails);





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.startAnimation();
                String codeforcesusername = codeforcestv.getText().toString().trim();
                if(codeforcesusername.equals("")){
                    save.stopAnimation();
                    Toast.makeText(UserDeatails.this, "Codeforces username is required", Toast.LENGTH_SHORT).show();
                    save.revertAnimation();
                }
                else {
                    ApiRequestViewModel apiRequestViewModel = new ViewModelProvider(ViewModelStore::new).get(ApiRequestViewModel.class);
                    CodeApi codeApi = RetrofitService.cteateService(CodeApi.class);
                    codeApi.getUserInfo(codeforcesusername).enqueue(new Callback<UserInfoResposne>() {
                        @Override
                        public void onResponse(Call<UserInfoResposne> call, Response<UserInfoResposne> response) {
                            if(response.isSuccessful()){
                                save.stopAnimation();
                                save.revertAnimation();
                                sharedPreferences = getBaseContext().getSharedPreferences("SP" , MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("cfuser" , codeforcesusername);
                                editor.apply();
                                firebaseDatabaseViewModel.setNewTruuser(codeforcesusername, getBaseContext());
                                startActivity(new Intent(UserDeatails.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(UserDeatails.this, "No user found with username: "+codeforcesusername, Toast.LENGTH_SHORT).show();
                                save.stopAnimation();
                                save.revertAnimation();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserInfoResposne> call, Throwable t) {

                        }
                    });

                    }
                }

        });
    }
}