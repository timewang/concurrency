/**
 *
 */
package org.snailgary.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ***********************************************************
 *
 * @类名 ：FileUtil.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/10/1
 * ***********************************************************
 */
public class FileUtil {

    /**
     * 获取文件夹下所有的文件
     * @param obj
     * @return
     */
    public static List<File> getListFiles(Object obj) {
        File directory = null;
        if (obj instanceof File) {
            directory = (File) obj;
        } else {
            directory = new File(obj.toString());
        }
        List<File> files = new ArrayList<File>();
        if (directory.isFile()) {
            files.add(directory);
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File fileOne = fileArr[i];
                files.addAll(getListFiles(fileOne));
            }
        }
        return files;
    }

}
