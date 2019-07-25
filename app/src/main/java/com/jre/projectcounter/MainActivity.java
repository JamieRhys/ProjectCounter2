package com.jre.projectcounter;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.jre.projectcounter.intent.AddProject;
import com.jre.projectcounter.project.Counter;
import com.jre.projectcounter.project.Project;
import com.jre.projectcounter.project.ProjectManager;
import com.jre.projectcounter.utils.KCColor;
import com.jre.projectcounter.utils.KCFiles;

/** MainActivity is our entry point and houses our project icons and anything else we need.
 * It also launches all other intents we are using.
 *
 * @author Jamie-Rhys Edwards
 * @author jamie_airfix@yahoo.co.uk
 * @since v0.0.01
 */
public class MainActivity extends AppCompatActivity {
    private ProjectManager mProjectManager;
    private String mSaveFilePath = "storage.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container_project_stack = findViewById(R.id.container_project_stack);

        KCColor.getInstance().initialiseBackgroundBorders(this);
        mProjectManager = new ProjectManager(this, this, container_project_stack);
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

    /**
     * Creates and launches Add Project intent.
     *
     * @param v the view in question
     */
    public void addProject(View v) {
        Intent i = new Intent(this, AddProject.class);
        this.startActivityForResult(i, ProjectManager.ACTIVITY_REQUESTS.REQUEST_ADD_PROJECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ProjectManager.ACTIVITY_REQUESTS.REQUEST_ADD_PROJECT) {
            if(resultCode == RESULT_OK) {
                String projectName = data.getStringExtra("projectName");
                mProjectManager.removeProjectButtons();
                mProjectManager.addProject(projectName);
                mProjectManager.createProjectButtons();
            }
        } else if (requestCode == ProjectManager.ACTIVITY_REQUESTS.REQUEST_PROJECT_VIEW) {
            if(resultCode == RESULT_OK) {
                String projectName = data.getStringExtra("projectName");
                System.out.println("HELLO: " + projectName);
            }
        }
    }
}
