package com.uddesh.notesapp.Repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.uddesh.notesapp.Database.NotesDao;
import com.uddesh.notesapp.Database.NotesEntity;
import com.uddesh.notesapp.Database.RoomDatabase;
import java.util.List;

public class NotesRepository {
    private NotesDao notesDao;
    private LiveData<List<NotesEntity>> allNotes;

    public NotesRepository(Application application)
    {
        RoomDatabase database = RoomDatabase.getInstance(application);
        notesDao = database.getNotesDao();
        allNotes = notesDao.getAllNotes();
    }

    public void insertNote(final NotesEntity note)
    {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                notesDao.insertNote(note);
            }
        });
    }

    public void updateNote(final NotesEntity note)
    {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                notesDao.updateNote(note);
            }
        });
    }

    public void deleteNote(final NotesEntity note)
    {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                notesDao.deleteNote(note);
            }
        });
    }

    public LiveData<List<NotesEntity>> getAllNotes()
    {
        return allNotes;
    }
}

