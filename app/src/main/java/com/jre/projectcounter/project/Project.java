package com.jre.projectcounter.project;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The type Project.
 */
public class Project implements Serializable {
    private String                              mProjectName;
    private int                                 mGlobalCounterRow;
    private int                                 mIncBy;
    private int                                 mMapPosition;
    private HashMap<Integer, Counter>           mCounterMap;


    /**
     * Instantiates a new Project.
     *
     * @param projectName      the project name
     * @param globalCounterRow the global counter row
     * @param incBy            the inc by
     * @param counterMap       the counter map
     */
    public Project(
            final String projectName,
            final int globalCounterRow,
            final int incBy,
            final HashMap<Integer, Counter> counterMap) {
        mProjectName = projectName;
        mGlobalCounterRow = globalCounterRow;
        mIncBy = incBy;
        mCounterMap = counterMap;
    }

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public final String getProjectName() { return mProjectName; }

    /**
     * Gets global counter row.
     *
     * @return the global counter row
     */
    public final int getGlobalCounterRow() { return mGlobalCounterRow; }

    /**
     * Gets inc by.
     *
     * @return the inc by
     */
    public final int getIncBy() { return mIncBy; }

    /**
     * Gets counter map.
     *
     * @return the counter map
     */
    public final HashMap<Integer, Counter> getCounterMap() { return mCounterMap; }

    /**
     * Gets map position.
     *
     * @return the map position
     */
    public final int getMapPosition() { return mMapPosition; }

    /**
     * Sets project name.
     *
     * @param name the name
     */
    public void setProjectName(final String name) { mProjectName = name; }

    /**
     * Sets global counter row.
     *
     * @param row the row
     */
    public void setGlobalCounterRow(final int row) { mGlobalCounterRow = row; }

    /**
     * Sets inc by.
     *
     * @param incBy the inc by
     */
    public void setIncBy(final int incBy) { mIncBy = incBy; }

    /**
     * Sets map position.
     *
     * @param position the position
     */
    public void setMapPosition(final int position) { mMapPosition = position; }
}
