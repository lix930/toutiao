package com.nowcoder.async;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface EventHandler {

    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
