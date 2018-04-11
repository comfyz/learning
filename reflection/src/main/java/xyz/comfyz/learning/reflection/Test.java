package xyz.comfyz.learning.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : comfy create at 2018-04-11 11:41
 */
public class Test {

    public Test(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InterruptedException, InvocationTargetException {
        Class clazz = Class.forName(Simple.class.getName());

        Field[] fields = clazz.getDeclaredFields();
        System.out.println("[Field] : " + Arrays.toString(fields));

        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("[Method] : " + Arrays.toString(methods));

        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println("[Constructor] : " + Arrays.toString(clazz.getDeclaredConstructors()));
        Arrays.stream(constructors).parallel().forEach(constructor -> {
            constructor.setAccessible(true);
            try {
                List<Object> params = Arrays.stream(constructor.getParameterTypes())
                        .map(argType -> {
                            try {
                                return argType.newInstance();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }).collect(Collectors.toList());
                Object o = constructor.newInstance(params.toArray(new Object[0]));
                System.out.println(o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public static class Simple {

        private final String name;

        private Simple(String name) {
            this.name = name;
        }

        private String name() {
            return this.name;
        }

        @Override
        public String toString() {
            return "{\"name\" : \"" + this.name + "\"}";
        }
    }
}
