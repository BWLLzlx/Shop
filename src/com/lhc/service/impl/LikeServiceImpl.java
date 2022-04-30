package com.lhc.service.impl;

import com.lhc.dao.LikeDao;
import com.lhc.dao.impl.LikeDaoImpl;
import com.lhc.pojo.Like;
import com.lhc.service.LikeService;

public class LikeServiceImpl implements LikeService {

    LikeDao likeDao = new LikeDaoImpl();

    @Override
    public void setLikeById(Integer userId, Like like) {
        likeDao.setLikeById(userId,like);
    }

    @Override
    public Like getLikeById(Integer userId) {
        return likeDao.getLikeById(userId);
    }

    @Override
    public void addLikeById(Integer userId, Like like) {
        likeDao.addLikeById(userId,like);
    }
}
