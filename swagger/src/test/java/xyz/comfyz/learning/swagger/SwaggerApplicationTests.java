package xyz.comfyz.learning.swagger;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SwaggerApplicationTests {

    public static void main(String[] args) throws InterruptedException {
        SwaggerApplicationTests test = new SwaggerApplicationTests();
        test.contextLoads();
    }

    @Test
    public void contextLoads() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(60, 100,
                10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
        HttpClient client = HttpClients.createDefault();
        HttpUriRequest getReq = new HttpGet("http://127.0.0.1:8080/user/test");

        for (int i = 0; i < 20; i++) {
            final int cu = i;
            executor.execute(() -> {
                try {
                    InputStream in = client.execute(getReq).getEntity().getContent();
                    BufferedInputStream inputStream = new BufferedInputStream(in);
                    byte[] b = new byte[512];
                    StringBuilder sb = new StringBuilder();
                    while (inputStream.read(b) != -1) {
                        sb.append(sb);
                    }
                    System.out.println(">>>>>>>>>>>> " + cu + " " + sb);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            });
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> FINISH");
        Thread.sleep(1000 * 60);
        while (!executor.isShutdown() && executor.getActiveCount() < 1) {
            executor.shutdown();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> SHUTDOWN");

    }

}
