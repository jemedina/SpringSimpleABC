package com.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private Main() {
        throw new IllegalAccessError("Is a Main class");
    }
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Application app = (Application) ctx.getBean("app");
        ((ClassPathXmlApplicationContext)ctx).close();
        app.start();

    }
}
