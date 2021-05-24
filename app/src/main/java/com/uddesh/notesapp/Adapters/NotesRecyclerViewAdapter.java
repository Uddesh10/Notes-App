package com.uddesh.notesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uddesh.notesapp.Activities.AddNotesActivity;
import com.uddesh.notesapp.Database.NotesEntity;
import com.uddesh.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewHolder> {
    List<NotesEntity> allNotes = new ArrayList<>();
    Context mContext;

    public NotesRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public NotesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_recycler_ui , parent , false);
        return new NotesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerViewHolder holder, final int position) {
        holder.getNoteDate().setText(allNotes.get(position).getNoteDate());
        holder.getNoteContent().setText(allNotes.get(position).getNotesDescription());
        holder.getNoteTitle().setText(allNotes.get(position).getNotesTitle());
        holder.getLinearLayout().setBackgroundColor(Color.parseColor(allNotes.get(position).getNotesBackgroundColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddNotesActivity.class);
                intent.putExtra("noteBackgroundColor" , allNotes.get(position).getNotesBackgroundColor());
                intent.putExtra("noteDescription" , allNotes.get(position).getNotesDescription());
                intent.putExtra("noteTitle" , allNotes.get(position).getNotesTitle());
                intent.putExtra("noteId" , allNotes.get(position).getId());
                intent.putExtra("isUpdating" , true);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }


    public void reloadRecyclerView(List<NotesEntity> allNotes)
    {
        this.allNotes = allNotes;
        notifyDataSetChanged();

    }
}
class NotesRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView noteTitle , noteDate , noteContent;
    private LinearLayout linearLayout;
    public NotesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        noteTitle = itemView.findViewById(R.id.note_title);
        noteDate = itemView.findViewById(R.id.note_date);
        noteContent = itemView.findViewById(R.id.note_content);
        linearLayout = itemView.findViewById(R.id.linear_layout);
    }

    public TextView getNoteTitle() {
        return noteTitle;
    }

    public TextView getNoteDate() {
        return noteDate;
    }

    public TextView getNoteContent() {
        return noteContent;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}

