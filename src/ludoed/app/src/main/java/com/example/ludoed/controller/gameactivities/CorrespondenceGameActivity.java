package com.example.ludoed.controller.gameactivities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ludoed.R;
import com.example.ludoed.model.GameStatus;
import com.example.ludoed.model.games.CorrespondenceGameModel;

public class CorrespondenceGameActivity extends AbstractGameActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        initGameActivity();
        super.onCreate(savedInstanceState);
    }


    public void initGameActivity(){
        model = new CorrespondenceGameModel();
        setContentView(R.layout.activity_coresp_game);
    }

    @Override
    public void setGameFeatures(){
        ImageView drawing = (ImageView) findViewById(R.id.iv_dessin);
        imageList.add((ImageView) findViewById(R.id.iv_image1));
        imageList.add((ImageView) findViewById(R.id.iv_image2));
    }

    /*
     * Creates all setOnClickListeners for all imageViews in imgList.
     */
    public void setListeners(){

        for( ImageView img : imageList){
            img.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    gameOver = model.isGameOver(imageList.indexOf(img));
                    if(gameOver == GameStatus.SUCCESSFUL){
                        playVictorySound();
                    }else{
                        wrongAnswer();
                    }
                    newGame(NB_MILLISEC_PLAYSOUND);
                };
            });
        }
    }

    @Override
    public void backAndDataSerialize() {

    }


}

