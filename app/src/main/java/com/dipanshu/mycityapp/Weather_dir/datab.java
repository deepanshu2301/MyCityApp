package com.dipanshu.mycityapp.Weather_dir;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "noteTable")
public class datab {


    private String name;

    public datab(String name) {
        this.name = name;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
