package com.example.shariqkhan.socialconnect;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shariqkhan.socialconnect.Adapter.AdapterChat;
import com.example.shariqkhan.socialconnect.Model.MessageModel;
import com.example.shariqkhan.socialconnect.Model.Messages;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    TextView time, name, date, message;
    RecyclerView recyclerView;
    String id = "1";
    AdapterChat adapter;
    Button b1;

    DatabaseReference mRootRef;

    ArrayList<Messages> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRootRef = FirebaseDatabase.getInstance().getReference().child("Messages");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        b1 = (Button) findViewById(R.id.pressMe);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterChat(arrayList);
        recyclerView.setAdapter(adapter);

        loadMessages();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }

        });


    }

    private void loadMessages() {

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Messages");

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    Log.e("Inside", "");
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        //arrayList.add(String.valueOf(dsp.geValue()));
                        Messages m = new Messages();

                        String name = dsp.child("name").getValue().toString();
                        String message = dsp.child("message").getValue().toString();
                        String type = dsp.child("type").getValue().toString();
                        m.setMessage(message);
                        m.setFrom(name);
                        m.setType(type);
                        arrayList.add(m);

                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });
    }


    private void sendMessage() {

        //   Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();
        String gettingText = "This is message for checking";
        if (!TextUtils.isEmpty(gettingText)) {
            //Defining strings takay har bar reference mai likhna na paray

            String current_user_reference = "Messages/";
            String chat_user_reference = "Messages/";
            //for push id of user

            DatabaseReference user_message_push_id = mRootRef
                    .push();

            String push_id = user_message_push_id.getKey();

            Map messageMap = new HashMap();
            messageMap.put("name", "Shariq");
            messageMap.put("message", gettingText);
            messageMap.put("seen", false);
            messageMap.put("type", "text");
            messageMap.put("time", ServerValue.TIMESTAMP);


            Map messageUserMap = new HashMap();

            messageUserMap.put("/" + push_id, messageMap);
//            messageUserMap.put(chat_user_reference + "/" + push_id, messageMap);

            mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError != null) {
                        Log.d("DbError", databaseError.getMessage().toString());
//                        mMessage.setText("");
                    } else {
                        arrayList.clear();
                        loadMessages();
                    }

                }
            });


        }

    }
}