package com.nowcoder.util;

/**
 * Created by Administrator on 2017/3/26.
 */
public class RedisKeyUtil {

    private static String SPLIT = ":";

    private static String BIZ_LIKE = "LIKE";

    private static String BIZ_DISLIKE = "DISLIKE";

    private static String BIZ_EVENT = "EVENT";

    public static String getEventQueueKey() {
        return BIZ_EVENT;
    }

    //例如 LIKE:1:12  其中1为entitytype 12为entityid
    public static String getLikeKey(int entityId, int entityType) {
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getDisLikeKey(int entityId, int entityType) {
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }
}
