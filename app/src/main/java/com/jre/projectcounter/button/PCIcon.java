package com.jre.projectcounter.button;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.jre.projectcounter.utils.KCColor;

/**
 * PCIcon is the blueprint for our buttons and counters.
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class PCIcon extends LinearLayout implements View.OnClickListener {
    private final int                       mLAYOUT_SIZE = 350;
    private final int[]                     mLAYOUT_MARGIN = new int[] { 10, 10, 10, 10 };
    private final int                       mBACKGROUND_ID;

    /**
     * Instantiates a new PCIcon.
     *
     * @param context      the context
     * @param backgroundID the background id
     */
    public PCIcon(Context context, final int backgroundID) {
        super(context);

        if(backgroundID != -1) {
            mBACKGROUND_ID = backgroundID;
        } else {
            mBACKGROUND_ID = KCColor.generateBackgroundID();
        }

        LayoutParams lp = new LayoutParams(mLAYOUT_SIZE, LayoutParams.MATCH_PARENT);
        lp.setMargins(mLAYOUT_MARGIN[0], mLAYOUT_MARGIN[1], mLAYOUT_MARGIN[2], mLAYOUT_MARGIN[3]);
        this.setLayoutParams(lp);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER);
        this.setBackground(KCColor.getBackgroundBorder(mBACKGROUND_ID));
    }

    /**
     * Instantiates a new PCIcon.
     *
     * @param context the context
     */
    public PCIcon(Context context) {
        this(context, -1);
    }

    /**
     * Gets background id.
     *
     * @return the background id
     */
    public final int getBackgroundID() { return mBACKGROUND_ID; }

    @Override
    public void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        this.setMeasuredDimension(width, width);
    }

    @Override
    public void onClick(View v) {

    }
}
