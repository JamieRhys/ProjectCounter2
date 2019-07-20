package com.jre.projectcounter.button;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.jre.projectcounter.utils.KCColor;

/**
 * KCButton is used as a template for all our main buttons.
 *
 * @author Jamie-Rhys Edwards
 * @author jamie_airfix@yahoo.co.uk
 * @since v0.0.01
 */
public class KCButton extends LinearLayout implements OnClickListener {
    private final int               mLAYOUT_SIZE                    = 350;
    private final int[]             mLAYOUT_MARGIN                  = new int[] { 10, 10, 10, 10};
    private int                     mBackgroundID;

    /**
     * Creates the button used by our assets.
     *
     * @param context      - The context we are attaching to this.
     * @param backgroundID - the ID for our background border.
     * @author Jamie -Rhys Edwards
     * @author jamie_airfix @yahoo.co.uk
     */
    public KCButton(
            Context context,
            final int backgroundID
    ) {
        super(context);

        if(backgroundID != -1) {
            mBackgroundID = backgroundID;
        } else {
            mBackgroundID = KCColor.getInstance().generateBackgroundID();
        }

        LayoutParams lp = new LayoutParams(mLAYOUT_SIZE, LayoutParams.MATCH_PARENT);
        lp.setMargins(mLAYOUT_MARGIN[0], mLAYOUT_MARGIN[1], mLAYOUT_MARGIN[2], mLAYOUT_MARGIN[3]);
        setLayoutParams(lp);

        setBackground(KCColor.getBackgroundBorder(mBackgroundID));
        setGravity(Gravity.CENTER);
    }

    /**
     * Instantiates a new Kc button.
     *
     * @param context the context
     */
    public KCButton(Context context) {
        this(context, -1);
    }

    /**
     * Returns the background ID for this button.
     *
     * @return - The background ID
     * @author Jamie -Rhys Edwards
     * @author jamie_airfix @yahoo.co.uk
     */
    public final int getBackgroundID() { return mBackgroundID; }

    /** Makes our button actually square (This creates a proper circle shape).
     *
     * @param onMeasureSpecWidth The width
     * @param onMeasureSpecHeight The height
     */
    @Override
    public void onMeasure(int onMeasureSpecWidth, int onMeasureSpecHeight) {
        super.onMeasure(onMeasureSpecWidth, onMeasureSpecHeight);

        int size = MeasureSpec.getSize(onMeasureSpecWidth);
        setMeasuredDimension(size, size);
    }

    @Override
    public void onClick(View v) {

    }
}
