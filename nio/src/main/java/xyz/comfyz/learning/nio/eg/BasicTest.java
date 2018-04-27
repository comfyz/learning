package xyz.comfyz.learning.nio.eg;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :    comfy
 * @date :      2018-04-23 10:52:24
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class BasicTest {

    public static void main(String[] args) throws InterruptedException {
        BasicTest nioTest = new BasicTest();
//        nioTest.sof();
//        nioTest.oom();
        nioTest.testByteBuffer();
    }

    private void sof() {
        this.sof();
    }

    private void oom() {
        List<String> ss = new ArrayList<>();
        int i = 0;
        while (true) {
            ss.add(String.valueOf(i).intern());
        }
    }

    public void testByteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        System.out.println(buffer.limit());     //default limit=allocate
        System.out.println(buffer.position());  //default position=0
        //buffer.limit(257);        //IllegalArgumentException limit<=allocate
        buffer.limit(199);
        //buffer.position(200);     //position<=limit
        buffer.position(199);
        buffer.clear();
        buffer.rewind();
        buffer.flip();
        buffer.remaining();
        buffer.hasRemaining();
    }


}
