package com.example.mmu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mmu.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update_Profile extends AppCompatActivity
{


    private RadioButton genderbutton;

    private RadioGroup radioGroup;
    private TextInputEditText name,phone,sesion;
    private  Button submit;
    private String gender;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__profile);
        //hookes

        name = findViewById(R.id.update_fullname_id);
        phone = findViewById(R.id.update_phone_id);
        sesion = findViewById(R.id.update_session_id);
        submit = findViewById(R.id.update_submit_button_id);

        //INITALIZE DATABASE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Update_User_Profile");


//------

        //__

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String email = bundle.getString("email");
            sesion.setText(email);

        }


        // Submit database work

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit.setBackgroundResource(R.color.button_color);


                //Gender
                radioGroup = findViewById(R.id.update_radioGroup_id);


                int selectedId = radioGroup.getCheckedRadioButtonId();

                genderbutton = findViewById(selectedId);

                gender = genderbutton.getText().toString();

                //---


                String name_string = name.getText().toString().trim();
                String phone_string = phone.getText().toString().trim();
                String session_string = sesion.getText().toString().trim();

                String gender_string = genderbutton.getText().toString().trim();


                //validation
                if (name_string.isEmpty()) {
                    name.setError("Required Field");
                    name.requestFocus();
                    return;
                }


                if (phone_string.length() != 10) {
                    phone.setError("*Required .Enter your 10 digit mobile no.");
                    phone.requestFocus();
                    return;
                }


//------------------


                String key = databaseReference.push().getKey();

                Update_mobule update_mobule = new Update_mobule(name_string, phone_string, session_string, gender_string);


                databaseReference.child(key).setValue(update_mobule);

                Toast.makeText(getApplicationContext(), "Sucessfully update your Details !", Toast.LENGTH_LONG).show();


            }
        });





    }





}









