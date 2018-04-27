package xyz.comfyz.learning.nio.traditional;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author :    comfy
 * @date :      2018-04-26 15:54:30
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class FileCopyIOTest {

    private final int BUFFER_SIZE = 1024;

    /**
     * 速度最快的数copyFileByStream 使用缓存 nio的copyFileBNio旗鼓相当
     */
    public static void main(String[] args) throws IOException {
        FileCopyIOTest test = new FileCopyIOTest();
        long var1 = System.currentTimeMillis();

        //文件大的话系统直接卡死
        //46s(17.4M)
//        test.copyFileByStream("C:\\Users\\comfy\\Desktop\\netty.txt",
//                "C:\\Users\\comfy\\Desktop\\netty_copy_stream.txt");
//        System.out.println(">>>>>>>>> Stream : " + ((System.currentTimeMillis() - var1)));

        //当缓存在文件实际大小的1/5时候速度最快，1G文件耗时0.88s
        //4.4s(1024, 0.98G) 0.09s(1024, 17.4M)
        test.copyFileByStream2("C:\\Users\\comfy\\Desktop\\netty.txt",
                "C:\\Users\\comfy\\Desktop\\netty_copy_stream2.txt");
        System.out.println(">>>>>>>>> Stream2 : " + ((System.currentTimeMillis() - var1)));

        //5.3s(1024, 0.98G) 0.16s(1024, 17.4M)
//        test.copyFileByReader("C:\\Users\\comfy\\Desktop\\netty.txt",
//                "C:\\Users\\comfy\\Desktop\\netty_copy_reader.txt");
//        System.out.println(">>>>>>>>> Reader : " + ((System.currentTimeMillis() - var1)));

        //7.9s(0.98G) 0.21s(1024, 17.4M)
//        test.copyFileByBuffer("C:\\Users\\comfy\\Desktop\\netty.txt",
//                "C:\\Users\\comfy\\Desktop\\netty_copy_buffer.txt");
//        System.out.println(">>>>>>>>> Buffer : " + ((System.currentTimeMillis() - var1)));

        //4.5s(1024, 0.98G) 0.11s(1024, 17.4M)
//        test.copyFileBNio("C:\\Users\\comfy\\Desktop\\netty.txt",
//                "C:\\Users\\comfy\\Desktop\\netty_copy_nio.txt");
//        System.out.println(">>>>>>>>> Buffer : " + ((System.currentTimeMillis() - var1)));
    }


    public void copyFileByStream(String sourcePath, String targetPath) throws IOException {
        FileInputStream fio = null;
        FileOutputStream fos = null;
        try {
            fio = new FileInputStream(new File(sourcePath));
            fos = new FileOutputStream(new File(targetPath));
            int r;
            while ((r = fio.read()) != -1) {
                fos.write(r);
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if (null != fio)
                fio.close();
            if (null != fos)
                fos.close();
        }
    }

    public void copyFileByStream2(String sourcePath, String targetPath) throws IOException {
        FileInputStream fio = null;
        FileOutputStream fos = null;
        try {
            fio = new FileInputStream(new File(sourcePath));
            fos = new FileOutputStream(new File(targetPath));
            byte[] r = new byte[BUFFER_SIZE];
            while ((fio.read(r)) != -1) {
                fos.write(r);
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if (null != fio)
                fio.close();
            if (null != fos)
                fos.close();
        }
    }

    public void copyFileByReader(String sourcePath, String targetPath) throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(new File(sourcePath));
            fw = new FileWriter(new File(targetPath));
            char[] r = new char[BUFFER_SIZE];
            while (fr.read(r) != -1) {
                fw.write(r);
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if (null != fr)
                fr.close();
            if (null != fw)
                fw.close();
        }
    }

    public void copyFileByBuffer(String sourcePath, String targetPath) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File(sourcePath)));
            bw = new BufferedWriter(new FileWriter(new File(targetPath)));
            String r;
            while ((r = br.readLine()) != null) {
                bw.write(r);
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if (null != br)
                br.close();
            if (null != bw)
                bw.close();
        }
    }

    public void copyFileBNio(String sourcePath, String targetPath) throws IOException {
        RandomAccessFile sourceFile = new RandomAccessFile(sourcePath, "r");
        RandomAccessFile targetFile = new RandomAccessFile(targetPath, "rw");
        FileChannel sfc = sourceFile.getChannel();
        FileChannel tfc = targetFile.getChannel();
        try {
            ByteBuffer b = ByteBuffer.allocate(BUFFER_SIZE);
            while (sfc.read(b) != -1) {
                b.flip();
                tfc.write(b);
                b.rewind();
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            sfc.close();
            tfc.close();
        }
    }
}
