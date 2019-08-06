package com.jre.projectcounter.intents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jre.projectcounter.R;
import com.jre.projectcounter.project.Project;

public class ProjectView extends AppCompatActivity {
    private Project                                 mProject;

    private TextView                                mTV_ProjectName;
    private TextView                                mTV_GlobalCounter;
    private LinearLayout                            mContainer_Counters;
    private LinearLayout                            mContainer_Row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        Intent i = this.getIntent();
        mProject = (Project) i.getSerializableExtra("project");
    }

    /**
     * We need to override finish() so that when the user presses back button, we can send the correct
     * result and also the project.
     */
    @Override
    public void finish() {
        Intent i = this.getIntent();
        i.putExtra("project", mProject);
        this.setResult(Activity.RESULT_OK, i);
        super.finish();
    }
}
