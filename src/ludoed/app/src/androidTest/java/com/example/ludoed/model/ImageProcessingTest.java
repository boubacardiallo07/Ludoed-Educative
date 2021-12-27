package com.example.ludoed.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.InstrumentationRegistry;

import junit.framework.TestCase;

import com.example.ludoed.R;

import org.junit.Test;

public class ImageProcessingTest extends TestCase {

    private ImageProcessing imageProcessing = new ImageProcessing();



    public void testSaveImage() {
        Context context = InstrumentationRegistry.getTargetContext();
        String myPath = "/storage/emulated/0/Android/data/com.example.ludoed/files/Pictures/LudoEducatif/Image2.jpg";
        Bitmap bmp = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.avatar_homme);
        String path = imageProcessing.saveImage(bmp, context, 2);
        if (path != null)
            assertEquals(path, myPath);
    }

    public void testLoadBitmapFromFile() {
        String path = "/storage/emulated/0/Android/data/com.example.ludoed/files/Pictures/LudoEducatif/Image2.jpg";
        Bitmap bitmap = imageProcessing.loadBitmapFromFile(path);
        Bitmap bmp = imageProcessing.loadBitmapFromFile(path);
        if (bitmap != null && bmp != null)
            assertEquals(bitmap.getWidth() == bmp.getWidth(), bitmap.getHeight() == bmp.getHeight());
    }

    public void testResizeBitmap() {
        Bitmap bmp = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.avatar_homme);
        if (bmp != null){
            bmp = bmp.copy(bmp.getConfig(), true);
            Bitmap after = imageProcessing.resizeBitmap(bmp, 605, 814);
            assertEquals(bmp.getWidth() == after.getWidth(), bmp.getHeight() == after.getHeight());
        }

    }

    @Test
    public void testDefaultScale() {
    }
}