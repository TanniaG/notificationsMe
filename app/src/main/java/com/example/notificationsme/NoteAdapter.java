package com.example.notificationsme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter < UserDatabase, NoteAdapter.NoteViewHolder> {

    /** Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link} for configuration options.@param options */

    Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<UserDatabase> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull UserDatabase userDatabase) {

        holder.title_tv.setText(userDatabase.topics);
        holder.amount_tv.setText(userDatabase.amounts);
        holder.date_tv.setText(userDatabase.dates);
        holder.time_tv.setText(userDatabase.times);
        holder.note_tv.setText(userDatabase.topics);
        holder.timestamp_tv.setText(Utility.timestampToString(userDatabase.timestamp));

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView title_tv, amount_tv, date_tv , time_tv, note_tv, timestamp_tv;

        public NoteViewHolder(@NonNull View itemView) {

            super(itemView);
            title_tv = itemView.findViewById(R.id.rtitle);
            amount_tv = itemView.findViewById(R.id.ramount);
            date_tv= itemView.findViewById(R.id.rdate);
            time_tv= itemView.findViewById(R.id.rtime);
            note_tv = itemView.findViewById(R.id.small_notes);
            timestamp_tv = itemView.findViewById(R.id.rtimeStamp);

        }
    }
}
