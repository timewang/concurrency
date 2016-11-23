/**
 *
 */
package org.snailgary.test.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ***********************************************************
 *
 * @类名 ：ChannelTest.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/11/23
 * ***********************************************************
 */
public class ChannelTest {


    @Test
    public void fileChannelTest() throws IOException {
        try(RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\snail\\Desktop\\temp\\data.txt", "rw")){
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
        }
    }

    @Test
    public void fileChannelTest2() throws IOException {
        try(FileInputStream aFile = new FileInputStream("C:\\Users\\snail\\Desktop\\temp\\data.txt")){
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
        }
    }


}
