package com.example.notificationsme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class Bills extends Fragment {

    public FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView review = recyclerView.findViewById(R.id.recycler);

        View view = inflater.inflate(R.layout.fragment_bills, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(), Notifications.class);
                startActivity(intent);
            }
        });

        setRecyclerView();

        return view;

       // return inflater.inflate(R.layout.fragment_bills, container, false);
        //floatingActionButton = findViewById(R.id.fab);
    }


    void setRecyclerView(){

        Query query = Utility.getCollectinReferenceForBills().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<UserDatabase> options = new FirestoreRecyclerOptions.Builder<UserDatabase>()
                .setQuery(query, UserDatabase.class).build()
                ;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options, this);
        recyclerView.setAdapter(noteAdapter);

    }

  /**  static void showToast(Context context , String message){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    static CollectionReference getCollectinReferenceForBills(){

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
       return FirebaseFirestore.getInstance().collection("bills").document(currentUser.getUid()).collection("myBills");

    } **/



}