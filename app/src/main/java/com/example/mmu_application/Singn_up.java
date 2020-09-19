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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Singn_up extends AppCompatActivity implements View.OnClickListener {
    TextView signin;

private TextInputEditText username, email, password;
    Button signup_button;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singn_up);



        mAuth = FirebaseAuth.getInstance();
        // Hookes
        username = findViewById(R.id.Username_id);
        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        signin = findViewById(R.id.signup_id);
        signup_button = findViewById(R.id.signup_button_id);

        //Add onclickListener
        signin.setOnClickListener(this);

        signup_button.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.signup_id:
                // change Intent from signup to sign in
                Intent intent = new Intent(Singn_up.this, Sign_in.class);
                startActivity(intent);

                break;


            case R.id.signup_button_id:

                signup_button.setBackgroundResource(R.color.button_color);
                  userRegister();

                break;
        }

    }

    private void userRegister() {


        String username_string=username.getText().toString();
        String email_string=email.getText().toString().trim();
        String password_string=password.getText().toString().trim();

        //check  validity

        if(username_string.isEmpty()){
            username.setError("Enter Username");
            username.requestFocus();
            return;
        }

        if(email_string.isEmpty()){
            email.setError("Enter email id");
            email.requestFocus();
            return;
        }

        if(password_string.isEmpty()){
            username.setError("Enter password");
            password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()){

            email.setError("Enter a valid email address");
            email.requestFocus();

            return;
        }


      if(password_string.length()<6){
          password.setError("Minimum length of password should be 6");
          password.requestFocus();
          return;
      }
//-------------

        mAuth.createUserWithEmailAndPassword(email_string,password_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Register is sucessful !",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Dashboard.class);

                    startActivity(intent);

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();


                }

                else {

                    if(task.getException()instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already Registered!",Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error !"+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    }
                }


            }
        });



    }
}
