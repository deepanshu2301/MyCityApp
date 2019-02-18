package com.dipanshu.mycityapp.Weather_dir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipanshu.mycityapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class weatherAdapter extends BaseAdapter {

    ArrayList<hori_detail> currdata;
    Context ctx;

    public weatherAdapter(ArrayList<hori_detail> currdata, Context ctx) {
        this.currdata = currdata;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return currdata.size();
    }

    @Override
    public hori_detail getItem(int position) {
        return currdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());

        View inflatedView;

        if (convertView == null)
            inflatedView = li.inflate(R.layout.hori_tab, parent, false);
        else
            inflatedView = convertView;

        hori_detail currentNote = getItem(position);

        TextView time, descp,temp;
        String fin=currentNote.getDt_txt();

//        String tt=fin.substring(0,11);
        String dd=fin.substring(11,16);

        time = inflatedView.findViewById(R.id.horitime);
        descp= inflatedView.findViewById(R.id.horidesc);
        temp = inflatedView.findViewById(R.id.horitemp);
        String ur = "http://openweathermap.org/img/w/" + currentNote.getWeather().get(0).getIcon() + ".png";
//                                Log.e("uri",":"+ur);
        ImageView im=inflatedView.findViewById(R.id.horiimage);
        Picasso.get().load(ur).into(im);
//
        Float curr= Float.parseFloat(currentNote.getMain().getTemp());
        int ans= (int) (curr-273.15);
        temp.setText(""+ans+"°");
        descp.setText(""+currentNote.getWeather().get(0).getMain());
        time.setText(""+dd);

        return inflatedView;
    }


}
