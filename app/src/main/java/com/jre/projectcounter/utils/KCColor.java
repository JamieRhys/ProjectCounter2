package com.jre.projectcounter.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.jre.projectcounter.R;

import java.util.Random;

/**
 * KCColor holds static colour related functions that are frequently used throughout app.
 *
 * @author Jamie-Rhys Edwards
 * @author jamie_airfix@yahoo.co.uk
 * @since v0.0.01
 */
public class KCColor {
    private static KCColor mInstance;
    private static Drawable[] mBackgroundBorders;

    private KCColor() {
        if(mInstance != null) {
            throw new RuntimeException("Do not use this directly.");
        }
    }

    /**
     * Returns the instance of KCColor.
     *
     * @return the instance
     * @author Jamie -Rhys Edwards
     * @author jamie_airfix @yahoo.co.uk
     */
    public static synchronized KCColor getInstance() {
        if(mInstance == null) {
            mInstance = new KCColor();
        }
        return mInstance;
    }

    /**
     * Initialises our BackgroundBorders array
     *
     * @param context - The main context
     * @throws RuntimeException - Throws if more than one instance is found.
     * @author Jamie -Rhys Edwards
     * @author jamie_airfix @yahoo.co.uk
     */
    public static void initialiseBackgroundBorders(Context context) {
        if(mBackgroundBorders == null) {
            mBackgroundBorders = new Drawable[10];
            mBackgroundBorders[0] = context.getDrawable(R.drawable.background_circle_accent);
            mBackgroundBorders[1] = context.getDrawable(R.drawable.background_circle_bright_blue);

            mBackgroundBorders[2] = context.getDrawable(R.drawable.background_circle_dark_blue);
            mBackgroundBorders[3] = context.getDrawable(R.drawable.background_circle_dark_green);
            mBackgroundBorders[4] = context.getDrawable(R.drawable.background_circle_dark_blue);

            mBackgroundBorders[5] = context.getDrawable(R.drawable.background_circle_light_blue);
            mBackgroundBorders[6] = context.getDrawable(R.drawable.background_circle_light_green);
            mBackgroundBorders[7] = context.getDrawable(R.drawable.background_circle_light_red);

            mBackgroundBorders[8] = context.getDrawable(R.drawable.background_circle_primary);
            mBackgroundBorders[9] = context.getDrawable(R.drawable.background_circle_primary_dark);
        } else {
            throw new RuntimeException("This must only be run once!");
        }
    }

    /**
     * Generates a random backgroundID between the start and end of our BackgroundBorders Array
     *
     * @return - The background ID.
     * @author Jamie -Rhys Edwards
     * @author jamie_airfix @yahoo.co.uk
     */
    public static int generateBackgroundID() {
        Random rand = new Random();
        return rand.nextInt(mBackgroundBorders.length);
    }

    /**
     * Returns the associated Drawable that belongs to the backgroundID
     *
     * @param backgroundID - The ID if the Drawable to be returned
     * @return - If backgroundID checks out, return the associated Drawable, otherwise return generic one.
     * @author Jamie -Rhys Edwards
     * @author jamie_airfix @yahoo.co.uk
     */
    public static Drawable getBackgroundBorder(final int backgroundID) {
        if(backgroundID == -1) {
            int i = generateBackgroundID();
            return mBackgroundBorders[i];
        } else {
            return mBackgroundBorders[backgroundID];
        }
    }
}
