package com.lhc.test;

import com.lhc.dao.LikeDao;
import com.lhc.dao.impl.LikeDaoImpl;
import com.lhc.pojo.Like;
import org.junit.Test;

import static org.junit.Assert.*;

public class LikeDaoTest {

    LikeDao likeDao = new LikeDaoImpl();

    @Test
    public void getLikeById() {
        System.out.println(likeDao.getLikeById(1));
    }


    @Test
    public void setLikeById() {
        likeDao.setLikeById(1,new Like(1,5,5,5,5,5,5));
    }

    @Test
    public void addLikeById() {
        likeDao.addLikeById(3,new Like(1,0,0,0,0,0,0));
    }
}