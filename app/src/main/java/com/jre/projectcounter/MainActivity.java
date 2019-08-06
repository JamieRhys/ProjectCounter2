package com.jre.projectcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.jre.projectcounter.button.ProjectButton;
import com.jre.projectcounter.intents.AddProject;
import com.jre.projectcounter.project.Project;
import com.jre.projectcounter.project.ProjectManager;
import com.jre.projectcounter.utils.KCColor;
import com.jre.projectcounter.utils.KCFiles;

import java.util.HashMap;
import java.util.Map;

/**
 * MainActivity is our entry point and houses our project icons and anything else we need.
 * It also launches all other intents we are using.
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class MainActivity extends AppCompatActivity {
    private ProjectManager                          mProjectManager;
    private String                                  mSaveFilePath = "storage.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KCColor.getInstance().initialiseBackgroundBorders(this);
        mProjectManager = new ProjectManager(this,
                this,
                (LinearLayout) findViewById(R.id.container_project_stack));
    }

    /**
     * Add project.
     *
     * @param v the v
     */
    public void addProject(View v) {
        Intent i = new Intent(this, AddProject.class);
        this.startActivityForResult(i, ProjectManager.ACTIVITY_REQUESTS.REQUEST_ADD_PROJECT);
    }

    /**
     * Sets .
     *
     * @param v the v
     */
    public void settings(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ProjectManager.ACTIVITY_REQUESTS.REQUEST_ADD_PROJECT && resultCode == RESULT_OK) {
            String projectName = data.getStringExtra("projectName");

            mProjectManager.destroyProjectButtons();
            mProjectManager.addProject(projectName);
            mProjectManager.createProjectButtons();
        } else if(requestCode == ProjectManager.ACTIVITY_REQUESTS.REQUEST_PROJECT_VIEW && resultCode == RESULT_OK) {
            Project project = (Project) data.getSerializableExtra("project");
            mProjectManager.updateProjectMap(project.getMapPosition(), project);
            mProjectManager.saveProjects();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(KCFiles.isFilePresent(this, mSaveFilePath)) {
            if(!mProjectManager.isProjectMapInitialised()) {
                mProjectManager.loadProjects();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mProjectManager.saveProjects();
    }
}
