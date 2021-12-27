package com.example.ludoed.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ludoed.R;
import com.example.ludoed.model.DataManagment;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.AbstractGameContext;
import com.example.ludoed.model.parameter.GameContext;

public class MainActivity extends AppCompatActivity {

    private Button play, parameter;
    private ImageView img, close, ivParametre;
    public GameContext container;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // allows to put in landscape mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);

        intent = getIntent();
        container = (GameContext)intent.getSerializableExtra(Settings.GAME_CONTEXT);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // initialization of properties
        play = (Button) findViewById(R.id.bt_play);
        parameter = (Button) findViewById(R.id.bt_parameter);
        img = (ImageView) findViewById(R.id.imageView);
        close = (ImageView) findViewById(R.id.iv_close);
        ivParametre = (ImageView) findViewById(R.id.iv_parametre);

        img.setVisibility(View.INVISIBLE);
        parameter.setVisibility(View.INVISIBLE);


        // switch from "MainActivity" to "GameActivity" by sending data after clicking the button.
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra(Settings.GAME_CONTEXT, container);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


        // switch from "MainActivity" to "SettingActivity" by sending data after clicking the button.
        ivParametre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

            }
        });


        /**
         * setOnClickListener allows to end this activity and to serialize the data of each game in
         * a file before exiting the application
         */
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                intent = getIntent();
                container = (GameContext) intent.getSerializableExtra(Settings.GAME_CONTEXT);

                AbstractGameContext correspondenceParamContainer = container.get_correspondance();
                AbstractGameContext colorationParamContainer = container.get_coloration();
                AbstractGameContext rotationParamContainer = container.get_rotation();


                DataManagment.serialize(getApplicationContext(), correspondenceParamContainer, Settings.CORRESPONDENCE_FILE_NAME);
                DataManagment.serialize(getApplicationContext(), colorationParamContainer, Settings.COLORATION_FILE_NAME);
                DataManagment.serialize(getApplicationContext(), rotationParamContainer, Settings.ROTATION_FILE_NAME);
            }
        });

    }
/*
    @Override
    public void onBackPressed() {
        //return nothing
        return;
    }
    */

}