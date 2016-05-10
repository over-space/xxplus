package com.xxplus.nio;

import com.xxplus.base.BaseTest;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by admin on 16/05/08.
 */
public class NIOTest extends BaseTest{


    @Test
    public void testNIO01() throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\admin\\workspace\\xxplus\\src\\test\\resources\\file\\buffer.txt", "rw");


        FileChannel channel = aFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytes = channel.read(byteBuffer);//read into buffer.

        logger.info("bytes : {}", bytes);

        while (bytes != -1){

            byteBuffer.flip();//写模式切换成读模式

            while (byteBuffer.hasRemaining()){
                logger.info("buf get : {}", (char)byteBuffer.get());
            }

            byteBuffer.clear();
            bytes = channel.read(byteBuffer);
        }
        aFile.close();
    }

}
