package com.dipanshu.mycityapp.Weather_dir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dipanshu.mycityapp.R;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {

    ArrayList<datab> notes;
    Context ctx;
    Activity activity;

    public NotesAdapter(ArrayList<datab> notes, Context ctx, FragmentActivity activity) {
        this.notes = notes;
        this.ctx = ctx;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public datab getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View inflatedView;

        LayoutInflater li = LayoutInflater.from(parent.getContext());


        if (convertView == null)
            inflatedView = li.inflate(R.layout.savedtab, parent, false);
        else
            inflatedView = convertView;

        final datab currentNote = getItem(position);

        TextView savedcity;
        savedcity = inflatedView.findViewById(R.id.savedcityname);
        ImageButton btnDelete = inflatedView.findViewById(R.id.dustbin);

//        Fragment newFragment =activity.FragmentB;
        // consider using Java coding conventions (upper first char class names!!!)
//        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
//        transaction.replace(R.id.fragment_container, newFragment);
//        transaction.addToBackStack(null);

        // Commit the transaction
//        transaction.commit();

        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in   =new Intent(activity, MainActivity1.class);
                in.putExtra("val","b");
                in.putExtra("cityname",currentNote.getName());
                activity.startActivity(in);
            }
        });

        final NoteDatabase nd = MyCityApp.getNoteDatabase(ctx);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nd.getNotesDao().deleteNote(notes.get(position));
                notifyDataSetChanged();
            }
        });

        savedcity.setText(currentNote.getName());

        return inflatedView;
    }

//    listvu.setOnItemClickListener(new ListView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String name= (String) parent.getItemAtPosition(position);
//            Toast.makeText(getContext(),""+name+" clicked !",Toast.LENGTH_SHORT).show();
//            Intent in   =new Intent(getContext(),FragmentA.class);
//            in.putExtra("cityname",name);
//            startActivity(in);
//        }
//    });
}
