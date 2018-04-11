package xyz.comfyz.learning.reflection;

import java.lang.reflect.*;
import java.util.Arrays;


/**
 * 这是java反射API的程序代码示例，其中包括java7以及java7之前的一些API
 */
public class ReflectionAPI {
    public static void main(String[] args) throws Exception {
        ReflectionAPI api = new ReflectionAPI();
        api.testConstructors();
        api.testFields();
        api.testMethods();
        api.testArrays();
    }

    /**
     * 反射获取构造方法的测试例子
     */
    @SuppressWarnings("all")
    private void testConstructors() {
        try {
            // Part1: 测试getConstructors方法，直接调用该方法，不会返回父类的构造方法。只会返回当前类的public类型的构造函数。
            Constructor<Child>[] childConstructors = (Constructor<Child>[]) Child.class.getConstructors();
            Arrays.stream(childConstructors).forEach(System.out::println);
            // Child.class.getConstructor(long.class); // 抛异常，获取不到非public构造，且不采取自动类型转换

            Constructor<Parent>[] parentConstructors = (Constructor<Parent>[]) Parent.class.getConstructors();
            Arrays.stream(parentConstructors).forEach(System.out::println);

            //Parent.class.newInstance();  //抛异常，调用public类型的无参构造
            //Parent.class.getConstructor();  //抛异常，只能获取public类型的
            Parent.class.getDeclaredConstructor();  //
            Parent.class.getConstructor(double.class);
            // Parent.class.getConstructor(void.class);   // 会抛出异常，当构造函数没有参数的时候直接不传即可，而不能传void.class
            // Parent.class.getConstructor(int.class);    // 会抛出异常，因为以int为参数的构造函数是不存在的

            // Part2: 测试getDeclaredConstruct，该方法能够得到当前类的所有构造函数，不管是public/protected/private还是默认访问权限的。
            childConstructors = (Constructor<Child>[]) Child.class.getDeclaredConstructors();
            Arrays.stream(childConstructors).forEach(System.out::println);
            Constructor<Child> _tempConstructor = Child.class.getDeclaredConstructor(long.class);
            //constructor.newInstance(1L);   // 抛异常 私有的构造函数实例化对象的时候需要先调用 constructor.setAccessible(true),否则会报权限错误。
            _tempConstructor.setAccessible(true);
            System.out.println(_tempConstructor.newInstance(1L));


            // Part3: 参数长度可变的构造函数
            // 获取构造函数的时候指定构造函数的参数应当当作数组来指定
            Constructor<VaragsConstructor> constructor = VaragsConstructor.class.getDeclaredConstructor(int[].class);
            System.out.println(constructor.getName());
            // 利用得到的Constructor创建对象的实例时，可以把传入的参数转换成Object类型，也可以使用参数类型的数组
            constructor.newInstance((Object) (new int[]{1, 2, 3, 4}));
            constructor.newInstance(new int[]{1, 2, 3, 4});

            // Part4: 嵌套类的构造方法
            // 嵌套类需要区分【静态嵌套类】和【非静态嵌套类】
            // 对于静态嵌套类，获得其构造函数的方法和其他的非嵌套类没有区别，
            // 对于非静态的嵌套类，因为必须先初始化外部类才能使用非静态的嵌套类， 因此在获取构造函数以及使用得到的构造函数实例化对象的时候第一个参数必须是外部类class和外部类实例

            Constructor<NonStaticNestedClass.NestedClass> constructor2 = NonStaticNestedClass.NestedClass.class.getDeclaredConstructor(NonStaticNestedClass.NestedClass.class.getDeclaringClass(), int.class);
            NonStaticNestedClass object = new NonStaticNestedClass();
            System.out.println(constructor2.newInstance(object, 12).toString());
        } catch (ReflectiveOperationException e) {  // 在java6中各种反射的异常需要逐个捕获。而java7提供了一个新的异常类： ReflectiveOperationException，该类可以捕获所有反射操作的异常(因为他是所有反射操作异常的父类)
            System.out.println(e.getCause().getMessage());  // 得到异常的具体信息
        }

    }

