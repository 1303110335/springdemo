/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.annotation.domain;

import com.example.springdemo.annotation.Validate;
import com.example.springdemo.annotation.Validate2;

import java.util.StringJoiner;

/**
 * @author xuleyan
 * @version User.java, v 0.1 2019-04-21 10:36 AM xuleyan
 */

public class User
{
    @Validate(min = 2, max = 5)
    private String name;

    @Validate(isNotNull = true, min = 1, max = 3)
    private String age;

    @Validate2(2)
    private String school;

    public Integer number;

    User(String name, String age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public User(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public User() {
    }

    /**
     * Getter method for property <tt>school</tt>.
     *
     * @return property value of school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Setter method for property <tt>school</tt>.
     *
     * @param school value to be assigned to property school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    /**
     * Getter method for property <tt>number</tt>.
     *
     * @return property value of number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Setter method for property <tt>number</tt>.
     *
     * @param number value to be assigned to property number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age='" + age + "'")
                .add("school='" + school + "'")
                .toString();
    }
}