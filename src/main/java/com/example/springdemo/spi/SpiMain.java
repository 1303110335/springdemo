/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;
/**
 * @author xuleyan
 * @version SpiMain.java, v 0.1 2019-04-16 8:34 AM xuleyan
 */
public class SpiMain {

    public static void getConnection(){
        ServiceLoader<DemoService> serviceLoader = ServiceLoader.load(DemoService.class);
        Iterator<DemoService> services = serviceLoader.iterator();
        while (services.hasNext()) {
            DemoService service = services.next();
            System.out.println(service.sayHi("world"));
        }
    }

}