package xyz.comfyz.learning.reflection.rpc.simple1;

import java.io.Serializable;

public class Call implements Serializable {
    private String className; //表示类名或接口名
    private String methodName; //表示方法名
    private Class[] paramTypes; //表示方法参数类型
    private Object[] params; //表示方法参数值
    //表示方法的执行结果
//如果方法正常执行，则 result 为方法返回值，如果方法抛出异常，那么 result 为该异常。
    private Object result;

    public Call() {
    }

    public Call(String className, String methodName, Class[] paramTypes,
                Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String toString() {
        return "className=" + className + " methodName=" + methodName;
    }
}