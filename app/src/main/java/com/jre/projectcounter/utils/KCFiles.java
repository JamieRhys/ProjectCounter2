package com.jre.projectcounter.utils;

import android.content.Context;

import java.io.File;

public class KCFiles {

    public static boolean isFilePresent(Context context, final String filename) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + filename;
        File file = new File(path);
        return file.exists();
    }
}
