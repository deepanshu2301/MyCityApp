package com.dipanshu.mycityapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.SuperheroHolder> {

    private int count;
    private ArrayList<details> eventdetails;
    private Context ctx;
    private AppCompatActivity appCompatActivity;

    public EventAdapter(ArrayList<details> details, AppCompatActivity activity) {
        this.eventdetails = details;
        this.appCompatActivity = activity;
    }

    @NonNull
    @Override
    public SuperheroHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ctx = viewGroup.getContext();
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View inflatedView = li.inflate(R.layout.single_event, viewGroup, false);
        return new SuperheroHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperheroHolder superheroHolder, int position) {

        final details det = eventdetails.get(position);
        superheroHolder.title.setText(det.getName().getText());
        superheroHolder.time.setText(det.start.getLocal());
        superheroHolder.desc.setText(det.description.getText());
        if(det.logo.getOriginal().getUrl()!=null){
            Picasso.get().load(det.logo.getOriginal().getUrl()).into(superheroHolder.img);

        }

    }

    @Override
    public int getItemCount() {
        return eventdetails.size();
    }

    class SuperheroHolder extends RecyclerView.ViewHolder {

        TextView title, desc, time;
        ImageView img;

        public SuperheroHolder(@NonNull View view) {
            super(view);


            time=view.findViewById(R.id.etime);
            title=view.findViewById(R.id.etitle);
            desc=view.findViewById(R.id.edesc);
            img=view.findViewById(R.id.imageview);
        }

    }


}
