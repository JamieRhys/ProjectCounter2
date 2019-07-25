package com.jre.projectcounter.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jre.projectcounter.MainActivity;
import com.jre.projectcounter.button.KCButton;
import com.jre.projectcounter.intent.ProjectView;

import java.util.HashMap;

/**
 * Project houses all project related information.
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class Project extends KCButton {
    private Context                         mMainContext;
    private MainActivity                    mMainActivity;
    private String                          mProjectName;
    private int                             mGlobalCounterRow;
    private HashMap<Integer, Counter>       mCounterMap;

    private TextView                        mTV_ProjectName;

    /**
     * Instantiates a new Project.
     *
     * @param context          the context
     * @param mainActivity     the main activity
     * @param projectName      the project name
     * @param globalCounterRow the global counter row
     * @param counterMap       the counter map
     * @param backgroundID     the background id
     */
    public Project(
            Context context,
            MainActivity mainActivity,
            final String projectName,
            final int globalCounterRow,
            final HashMap<Integer, Counter> counterMap,
            final int backgroundID) {
        super(context, backgroundID);

        mMainContext = context;
        mMainActivity = mainActivity;
        mProjectName = projectName;
        mGlobalCounterRow = globalCounterRow;
        mCounterMap = counterMap;

        initialiseIcon();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(mMainActivity, ProjectView.class);
        i.putExtra("projectName", mProjectName);
        i.putExtra("globalCounterRow", mGlobalCounterRow);
        i.putExtra("counterMap", mCounterMap);
        mMainActivity.startActivityForResult(i, ProjectManager.ACTIVITY_REQUESTS.REQUEST_PROJECT_VIEW);
    }

    /** Initialises our icon to display in the intended container */
    private void initialiseIcon() {
        this.setClickable(true);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        mTV_ProjectName = new TextView(mMainContext);
        mTV_ProjectName.setLayoutParams(lp);
        mTV_ProjectName.setTextSize(20);
        mTV_ProjectName.setText(mProjectName);
        mTV_ProjectName.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        this.addView(mTV_ProjectName);
    }

    public final String getProjectName() { return mProjectName; }

    public final int getGlobalCurrentRow() { return mGlobalCounterRow; }

}
