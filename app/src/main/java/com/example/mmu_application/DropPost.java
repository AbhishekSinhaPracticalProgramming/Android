package com.example.mmu_application;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mmu.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DropPost extends AppCompatActivity
{

   private TextView emailview;
private TextInputEditText textInputEditText;

 private    Button submit;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_post);


        databaseReference = FirebaseDatabase.getInstance().getReference("postBox");

        emailview=findViewById(R.id.post_email_id);
        textInputEditText=findViewById(R.id.post_id);
        submit=findViewById(R.id.post_submit_button_id);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String email = bundle.getString("username2");
            emailview.setText(email);

        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPost();
            }
        });







    }

    private void newPost() {

        String post =textInputEditText.getText().toString();
        String email=emailview.getText().toString();

       String key = databaseReference.push().getKey();

      PostSubmitModule postSubmitModule = new PostSubmitModule(email,post );

        databaseReference.child(key).setValue(postSubmitModule);

        Toast.makeText(getApplicationContext(), "sucessfully  !", Toast.LENGTH_LONG).show();



    }

}
