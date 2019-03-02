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

    private String afrom;

    public String getAto() {
        return ato;
    }

    public void setAto(String ato) {
        this.ato = ato;
    }

    private String ato;

    public String getAfrom() {
        return afrom;
    }

    public void setAfrom(String afrom) {
        this.afrom = afrom;
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
