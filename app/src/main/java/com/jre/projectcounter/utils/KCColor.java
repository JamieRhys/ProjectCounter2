package com.jre.projectcounter.utils;

public class KCColor {
    private static KCColor mInstance;

    private KCColor() {
        if(mInstance != null) {
            throw new RuntimeException("Do not use this directly.");
        }
    }

    /** Returns the instance of KCColor. */
    public synchronized KCColor getInstance() {
        if(mInstance == null) {
            mInstance = new KCColor();
        }
        return mInstance;
    }
}
