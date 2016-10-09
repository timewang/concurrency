/**
 *
 */
package org.snailgary.util;

import java.io.*;
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
     *
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

    /**
     * 获取文件编码格式
     *
     * @param filePpath
     * @return
     * @throws IOException
     */
    public static String getFileEncod(String filePpath) throws IOException {
        try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream(filePpath))){
            int p = (bin.read() << 8) + bin.read();
            String code;
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
            return code;
        }
    }

    /**
     * 指定编码格式读取文件
     *
     * @param filePath:文件绝对路径
     * @param encoding:文件编码
     * @author wang zhong fu
     */
    public static String readFileContent(String filePath, String encoding) throws IOException {
        File wordFile = new File(filePath);
        Long filelength = wordFile.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try (FileInputStream in = new FileInputStream(wordFile)) {
            in.read(filecontent);
            String wordsContent = new String(filecontent, encoding);
            return wordsContent;
        }
    }

}
