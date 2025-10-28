package com.example.sugiharamap.utils.filesUtil;


import java.io.File;

public class FilesService {
    public static final String resPath = "src/main/resources/com/example/sugiharamap/";
    public static final String survivorsPath = resPath + "survivors/";
    public static final String imagesPath = resPath + "images/";

    public static String[] getAllFileNameInDir(String path)
    {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null)
        {
            String[] names = new String[files.length];
            for (int i = 0; i < files.length; i++)
            {
                names[i] = files[i].getName();
            }
            return names;
        }
        throw new RuntimeException("Wrong path!");

    }
}
