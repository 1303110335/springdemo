/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.hashmap;

import java.util.Objects;

/**
 * @author xuleyan
 * @version Person.java, v 0.1 2020-03-30 4:20 PM xuleyan
 */
public class Person {
    /**
     * 名字
     */
    private final String name;
    /**
     * 帅气指数
     */
    private final int handsome;

    public Person(String name, int handsome) {
        super();
        this.name = name;
        this.handsome = handsome;
    }

    public String getName() {
        return name;
    }

    public int getHandsome() {
        return handsome;
    }

    @Override
    public String toString() {
        return "姓名:" + this.name + " -->帅气指数 : " + this.handsome + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return handsome == person.handsome &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, handsome);
    }
}