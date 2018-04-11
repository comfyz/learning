package xyz.comfyz.learning.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : comfy create at 2018-04-11 11:41
 */
public class DeclaredTest {

    public DeclaredTest(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        try {
            //class xyz.comfyz.learning.reflection.Test$Simple
            test(new Simple("new Simple"));

            List<Simple> simples = new ArrayList<>();
            simples.add(new Simple("new Simple in list"));
            //class java.util.ArrayList
            test(simples);

            //class [Ljava.lang.Integer;
            test(new Integer[]{1, 2, 3, 4, 5});
            //class [Ljava.lang.String;
            test(new String[0]);
            //class [Lxyz.comfyz.learning.reflection.Test$Simple;
            test(new Simple[0]);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void test(Object obj) throws ClassNotFoundException {
        Class clazz = Class.forName(obj.getClass().getName());

        Field[] fields = clazz.getDeclaredFields();
        System.out.println(clazz + "-[Field] : " + Arrays.toString(fields));

        Method[] methods = clazz.getDeclaredMethods();
        System.out.println(clazz + "-[Method] : " + Arrays.toString(methods));

        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println(clazz + "-[Constructor] : " + Arrays.toString(constructors));
    }

    @SuppressWarnings("unused")
    public static class Simple {

        private final String name;

        private Simple() {
            this.name = "default name";
        }

        Simple(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return "{\"name\" : \"" + this.name + "\"}";
        }
    }
}
