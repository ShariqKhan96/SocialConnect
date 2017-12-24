package com.example.shariqkhan.socialconnect.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shariqkhan.socialconnect.Model.Messages;
import com.example.shariqkhan.socialconnect.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShariqKhan on 12/23/2017.
 */

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.MyHolder> {

    ArrayList<Messages> arrayList = new ArrayList<>();

    public AdapterChat(ArrayList<Messages> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Messages m = arrayList.get(position);
        holder.message.setText(m.getMessage());
        holder.name.setText(m.getFrom());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
public static  class MyHolder extends RecyclerView.ViewHolder
{
    TextView name, message, date, time;
    View view;

    public MyHolder(View itemView) {
        super(itemView);
        view = itemView;
        name=(TextView)view.findViewById(R.id.name);
        message = (TextView)view.findViewById(R.id.message);
        date= (TextView)view.findViewById(R.id.textView);
        time = (TextView)view.findViewById(R.id.time);

    }
}

}
