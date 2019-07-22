package com.jre.projectcounter.project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jre.projectcounter.MainActivity;
import com.jre.projectcounter.utils.KCColor;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectManager manages our projects throughout the apps lifetime.
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class ProjectManager extends AppCompatActivity {
    private Context                         mMainContext;
    private MainActivity                    mMainActivity;

    private LinearLayout                    mLayout_Main;
    private LinearLayout                    mLayout_Row;

    private HashMap<Integer, Project>       mProjectMap;
    private int                             mProjectMapPrevVal;

    /**
     * Activity request class.
     */
    public static final class ACTIVITY_REQUESTS {
        /**
         * The constant REQUEST_ADD_PROJECT.
         */
        public static final int REQUEST_ADD_PROJECT = 0;
        /**
         * The constant REQUEST_PROJECT_VIEW.
         */
        public static final int REQUEST_PROJECT_VIEW = 1;
    }

    /**
     * Instantiates a new Project manager.
     *
     * @param context  the context we're attaching to.
     * @param activity the activity that deals with launching additional activities.
     * @param layout   the layout that we'll house our project icons on.
     */
    public ProjectManager(
            Context context,
            MainActivity activity,
            LinearLayout layout) {
        mMainContext            = context;
        mMainActivity           = activity;
        mLayout_Main            = layout;

        mProjectMap             = new HashMap<>();
        mProjectMapPrevVal      = mProjectMap.size();
    }

    /**
     * Add's a new project.
     *
     * @param projectName      the project name
     * @param globalCounterRow the global counter current row
     * @param counterMap       the counter map
     * @param backgroundID     the background id
     */
    public void addProject(
            final String projectName,
            final int globalCounterRow,
            final HashMap<Integer, Counter> counterMap,
            int backgroundID) {

        if(backgroundID < 0) {
            backgroundID = KCColor.getInstance().generateBackgroundID();
        }

        Project project = new Project(
                mMainContext,
                mMainActivity,
                projectName,
                globalCounterRow,
                counterMap,
                backgroundID);

        project.setOnClickListener(project);

        mProjectMap.put(mProjectMap.size() + 1, project);
    }

    /**
     * Add project.
     *
     * @param projectName the project name
     */
    public void addProject(final String projectName) {
        Project project = new Project(
                mMainContext,
                mMainActivity,
                projectName,
                0,
                new HashMap<Integer, Counter>(),
                -1);

        project.setOnClickListener(project);

        mProjectMap.put(mProjectMap.size() + 1, project);
    }

    /**
     * Creates our project buttons and then displays them.
     * TODO: mLayout_Main needs to be changed to the relevant layout once activity_main is designed.
     */
    public void createProjectButtons() {
        for(Map.Entry<Integer, Project> entry : mProjectMap.entrySet()) {
            if((entry.getKey() - 1) % 2 == 0 || mLayout_Main == null) {
                mLayout_Row = new LinearLayout(mMainContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                mLayout_Row.setLayoutParams(lp);
                mLayout_Row.setOrientation(LinearLayout.HORIZONTAL);
                mLayout_Row.setGravity(Gravity.CENTER);
                mLayout_Main.addView(mLayout_Row);
            }

            mLayout_Row.addView(entry.getValue());
        }
    }

    /**
     * Remove already active project buttons from our layout.
     */
    public final void removeProjectButtons() {
        if(mProjectMap != null) {
            for(Map.Entry<Integer, Project> entry : mProjectMap.entrySet()) {
                if(entry.getValue().getParent() != null) {
                    ((ViewGroup) entry.getValue().getParent()).removeView(entry.getValue());
                }
            }
        }
    }
}
