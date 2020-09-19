package com.example.mmu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mmu.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class StudentDashboard extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    // Right menu
    ImageView rightMenu;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    View hView;
    TextView welcome;
    //header menu
    ImageView dashboard_person_profile_pic;
    TextView dashboard_username_menuheader;
    // Card view relativelayout
    View  addOurAlumni, viewProfile,aboutDeveloper,viewPost2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        mAuth=FirebaseAuth.getInstance();



//Welcome Hook
        welcome=findViewById(R.id.welcome_id);

        //set username in welcome

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String email = bundle.getString("username");
            welcome.setText(email);

        }
// Right menu Hookes
        rightMenu=findViewById(R.id.right_menu_icon);


        rightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent=new Intent(getApplicationContext(),Sign_in.class);
                startActivity(intent);


            }
        });










        // Menu Hookes

        drawerLayout = findViewById(R.id.drawerlayout_id);
        navigationView = findViewById(R.id.navigation_view_id);
        menuIcon = findViewById(R.id.menu_icon_id);

        //menu header Hookes

        dashboard_person_profile_pic = findViewById(R.id.dashboard_person_profile_id);
        dashboard_username_menuheader = findViewById(R.id.dashboard_username_menuheader);

        //Add cards

        addOurAlumni=findViewById(R.id.Alumi_card_id);
        viewProfile=findViewById(R.id.add_profile_card_id);
        aboutDeveloper=findViewById(R.id.about_developer_card_id);
viewPost2=findViewById(R.id.postviewS_card_id);

        //Card listener

        viewPost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getApplicationContext(),ViewPost.class);
                startActivity(intent);
            }
        });




        addOurAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Our_Alumni.class);
                startActivity(intent);

            }
        });


        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),ViewAlumniProfile.class);
                startActivity(intent);

            }
        });


        aboutDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Developer.class);
                startActivity(intent);


            }
        });





        //Navigation Drawer


        navigationDrawer();


    }





    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        navigationView.findViewById(R.id.drawerlayout_id);
        hView = navigationView.getHeaderView(0);

        //set profiles in menuheader

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String email = bundle.getString("username");
            dashboard_username_menuheader = (TextView) hView.findViewById(R.id.dashboard_username_menuheader);

            dashboard_username_menuheader.setText(email);
        }


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);


                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.home_id:
                break;

            case R.id.update_profile_menu:

                Bundle bundle = getIntent().getExtras();
                if(bundle!=null) {
                    String email = bundle.getString("username");


                    Intent intent = new Intent(getApplicationContext(), Update_Profile.class);
                    intent.putExtra("email",email);
                    startActivity(intent);

                }
                break;



            case  R.id.signout_menu:

                FirebaseAuth.getInstance().signOut();
                finish();

                Intent intent=new Intent(getApplicationContext(),Sign_in.class);
                startActivity(intent);
                break;

        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
