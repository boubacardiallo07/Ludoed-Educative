package com.example.ludoed.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ludoed.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManagment  {

    /**
     * This method allows you to save the data in a file before quitting the application
     * the application to save the state of a given game
     * @param context the context of the current activity
     * @param data the data to be serialized (images, sound)
     * @param fileName the name of the file in which the data will be saved
     */
    public static void serialize(Context context, Object data, String fileName) {

        try {
            FileOutputStream file = context.openFileOutput(fileName, context.MODE_PRIVATE);

            try {
                ObjectOutputStream newFile = new ObjectOutputStream(file);
                newFile.writeObject(data);
                newFile.flush();
                newFile.close();
                Toast.makeText(context, "data saved",Toast.LENGTH_SHORT).show();
            }catch (IOException e ){
                Toast.makeText(context, "data not saved",Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method allows you to deSerialize the data that is in the file passed in
     * parameter of the function
     * @param context the context of the current activity
     * @param fileName the name of the file in which the data will be saved
     * @return returns the data already serialized in the file "fileName
     */
    public static Object deSerialize(Context context, String fileName) {

        try {
            FileInputStream file = context.openFileInput(fileName);
            try {
                ObjectInputStream data = new ObjectInputStream(file);
                try {
                    Object object = data.readObject();
                    data.close();
                    return object;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}


