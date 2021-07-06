package com.example.profilecruddb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.profilecruddb.adapter.ViewUserAdapter;
import com.example.profilecruddb.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewUserAdapter viewUserAdapter;
    private DatabaseReference databaseReference;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recycler_view);
        viewUserAdapter = new ViewUserAdapter(this);
        viewUserAdapter.setUsers(users);
        recyclerView.setAdapter(viewUserAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    users.add(user);
                    Toast.makeText(ViewActivity.this, "Dumaan ako dito", Toast.LENGTH_SHORT).show();
                    System.out.println(users.get(0));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}