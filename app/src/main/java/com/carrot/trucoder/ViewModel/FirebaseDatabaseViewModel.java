package com.carrot.trucoder.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDatabaseViewModel extends AndroidViewModel {

    private FirebaseFirestore db;
    private CollectionReference reference;

    public FirebaseDatabaseViewModel(@NonNull Application application) {
        super(application);
        db = FirebaseFirestore.getInstance();
        reference = db.collection("TRUUSER>codeforces>test01");
    }


    public void setNewTruuser(String codeforcestxt , final  Context context) {
        Map<String, Object> note = new HashMap<>();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        note.put("NAME" , account.getDisplayName());
        note.put("CODEFORCES", codeforcestxt);
        note.put("EMAIL" , account.getEmail());
        String acc ;


        reference.document().set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Data added check it now", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error 1", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public int getTruuser(String email){
        final Map<String, Object>[] note = new Map[]{new HashMap<>()};
        note[0] = null;

        reference.document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            note[0] = documentSnapshot.getData();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("firestore" , "error in fetchning data");
                    }
                });
        if(note[0] != null)
            return 1;
        return  0;
    }



}
