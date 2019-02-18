package com.dipanshu.mycityapp.Weather_dir;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

//    @Insert
//    void insertNote(Note note);

    @Insert
    List<Long> insertNotes(datab... notes);

    @Delete
    void deleteNote(datab note);

    @Query("SELECT * FROM noteTable")
    LiveData<List<datab>> getAllNotes();

    @Query("SELECT * FROM noteTable WHERE id = :id")
    datab getNoteById(Long id);

    @Update
    void updateNote(datab note);
}