    /**
     * 反射进行域处理的例子
     */
    private void testFields() {
        // 同样测试getField和getDeclareField的区别

        Child child = new Child(267);
        Field[] fields = child.getClass().getFields();   //获取public访问权限的属性，并且获取到了父类的public的属性
        System.out.println();
        Arrays.stream(fields).forEach(System.out::println);

        fields = Child.class.getDeclaredFields();   //获取自身所有访问权限的属性,不包含父类的。
        System.out.println();
        Arrays.stream(fields).forEach(field -> {
            System.out.println(field);
            // 给私有的域设置值需要先调用 setAccessible(true)，否则会报权限错误。
            try {
                field.setAccessible(true);
                System.out.println(field.get(child));
                field.set(child, "haha");
                System.out.println(field.get(child));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });


    }

    private void testMethods() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Child child = new Child(267);
        Method[] methods = child.getClass().getMethods();        //获取public方法，1.自身，2.父类，3.Object
        System.out.println();
        for (Method method : methods) {
            System.out.println(method);
        }

        methods = child.getClass().getDeclaredMethods();     //获取自身所有方法
        System.out.println();
        for (Method method : methods) {
            System.out.println(method);
            System.out.println(method.getDefaultValue());
            method.setAccessible(true);     //对于私有的method如果直接调用会抛出异常但是通过 method.setAccessible(true)就可以解决这一问题。
            System.out.println(method.invoke(child));       //反射调用不支持自动拆装箱
        }
    }

    /**
     * 使用java.lang.reflect.Array来操作和创建数组
     */
    private void testArrays() {
        // 这个包下的Array类提供了一个newInstance方法来创建新的数组,第一个参数是数组中的元素类型，后面的参数是数组的维度信息
        // 创建一个String类型的长度为10的数组
        String[] names = (String[]) Array.newInstance(String.class, 10);
        names[0] = "KiDe";
        // 设置数组中下标为1的元素的值
        Array.set(names, 1, "Kid");

        // 创建两个三维数组
        int[][][] matrix1 = (int[][][]) Array.newInstance(int.class, 3, 3, 3);
        matrix1[0][0][0] = 12;
        int[][][] matrix2 = (int[][][]) Array.newInstance(int[].class, 3, 4);  // 这个其实也是三维数组，因为数组的类型是int[]
        matrix2[0][0] = new int[1];
        matrix2[0][0][0] = 12;
    }

    /**
     * 用反射实现的设置某个属性值的方法
     * 通过反射API，java也可以应用在灵活性很高的场景中，最长见的Servlet中http请求参数值的填充。
     * 虽然随着java虚拟机性能的改进，反射API的性能有所提升。但是反射方法和非反射方法的性能差距还是客观存在的。
     * 因此在一些性能要求很高的场景中要慎用反射API或者将常调用的反射获得的方法先缓存起来。
     */
    void invokeSetter(Object obj, String field, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String methodName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
        Class<?> clazz = obj.getClass();
        Method setMethod = clazz.getMethod(methodName, value.getClass());
        setMethod.invoke(obj, value);
    }

    /**
     * 用反射实现的设置某个属性值的方法
     * 通过反射API，java也可以应用在灵活性很高的场景中，最长见的Servlet中http请求参数值的填充。
     * 虽然随着java虚拟机性能的改进，反射API的性能有所提升。但是反射方法和非反射方法的性能差距还是客观存在的。
     * 因此在一些性能要求很高的场景中要慎用反射API或者将常调用的反射获得的方法先缓存起来。
     */
    Object invokeGetter(Object obj, String field) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
        Class<?> clazz = obj.getClass();
        Method setMethod = clazz.getMethod(methodName);
        return setMethod.invoke(obj);
    }
}

class Parent {
    public String field3;
    protected String field2;
    String field0;
    private String field1;

    public Parent(double a) {
    }

    private Parent() {
    }

    public void parentMethod() {
    }
}

class Child extends Parent {
    public String field3;
    protected String field2;
    String field0;
    private String field1 = "private String filed1";

    private Child() {
        super(1);
    }

    // 如果父类没有同样的构造函数，则默认会调用父类的无参构造函数，如果父类没有无参构造函数，则编译会报错，会要求显示调用父类的某一个构造函数，以确保父类会在子类被实例化之前被实例化。
    public Child(int i) {
        super(1);
    }

    private Child(long l) {
        super(1);
    }

    private void ChildMethod() {
    }
}

class VaragsConstructor {
    public VaragsConstructor(int... varags) {
    }
}

class NonStaticNestedClass {
    class NestedClass {
        NestedClass(int i) {
        }
    }
}
