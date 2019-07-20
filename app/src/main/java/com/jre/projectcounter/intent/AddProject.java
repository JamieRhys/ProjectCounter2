package com.jre.projectcounter.intent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jre.projectcounter.R;

/** AddProject allows the app to grab input from user to populate a new project.
 *
 * @brief This grabs the project name and then passes to ProjectManager to create the project in question.
 *
 * @author Jamie-Rhys Edwards
 * @author jamie_airfix@yahoo.co.uk
 * @since v0.0.01
 */
public class AddProject extends AppCompatActivity {
    private EditText            mET_ProjectName;
    private Button              mButton_Create;
    private Button              mButton_Close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        mET_ProjectName = findViewById(R.id.et_add_project);
        mButton_Create = findViewById(R.id.button_add_project_create);
        mButton_Close = findViewById(R.id.button_add_project_close);
    }

    public void createProject(View v) {
        Intent i = this.getIntent();
        i.putExtra("projectName", mET_ProjectName.getText().toString());
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    public void closeIntent(View v) {
        finish();
    }
}
