package com.example.mmu_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mmu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Student_Sign_in extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText email, password;
    private TextView signup;
    private Button singinButton;
    private  TextView studentLoginText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__sign_in);


        mAuth = FirebaseAuth.getInstance();

        //Hooks

        email = findViewById(R.id.email_singnin_id);
        password = findViewById(R.id.password_singnin_id);
        signup = findViewById(R.id.new_user_signup);
        singinButton = findViewById(R.id.signin_button_id);
        studentLoginText=findViewById(R.id.login_as_student_id);


        singinButton.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.new_user_signup:
                // change Intent from signin to sign up
                Intent intent = new Intent(getApplicationContext(), Singn_up.class);

                startActivity(intent);

                break;

            case R.id.signin_button_id:


                usersingnin();

                break;

        }

    }

    private void usersingnin() {

        String email_string = email.getText().toString().trim();

        String password_string = password.getText().toString().trim();


        //validation
        if (email_string.isEmpty()) {
            email.setError("Enter email id");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {

            email.setError("Enter a valid email address");
            email.requestFocus();

            return;
        }


        if (password_string.length() < 6) {
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;
        }
//-------------


        mAuth.signInWithEmailAndPassword(email_string, password_string).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    singinButton.setBackgroundResource(R.color.button_color);

                    Intent intent = new Intent(getApplicationContext(), StudentDashboard.class);
                    String email_string1 ;
                    email_string1=email.getText().toString().trim();
                    intent.putExtra("username",email_string1);
                    startActivity(intent);

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();


                } else {


                    Toast.makeText(getApplicationContext(), "Invalid email id or Password !", Toast.LENGTH_LONG).show();


                }


            }
        });

    }
}

