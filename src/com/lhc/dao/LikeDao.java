package com.lhc.dao;

import com.lhc.pojo.Like;

public interface LikeDao {
    public Like getLikeById(Integer userId);
    public int setLikeById(Integer userId,Like like);

    public int addLikeById(Integer userId, Like like);
}
