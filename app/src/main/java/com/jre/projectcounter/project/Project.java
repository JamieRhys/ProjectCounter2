package com.jre.projectcounter.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jre.projectcounter.button.KCButton;

public class Project extends AppCompatActivity {
    private ProjectButton mProjectButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** This is our project button. Used to launch our activity. */
    private class ProjectButton extends KCButton {
        public ProjectButton(Context context) {
            super(context);
        }
    }
}
