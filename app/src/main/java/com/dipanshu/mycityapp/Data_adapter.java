package com.dipanshu.mycityapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Data_adapter extends RecyclerView.Adapter<Data_adapter.dataholder> {

    private ArrayList<Article> arrayList;
    private final clicklistner listener;


    Context ctx;

    public Data_adapter(ArrayList<Article> arrayList , clicklistner listener) {
        this.arrayList = arrayList;
        this.listener=listener;
    }



//    used until recyclable layout is not present
    @NonNull
    @Override
    public dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View inflatedview=li.inflate(R.layout.layout_row,parent,false);
        return new dataholder(inflatedview,listener);
    }


//    called everytime
//    use when some work is to be done each and everytime
    @Override
    public void onBindViewHolder(@NonNull final dataholder holder, int position) {

        Article data1 =arrayList.get(position);
        holder.city.setText(data1.getCity());
        holder.message.setText(data1.getMessage());
        }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class dataholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView city, message;
        private WeakReference<clicklistner> listenerRef;


        public dataholder(@NonNull final View itemView , clicklistner listner) {
            super(itemView);
            listenerRef = new WeakReference<>(listner);
            message =itemView.findViewById(R.id.message1);
            city =itemView.findViewById(R.id.city1);

            listenerRef.get().onPositionClicked(getAdapterPosition());

            


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Article currentdata=arrayList.get(getAdapterPosition());
//                    Intent i = new Intent(ctx,otheractivity.class);
//                    i.putExtra("Title",currentdata.getAhead());
//                    i.putExtra("Category",currentdata.getAcategory());
//                    i.putExtra("Data",currentdata.getAdata());
//                    ctx.startActivity(i);
                }
            });
        }

//
        @Override
        public void onClick(View v) {
//            if (v.getId() == itemView.getId()) {
//                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            }
//
//            listenerRef.get().onPositionClicked(getAdapterPosition());
//
        }

    }

}
