package com.jre.projectcounter.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.jre.projectcounter.R;

/** Allows the user to actually see and interact with the given project view.
 *
 * @author Jamie-Rhys Edwards
 * @author jamie_airfix@yahoo.co.uk
 * @since v0.0.01
 */
public class ProjectView extends AppCompatActivity {
    private TextView                mTV_ProjectName;
    private String                  mProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        mTV_ProjectName = findViewById(R.id.tv_project_view_project_name);

        Intent i = this.getIntent();
        mProjectName = i.getStringExtra("projectName");
        mTV_ProjectName.setText(mProjectName);
    }

    @Override
    public void finish() {
        Intent i = this.getIntent();
        i.putExtra("projectName", "GoodBye");
        this.setResult(RESULT_OK, i);
        super.finish();

    }
}
