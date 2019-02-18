package com.dipanshu.mycityapp.Weather_dir;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dipanshu.mycityapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment {


    ArrayList<datab> sending = new ArrayList<>();
    NotesAdapter na;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_b,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);


        final NoteDatabase noteDatabase = MyCityApp.getNoteDatabase(getActivity().getApplicationContext());
        final ListView listvu = getActivity().findViewById(R.id.savedlistviewaabb);
        na = new NotesAdapter(sending, getActivity().getApplicationContext(),getActivity());
        listvu.setAdapter(na);


        LiveData<List<datab>> liveNotes = noteDatabase.getNotesDao().getAllNotes();
        liveNotes.observe(this, new Observer<List<datab>>() {
            @Override
            public void onChanged(@Nullable List<datab> notes) {
                if (sending.size() != 0) {

                    notes.removeAll(sending);
                    sending.clear();
                    sending.addAll(notes);
                } else {
                    sending.addAll(notes);

                }
                Log.e("TAG", "size of database" + notes.size());
                na.notifyDataSetChanged();
            }
        });


//        listvu.setOnItemClickListener(new ListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String name= (String) parent.getItemAtPosition(position);
//                Toast.makeText(getContext(),""+name+" clicked !",Toast.LENGTH_SHORT).show();
//                Intent in   =new Intent(getContext(),FragmentA.class);
//                in.putExtra("cityname",name);
//                startActivity(in);
//            }
//        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menproblem:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "deepanshujindal40@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Problem reported in Weather APP");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey There ! I have noticed some issue in Weather App.The Details are as follows-");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //TODO smth
                }
                break;
            case R.id.menwrite:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "deepanshujindal40@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Weather App");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey There !");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //TODO smth
                }
                break;
            case R.id.menrate:

                Toast.makeText(getActivity(), "Thanks for your concern. Please use rating section on Play store.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.menpro:
                Toast.makeText(getActivity(), "This Service is under Maintenance.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mennoti:
                break;
            case R.id.menabout:
                Intent it = new Intent(getActivity(), sett.class);
                startActivity(it);
                break;

        }

        return false;
    }



}
