/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.annotation.check;

import com.example.springdemo.annotation.Validate;
import com.example.springdemo.annotation.domain.User;

import java.lang.reflect.Field;

/**
 * @author xuleyan
 * @version UserCheck.java, v 0.1 2019-04-21 10:37 AM xuleyan
 */
public class UserCheck {
    public static boolean check(User user) {
        if (user == null) {
            System.out.println("校验对象为空");
            return false;
        }

        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            //如果属性有注解，则进行校验
            if (field.isAnnotationPresent(Validate.class)) {
                Validate validate = field.getAnnotation(Validate.class);
                if (field.getName().equals("age")) {
                    if (user.getAge() == null)
                    {
                        if (validate.isNotNull())
                        {
                            System.out.println("！！年龄可空校验不通过：不可为空！！");
                            return false;
                        }
                        else
                        {
                            System.out.println("年龄可空校验通过：可以为空");
                            continue;
                        }
                    }
                    else
                    {
                        System.out.println("年龄可空校验通过");
                    }

                    if (user.getAge().length() < validate.min())
                    {
                        System.out.println("！！年龄最小长度校验不通过！！");
                        return false;
                    }
                    else
                    {
                        System.out.println("年龄最小长度校验通过");
                    }

                    if (user.getAge().length() > validate.max())
                    {
                        System.out.println("！！年龄最大长度校验不通过！！");
                        return false;
                    }
                    else
                    {
                        System.out.println("年龄最大长度校验通过");
                    }
                }
            }
        }
        return true;
    }
}