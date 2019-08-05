package com.jre.projectcounter.project;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;

public class Project implements Serializable {
    private String                              mProjectName;
    private int                                 mGlobalCounterRow;
    private int                                 mIncBy;
    private HashMap<Integer, Counter>           mCounterMap;


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

    public final String getProjectName() { return mProjectName; }
}
