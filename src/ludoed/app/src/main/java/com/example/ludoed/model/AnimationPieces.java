package com.example.ludoed.model;

import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import java.util.Random;


public class AnimationPieces {
    private ImageView piece;
    private int startRotation;
    private int endRotation;
    private static int numberInstance = Settings.ZERO; // Common to all the objects of this class, it is incremented at each instantiation
    private static int numberPosition = Settings.ZERO;  // Common to all the objects of this class, it is incremented as soon as a piece is in the right position
    private boolean verify = false; // Allows to increment numberPosition only if the part passes for the first time to its correct position.

    /**
     *
     * @param piece
     */
    public AnimationPieces(ImageView piece) {
        this.piece = piece;
        this.startRotation = Settings.ZERO;
        numberInstance++;

        int random = new Random().nextInt(Settings.THREE) + Settings.ONE; // Random value [1-3] which will be multiplied by degreeRotation for the random rotation of the part
        this.endRotation = random*Settings.DEGREE_ROTATION;

        RotateAnimation rotate = new RotateAnimation(this.startRotation, this.endRotation, RotateAnimation.RELATIVE_TO_SELF,
                Settings.PIVOT_VALUE, RotateAnimation.RELATIVE_TO_SELF,Settings.PIVOT_VALUE);
        rotate.setFillAfter(true);
        this.piece.startAnimation(rotate);
    }

    /**
     * Rotates a part 90 ° from its current position.
     * @return TRUE if all the pieces are in the correct position and FALSE otherwise
     */
    public boolean rotateImage(){
        this.startRotation = this.endRotation;
        this.endRotation += Settings.DEGREE_ROTATION;
        RotateAnimation rotate = new RotateAnimation(this.startRotation, this.endRotation, RotateAnimation.RELATIVE_TO_SELF,
                Settings.PIVOT_VALUE, RotateAnimation.RELATIVE_TO_SELF,Settings.PIVOT_VALUE);

        rotate.setFillAfter(true);
        this.piece.startAnimation(rotate);

        return ( isCorrectPosition() && (numberInstance == numberPosition) );
    }

    /**
     *
     * @return TRUE if the part is in the correct position and FALSE otherwise
     */
    public boolean isCorrectPosition(){
        if( endRotation % Settings.DEGREE_END_ROTATION == Settings.ZERO ) {
            if( !verify ) {
                numberPosition++;
                verify = true;
            }
            return true;
        }

        return false;
    }

    public int getEndRotation() {
        return endRotation;
    }

    public int getStartRotation() { return startRotation; }

    public ImageView getPiece() { return piece; }

    public static int getNumberInstance() {
        return numberInstance;
    }

    public static int getNumberPosition() {
        return numberPosition;
    }


    public void setEndRotation(int endRotation) {
        this.endRotation = endRotation;
    }
}