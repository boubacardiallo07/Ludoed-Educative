package com.example.ludoed.model;

import com.example.ludoed.model.parameter.GameContext;

public interface GameModel {

    public GameStatus isGameOver();

    public GameStatus isGameOver(int n);

    public void createGame(GameContext container);

}

