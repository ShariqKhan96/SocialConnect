package com.example.shariqkhan.socialconnect;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shariqkhan.socialconnect.Model.MessageModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    TextView time, name, date, message;
    RecyclerView recyclerView;
    String id = "1";
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
//        name=(TextView)findViewById(R.id.name);
//        message = (TextView)findViewById(R.id.message);
//        date= (TextView)findViewById(R.id.textView);
//        message = (TextView)findViewById(R.id.time);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        b1 = (Button) findViewById(R.id.pressMe);
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        final DatabaseReference messageRef = db.getReference().child("Messages");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //     cleanUpAdapter();

                HashMap<String, String> hashMap = new HashMap<>();

                Date date = Calendar.getInstance().getTime();

                messageRef.child("7");
                hashMap.put("message", "Hello this is me shariq");
                hashMap.put("date", date.toString());
                hashMap.put("name", "ShariqKhan");
                hashMap.put("time", String.valueOf(ServerValue.TIMESTAMP));


                messageRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChatActivity.this, "Yes we did it!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ChatActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }


        });


        DatabaseReference dbref = db.getReference().child("Messages").child(id);
        DatabaseReference dbref2 = db.getReference().child("Messages").child("2");
        DatabaseReference dbref3 = db.getReference().child("Messages").child("3");


        HashMap<String, String> hashMap = new HashMap<>();

        Date date = Calendar.getInstance().getTime();


        hashMap.put("message", "Hello this is me shariq");
        hashMap.put("date", date.toString());
        hashMap.put("name", "ShariqKhan");
        hashMap.put("time", String.valueOf(ServerValue.TIMESTAMP));


        dbref3.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ChatActivity.this, "Yes we did it!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dbref2.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ChatActivity.this, "Yes we did it!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ChatActivity.this, "Yes we did it!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //   dRef.setValue("Hello World");

//
//        adapter = new FirebaseRecyclerAdapter<MessageModel, MyHolder>(
//                MessageModel.class,
//                R.layout.item,
//                MyHolder.class,
//                messageRef) {
//            @Override
//            protected void populateViewHolder(MyHolder viewHolder, MessageModel model, int position) {
//
//                viewHolder.name.setText(model.getName());
//                viewHolder.time.setText(model.getTime());
//                viewHolder.message.setText(model.getMessage());
//                viewHolder.date.setText(model.getDate());
//            }
//        };
//        recyclerView.setAdapter(adapter);
//    }


    }


}