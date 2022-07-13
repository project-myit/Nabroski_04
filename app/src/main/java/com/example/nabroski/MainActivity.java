package com.example.nabroski;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_room_layout);
        MotionLayout motion_container = findViewById(R.id.papa);
        ImageView bt_change=findViewById(R.id.bt_change);
        ImageView bt_save=findViewById(R.id.bt_save);
        Button bt_relog=findViewById(R.id.users_relog);
        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motion_container.transitionToEnd();
                bt_save.startAnimation(AnimationUtils.loadAnimation(motion_container.getContext(),R.anim.endalpha));
                bt_relog.startAnimation(AnimationUtils.loadAnimation(motion_container.getContext(),R.anim.endalpha));
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motion_container.transitionToStart();
                bt_save.startAnimation(AnimationUtils.loadAnimation(motion_container.getContext(),R.anim.myalpha));
                bt_relog.startAnimation(AnimationUtils.loadAnimation(motion_container.getContext(),R.anim.myalpha));
            }
        });
    }
}