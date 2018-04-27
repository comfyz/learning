package xyz.comfyz.learning.quartz;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author :    comfy
 * @date :      2018-04-21 09:11:55
 * @since :     1.8
 * <p>
 * Least Recently Used
 * 淘汰算法
 * </p>
 */
public class LRUCacheTest {

    public static void main(String[] args) {
        final int size = 3;
        LinkedHashMap<String, Object> lruCache = new LinkedHashMap<String, Object>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size + 1 == this.size();
            }
        };

        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        lruCache.put("4", "4");
        lruCache.get("2");
        Set<Map.Entry<String, Object>> values = lruCache.entrySet();
        values.forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }
}
