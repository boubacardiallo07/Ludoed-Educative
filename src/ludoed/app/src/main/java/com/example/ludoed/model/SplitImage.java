package com.example.ludoed.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by booba Idia on 07/04/21.
 */
public class SplitImage {


    /**
     *
     * @param image
     * @param numberOfBlocks
     */
    public static List<String> splitImage(Context context, ImageView image, int numberOfBlocks)
    {
        int rows, cols;
        int smallimage_Height, smallimage_Width;
        //imageChunksStorageList = new ArrayList<>();

        BitmapDrawable mydrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = mydrawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(numberOfBlocks);
        smallimage_Height = bitmap.getHeight()/rows;
        smallimage_Width = bitmap.getWidth()/cols;

        ImageProcessing imageProcessing = new ImageProcessing();
        List<String> imageChunksStorageList = new ArrayList<>();

        int yCo = 0;
        for(int x = 0; x < rows; x++){
            int xCo = 0;
            for(int y = 0; y < cols; y++){
                try {
                    Bitmap bmp = Bitmap.createBitmap(scaledBitmap, xCo, yCo, smallimage_Width, smallimage_Height);
                    String filename = "piece" + x + "" + y + ".png";
                    FileOutputStream stream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    String pathModel = imageProcessing.saveImage(bmp, context, x+y);
                    imageChunksStorageList.add(pathModel);

                    stream.close();
                    bmp.recycle();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                xCo += smallimage_Width;
            }
            yCo+= smallimage_Height;
        }

        return imageChunksStorageList;
    }

}
