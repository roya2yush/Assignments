package com.example.DependencyInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.DependencyInjection.MyDbConfig;
import com.example.DependencyInjection.MyApplicationConfig;

public class MessageDITest {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MyApplicationConfig.class);
        MyDbConfig dbConfig = (MyDbConfig) context.getBean("dbConfig");

        String msg = dbConfig.getMsg();
        String email = dbConfig.getEmail();
        MessageServiceInjector injector = null;
        Consumer app = null;

        //Send email
        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, email);
    }
}
