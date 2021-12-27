package com.example.ludoed.model;

import android.content.Context;
import android.media.MediaPlayer;


import static android.media.MediaPlayer.create;

/**
 * Cette classe permettra de créer  toutes les animations du jeu
 */

public class AnimationProcessing {

    private MediaPlayer media;

    public AnimationProcessing(){
        this.media = new MediaPlayer();
    }

    public void playSound(Context context, int song, boolean loop){
        media= create(context, song);
        media.start();
        media.setLooping(loop);
    }
    public void stopSound()
    {
        media.stop();
    }
    public MediaPlayer getMedia(){
        return this.media;
    }


}
