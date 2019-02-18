package com.dipanshu.mycityapp.Weather_dir;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {datab.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NotesDao getNotesDao();

}
