package com.jre.projectcounter.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class KCFiles {

    public static void createNewJSONFile(Context context, final String filename) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename, context.MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        osw.write("{}");
        osw.flush();
        osw.close();
    }

    public static boolean isFilePresent(Context context, final String filename) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + filename;
        File file = new File(path);
        return file.exists();
    }
}
