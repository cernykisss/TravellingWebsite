package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavouriteDao {

    Favorite findByRidAndUid(int rid, int uid);
}
