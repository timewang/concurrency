/**
 *
 */
package org.snailgary.test;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.snailgary.tika.TikaParser;
import org.snailgary.tika.task.TaskExcecutionParser;
import org.snailgary.util.FileUtil;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * ***********************************************************
 *
 * @类名 ：ParseTest.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/10/1
 * ***********************************************************
 */
public class ParseTest {

    @Test
    public void parseSingleFileTest() throws IOException, TikaException, SAXException {
       System.out.println(TikaParser.parseSingleFile("C:\\Users\\snail\\Desktop\\temp\\parsetest\\PDF-BOOK\\couchdb.pdf"));
    }

    @Test
    public void testGetFileCount(){
        System.out.print("总文件数：" + FileUtil.getListFiles("C:\\Users\\snail\\Desktop\\temp\\parsetest").size());
    }

    @Test
    public void parseFilesTest() throws IOException, TikaException, SAXException {
        Long start = System.currentTimeMillis();

        TikaParser.parseFilesInFolder("C:\\Users\\snail\\Desktop\\temp\\parsetest");

        Long end = System.currentTimeMillis();

        System.out.println("普通耗时：" + (end - start));
    }

    @Test
    public void exceutionParse() throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
        //TaskExcecutionParser taskExcecutionParser = new TaskExcecutionParser(Executors.newFixedThreadPool(200));
        TaskExcecutionParser taskExcecutionParser = new TaskExcecutionParser(Executors.newCachedThreadPool());

        taskExcecutionParser.parseFiles(FileUtil.getListFiles("C:\\Users\\snail\\Desktop\\temp\\parsetest"));

        Long end = System.currentTimeMillis();

        System.out.println("并发耗时：" + (end - start));

    }

    @Test
    public void parsePdf() throws TikaException, SAXException, IOException {
        //Tika默认是10*1024*1024，这里防止文件过大导致Tika报错
        BodyContentHandler handler = new BodyContentHandler(100*1024*1024);

        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\snail\\Desktop\\temp\\parsetest\\PDF-BOOK\\couchdb.pdf"));
        ParseContext pcontext = new ParseContext();

        // 解析PDF文档时应由超类AbstractParser的派生类PDFParser实现
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);

        // 获取PDF文档的内容
        System.out.println("PDF文档内容:" + handler.toString());

        // 获取PDF文档的元数据
        System.out.println("PDF文档元数据:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + " : " + metadata.get(name));
        }
    }

}
