package com.nowcoder.service;

import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/26.
 */

@Service
public class LikeService {

    @Autowired
    JedisAdapter jedisAdapter;

    /**
     * 如果喜欢返回1，如果不喜欢返回-1，否则返回0
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    public int getLikeStatus(int userId, int entityType, int entityId) {

        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        //判断用户是否喜欢
        if (jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
            return 1;
        }
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);

        return jedisAdapter.sismember(disLikeKey, String.valueOf(userId)) ? -1 : 0;
    }

    /**
     *  喜欢
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    public long like(int userId, int entityType, int entityId) {
        //生成likeKey
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        //加入喜欢，userid为发起点赞的用户
        jedisAdapter.sadd(likeKey, String.valueOf(userId));

        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        jedisAdapter.srem(disLikeKey, String.valueOf(userId));

        return jedisAdapter.scard(likeKey); //返回集合中的个数
    }

    /**
     * 不喜欢
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    public long disLike(int userId, int entityType, int entityId) {

        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        //加入不喜欢
        jedisAdapter.sadd(disLikeKey, String.valueOf(userId));

        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.srem(likeKey, String.valueOf(userId));

        return jedisAdapter.scard(likeKey);
    }



}
