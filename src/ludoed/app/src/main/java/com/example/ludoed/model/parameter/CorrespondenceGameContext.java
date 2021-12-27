package com.example.ludoed.model.parameter;

import java.util.List;
import java.util.Map;

public class CorrespondenceGameContext extends AbstractGameContext {
    private Map<Integer, String> _correspImage;
    private List<String> _otherImage;

    public CorrespondenceGameContext(int difficulty, int level, Map<Integer,  String> imageList, Map<Integer, String> _correspImage, List<Integer> soundList, List<String> otherImage){
        super(difficulty, level, imageList, soundList);
        this._correspImage = _correspImage;
        this._otherImage = otherImage;
    }

    public Map<Integer, String> getCorrespImage() {
        return _correspImage;
    }

    public List<String> getOtherImage() {
        return _otherImage;
    }
}
