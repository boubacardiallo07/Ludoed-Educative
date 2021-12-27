package com.example.ludoed.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.ludoed.R;
import com.example.ludoed.model.DataManagment;
import com.example.ludoed.model.ImageProcessing;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.AbstractGameContext;
import com.example.ludoed.model.parameter.ColorationGameContext;
import com.example.ludoed.model.parameter.CorrespondenceGameContext;
import com.example.ludoed.model.parameter.GameContext;
import com.example.ludoed.model.parameter.RotationGameContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    // The path of the game data backup files
    private String pathCorrespondenceFile;
    private String pathColorationFile;
    private String pathRotationFile;

    private ImageProcessing imageProcessing;    // For the process of retrieving a path from an image

    // Hard storage of the data of each game in an array
    private static final int [] ROTATION = {R.drawable.chat_dessin, R.drawable.chien_image, R.drawable.modele_img};

    private static final int [] CORRESPONDENCEMODEL = {R.drawable.chat_dessin};
    private static final int [] CORRESPONDENCECORRESP = {R.drawable.chat_image};
    private static final int [] OTHER = {R.drawable.chien_image};

    private static final int [] COLORMODEL = {R.drawable.cercle_p, R.drawable.cercle_v, R.drawable.etoile_p, R.drawable.etoile_v, R.drawable.triangle_p, R.drawable.triangle_v};

    // Represents the games container and the main container
    AbstractGameContext dataCorrespondenceGame;
    AbstractGameContext dataColorGame;
    AbstractGameContext dataRotationGame;

    GameContext container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init(); // Data initialization

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(Settings.GAME_CONTEXT, container);  // We pass the container to the next activity
                startActivity(intent);

                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            }
        };

        //handler post delay
        new Handler().postDelayed(runnable, Settings.WAITING_TIME);
    }


    /*
     Method to initialize all data before starting to play
     */
    public void init() {
        imageProcessing = new ImageProcessing();

        pathCorrespondenceFile = getApplicationContext().getFilesDir() + "/" + Settings.CORRESPONDENCE_FILE_NAME;
        pathColorationFile = getApplicationContext().getFilesDir() + "/" + Settings.COLORATION_FILE_NAME;
        pathRotationFile = getApplicationContext().getFilesDir() + "/" + Settings.ROTATION_FILE_NAME;

        /*
         Open the data backup files for the different games
         */
        File fCorrespondence = new File(pathCorrespondenceFile);
        File fColoration = new File(pathColorationFile);
        File fRotation = new File(pathRotationFile);

        imageProcessing = new ImageProcessing();

        /* We check the existence of the backup files, if they exist, the data of each
        set is recovered by deserializing the containers stored in the files;
        Otherwise we recover the data in hard
        */
        if ( fCorrespondence.isFile() && fColoration.isFile() && fRotation.isFile() ) {

            Settings.setExecute(true);

            dataCorrespondenceGame = (AbstractGameContext) DataManagment.deSerialize(getApplicationContext(), Settings.CORRESPONDENCE_FILE_NAME);
            dataColorGame = (AbstractGameContext) DataManagment.deSerialize(getApplicationContext(), Settings.COLORATION_FILE_NAME);
            dataRotationGame = (AbstractGameContext) DataManagment.deSerialize(getApplicationContext(), Settings.ROTATION_FILE_NAME);

        }
        else {
            Map<Integer, String> correspondenceGameModel = new HashMap<Integer, String>();
            Map<Integer, String> correspondenceGameCorresp = new HashMap<Integer, String>();
            for ( int i = 0; i < CORRESPONDENCEMODEL.length; i++ ) {

                ImageView imgModel = new ImageView(getApplicationContext());
                Drawable drawableModel = getResources().getDrawable(CORRESPONDENCEMODEL[i]);
                imgModel.setImageDrawable(getDrawable(CORRESPONDENCEMODEL[i]));
                drawableModel = imgModel.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawableModel).getBitmap();
                String pathModel = imageProcessing.saveImage(bitmap, getApplicationContext(), CORRESPONDENCEMODEL[i]);

                ImageView imgCorresp = new ImageView(getApplicationContext());
                Drawable drawableCorresp = getResources().getDrawable(CORRESPONDENCECORRESP[i]);
                imgCorresp.setImageDrawable(getDrawable(CORRESPONDENCECORRESP[i]));
                drawableCorresp = imgModel.getDrawable();
                Bitmap bitmapCorresp = ((BitmapDrawable) drawableCorresp).getBitmap();
                String pathC = imageProcessing.saveImage(bitmapCorresp, getApplicationContext(), CORRESPONDENCECORRESP[i]);


                correspondenceGameModel.put(i, pathModel);
                correspondenceGameCorresp.put(i, pathC);
            }

            List<String> other = new ArrayList<String>();
            for ( int tab : OTHER ) {
                ImageView img = new ImageView(getApplicationContext());
                Drawable drawable = getResources().getDrawable(tab);
                img.setImageDrawable(getDrawable(tab));
                drawable = img.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                String path = imageProcessing.saveImage(bitmap, getApplicationContext(), tab);

                other.add(path);
            }

            Map<Integer, String> rotateGame = new HashMap<Integer, String>();
            for ( int i = 0; i < ROTATION.length; i++ ) {
                ImageView img = new ImageView(getApplicationContext());
                Drawable drawable = getResources().getDrawable(ROTATION[i]);
                img.setImageDrawable(getDrawable(ROTATION[i]));

                drawable = img.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                String path = imageProcessing.saveImage(bitmap, getApplicationContext(), ROTATION[i]);

                rotateGame.put(i, path);
            }

            Map<Integer,String> colorGame = new HashMap<Integer, String>();
            for(int tab: COLORMODEL) {
                ImageView img = new ImageView(getApplicationContext());
                Drawable drawable = getResources().getDrawable( tab );

                img.setImageDrawable( getDrawable(tab) );

                drawable = img.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                String path = imageProcessing.saveImage(bitmap , this, tab);

                colorGame.put(tab, path);
            }

            ArrayList<Integer> list = new ArrayList<Integer>();
            dataCorrespondenceGame = new CorrespondenceGameContext(Settings.ONE, Settings.ONE, correspondenceGameModel, correspondenceGameCorresp, list, other);

            dataRotationGame = new RotationGameContext(Settings.ONE, Settings.ONE, rotateGame, list);

            dataColorGame = new ColorationGameContext(Settings.ONE, Settings.ONE, colorGame, list);
        }

        // Filling the container
        container = new GameContext(dataColorGame, dataCorrespondenceGame, dataRotationGame);
    }
}