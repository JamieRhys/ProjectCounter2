package com.jre.projectcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.jre.projectcounter.button.ProjectButton;
import com.jre.projectcounter.project.Project;
import com.jre.projectcounter.utils.KCColor;

import java.util.HashMap;

/**
 * MainActivity is our entry point and houses our project icons and anything else we need.
 * It also launches all other intents we are using.
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KCColor.getInstance().initialiseBackgroundBorders(this);

        Project project = new Project("Hello", 0, 1, new HashMap());
        ProjectButton projectButton = new ProjectButton(this, this, project);

        LinearLayout layout = this.findViewById(R.id.container_project_stack);

        layout.addView(projectButton);
    }

    public void launchProject(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK) {

        }
    }
}
