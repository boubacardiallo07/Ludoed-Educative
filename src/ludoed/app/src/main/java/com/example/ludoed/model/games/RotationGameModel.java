package com.example.ludoed.model.games;

import com.example.ludoed.model.GameModel;
import com.example.ludoed.model.GameStatus;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.GameContext;

public class RotationGameModel implements GameModel {

    private int numberPieces;
    /*private static int ImageIndex = 0;
    private static int count = 0;
    private static final int NOMBER = 5;
    private  static  final int MULTIPLIER = 2; */

    public RotationGameModel() {
        this.numberPieces = Settings.ZERO;
    }

    @Override
    public GameStatus isGameOver() {
        return GameStatus.UNFINICHED;
    }

    @Override
    public GameStatus isGameOver(int n) {
        return GameStatus.UNFINICHED;
    }

    @Override
    public void createGame(GameContext container) {

        /*count ++;

        RotationParamContainer rotation = (RotationParamContainer) container.get_rotation();
        //this.modelImage = rotation.getModelImages().get(ImageIndex);

        int difficulty = rotation.getDifficulty();

        if(count == 1 || count == MULTIPLIER)
            this.numberPieces = count;
        else
            this.numberPieces = count * MULTIPLIER;


        if(count == NOMBER ){
            ImageIndex++;
            count =0;
            rotation.setDifficulty(difficulty ++);
            //rotationParamContainer.setDifficulty(difficulty++);
        }


        // Appel de la méthode qui découpe l'image modèle en numberPieces
*/
    }
}
