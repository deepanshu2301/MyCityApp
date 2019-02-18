package com.dipanshu.mycityapp.Weather_dir;

import java.util.ArrayList;

public class hori_data {

    private ArrayList<hori_detail> list;


    public hori_data(ArrayList<hori_detail> list) {
        this.list = list;
    }

    public ArrayList<hori_detail> getList() {
        return list;
    }

    public void setList(ArrayList<hori_detail> list) {
        this.list = list;
    }
}
