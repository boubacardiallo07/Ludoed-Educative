package com.example.ludoed.model.parameter;

import java.util.ArrayList;
import java.util.Map;

public class ColorationGameContext extends AbstractGameContext {
    public ColorationGameContext(int difficulty, int level, Map<Integer, String> imageList, ArrayList<Integer> soundList){
        super(difficulty, level, imageList, soundList);
    }
}
