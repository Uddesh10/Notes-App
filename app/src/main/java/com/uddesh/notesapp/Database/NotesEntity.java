package com.uddesh.notesapp.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class NotesEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String notesTitle;
    private String notesDescription;
    private String notesBackgroundColor;
    private String noteDate;

    public NotesEntity(String notesTitle, String notesDescription, String notesBackgroundColor , String noteDate) {
        this.notesTitle = notesTitle;
        this.notesDescription = notesDescription;
        this.notesBackgroundColor = notesBackgroundColor;
        this.noteDate = noteDate;
    }
    @Ignore
    public NotesEntity(int id, String notesTitle, String notesDescription, String notesBackgroundColor, String noteDate) {
        this.id = id;
        this.notesTitle = notesTitle;
        this.notesDescription = notesDescription;
        this.notesBackgroundColor = notesBackgroundColor;
        this.noteDate = noteDate;
    }
    @Ignore
    public NotesEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public String getNotesDescription() {
        return notesDescription;
    }

    public String getNotesBackgroundColor() {
        return notesBackgroundColor;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setId(int id) {
        this.id = id;
    }
}
