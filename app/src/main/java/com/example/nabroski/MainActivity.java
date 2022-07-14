package com.example.nabroski;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOG";
    ImageView avatar;
    ImageView avatar_change;
    Button save_changes;
    Button bt_relog;
    ImageView bt_save;

    EditText change_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_room_layout);

        MotionLayout motion_container = findViewById(R.id.papa);

        ImageView bt_change=findViewById(R.id.bt_change);

        bt_save=findViewById(R.id.bt_save);
        bt_relog=findViewById(R.id.users_relog);

        avatar_change=findViewById(R.id.users_avatar_change);
        avatar=findViewById(R.id.users_avatar);
        change_name=findViewById(R.id.users_name_change);

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
                //код сохраняющий фото
                Bitmap bitmap = ((BitmapDrawable)avatar.getDrawable()).getBitmap();
                saveReceivedImage(bitmap, "avatar");
                //а дальше я хз,нужно както чтото гдето
            }
        });
       avatar_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                resultimage.launch(intent);
            }
        });
        bt_relog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //код по выходу из аккаунта и запуску окна регистрации или входа
            }
        });

    }
    ActivityResultLauncher<Intent> resultimage= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== Activity.RESULT_OK)
            {
                Intent resultIntent=result.getData();
                Uri data =resultIntent.getData();
                avatar_change.setImageURI(data);
            }
        }

    });
    private void saveReceivedImage(Bitmap image, String imageName){
        try {
            File path = new File(avatar.getContext().getFilesDir(), "MyAppName" + File.separator + "Images");
            if(!path.exists()){
                path.mkdirs();
            }
            File outFile = new File(path, imageName + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(outFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Saving received message failed with", e);
        } catch (IOException e) {
            Log.e(TAG, "Saving received message failed with", e);
        }
    }
}