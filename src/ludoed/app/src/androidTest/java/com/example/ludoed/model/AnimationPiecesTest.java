package com.example.ludoed.model;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.ludoed.R;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert.*;
import org.junit.Test;


import org.junit.Before;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


/**
 * Created by booba Idia on 07/04/21.
 */
public class AnimationPiecesTest {

    private Activity mActivity = new Activity();


    Drawable d = mActivity.getResources().getDrawable(R.drawable.modele_img);


    @Test
    public void testRotateImage() {
        System.out.println("************************** " + mActivity);
        List<ImageView> pieces = new ArrayList<ImageView>();
        pieces.add(mActivity.findViewById(R.id.piece1));
        AnimationPieces anim = new AnimationPieces(pieces.get(0));


        assertEquals(anim.rotateImage(), anim.rotateImage());

    }

    @Test
    public void testIsCorrectPosition() {
        List<ImageView> pieces = new ArrayList<ImageView>();
        pieces.add(mActivity.findViewById(R.id.piece1));
        AnimationPieces anim = new AnimationPieces(pieces.get(0));

        assertTrue(anim.isCorrectPosition());
        //assertTrue(true);
    }

}