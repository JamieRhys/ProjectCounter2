package com.jre.projectcounter.project;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jre.projectcounter.MainActivity;
import com.jre.projectcounter.button.ProjectButton;
import com.jre.projectcounter.utils.KCColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ProjectManager manages our projects throughout the apps lifetime.
 *
 * @author Jamie -Rhys Edwards
 * @author jamie_airfix @yahoo.co.uk
 * @since v0.0.01
 */
public class ProjectManager {
    private Context                                         mContext;
    private MainActivity                                    mActivity;
    private HashMap<Integer, ProjectContainer>              mProjectMap;

    private int                                             mProjMapPreVal;

    private LinearLayout                                    mContainer_ProjectStack;
    private LinearLayout                                    mContainer_Row;

    /**
     * Instantiates a new Project manager.
     *
     * @param context  the context
     * @param activity the activity
     * @param layout   the layout
     */
    public ProjectManager(final Context context,
                          final MainActivity activity,
                          final LinearLayout layout) {
        mContext = context;
        mActivity = activity;

        mContainer_ProjectStack = layout;

        mProjectMap = new HashMap<>();
        mProjMapPreVal = mProjectMap.size();
    }

    /**
     * Add project.
     *
     * @param projectName  the project name
     * @param globalRow    the global row
     * @param incBy        the inc by
     * @param counterMap   the counter map
     * @param backgroundID the background id
     */
    public void addProject(final String projectName,
                           final int globalRow,
                           final int incBy,
                           final HashMap<Integer, Counter> counterMap,
                           int backgroundID) {
        if(backgroundID < 0) {
            backgroundID = KCColor.getInstance().generateBackgroundID();
        }

        int mapPosition = mProjectMap.size() + 1;

        Project project = new Project(projectName,
                globalRow,
                incBy,
                counterMap);

        ProjectContainer projectContainer = new ProjectContainer(
                project,
                new ProjectButton(mContext, mActivity, project, backgroundID));

        projectContainer.getProjectButton().setOnClickListener(projectContainer.getProjectButton());
        mProjectMap.put(mapPosition, projectContainer);
        project.setMapPosition(mapPosition);
    }

    /**
     * Add project.
     *
     * @param projectName the project name
     */
    public void addProject(final String projectName) {
        this.addProject(projectName,
                0,
                1,
                new HashMap<Integer, Counter>(),
                -1);
    }

    public void updateProjectMap(int position, Project project) {
        mProjectMap.get(position).setProject(project);
    }

    /**
     * Creates our project buttons
     */
    public void createProjectButtons() {
        for(Map.Entry<Integer, ProjectContainer> entry : mProjectMap.entrySet()) {
            if((entry.getKey() - 1) % 2 == 0 || mContainer_ProjectStack == null) {
                mContainer_Row = new LinearLayout(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                mContainer_Row.setLayoutParams(lp);
                mContainer_Row.setOrientation(LinearLayout.HORIZONTAL);
                mContainer_Row.setGravity(Gravity.CENTER);
                mContainer_ProjectStack.addView(mContainer_Row);
            }

            mContainer_Row.addView(entry.getValue().getProjectButton());
        }
    }

    /**
     * Destroys any active buttons.
     */
    public void destroyProjectButtons() {
        if(mProjectMap != null) {
            for(Map.Entry<Integer, ProjectContainer> entry : mProjectMap.entrySet()) {
                if(entry.getValue().getProjectButton().getParent() != null) {
                    ((ViewGroup) entry.getValue().mProjectButton.getParent()).removeView(entry.getValue().mProjectButton);
                }
            }
        }
    }

    private boolean hasProjMapChanged() { return
            mProjMapPreVal > mProjectMap.size() ||
                    mProjMapPreVal < mProjectMap.size();
    }

    /**
     * Is project map initialised boolean.
     *
     * @return the boolean
     */
    public final boolean isProjectMapInitialised() { return mProjectMap.size() > 0; }

    private void writeJSONStream() {
        if(mProjectMap.size() > 0 && hasProjMapChanged()) {
            JSONObject root = new JSONObject();
            JSONArray projects = new JSONArray();

            for(Map.Entry<Integer, ProjectContainer> entry : mProjectMap.entrySet()) {
                JSONObject project = new JSONObject();
                JSONObject projectDetails = new JSONObject();
                JSONArray projectTotal = new JSONArray();

                project.put("projectName", entry.getValue().mProject.getProjectName());

                projectDetails.put("backgroundID", entry.getValue().mProjectButton.getBackgroundID());
                projectDetails.put("globalRow", entry.getValue().mProject.getGlobalCounterRow());
                projectDetails.put("incBy", entry.getValue().mProject.getIncBy());

                projectTotal.add(projectDetails);

                project.put("details", projectTotal);

                projects.add(project);
            }

            root.put("projects", projects);

            try(FileWriter fw = new FileWriter(mContext.getFilesDir().getAbsolutePath() + "/" + "storage.json")) {
                fw.write(root.toJSONString());
                fw.flush();
            } catch(IOException e) {

            }
        }
    }

    private void readJSONStream() {
        JSONParser parser = new JSONParser();

        try(FileReader fr = new FileReader(mContext.getFilesDir().getAbsolutePath() + "/" + "storage.json")) {
            JSONObject jsonObj = (JSONObject) parser.parse(fr);
            JSONArray projectRoot = (JSONArray) jsonObj.get("projects");

            for(int i = 0; i < projectRoot.size(); i++) {
                JSONObject obj = (JSONObject) projectRoot.get(i);
                JSONArray arr = (JSONArray) obj.get("details");

                String projectName = (String) obj.get("projectName");
                long globalRow = 0;
                long incBy = 0;
                long backgroundID = -1;

                for(int j = 0; j < arr.size(); j++) {
                    JSONObject projectDetails = (JSONObject) arr.get(j);

                    globalRow = (long) projectDetails.get("globalRow");
                    incBy = (long) projectDetails.get("incBy");
                    backgroundID = (long) projectDetails.get("backgroundID");
                }

                addProject(projectName, (int)globalRow, (int)incBy, new HashMap<Integer, Counter>(), (int)backgroundID);
            }

            createProjectButtons();
        } catch(IOException e) {

        } catch(ParseException e) {

        }
    }

    public void saveProjects() { writeJSONStream(); }

    public void loadProjects() { readJSONStream(); }

    /**
     * The type Activity requests.
     */
    public static final class ACTIVITY_REQUESTS {
        /**
         * The constant REQUEST_PROJECT_VIEW.
         */
        public static int REQUEST_PROJECT_VIEW          = 0;
        /**
         * The constant REQUEST_ADD_PROJECT.
         */
        public static int REQUEST_ADD_PROJECT           = 1;
        /**
         * The constant REQUEST_ADD_COUNTER.
         */
        public static int REQUEST_ADD_COUNTER           = 2;
    }

    public class ProjectContainer extends Object {
        private Project                 mProject;
        private ProjectButton           mProjectButton;

        /**
         * Instantiates a new Project container.
         *
         * @param project       the project
         * @param projectButton the project button
         */
        public ProjectContainer(final Project project, final ProjectButton projectButton) {
            mProject = project;
            mProjectButton = projectButton;
        }

        /**
         * Gets project button.
         *
         * @return the project button
         */
        public final ProjectButton getProjectButton() { return mProjectButton; }

        /**
         * Gets project.
         *
         * @return the project
         */
        public final Project getProject() { return mProject; }

        public final void setProject(Project project) { mProject = project; }
    }
}
