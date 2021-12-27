package com.example.ludoed.model.games;

import com.example.ludoed.model.GameModel;
import com.example.ludoed.model.GameStatus;
import com.example.ludoed.model.Settings;
import com.example.ludoed.model.parameter.GameContext;

public class CorrespondenceGameModel implements GameModel {

    private static int number;
    private int goodOccurence;

    public CorrespondenceGameModel(){
        number = Settings.ZERO;
        goodOccurence = Settings.ZERO;
    }

    public GameStatus isGameOver(){
        return GameStatus.UNFINICHED;
    }

    public GameStatus isGameOver(int occ) {
        if(isSuccess(occ)){
            return GameStatus.SUCCESSFUL;
        }else{
            return GameStatus.UNSUCCESSFUL;
        }
    }


    public boolean isSuccess(int occ){
        return occ == goodOccurence;
    }

    public void createGame(GameContext container) {
        //CorrespondenceParamContainer correspContainer = (CorrespondenceParamContainer) container.get_correspondance();
        //int difficulty = correspContainer.getDifficulty();  //Here difficulty = nb wrong images


        //Map<Integer, Bitmap> dualImgList = correspContainer.getModelImages();

        //List<String> falseImgList = correspContainer.getOtherImages();

        //unseulcouple = dualImgList.get(number);
        //Hard writen, supposed to take from dualImgList
        //String drawing = null ;
        //String trueimg = null;

        //if(number == dualImgList.size()){
        //    number = 0;
        //}else{
        //    number++;
        //}

        //List<String> listFalse = new ArrayList<String>();
        //for(int i=0; i<difficulty; ++i){
        //    listFalse.add(falseImgList.get(new Random().nextInt(falseImgList.size())));
        //}

        //In this list, first index is drawing, the others are images to print. True image is shuffled within the false images here, and we stock here the index for the good image.
        //List<String> listRessources = new ArrayList<String>();
        //listRessources.add(drawing);

        //int i;
        //int listSize = falseImgList.size();

        //Here, we randomize each turn for the true image to get added. If we are at the last round, we add it anyways (and there is not much left in the list anyways)
        //If added, we only add false images until we have the right number of images.
        //for(i=0; i<listSize+1; ++i){
        //    if(i == listSize || new Random().nextInt(falseImgList.size()) == 0 ){
        //        listRessources.add(trueimg);
        //       goodOccurence = listRessources.indexOf(trueimg);
        //       break;
        //    }else{
        //       listRessources.add(falseImgList.get(0)); // To randomize?
        //   }
        //}
        //for(; i<listSize+1; i++){
        //    listRessources.add(falseImgList.get(0));
        //}

        //Send via an intent?


    }
}
