package com.vth.examples.annotation;

import java.lang.reflect.Method;

public class Hello {
    @VSAnnotation(value = 10)
    public void sayHello() {
        System.out.println("hello annotation");
    }

    public static void main(String[] args) throws Exception {
        Hello h = new Hello();
        Method m = h.getClass().getMethod("sayHello");

        VSAnnotation vsAnnotation = m.getAnnotation(VSAnnotation.class);
        System.out.println("Value is: " + vsAnnotation.value());
    }
}

