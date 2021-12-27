package com.example.ludoed.model.parameter;

import java.util.List;
import java.util.Map;

public class RotationGameContext extends AbstractGameContext {
    public RotationGameContext(int difficulty, int level, Map<Integer, String> imageList, List<Integer> soundList){
        super(difficulty, level, imageList, soundList);
    }

}
