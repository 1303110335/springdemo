/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.threads;

import lombok.Data;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


/**
 * @author xuleyan
 * @version ExternalDemo.java, v 0.1 2019-12-09 10:20 AM xuleyan
 */
@Data
class User implements Externalizable {

    private String name;

    transient private int age;

    public User() {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }

}

public class ExternalDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setName("xuleyan");
        user.setAge(20);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("temp"))
        ) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read  from file
        File file = new File("temp");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            User newInstance = (User) ois.readObject();
            //output
            System.out.println(newInstance);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}