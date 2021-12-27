package com.example.ludoed.controller.gameactivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



import com.example.ludoed.R;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.games.ColorationGameModel;

import static android.media.MediaPlayer.create;

public class ColorationGameActivity extends AbstractGameActivity implements View.OnClickListener {

    private ImageView emptyCircle, fullCircle, emptyTriangle, fullTriangle, emptyStar, fullStar;
    private Button redButton, blueButton, yellowButton;
    private int idButtonClicked, cptWin;
    private boolean correctChoiceCircle = false, correctChoiceTriangle = false , correctChoiceStar = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        initGameActivity();
        emptyCircle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v== emptyCircle && (idButtonClicked == R.id.button_red)) {
                    emptyCircle.setImageResource(R.drawable.cercle_p);
                    if (!correctChoiceCircle) {
                        correctChoiceCircle = true;
                        success();
                    }
                }else
                    wrongAnswer();
            }
        });
        emptyTriangle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v != fullTriangle)
                    if(v== emptyTriangle && (idButtonClicked == R.id.button_yellow)) {
                        emptyTriangle.setImageResource(R.drawable.triangle_p);
                        if (!correctChoiceTriangle) {
                            correctChoiceTriangle = true;
                            success();
                        }
                    }else
                        wrongAnswer();
            }
        });
        emptyStar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v!= fullStar)
                    if(v== emptyStar && (idButtonClicked == R.id.button_blue)) {
                        emptyStar.setImageResource(R.drawable.etoile_p);
                        if (!correctChoiceStar) {
                            correctChoiceStar = true;
                            success();
                        }
                    }else
                        wrongAnswer();
            }
        });
        super.onCreate(savedInstanceState);
    }


    public void initGameActivity(){
        setContentView(R.layout.activity_color_game);
        model = new ColorationGameModel();

        fullCircle = (ImageView) findViewById(R.id.ivCercleP);
        emptyCircle = (ImageView) findViewById(R.id.ivCercleV);
        fullTriangle = (ImageView) findViewById(R.id.iv_triangle_p);
        emptyTriangle = (ImageView) findViewById(R.id.iv_triangle_v);
        fullStar = (ImageView) findViewById(R.id.iv_etoile_p);
        emptyStar = (ImageView) findViewById(R.id.iv_etoile_v);

        redButton = (Button) findViewById(R.id.button_red);
        blueButton = (Button) findViewById(R.id.button_blue);
        yellowButton = (Button) findViewById(R.id.button_yellow);

        redButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);

        cptWin = 0;
    }

    @Override
    public void setGameFeatures() {
        //TODO
    }

    @Override
    protected void setListeners() {
        //TODO
    }

    @Override
    public void backAndDataSerialize() {

    }

    @Override
    public void onClick(View v) {
        idButtonClicked = v.getId();
    }

    /**
     * Called whenever the user has a correct answer
     */
    public void success(){
        cptWin ++;
        if(cptWin == Settings.THREE) {
            playVictorySound();

            newGame(NB_MILLISEC_PLAYSOUND);
        }
    }

}