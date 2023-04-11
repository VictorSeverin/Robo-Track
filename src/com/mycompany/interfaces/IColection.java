package com.mycompany.interfaces;

import com.mycompany.a3.GameObject;

public interface IColection {
    public void add(GameObject object);

    public IIterator getIterator();
}
