package com.example.shariqkhan.socialconnect.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shariqkhan.socialconnect.R;

/**
 * Created by ShariqKhan on 12/23/2017.
 */

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.MyHolder> {

//    AdapterChat(ArrayAdapter<String>)
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
