package com.mycompany.interfaces;

import com.mycompany.a3.GameWorld;

public interface IStrategy {

    public void setStrategy();

    public void invokeStrategy(GameWorld gw);
}
