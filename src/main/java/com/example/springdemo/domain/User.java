/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xuleyan
 * @version User.java, v 0.1 2019-02-14 4:31 PM xuleyan
 */
public class User implements Serializable {

    private static final long serialVersionUID = -8772420179022299451L;

    private Long id;
    private String name;
    private Integer age;
    private Integer number;
    private String play;

    public User(Integer number, String name, Integer age) {
        this.number = number;
        this.name = name;
        this.age = age;
    }

    public User(Integer number, String name, String play) {
        this.number = number;
        this.name = name;
        this.play = play;
    }
    public User() {};

    public static void main(String[] args) {
        User user1 = new User();
        user1.setAge(12);

        User user2 = new User();
        user2.setAge(15);

        boolean bb = user1.equals(user2);
        System.out.println(bb);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
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

    /**
     * Getter method for property <tt>play</tt>.
     *
     * @return property value of play
     */
    public String getPlay() {
        return play;
    }

    /**
     * Setter method for property <tt>play</tt>.
     *
     * @param play value to be assigned to property play
     */
    public void setPlay(String play) {
        this.play = play;
    }
}