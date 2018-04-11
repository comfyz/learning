package xyz.comfyz.learning.socket;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author : comfy create at 2018-04-10 13:32
 */
public class Test {

    public static void main(String[] args) {
        ConcurrentSkipListMap<String, Object> map = new ConcurrentSkipListMap<String, Object>() {
            @Override
            public Object put(String key, Object value) {
                if (super.size() > 100)
                    super.remove(firstKey());
                return super.put(key, value);
            }

            @Override
            public Object get(Object key) {
                Object r = super.get(key);
                super.remove(key);
                super.put(String.valueOf(key), r);
                return super.get(key);
            }
        };

        for (int i = 0; i < 10; i++) {
            if (i > 0) {
                System.out.println(map.get("0"));
            }
            map.put(String.valueOf(i), i);
        }

        System.out.println(map.entrySet());
    }
}
