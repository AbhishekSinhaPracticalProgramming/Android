package com.example.mmu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.mmu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Our_Alumni extends AppCompatActivity {
    DatabaseReference databaseReference;

    private List <Add_Alumni_module> alumnilist;
    private  Sample_Layout_Adapter sample_layout_adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our__alumni);
      databaseReference= FirebaseDatabase.getInstance().getReference("Add_Alumni");


      alumnilist=new ArrayList<>();

      sample_layout_adapter=new Sample_Layout_Adapter(Our_Alumni.this,alumnilist);



        listView=findViewById(R.id.alumni_listview_id);



    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alumnilist.clear();

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    Add_Alumni_module add_alumni_module=dataSnapshot1.getValue(Add_Alumni_module.class);

                    alumnilist.add(add_alumni_module);
                }
                listView.setAdapter(sample_layout_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
