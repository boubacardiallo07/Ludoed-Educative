package com.example.ludoed.controller.gameactivities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.example.ludoed.R;
import com.example.ludoed.controller.GameActivity;
import com.example.ludoed.model.AnimationPieces;
import com.example.ludoed.model.DataManagment;
import com.example.ludoed.model.ImageProcessing;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.SplitImage;
import com.example.ludoed.model.games.RotationGameModel;
import com.example.ludoed.model.parameter.AbstractGameContext;
import com.example.ludoed.model.parameter.RotationGameContext;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RotationGameActivity extends AbstractGameActivity {
    List<AnimationPieces> allAnimationPieces;   // A list for the management of clicks on the pieces
    private static boolean verify = Settings.isExecute();  // To read the data in 'file' only once after restarting the game
    private static int numberInstance = 0;  // To retrieve the image to display
    private ImageView modelImage;   // The model image to display in front of the user
    private String path;    // To retrieve the path of the "fileName" file
    private File file; // To open a file in the "path" directory


    Intent intent;
    Handler handler = new Handler();


    /**
     * here we will initialize our activity
     * @param savedInstanceState saved Instance State
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        init(); // Data initialization

        super.onCreate(savedInstanceState);
    }

    /**
     * Method used to initialize all the objects
     */
    public void init() {

        setContentView(R.layout.activity_rotate_game);
        model = new RotationGameModel();

        modelImage = findViewById(R.id.model_image);

        path = getApplicationContext().getFilesDir() + "/"+ Settings.ROTATION_GAME_FILE_NAME;

        file = new File(path);

        allAnimationPieces = new ArrayList<AnimationPieces>();
    }


    @Override
    public void setGameFeatures() {
        String pathImage;   // Le chemin de l'image à afficher
        ImageProcessing imageProcessing = new ImageProcessing();    // To retrieve an image from a path and vice versa



        /**
         * We load an image for a new game or to continue on an already started game.
         * First we check if the save data file of the current game exists and then we make sure
         * to execute this block of instructions only once as long as the application has not been restarted.
         *
         */
        if( file.isFile() && verify  ) {
            AbstractGameContext data = (AbstractGameContext) DataManagment.deSerialize(getApplicationContext(), Settings.ROTATION_GAME_FILE_NAME);

            for(Map.Entry<Integer, String > entry: data.get_imageList().entrySet()) {
                numberInstance = entry.getKey();
                break;
            }

            pathImage = container.get_rotation().get_imageList().get(numberInstance);

            Bitmap imageBitmap = imageProcessing.loadBitmapFromFile(pathImage);
            modelImage.setImageBitmap(imageBitmap);

            verify = false;
        }
        else {
            pathImage = container.get_rotation().get_imageList().get(numberInstance);
            Bitmap imageBitmap;
            imageBitmap = imageProcessing.loadBitmapFromFile(pathImage);
            modelImage.setImageBitmap(imageBitmap);
        }

        // Adding the model image to the imgList
        imageList.add(modelImage);

        List<String> listSmallImages =  SplitImage.splitImage(getApplicationContext(), modelImage, 2);

        List<ImageView> pieces = new ArrayList<ImageView>(); //Temporary parts list for the current game
        pieces.add(findViewById(R.id.piece1));
        pieces.add(findViewById(R.id.piece2));
        pieces.add(findViewById(R.id.piece3));
        pieces.add(findViewById(R.id.piece4));

        for (int i = 0; i<listSmallImages.size(); i++){
            Bitmap imageBitmap;
            imageBitmap = imageProcessing.loadBitmapFromFile( listSmallImages.get(i) );
            pieces.get(i).setImageBitmap(imageBitmap);
        }

        /* Add all the animations in the list of animationPieces,
            Random rotation of different pieces and tuned for clicks
        */
        for (ImageView piece: pieces) {
            AnimationPieces animationPieces = new AnimationPieces(piece);
            allAnimationPieces.add(animationPieces);
        }
    }

    /**
     * listening for clicks on the different pieces as well
     * as playing a sound if they are all in the right position
     * and go to the next game
     */
    @Override
    protected void setListeners() {

        for (AnimationPieces piece: allAnimationPieces) {
            piece.getPiece().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (piece.getEndRotation() % Settings.DEGREE_END_ROTATION != Settings.ZERO) {
                        if (piece.rotateImage()) {
                            numberInstance++;
                            playVictorySound();

                            newGame(NB_MILLISEC_PLAYSOUND);  // Create a new game after victory
                        }
                    }
                }
            });
        }
    }

    /**
     *
     */
    @Override
    public void backAndDataSerialize() {
        Map<Integer, String> modelImage = new HashMap<Integer, String>();

        modelImage.put(numberInstance, Settings.ROTATION_GAME_FILE_NAME);

        List<Integer> sounds = new ArrayList<Integer>();

        AbstractGameContext rotationGame = new RotationGameContext( container.get_rotation().get_level(),
                container.get_rotation().get_difficulty(), modelImage, sounds);

        // Serialization of the data during the game on return
        DataManagment.serialize(getApplicationContext(), rotationGame, Settings.ROTATION_GAME_FILE_NAME);

        Intent intent  = new Intent(RotationGameActivity.this, GameActivity.class);
        intent.putExtra(Settings.GAME_CONTEXT, container);

        finish();

        startActivity(intent);
    }




    
}