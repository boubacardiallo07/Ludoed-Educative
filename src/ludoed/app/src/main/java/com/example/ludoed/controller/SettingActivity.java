package com.example.ludoed.controller;

import android.app.Activity;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;




import com.example.ludoed.R;
import com.example.ludoed.model.parameter.GameContext;

import java.util.HashSet;
import java.util.Set;


public class SettingActivity extends Activity  implements AdapterView.OnItemSelectedListener{

    private GameContext gameContext;
    Set<Spinner> spinners = new HashSet<Spinner>() ;
    Set<Button> buttons =new HashSet<Button>();
    Set<RatingBar> ratingBars = new HashSet<RatingBar>();

    private int lvlCorrespGame,lvlColorGame,lvlRotateGame;
    private int difficultyCorrespGame,difficultyColorGame,difficultyRotateGame;
    int id_button_clicked;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_activity);


        spinners.add(findViewById(R.id.spinner1));
        spinners.add(findViewById(R.id.spinner2));
        spinners.add(findViewById(R.id.spinner3));

        buttons.add(findViewById(R.id.but_import_img_1));
        buttons.add(findViewById(R.id.but_import_img_2));
        buttons.add(findViewById(R.id.but_import_img_3));
        buttons.add(findViewById(R.id.but_import_song));

        ratingBars.add(findViewById(R.id.ratingBar1));
        ratingBars.add(findViewById(R.id.ratingBar2));
        ratingBars.add(findViewById(R.id.ratingBar3));

        //to file in the our spinner
        for(Spinner sp : spinners) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);
            sp.setOnItemSelectedListener(this);
        }

        for(Button bt : buttons){
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_button_clicked = v.getId();
                    importFile(id_button_clicked);
                }
            });
        }
        for(RatingBar bar : ratingBars){
            bar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int difficultySelected = (int) bar.getRating();
                    switch (v.getId()){
                        case R.id.ratingBar1 :
                            difficultyColorGame = difficultySelected;
                            break;
                        case R.id.ratingBar2 :
                            difficultyCorrespGame = difficultySelected;
                            break;
                        case R.id.ratingBar3 :
                            difficultyRotateGame = difficultySelected;
                            break;
                    }
                    return false;
                }
            });
    }



    }
    public void importFile(int id_button_clicked){
        switch (id_button_clicked){
            case R.id.but_import_img_1 :
                //TODO : code import image for game 1


                break;
            case R.id.but_import_img_2:
                //TODO : code import image for game 2

                break;
            case R.id.but_import_img_3 :
                //TODO : code import image for game 3

                break;
            case R.id.but_import_song :
                //TODO : code import the song

            default:
                break;
        }
    }



    //for item selected on spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item_selected_str = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),item_selected_str,Toast.LENGTH_SHORT).show();
        int levelSelected = Integer.parseInt(item_selected_str);
        switch (parent.getId()) {
            case R.id.spinner1:
                lvlCorrespGame = levelSelected;
                break;
            case R.id.spinner2:
                lvlColorGame = levelSelected;
                break;
            case R.id.spinner3:
                lvlRotateGame = levelSelected;
                break;
            default:
                assert (true);
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}