package com.example.ludoed.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ludoed.R;
import com.example.ludoed.model.ImageProcessing;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.GameContext;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ParameterActivity extends Activity {
    private Button btnImport, btnReturn;
    private ImageView selectedImg, back, img2;
    private Bitmap used_bitmap;
    private ImageProcessing imageProcessing;
    private GameContext gameContext;
    private String path = "/storage/emulated/0/Android/data/com.example.ludoed/files/Pictures/LudoEducatif/Image2.jpg";
    private Bitmap imageBitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.parameter_activity);

        init();

        //int image = 1000;
        //if (gameContextContainer.get_coloration().get_imageList() != null){
        //    image = gameContextContainer.get_coloration().get_imageList().size();
        //}

       if (path != null){
           Log.i("-------> chemin d'acces", ""+ path);
            imageBitmap = imageProcessing.loadBitmapFromFile(path);
            img2.setImageBitmap(imageBitmap);
       }

        //Log.i("------------------>", ""+image);

        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Settings.RESULT_LOAD_IMG);
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (path != null){
            Log.i("-------> chemin d'acces", ""+ path);
            imageBitmap = imageProcessing.loadBitmapFromFile(path);
            img2.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                used_bitmap = Bitmap.createScaledBitmap( selectedImage.copy(selectedImage.getConfig(),true),
                        (selectedImage.getWidth()* Settings.IMAGE_SIZE)/selectedImage.getHeight(),
                        Settings.IMAGE_SIZE,false );

                path = imageProcessing.saveImage(used_bitmap , this, Settings.NUMBER_OF_IMAGE);

                if (path != null){
                    Log.i("-------> chemin d'acces", ""+ path);
                    //imageBitmap = imageProcessing.loadBitmapFromFile(path);
                    //img2.setImageBitmap(imageBitmap);
                }

                selectedImg.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Une erreur s'est produite",Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(getApplicationContext(),"Vous n'avez pas choisi d'image", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Cette methode permet de désactiver la touche retour de l'appareil
     */
    public void onBackPressed() {
        //return nothing
        return;
    }

    /**
     * Cette methode permet d'initialiser toutes les variables utilisées dans cette activité
     */
    void init(){
        btnImport = findViewById(R.id.import_img);
        selectedImg = findViewById(R.id.selected);

        img2 = (ImageView) findViewById(R.id.img2);
        back = (ImageView) findViewById(R.id.iv_back);

        imageProcessing = new ImageProcessing();

        Intent i = getIntent();
        gameContext = (GameContext)i.getSerializableExtra("gameContext");

    }
}