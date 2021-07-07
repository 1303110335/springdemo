/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import com.example.springdemo.domain.Student;
import com.example.springdemo.domain.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xuleyan
 * @version TestValue.java, v 0.1 2019-03-11 8:27 AM xuleyan
 */
public class TestValue {

    public static void main(String[] args) throws IOException {
//        readFile("/Volumes/code/java/test/springdemo/src/main/resources/test.txt");

        System.out.println(getInt());
    }

    private static void readFile(String filePath) throws IOException {

        class MyFileReader extends FileReader {

            public MyFileReader(File file) throws FileNotFoundException {
                super(file);
            }

            @Override
            public void close() throws IOException {
                super.close();
                System.out.println("close file reader");
            }
        }
        File file = new File(filePath);
        String result;
        // 使用try catch resource
        try (BufferedReader reader = new BufferedReader(new MyFileReader(file))) {

            while ((result = reader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println("readFile method catch block.");
            throw e;
        }
    }

    /**
     * finally – finally语句块总是会被执行。它主要用于回收在try块里打开的物力资源(如数据库连接、网络连接和磁盘文件)。
     * 只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，
     * 如果finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
     *
     * @return
     */
    public static Integer testFinally() {
        int num = 0;
        try {
            num = 2;
            System.out.println(2);
            throw new Exception("name");
        } catch (Exception ex) {

        } finally {
            num = 3;
            return num;
        }
    }

    public static int getInt() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            return a;
            /*
             * return a 在程序执行到这一步的时候，这里不是return a 而是 return 30；这个返回路径就形成了
             * 但是呢，它发现后面还有finally，所以继续执行finally的内容，a=40
             * 再次回到以前的路径,继续走return 30，形成返回路径之后，这里的a就不是a变量了，而是常量30
             */
        } finally {
            a = 40;
        }
        return a;
    }

    public static void testString() throws NoSuchFieldException, IllegalAccessException {
        // 创建字符串"Hello World"， 并赋给引用s
        String s = "Hello World";

        System.out.println("s = " + s); // Hello World

        // 获取String类中的value字段
        Field valueFieldOfString = String.class.getDeclaredField("value");

        // 改变value属性的访问权限
        valueFieldOfString.setAccessible(true);

        // 获取s对象上的value属性的值
        char[] value = (char[]) valueFieldOfString.get(s);

        // 改变value所引用的数组中的第5个字符
        value[5] = '_';

        System.out.println("s = " + s); // Hello_World

    }

    public static void testClass() throws ClassNotFoundException {
        //获取反射机制三种方式

        // 方式一(通过建立对象)
        Student student = new Student();
        Class classobj1 = student.getClass();
        System.out.println(classobj1.getName());

        // 方式二（所在通过路径-相对路径）
        Class classobj2 = Class.forName("com.example.springdemo.domain.Student");
        System.out.println(classobj2.getName());

        // 方式三（通过类名）
        Class classobj3 = Student.class;
        System.out.println(classobj3.getName());
    }

    public static void testFiles() throws IOException {
        //        boolean exists = Files.exists(Paths.get("/Volumes/code/java/test/springdemo/src/main/java/com/example/springdemo/test/TestStream.java"));
        Path file = Files.createFile(Paths.get("/Volumes/code/java/test/springdemo/src/main/java/com/example/springdemo/test/TestStream2.java"));
        System.out.println(file);
    }

    public static void test2() {

//      TestValue testValue = new TestValue();
//      testValue.outMethod();


//        for (int i = 1; i < 4; i++) {
//            int z = 2 - i;
//            String result = StringUtils.repeat("  ", z);
//            System.out.print(result);
//
//            for (int j = 0; j < i; j++) {
//                if (j == 0) {
//                    System.out.print("* ");
//                } else {
//                    System.out.print(" * ");
//                }
//
//            }
//            System.out.println();
//        }


//        Date date = new Date();

//        List<String> stringList = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7"));
//        format (stringList);
//        System.out.println(stringList);
//        Collections.shuffle(stringList);
//        System.out.println(stringList);
//
//        System.out.println("J".compareTo("10"));
//        System.out.println("10".compareTo("9"));
//        System.out.println("9".compareTo("8"));

//        Arrays.asList(null);

//        Integer.parseInt("");
//        User user = new User();
//        user.setAge(12);
//        String name = "xly";
//        user.setName(name);
//
//        change(user);
//
//        System.out.println(user);
//        System.out.println("user3:" + System.identityHashCode(user));


//        int[] nums = new int[] {1,2};
//        System.out.println(Arrays.asList(nums));
    }

    void outMethod() {
        final int a = 10;
        class Inner {
            void innerMethod() {
                System.out.println(a);
            }
        }
        new Inner().innerMethod();
    }

    private static void change(User user) {
        System.out.println("user1:" + System.identityHashCode(user));
        user = new User();
        String name = "haha";
        user.setName(name);
        System.out.println(user);
        System.out.println("user2:" + System.identityHashCode(user));
    }

}