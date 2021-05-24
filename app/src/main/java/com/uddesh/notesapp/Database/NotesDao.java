package com.uddesh.notesapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insertNote(NotesEntity note);

    @Query("SELECT * FROM Notes")
    LiveData<List<NotesEntity>> getAllNotes();

    @Update
    void updateNote(NotesEntity note);

    @Delete
    void deleteNote(NotesEntity note);
}
