package com.example.ludoed.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ludoed.R;
import com.example.ludoed.controller.gameactivities.ColorationGameActivity;
import com.example.ludoed.controller.gameactivities.CorrespondenceGameActivity;
import com.example.ludoed.controller.gameactivities.RotationGameActivity;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.GameContext;

import java.util.HashSet;
import java.util.Set;

public class GameActivity extends Activity {

    private ImageView ivBack;
    private GameContext container;
    private Set<Button> buttons = new HashSet<Button>();
    protected Class classIntent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Rotate the screen
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_activity);
        init();
        setButtonslisteners();
    }

    /**
     * manages clicks to start the right activitie
     */
    private void setButtonslisteners() {
        for(Button b : buttons){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btCorrespGame:
                            classIntent = CorrespondenceGameActivity.class;
                            break;
                        case R.id.btColorGame:
                            classIntent = ColorationGameActivity.class;
                            break;
                        case R.id.btRotationGame:
                            classIntent = RotationGameActivity.class;
                        default:
                            break;
                    }
                    startAnotherActivity(classIntent);
                }
            });
        }
        ivBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startAnotherActivity(MainActivity.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    /**
     * starts a new Activity
     * @param c The component class that is to be used for the intent
     */
    private void startAnotherActivity( Class c ) {
        Intent intent = new Intent(GameActivity.this, c);
        intent.putExtra(Settings.GAME_CONTEXT, container);
        startActivity(intent);
        finish();
    }

    /**
     * Initializes all the variables and objects necessary
     * for the proper functioning of this activity
     */
    public void init(){
        buttons.add((Button) findViewById(R.id.btCorrespGame));
        buttons.add((Button) findViewById(R.id.btColorGame));
        buttons.add((Button) findViewById(R.id.btRotationGame));

        ivBack = (ImageView) findViewById(R.id.iv_back);

        Intent i = getIntent();
        container = (GameContext)i.getSerializableExtra(Settings.GAME_CONTEXT);

    }

    @Override
    public void onBackPressed() {

    }


}
