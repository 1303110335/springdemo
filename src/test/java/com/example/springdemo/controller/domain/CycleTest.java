/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.controller.domain;

import com.example.springdemo.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试spring bean的生命周期
 *
 * @author xuleyan
 * @version CycleTest.java, v 0.1 2019-10-08 1:57 PM xuleyan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CycleTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        student.play();
        System.out.println(student);
        ((ClassPathXmlApplicationContext) context).close();
    }
}