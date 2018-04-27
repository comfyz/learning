package xyz.comfyz.learning.reflection.basic;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : comfy create at 2018-04-11 18:28
 */
public class BeanUtils {

    // TODO 克隆
    public static <T> T clone(T source) {
        if (isPrimitive(source)) {
            return source;
        }
        Class<T> clazz = (Class<T>) source.getClass();
        T result = null;
        //判断是数组？集合？简单对象？
        if (clazz.isArray()) {
            final int length = Array.getLength(source);
            result = (T) Array.newInstance(clazz.getComponentType(), length);
            for (int i = 0; i < length; i++) {
                Array.set(result, i, clone(Array.get(source, i)));
            }
        } else {
            //是否是内部类,是否是静态类
            try {
                result = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException ignored) {
            }
            if (result == null) {
                Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
                System.out.println(JSON.toJSONString(constructor));
                constructor.setAccessible(true);
                Class[] paramTypes = constructor.getParameterTypes();
                System.out.println(JSON.toJSONString(paramTypes));
                try {
                    if (paramTypes.length == 0)
                        result = constructor.newInstance();
//                    else
//                        result = constructor.newInstance(Arrays.stream(paramTypes).map(BeanUtils::newInstance).collect(Collectors.toList()));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            //设置属性
            clazz.getDeclaredFields();
        }

        return result;
    }

    public static <T> T[] clone(T[] array) {
        if (array == null)
            return null;
        return array.clone();
    }

    // TODO 属性拷贝
    public static void copyProperties(Object target, Object source) {

    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        List<Integer> simples = new ArrayList<>();
        System.out.println(getGenericType(simples.getClass()));
    }

    private static void test3() {
        List[] h = new List[]{new ArrayList(), new ArrayList(), new ArrayList()};
        List[] i = h.clone();
//        Integer[] j = clone(h);
        h[0].add(1);
        System.out.println("h : " + Arrays.toString(h));
        System.out.println("i : " + Arrays.toString(i));
//        System.out.println("j : " + Arrays.toString(j));
    }

    private static void test2() {
        Integer h = 1;
        Integer i = h;
        Integer j = clone(i);
        i = 0;
        System.out.println("h : " + h);
        System.out.println("i : " + i);
        System.out.println("j : " + j);
    }

    private static void test1() {
        boolean o1 = true;
        Boolean o2 = true;
        char o3 = '1';
        Character o4 = '1';
        Byte o5 = 0;
        Byte o6 = 0;
        short o7 = 0;
        Short o8 = 0;
        int o9 = 0;
        Integer o10 = 0;
        long o11 = 0;
        Long o12 = 0L;
        float o13 = 0;
        Float o14 = 0F;
        double o15 = 0;
        Double o16 = 0D;
        System.out.println("o1 : " + isPrimitive(o1));
        System.out.println("o2 : " + isPrimitive(o2));
        System.out.println("o3 : " + isPrimitive(o3));
        System.out.println("o4 : " + isPrimitive(o4));
        System.out.println("o5 : " + isPrimitive(o5));
        System.out.println("o6 : " + isPrimitive(o6));
        System.out.println("o7 : " + isPrimitive(o7));
        System.out.println("o8 : " + isPrimitive(o8));
        System.out.println("o9 : " + isPrimitive(o9));
        System.out.println("o10 : " + isPrimitive(o10));
        System.out.println("o11 : " + isPrimitive(o11));
        System.out.println("o12 : " + isPrimitive(o12));
        System.out.println("o13 : " + isPrimitive(o13));
        System.out.println("o14 : " + isPrimitive(o14));
        System.out.println("o15 : " + isPrimitive(o15));
        System.out.println("o16 : " + isPrimitive(o16));
    }

    /**
     * 是否是原生类型，根据常见使用状况排序判断
     *
     * @param obj 判断对象
     * @return <code>true</code>or<code>false</code>
     */
    public static boolean isPrimitive(Object obj) {
        return (obj instanceof Character
                || obj instanceof Integer
                || obj instanceof Long
                || obj instanceof Boolean
                || obj instanceof Double
                || obj instanceof Float
                || obj instanceof Byte
                || obj instanceof Short
        );
    }

    public static Class<?> getGenericType(Class<?> clazz) {
        if (clazz == Object.class) {
            return null;
        }
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = ((ParameterizedType) type);
            Type[] args = ptype.getActualTypeArguments();
            return (Class<?>) args[0];
        }
        return getGenericType(clazz.getSuperclass());
    }

}
