package com.lhc.service;

import com.lhc.pojo.Like;

public interface LikeService {
    public void setLikeById(Integer userId,Like like);
    public Like getLikeById(Integer userId);

    void addLikeById(Integer userId, Like like);
}
