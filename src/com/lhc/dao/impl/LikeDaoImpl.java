package com.lhc.dao.impl;

import com.lhc.dao.LikeDao;
import com.lhc.pojo.Like;

public class LikeDaoImpl extends BaseDao implements LikeDao {
    @Override
    public Like getLikeById(Integer userId) {
        String sql = "select `user_id`userId, `food`,`clothes`,`daily`,`furniture`,`electric`,`fun` from user_like where user_id = ?; ";
        return queryForOne(Like.class, sql, userId);
    }

    @Override
    public int setLikeById(Integer userId, Like like) {
        String sql = "update user_like set `food`=?,`clothes`=?,`daily`=?,`furniture`=?,`electric`=?,`fun`=? where user_id = ?";
        return update(sql,like.getFood(),like.getClothes(),like.getDaily(),like.getFurniture(),like.getElectric(),like.getFun(),userId);
    }

    @Override
    public int addLikeById(Integer userId, Like like) {
        String sql = "insert into user_like(`user_id`,`food`,`clothes`,`daily`,`furniture`,`electric`,`fun`) values(?,?,?,?,?,?,?)";
        return update(sql,userId,like.getFood(),like.getClothes(),like.getDaily(),like.getFurniture(),like.getElectric(),like.getFun());
    }
}
