package com.lhc.dao.impl;

import com.lhc.dao.UserCategoryDao;
import com.lhc.pojo.UserCategory;

import java.util.List;

public class UserCategoryDaoImpl extends BaseDao implements UserCategoryDao {

    @Override
    public int addCategory(UserCategory userCategory) {
        String sql = "insert into user_category (`food`,`clothes`,`daily`,`furniture`,`electric`,`fun`) values(?,?,?,?,?,?);";
        return update(sql,userCategory.getFood(),userCategory.getClothes(),userCategory.getDaily(),userCategory.getFurniture(),userCategory.getElectric(),userCategory.getFun());
    }

    @Override
    public Integer[] getMax(UserCategory userCategory) {
        Integer max[] = new Integer[6];
        max[0] = userCategory.getFood();
        max[1] = userCategory.getClothes();
        max[2] = userCategory.getDaily();
        max[3] = userCategory.getFurniture();
        max[4] = userCategory.getElectric();
        max[5] = userCategory.getFun();
        Integer sum = 0;
        for (Integer i : max) {
            sum+=i;
        }
        for (int i = 0; i<max.length; i++) {
            if (max[i]==0) continue;
            max[i] = max[i]*UserCategory.PAGE_SIZE/sum;
            if (max[i]==0){
                max[i]=1;
            }
        }

        return max;
    }

    @Override
    public UserCategory queryById(Integer categoryId) {
        String sql = "select `id`,`food`,`clothes`,`daily`,`furniture`,`electric`,`fun` from user_category where id = ?";
        return queryForOne(UserCategory.class,sql,categoryId);
    }

    @Override
    public UserCategory queryByAll(UserCategory userCategory) {
        String sql = "select `id`,`food`,`clothes`,`daily`,`furniture`,`electric`,`fun` from user_category " +
                "where food = ? and clothes = ? and daily = ? and furniture = ? and electric = ? and fun = ?";
        return queryForOne(UserCategory.class,sql,userCategory.getFood(),userCategory.getClothes(),
                userCategory.getDaily(),userCategory.getFurniture(),userCategory.getElectric(),userCategory.getFun());
    }

    @Override
    public void setCategoryId(Integer categoryId,Integer userId) {
        String sql = "update user set `category_id`=? where id = ?";
        update(sql,categoryId,userId);
    }

    @Override
    public List<UserCategory> queryForAll() {
        String sql = "select `id`,`food`,`clothes`,`daily`,`furniture`,`electric`,`fun` from user_category";
        return queryForList(UserCategory.class,sql);
    }
}
