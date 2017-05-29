package com.nowcoder.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowcoder.controller.IndexController;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/12.
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {

    @Autowired
    JedisAdapter jedisAdapter;

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    private ApplicationContext applicationContext;

    private Map<EventType, List<EventHandler>> config = new HashMap<EventType, List<EventHandler>>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //找所有 EventHandler
        //遍历上下文中 所有实现了EventHandler的类
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                List<EventType> eventTypes = entry.getValue().getSupportEventTypes();
                for (EventType type :
                        eventTypes) {
                    if (!config.containsKey(type)) {
                        config.put(type, new ArrayList<EventHandler>());
                    }
                    config.get(type).add(entry.getValue());
                }
            }
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //一直取事件
                while (true) {
                    String key = RedisKeyUtil.getEventQueueKey();
                    List<String> events = jedisAdapter.brpop(0, key);
                    for (String message : events) {
                        //得到key 忽略 不处理
                        if (message.equals(key)) {
                            continue;
                        }
                        //得到事件
                        EventModel eventModel = JSON.parseObject(message, EventModel.class);
                        //事件没注册  抛弃
                        if (!config.containsKey(eventModel.getType())) {
                            logger.error("不能识别的事件");
                            continue;
                        }
                        //处理事件
                        for (EventHandler handler : config.get(eventModel.getType())) {
                            handler.doHandle(eventModel);
                        }

                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
