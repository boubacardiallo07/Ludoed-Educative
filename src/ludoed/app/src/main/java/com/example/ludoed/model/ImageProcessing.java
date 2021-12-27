package com.example.ludoed.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Cette classe contiendra toutes les methodes qui permettrons de
 * manipuler une image
 */
public class ImageProcessing {

    /**
     * Cette methode permet de charger une image et l'enregistre dans le sous repertoire du jeu.
     * @param used_bitmap l'image qu'on voudrait enregistrer
     * @param context l'activité qui a fait appel la methode
     * @param numberOfImage determine le numero de l'image afin d'eviter d'ecraser les images déjà enregistrée
     * @return chemin vers l'image qu'on voudrait enregistrer
     */
    public String saveImage(Bitmap used_bitmap, Context context, int numberOfImage){
        String imageDirector = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/LudoEducatif";
        String filename = "Image" + numberOfImage + ".jpg";

        File parentDir = new File(imageDirector);    //A la place de ludoeducatif dans le futur, le nom de l'app?
        File file = new File(parentDir, filename);
        file.mkdirs();

        if (file.exists()) file.delete();

        try {
            FileOutputStream out = new FileOutputStream(file);
            used_bitmap = resizeBitmap(defaultScale(used_bitmap), Settings.DEFAULT_WIDTH, Settings.DEFAULT_HEIGHT);
            used_bitmap.compress(Bitmap.CompressFormat.JPEG, Settings.IMAGE_QUALITY, out);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        if(file == null){
            CharSequence text = "A problem occured, image could not be saved";
            Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            context.sendBroadcast(mediaScanIntent);

            CharSequence text = imageDirector+"/"+filename;
            Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();

            return imageDirector+"/"+filename;

        }
        return null;
    }

    /**
     * Cette methode permet de recuperer image bitmap a partir d'un lien d'acces de l'image qu'on voudrait charger
     * @param filePath lien d'acces de l'image
     * @return l'image chargée
     */
    public Bitmap loadBitmapFromFile(String filePath){
        Bitmap bitmap = null;
        try {
            File file = new File(filePath);
            if(file.exists()){
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  bitmap;
    }

    /**
     * cette methode permet de rédimensionner notre image pour soit former un carré ou rectangle en fonction
     * des valeur du width et height
     * @param originalBitmap image à rédimensionner
     * @param newWidth nouvelle largeur de l'image
     * @param newHeight nouvelle hauteur de l'image
     * @return l'image issue de la rédimension
     */
    public Bitmap resizeBitmap(Bitmap originalBitmap, int newWidth, int newHeight){
        if (originalBitmap == null)
            return null;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                originalBitmap, newWidth, newHeight, false);
        return  resizedBitmap;
    }


    /**
     * Cette methode permet de rémettre à défaut l'image qu'on a charger
     * @param bmp image retourné
     * @return image issue de la transformation
     */
    public Bitmap defaultScale(Bitmap bmp){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        return bitmap;
    }
}
