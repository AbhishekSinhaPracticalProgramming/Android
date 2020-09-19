package com.example.mmu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mmu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAlumniProfile extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Profile_Adapter profile_adapter;
    private List<Uplaod> uplaodList;
    private ProgressBar progressBar;
    DatabaseReference databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alumni_profile);

        progressBar=findViewById(R.id.progressbar_id);

        recyclerView=findViewById(R.id.recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uplaodList=new ArrayList<>();

        databaseReference2= FirebaseDatabase.getInstance().getReference("Upload");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    Uplaod uplaod=dataSnapshot1.getValue(Uplaod.class);
                    uplaodList.add(uplaod);

                }

                profile_adapter=new Profile_Adapter(ViewAlumniProfile.this,uplaodList);
                recyclerView.setAdapter(profile_adapter);

                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                Toast.makeText(getApplicationContext(),"Error :"+databaseError.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}
