package com.example.ludoed.model.parameter;

import com.example.ludoed.model.Settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGameContext implements Serializable {
    private int _difficulty;
    private int _level;
    private Map<Integer, String> _imageList;
    private List<Integer> _soundList;

    public AbstractGameContext(){
        this._difficulty = Settings.ZERO;
        this._level = Settings.ZERO;
        this._imageList = new HashMap<Integer, String>();
        this._soundList = new ArrayList<Integer>();
    }

    public AbstractGameContext(int _difficulty, int _level, Map<Integer, String> _imageList, List<Integer> _soundList){
        this._difficulty = _difficulty;
        this._level = _level;
        this._imageList = _imageList;
        this._soundList = _soundList;
    }

    public int get_difficulty() {
        return _difficulty;
    }

    public int get_level() {
        return _level;
    }

    public Map<Integer, String> get_imageList() {
        return _imageList;
    }

    public List<Integer> get_soundList() {
        return _soundList;
    }

    public void set_difficulty(int _difficulty) {
        this._difficulty = _difficulty;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public void set_imageList(Map<Integer, String> _imageList) {
        this._imageList = _imageList;
    }

    public void set_soundList(ArrayList<Integer> _soundList) {
        this._soundList = _soundList;
    }

    public int imageListSize(){
        if (_imageList != null)
            return this._imageList.size();
        return 0;
    }

    public int soundListSize(){
        if (_soundList != null)
            return _soundList.size();
        return  0;
    }

}
