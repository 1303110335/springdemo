package com.example.springdemo;

import com.example.springdemo.dto.ErrorInfo;
import com.example.springdemo.exception.MyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@ControllerAdvice
@ComponentScan("com.example.springdemo.*")
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) {
        ErrorInfo<String> r = new ErrorInfo<>();
        try {
            throw e;
        } catch (MyException mye) {
            r.setMessage(mye.getMessage());
            r.setCode(ErrorInfo.ERROR);
            r.setData("my Data");
        } catch (Exception ex) {
            r.setMessage(ex.getMessage());
            r.setCode(ErrorInfo.FATALERROR);
            r.setData("system ex");
        }
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
//
//    @Component
//    static class MyBeanPostProcessor4 implements MergedBeanDefinitionPostProcessor {
//        @Override
//        @Nullable
//        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//            System.out.println("bean 初始化之前");
//            return bean;
//        }
//
//        @Override
//        @Nullable
//        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//            System.out.println("bean 初始化之后");
//            return bean;
//        }
//
//
//        @Override
//        public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
//            System.out.println("bean 合并");
//        }
//
//    }
}

