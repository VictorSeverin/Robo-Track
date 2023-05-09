package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

public abstract class SubObject {
    private Transform myTranslation;
    private Transform myRotation;
    private Transform myScale;

    public SubObject() {
        myTranslation = Transform.makeIdentity();
        myRotation = Transform.makeIdentity();
        myScale = Transform.makeIdentity();
    }

    public void rotate(float degrees) {
        myRotation.rotate((float) Math.toRadians(degrees), 0, 0);
    }

    public void translate(float tx, float ty) {
        myTranslation.translate(tx, ty);
    }

    public void scale(float sx, float sy) {
        myScale.scale(sx, sy);
    }

    public Transform getTranslate() {
        return myTranslation;
    }

    public Transform getScale() {
        return myScale;
    }

    public Transform getRotation() {
        return myRotation;
    }

}
