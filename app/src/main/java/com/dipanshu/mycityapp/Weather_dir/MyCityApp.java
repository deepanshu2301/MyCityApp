package com.dipanshu.mycityapp.Weather_dir;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

public class MyCityApp extends Application {

    static NoteDatabase noteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    static NoteDatabase getNoteDatabase(Context context) {
        if (noteDatabase == null) {
            noteDatabase = Room.databaseBuilder(context,
                    NoteDatabase.class,
                    "noteDatabase.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return noteDatabase;
    }

}
