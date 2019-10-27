package com.test;

import com.config.AppConfig;
import com.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        IndexDao dao = annotationConfigApplicationContext.getBean(IndexDao.class);
        dao.seeUser();
    }
}
