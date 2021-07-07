/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.supplier;

import java.util.function.Supplier;

/**
 * @author xuleyan
 * @version TestSupplier.java, v 0.1 2020-05-09 3:44 PM xuleyan
 */
public class TestSupplier {
    public static void main(String[] args) {
//        Supplier<String> supplier = String::new;
//        System.out.println(supplier.get());//""
//        Supplier<Emp> supplierEmp = Emp::new;
//        Emp emp = supplierEmp.get();
//        emp.setName("dd");
//        System.out.println(emp.getName());//dd

        test(() -> {
            Emp emp = new Emp();
            emp.setName("haha");
            return emp.getName();
        });

    }


    public static void test(Supplier<String> stringSupplier) {
        String result = stringSupplier.get();
        System.out.println(result);
    }
}