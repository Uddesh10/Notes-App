package com.uddesh.notesapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.uddesh.notesapp.Database.NotesEntity;
import com.uddesh.notesapp.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository notesRepository;
    private LiveData<List<NotesEntity>> allNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        allNotes = notesRepository.getAllNotes();
    }

    public void insertNote(NotesEntity note)
    {
        notesRepository.insertNote(note);
    }
    public void updateNote(NotesEntity note)
    {
        notesRepository.updateNote(note);
    }
    public void deleteNote(NotesEntity note)
    {
        notesRepository.deleteNote(note);
    }

    public LiveData<List<NotesEntity>> getAllNotes() {
        return allNotes;
    }
}
