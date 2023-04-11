package com.mycompany.a3;

import java.util.ArrayList;

import com.mycompany.interfaces.IColection;
import com.mycompany.interfaces.IIterator;

public class GameCollection implements IColection {
    private ArrayList<GameObject> objects;

    public GameCollection() {
        this.objects = new ArrayList<GameObject>();
    }

    public void add(GameObject object) {
        this.objects.add(object);
    }

    public void remove(GameObject object) {
        this.objects.remove(object);
    }

    public IIterator getIterator() {
        return new Iterator();
    }

    private class Iterator implements IIterator {
        private int currElement;

        public Iterator() {
            currElement = -1;
        }

        public boolean hasNext() {
            if (objects.size() <= 0) {
                return false;
            }
            if (currElement == objects.size() - 1) {
                return false;
            }
            return true;
        }

        public GameObject getNext() {
            currElement++;
            return objects.get(currElement);
        }
    }
}
