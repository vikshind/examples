package com.vth.examples.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Class aClass = null;
        try {
            aClass = Class.forName("com.vth.examples.reflection.MyClass");
            System.out.println(aClass.isInterface());
            Method[] methods = aClass.getMethods();
            System.out.println("All methods = " + methods.length);
            List<Method> methodList = Arrays.asList(methods);
            methodList.stream().forEach(method -> System.out.println(method.getName() + " : " + method.getDeclaringClass().getName()));
            System.out.println("Methods from MyClass = " + methodList.stream().filter(m -> m.getDeclaringClass().getName().equals("com.vth.examples.reflection.MyClass")).count());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
