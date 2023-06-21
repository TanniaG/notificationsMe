package com.example.notificationsme;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility {

    static void showToast(Context context , String message){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    static CollectionReference getCollectinReferenceForBills(){

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("bills").document(currentUser.getUid()).collection("myBills");

    }

    static String timestampToString(Timestamp timestamp){

        return new SimpleDateFormat("DD/MM/YYYY").format(timestamp.toDate());
    }

}
