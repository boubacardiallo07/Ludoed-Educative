package com.example.ludoed.controller.gameactivities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ludoed.R;
import com.example.ludoed.model.AnimationProcessing;
import com.example.ludoed.model.GameModel;
import com.example.ludoed.model.GameStatus;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.GameContext;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractGameActivity extends Activity {

    protected static int NB_MILLISEC_PLAYSOUND = 3000;

    protected GameModel model;
    protected GameContext container;
    protected List<ImageView> imageList = new ArrayList<ImageView>();
    private static AnimationProcessing victorySound;
    protected GameStatus gameOver;
    private ImageView button1, button2;
    private boolean pressButton1 = false, pressButton2 = false;
    private Intent i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGlobal();

        setReturnButtonListeners();
        setGameLoop();
    }


    /**
     * This method consists in initializing all the variables necessary
     * for the good functioning of this activity
     */
    public void initGlobal(){

        i = getIntent();
        container = (GameContext) i.getSerializableExtra(Settings.GAME_CONTEXT);

        victorySound = new AnimationProcessing();

        button1 = (ImageView) findViewById(R.id.touche1);
        button2 = (ImageView) findViewById(R.id.touche2);

        setGameContext();
    }

    @Override
    public void onDestroy()
    {
        victorySound.stopSound();
        super.onDestroy();
    }

    /**
     * This method allows you to rotate the activity in landscape mode and to hide the notification bar.
     */
    public void setGameContext(){
        //Rotate the screen
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /* Function designed to create a new game, used to start over, or continue playing*/

    /**
     * This method allows you to create a new game
     */
    public void setGameLoop(){
        gameOver = GameStatus.UNFINICHED;
        model.createGame(container);
        setGameFeatures();
        setListeners();
    }

    /** Here are functions to be implemented for each game
     *   setGameFeatures -> allows to implement Imageviews (and buttons?) for a game
     *   setListeners() -> for event management on imageView
     *   backAndDataSerialize() -> This function allows to save all the data of the current game in
     *      the container before handing over the activity "GameActivity" and finish the current activity
     */
    public abstract void setGameFeatures();
    protected abstract void setListeners();
    public abstract void backAndDataSerialize();

    //Add le lien vers l'image, en string ou btmp?

    /**
     * This method allows me to add the link to the image
     * @param id key of the image
     * @param link path to the image
     */
    public void addImage(int id, String link){
        ImageView img = (ImageView) findViewById(id);
        imageList.add(img);
    }


    /**
     * Function that allows to play the sound
     */
    public void playVictorySound(){
        victorySound.playSound(this, R.raw.ring, false);
    }

    /**
     * This function allows to create a new part
     */
    public void newGame(int nbMillisecPlaySound) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                victorySound.stopSound();
                setGameLoop();  // Create a new game after victory
            }
        }, nbMillisecPlaySound);
    }


    /**
     * Allows to manage the selection on a bad image
     */
    public void wrongAnswer(){
        Toast toast = new Toast(getBaseContext());
        LinearLayout layoutImage = new LinearLayout(getBaseContext());
        ImageView wrongAnswerImage = new ImageView(getBaseContext());
        wrongAnswerImage.setImageResource(R.drawable.i_false);
        layoutImage.addView(wrongAnswerImage);
        toast.setView(layoutImage);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * This method allows you to check if you have decided to quit on the current game
     */
    public void retourPage() {
        if (!pressButton1) {
            pressButton1 = true;
            if (pressButton2) {
                finish();
                backAndDataSerialize();
            }
        } else {
            if (!pressButton2) {
                finish();
                backAndDataSerialize();
            }
        }
    }

    /**
     * Allows you to deactivate the return button of the telephone
     */
    public void onBackPressed() {
        //return nothing
        return;
    }

    /**
     * For key management to manage the return to a previous activity
     */
    public void setReturnButtonListeners() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourPage();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourPage();
            }
        });
    }

}

