package com.example.ludoed.model;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.example.ludoed.R;
import junit.framework.TestCase;
import org.junit.Test;



/**
 * Created by booba Idia on 07/04/21.
 */
public class AnimationPiecesTest extends TestCase {

    private Activity mActivity = new Activity();

    @Test
    public void testRotateImage() {

        boolean expeced = true, actual = false;

        Bitmap bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.avatar_homme);
        ImageView imageView = new ImageView(null);

        Drawable d = new BitmapDrawable(bitmap);
        imageView.setImageDrawable(d);

        AnimationPieces anim = new AnimationPieces(imageView);

        anim.setEndRotation(270);
        actual = anim.rotateImage();

        assertEquals(expeced, actual);
    }

    @Test
    public void testIsCorrectPosition() {

        boolean expeced = false;
        ImageView imageView = new ImageView(null);

        AnimationPieces anim = new AnimationPieces(imageView);

        anim.setEndRotation(0);
        expeced = anim.isCorrectPosition();

        assertTrue(expeced);
    }
}