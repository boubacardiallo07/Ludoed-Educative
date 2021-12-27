package com.example.ludoed.model.games;

import com.example.ludoed.model.GameModel;
import com.example.ludoed.model.GameStatus;
import com.example.ludoed.model.parameter.GameContext;

public class ColorationGameModel implements GameModel {
    int cpt_win = 0;
    int nb_to_color, id_button_clicked;



    @Override
    public GameStatus isGameOver() {
    return GameStatus.UNFINICHED;
    }

    @Override
    public GameStatus isGameOver(int n) {
        checkGoodColor(n);
        if (cpt_win == nb_to_color)
            return GameStatus.SUCCESSFUL;
        else return GameStatus.UNFINICHED;
    }

    @Override
    public void createGame(GameContext container) {
        nb_to_color = 3; //Hard for now.
    }

    public void painting(int occ){

    }

    public void checkGoodColor(int occ){

    }
}
