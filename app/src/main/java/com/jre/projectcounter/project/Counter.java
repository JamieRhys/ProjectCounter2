package com.jre.projectcounter.project;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jre.projectcounter.R;
import com.jre.projectcounter.button.KCButton;

public class Counter extends KCButton {
    private final int[]                         BUTTON_MARGINS              = new int[] { 20, 5, 20, 5};
    private final int[]                         BUTTON_PADDING              = new int[] { 10, 10, 10, 10 };

    private String                              mCounterName                = "Counter";
    private int                                 mCurrentRow                 = 0;
    private boolean                             mIsGlobal                   = true;

    private LinearLayout                        mContainer_Content;
    private LinearLayout                        mContainer_Buttons;

    private TextView                            mTV_CounterName;
    private TextView                            mTV_CurrentRow;

    private Button                              mButton_Inc;
    private Button                              mButton_Dec;

    public Counter(
            Context context,
            int backgroundID,
            final String counterName,
            final int currentRow,
            final boolean isGlobal) {
        super(context, backgroundID);

        if(counterName.length() > 0) {
            mCounterName = counterName;
        }

        if(currentRow > mCurrentRow || currentRow > 0) {
            mCurrentRow = currentRow;
        }

        if(isGlobal != mIsGlobal) {
            mIsGlobal = isGlobal;
        }

        initCounter(context);
    }

    private void initCounter(Context context) {
        LayoutParams lpCont_Content = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lpCont_Buttons = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lpTV_Name = new LayoutParams(LayoutParams.MATCH_PARENT, 40);
        LayoutParams lpTV_Row = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lpButtons = new LayoutParams(100, 100);

        // Set our layout ID.
        this.setId(R.id.layout_counter);

        // Let's sort out the content container:
        mContainer_Content = new LinearLayout(context);
        mContainer_Content.setId(R.id.container_counter_content);
        mContainer_Content.setLayoutParams(lpCont_Content);
        mContainer_Content.setOrientation(LinearLayout.VERTICAL);
        mContainer_Content.setGravity(Gravity.CENTER);

        // Now the button container:
        mContainer_Buttons = new LinearLayout(context);
        mContainer_Buttons.setId(R.id.container_counter_buttons);
        mContainer_Buttons.setLayoutParams(lpCont_Buttons);
        mContainer_Buttons.setOrientation(LinearLayout.HORIZONTAL);
        mContainer_Buttons.setGravity(Gravity.CENTER);

        // Now they're sorted, let's do our text views:
        mTV_CounterName = new TextView(context);
        mTV_CounterName.setId(R.id.tv_counter_name);
        mTV_CounterName.setLayoutParams(lpTV_Name);
        mTV_CounterName.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        mTV_CounterName.setText(mCounterName);
        mTV_CounterName.setGravity(Gravity.CENTER);

        mTV_CurrentRow = new TextView(context);
        mTV_CurrentRow.setId(R.id.tv_counter_row);
        mTV_CurrentRow.setLayoutParams(lpTV_Row);
        mTV_CurrentRow.setTextAppearance(R.style.TextAppearance_AppCompat_Display1);
        mTV_CurrentRow.setText(Integer.toString(mCurrentRow));
        mTV_CurrentRow.setGravity(Gravity.CENTER);

        // Finally, our buttons:
        lpButtons.setMargins(BUTTON_MARGINS[0], BUTTON_MARGINS[1], BUTTON_MARGINS[2], BUTTON_MARGINS[3]);

        mButton_Inc = new Button(context);
        mButton_Inc.setId(R.id.button_counter_inc);
        mButton_Inc.setLayoutParams(lpButtons);
        mButton_Inc.setTextColor(context.getColor(R.color.colorWhite));
        mButton_Inc.setTypeface(null, Typeface.BOLD);
        mButton_Inc.setTextSize(20);
        mButton_Inc.setTextAppearance(R.style.Widget_AppCompat_Button_Colored);
        mButton_Inc.setBackground(context.getDrawable(R.drawable.background_circle_counter_button));
        mButton_Inc.setText("+");
        mButton_Inc.setGravity(Gravity.CENTER);
        mButton_Inc.setPadding(BUTTON_PADDING[0], BUTTON_PADDING[1], BUTTON_PADDING[2], BUTTON_PADDING[3]);

        mButton_Dec = new Button(context);
        mButton_Dec.setId(R.id.button_counter_dec);
        mButton_Dec.setLayoutParams(lpButtons);
        mButton_Dec.setTextColor(context.getColor(R.color.colorWhite));
        mButton_Dec.setTypeface(null, Typeface.BOLD);
        mButton_Dec.setTextSize(20);
        mButton_Dec.setTextAppearance(R.style.Widget_AppCompat_Button_Colored);
        mButton_Dec.setBackground(context.getDrawable(R.drawable.background_circle_counter_button));
        mButton_Dec.setText("-");
        mButton_Dec.setGravity(Gravity.CENTER);
        mButton_Dec.setPadding(BUTTON_PADDING[0], BUTTON_PADDING[1], BUTTON_PADDING[2], BUTTON_PADDING[3]);

        // Let's now add them to the root layout:
        mContainer_Content.addView(mTV_CounterName);
        mContainer_Content.addView(mTV_CurrentRow);

        mContainer_Buttons.addView(mButton_Inc);
        mContainer_Buttons.addView(mButton_Dec);

        this.addView(mContainer_Content);
        this.addView(mContainer_Buttons);
    }
}
