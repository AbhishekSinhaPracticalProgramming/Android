package com.example.mmu_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mmu.R;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Animation animationt, animationb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        imageView = findViewById(R.id.logo_id);
        textView = findViewById(R.id.Univ_name_id);

        animationt = AnimationUtils.loadAnimation(this, R.anim.logo_zoom);
        animationb = AnimationUtils.loadAnimation(this, R.anim.text_move);

        imageView.setAnimation(animationt);
        textView.setAnimation(animationb);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Sign_in.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
}
