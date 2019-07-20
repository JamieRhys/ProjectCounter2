package com.jre.projectcounter.project;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.jre.projectcounter.MainActivity;
import com.jre.projectcounter.button.KCButton;
import com.jre.projectcounter.intent.ProjectView;

/** Project houses all project related information.
 *
 * @author Jamie-Rhys Edwards
 * @author jamie_airfix@yahoo.co.uk
 * @since v0.0.01
 */
public class Project extends KCButton {
    private Context                 mMainContext;
    private MainActivity            mMainActivity;
    private String                  mProjectName;

    /**
     * Instantiates a new Project.
     *
     * @param context      the context
     * @param mainActivity the main activity
     * @param projectName  the project name
     * @param backgroundID the background id
     */
    public Project(
            Context context,
            MainActivity mainActivity,
            final String projectName,
            final int backgroundID) {
        super(context, backgroundID);
        this.setClickable(true);

        mMainContext = context;
        mMainActivity = mainActivity;
        mProjectName = projectName;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(mMainActivity, ProjectView.class);
        i.putExtra("projectName", mProjectName);
        mMainActivity.startActivityForResult(i, ProjectManager.ACTIVITY_REQUESTS.REQUEST_PROJECT_VIEW);
    }
}
