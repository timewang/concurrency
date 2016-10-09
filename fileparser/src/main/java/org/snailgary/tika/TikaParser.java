/**
 *
 */
package org.snailgary.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.snailgary.util.FileUtil;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ***********************************************************
 *
 * @类名 ：TikaParser.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/10/1
 * ***********************************************************
 */
public class TikaParser {


    private static final Tika tika = new Tika();

    /**
     * 解析单个文件
     * @param filePath
     * @return
     */
    public static String parseSingleFile(final String filePath) throws IOException, TikaException, SAXException {
        return parseFileToString(new File(filePath));
    }

    public static Map<String,String> parseFilesInFolder(final String folderPath) throws IOException, TikaException, SAXException {
        List<File> files = FileUtil.getListFiles(folderPath);
        return parseFilesToString(files);
    }



    public static Map<String,String> parseFilesToString(final List<File> files) throws TikaException, IOException, SAXException {
        Map<String,String> map = new HashMap();
        for(final File file : files)
            map.put(file.getName() + System.currentTimeMillis(), parseFileToString(file));
        return map;
    }

    public static String parseFileToString(final File file) throws IOException, TikaException, SAXException {
        Tika tika = new Tika();
        if(file.getName().toLowerCase().endsWith(".pdf")){
            return scape(parsePdf(file));
        }
        return scape(tika.parseToString(file));
    }

    private static String scape(String s){
        return s.replaceAll("\n", "").replaceAll("�", "")
                .replaceAll("\t", "")
                .replaceAll("<", "&lt;")
                .replaceAll(">", " 	&gt;")
                .replaceAll("&", "&amp;")
                .replaceAll("\"", "&quot;")
                .replaceAll("©", "&copy;")
                .replaceAll("®", "&reg;")
                .replaceAll("™", "™")
                .replaceAll("×", "&times;")
                .replaceAll("÷", "&divide;")
                .trim();
    }

    private static String parsePdf(final File pdfFile) throws IOException, TikaException, SAXException {
        //Tika默认是10*1024*1024，这里防止文件过大导致Tika报错
        BodyContentHandler handler = new BodyContentHandler(100*1024*1024);

        Metadata metadata = new Metadata();
        try(FileInputStream inputstream = new FileInputStream(pdfFile)){
            ParseContext pcontext = new ParseContext();

            // 解析PDF文档时应由超类AbstractParser的派生类PDFParser实现
            PDFParser pdfparser = new PDFParser();
            pdfparser.parse(inputstream, handler, metadata, pcontext);

            return handler.toString();
        }
    }

}
