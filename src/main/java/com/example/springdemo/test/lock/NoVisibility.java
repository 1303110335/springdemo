/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.lock;

import org.apache.commons.collections.list.SynchronizedList;

/**
 * @author xuleyan
 * @version NoVisibility.java, v 0.1 2019-09-15 8:26 PM xuleyan
 */
public class NoVisibility {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) {
//        new ReaderThread().start();
//        number = 420;
//        ready = true;
//        List<User> users = new ArrayList<>();
//        User user = new User();
//        user.setAge("18");
//        user.setName("徐乐雁");
//        users.add(user);
//
//        User user2 = new User();
//        user2.setAge("13");
//        user2.setName("乐2");
//        users.add(user2);
//
//
//        User user3 = new User();
//        user3.setAge("1");
//        user3.setName("乐3");
//        users.add(user3);

//        Map<String, List<User>> userMapList = users.stream().collect(Collectors.groupingBy(User::getName));
//        Map<String, Long> userMapList = users.stream().collect(Collectors.groupingBy(User::getAge, counting()));
//        System.out.println(userMapList);


//        String[] arr ={"aa","ccc","sss"};
//        System.out.println(Arrays.stream(arr).collect(joining()));
//        System.out.println(Arrays.stream(arr).collect(toList()));

        SynchronizedList synchronizedList;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println(2);
                Thread.yield();
            }
            System.out.println(number);
        }
    }

}