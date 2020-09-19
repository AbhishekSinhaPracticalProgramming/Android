package com.example.mmu_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mmu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_alumni extends AppCompatActivity implements View.OnClickListener {
//Add Alumni

    TextInputEditText fullname, email, linkedin, phone, session, company, designation, brief;
    Button image_button, submit_button;

    //image URI
    private Uri imageUri;
    ImageView uploadImg;
    StorageTask uploadTask;

    //profile pic



    private static final int IMAGE_REQUEST = 1;

    //database reference

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    //Storage Reference

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumni);

        //initialize database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Add_Alumni");

        //intialize firebse database for Uplaod img
        databaseReference1=FirebaseDatabase.getInstance().getReference("Upload");
        storageReference= FirebaseStorage.getInstance().getReference("Upload");


        // Add Alumi Hookes

        fullname = findViewById(R.id.add_fullname_id);
        email = findViewById(R.id.add_email_id);
        linkedin = findViewById(R.id.add_linkedin_id);
        phone = findViewById(R.id.add_phone_id);
        session = findViewById(R.id.add_session_id);
        company = findViewById(R.id.add_Companyname_id);
        designation = findViewById(R.id.add_designation_id);
        brief = findViewById(R.id.add_brief_id);
        image_button = findViewById(R.id.add_uplaod_img_id);
        submit_button = findViewById(R.id.add_submit_button_id);
        uploadImg= findViewById(R.id.add_upload_id);



        //Add listener

        image_button.setOnClickListener(this);
        submit_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_uplaod_img_id:

                openFileChooser();


                break;


            case R.id.add_submit_button_id:

                submit_button.setBackgroundResource(R.color.button_color);

                uploadimage();
                if(uploadTask!=null&&uploadTask.isInProgress())
            {
                Toast.makeText(getApplicationContext(),"Your details is Uploading ...",Toast.LENGTH_LONG).show();
            }
                else{
                addAlumniDetails();
                Toast.makeText(getApplicationContext(),"Sucessfully upload your details !",Toast.LENGTH_LONG).show();


            }


                break;
        }


    }

    //get file extension

    public  String getFileExtension(Uri imageUri){

        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap =MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }

    //Uplaod img method

    private void uploadimage() {

        String name=fullname.getText().toString().trim();
        if(name.isEmpty())
        {
            fullname.setError("You must enter your name");
            fullname.requestFocus();
            return;
        }

        StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));


        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        String name_string = fullname.getText().toString();
                        String email_string = email.getText().toString();

                        String phone_string = phone.getText().toString();
                        String session_string = session.getText().toString();


                        Task<Uri> urlTask=taskSnapshot.getStorage().getDownloadUrl();

                        while (!urlTask.isSuccessful());

                        Uri downloadUrl= urlTask.getResult();

                       Uplaod uplaod =new Uplaod(name_string,email_string,phone_string,session_string,downloadUrl.toString());

                       String uploadId =databaseReference1.push().getKey();
                       databaseReference1.child(uploadId).setValue(uplaod);



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(getApplicationContext(),"Image is not Stored Sucessfully",Toast.LENGTH_LONG).show();
                    }
                });

    }

//Add alumni detalis method
    private void addAlumniDetails() {

        String fullname_string = fullname.getText().toString();
        String email_string = email.getText().toString();
        String linkedin_string = linkedin.getText().toString();
        String phone_string = phone.getText().toString();
        String session_string = session.getText().toString();
        String company_string = company.getText().toString();
        String designation_string = designation.getText().toString();
        String berif_string = brief.getText().toString();


        String key = databaseReference.push().getKey();

        Add_Alumni_module add_alumni_module = new Add_Alumni_module(fullname_string, email_string, linkedin_string, phone_string, session_string, company_string, designation_string, berif_string);


        databaseReference.child(key).setValue(add_alumni_module);

        Toast.makeText(getApplicationContext(), "You sucessfully add the Alumni Details !", Toast.LENGTH_LONG).show();

    }


    //File chooser

    private void openFileChooser() {

        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(uploadImg);

        }
    }
}

