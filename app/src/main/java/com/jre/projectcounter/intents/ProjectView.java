package com.jre.projectcounter.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jre.projectcounter.R;
import com.jre.projectcounter.project.Project;

public class ProjectView extends AppCompatActivity {
    private Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        Intent i = this.getIntent();
        mProject = (Project) i.getSerializableExtra("project");

        System.out.println("PROJECT NAME: " + mProject.getProjectName());
    }
}
