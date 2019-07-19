package com.jre.projectcounter.button;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class KCButton extends LinearLayout implements OnClickListener {
    private final int               mLAYOUT_SIZE                    = 350;
    private final int[]             mLAYOUT_MARGIN                  = new int[] { 10, 10, 10, 10};
    private final int               mBACKGROUND_COLOR_ID;

    public KCButton(
            Context context,
            final int backgroundID
    ) {
        super(context);

        if(backgroundID != -1) {
            mBACKGROUND_COLOR_ID = backgroundID;
        } else {
            mBACKGROUND_COLOR_ID = backgroundID; // TODO: Change this!
        }
    }

    @Override
    public void onClick(View v) {

    }
}
