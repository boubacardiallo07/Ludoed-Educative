package com.example.ludoed.model.parameter;

import java.io.Serializable;

public class GameContext implements Serializable {
    private AbstractGameContext _coloration;
    private AbstractGameContext _correspondance;
    private AbstractGameContext _rotation;

    public GameContext(AbstractGameContext coloration, AbstractGameContext correspondance, AbstractGameContext rotation){
        this._coloration = coloration;
        this._correspondance = correspondance;
        this._rotation = rotation;
    }

    public AbstractGameContext get_coloration() {
        return _coloration;
    }

    public AbstractGameContext get_correspondance() {
        return _correspondance;
    }

    public AbstractGameContext get_rotation() {
        return _rotation;
    }
}
