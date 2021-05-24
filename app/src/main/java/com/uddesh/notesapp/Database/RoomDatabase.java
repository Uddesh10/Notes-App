package com.uddesh.notesapp.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;

@Database(exportSchema = false ,version = 1 , entities = {NotesEntity.class })
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public static RoomDatabase notesRoomDatabaseInstance;
    public static final String DB_NAME = "NOTES_TABLE";
    public static RoomDatabase getInstance(Context context)
    {
        if(notesRoomDatabaseInstance == null)
        {
            notesRoomDatabaseInstance = Room.databaseBuilder(context.getApplicationContext() , RoomDatabase.class , DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return notesRoomDatabaseInstance;
    }

    public abstract NotesDao getNotesDao();
}
