package com.jre.projectcounter.button;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jre.projectcounter.MainActivity;
import com.jre.projectcounter.intents.ProjectView;
import com.jre.projectcounter.project.Project;

/**
 * ProjectButton is exactly that, it allows us to pass
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class ProjectButton extends PCIcon {
    private Context                                     mContext;
    private MainActivity                                mActivity;
    private Project                                     mProject;

    private TextView                                    mTV_ProjectName;

    /**
     * Instantiates a new Project button.
     *
     * @param context the context
     * @param project the project
     */
    public ProjectButton(Context context, final MainActivity activity, final Project project) {
        super(context);
        mContext = context;
        mActivity = activity;
        mProject = project;

        this.initialiseIcon();
    }

    private void initialiseIcon() {
        this.setClickable(true);
        this.setOnClickListener(this);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        mTV_ProjectName = new TextView(mContext);
        mTV_ProjectName.setLayoutParams(lp);
        mTV_ProjectName.setTextSize(20);
        mTV_ProjectName.setText(mProject.getProjectName());
        mTV_ProjectName.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        this.addView(mTV_ProjectName);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(mContext, ProjectView.class);
        i.putExtra("project", mProject);
        mActivity.startActivityForResult(i, 1);
    }

    /**
     * Gets project.
     *
     * @return the project
     */
    public final Project getProject() { return mProject; }
}
