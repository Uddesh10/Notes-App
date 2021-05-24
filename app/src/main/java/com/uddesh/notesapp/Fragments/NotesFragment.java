package com.uddesh.notesapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uddesh.notesapp.Activities.AddNotesActivity;
import com.uddesh.notesapp.Adapters.NotesRecyclerViewAdapter;
import com.uddesh.notesapp.Database.NotesEntity;
import com.uddesh.notesapp.R;
import com.uddesh.notesapp.ViewModel.NotesViewModel;

import java.util.List;


public class NotesFragment extends Fragment {
    private int layoutPreference;
    SharedPreferences sharedPreferences;
    private ImageView notesListView , notesGridView;
    RecyclerView recyclerView;
    FloatingActionButton addNotesFab;
    NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    NotesViewModel notesViewModel;
    public NotesFragment() {
        // Required empty public constructor
    }


    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notesViewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(NotesViewModel.class);
        addNotesFab = getView().findViewById(R.id.floatingActionButton);
        recyclerView = getView().findViewById(R.id.recycler_view);
        notesListView = getView().findViewById(R.id.notes_list_view);
        notesGridView = getView().findViewById(R.id.notes_grid_view);
        sharedPreferences = this.getActivity().getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE);
        layoutPreference = sharedPreferences.getInt("layoutPreference" , 0);
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(notesRecyclerViewAdapter);
        if(layoutPreference == 0)
        {

            notesGridView.setVisibility(View.GONE);
            notesListView.setVisibility(View.VISIBLE);
           recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL));
        }
        else
        {
            notesListView.setVisibility(View.GONE);
            notesGridView.setVisibility(View.VISIBLE);

            recyclerView.setLayoutManager(new LinearLayoutManager( getContext() ,LinearLayoutManager.VERTICAL , false));
        }

        notesListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPreference = 1;
                saveLayoutPreferenceInSharedPreference(layoutPreference);
                notesListView.setVisibility(View.GONE);
                notesGridView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager( getContext() ,LinearLayoutManager.VERTICAL , false));
            }
        });

        notesGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPreference = 0;
                saveLayoutPreferenceInSharedPreference(layoutPreference);
                notesGridView.setVisibility(View.GONE);
                notesListView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL));
            }
        });


        addNotesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newNotesIntent = new Intent(getContext() , AddNotesActivity.class);
                startActivity(newNotesIntent);
            }
        });

        notesViewModel.getAllNotes().observe(requireActivity(), new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notesEntities) {
                notesRecyclerViewAdapter.reloadRecyclerView(notesEntities);
            }
        });

   }

    private void saveLayoutPreferenceInSharedPreference(int layoutPreference)
    {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("layoutPreference" , layoutPreference);
        editor.apply();
    }


}