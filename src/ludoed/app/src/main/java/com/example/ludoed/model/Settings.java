package com.example.ludoed.model;

public class Settings {

    // The properties to make a comparison in all the program
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int THREE = 3;
    public static final int DEGREE_END_ROTATION = 360;
    public static final int DEGREE_ROTATION = 90;
    public static final float PIVOT_VALUE = .5f;

    // Properties linked to image processing
    public static final int IMAGE_SIZE = 700;   //IMAGE_SIZE is the size of the showed image (resized)
    public static final int NUMBER_OF_IMAGE = 2;
    public static final int RESULT_LOAD_IMG = 1;
    public static final int IMAGE_QUALITY = 90;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;

    public static final String GAME_CONTEXT = "gameContext";    // The context to pass between the activities
    public static final int WAITING_TIME = 3000;    // The waiting time before going to the next activity

    // The name of the files in which the data of each set will be saved
    public static final String CORRESPONDENCE_FILE_NAME = "correspondenceContainer.txt";
    public static final String COLORATION_FILE_NAME = "colorContainer.txt";
    public static final String ROTATION_FILE_NAME = "rotationContainer.txt";

    public static final String CORRESPONDENCE_GAME_FILE_NAME = "correspondenceGame.txt";
    public static final String COLORATION_GAME_FILE_NAME = "colorationGame.txt";
    public static final String ROTATION_GAME_FILE_NAME = "rotationGame.txt";

    // To check the record in a file
    private static boolean execute = false;

    public static boolean isExecute() {
        return execute;
    }

    public static void setExecute(boolean execute) {
        Settings.execute = execute;
    }
}
