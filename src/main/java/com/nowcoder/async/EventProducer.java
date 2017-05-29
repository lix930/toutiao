package com.nowcoder.async;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/12.
 */
@Service
//用来产生（发送）事件
public class EventProducer {

    @Autowired
    JedisAdapter jedisAdapter;

    //发送事件
    public boolean fireEvent(EventModel model) {
        try {
            String json = JSONObject.toJSONString(model); // 事件序列化
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json); //事件加入异步队列
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
