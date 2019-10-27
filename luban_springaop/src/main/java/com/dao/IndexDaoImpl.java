package com.dao;

import org.springframework.stereotype.Component;

@Component
public class IndexDaoImpl implements IndexDao {
    @Override
    public void seeUser() {
        System.out.println("See a User");
    }
}
