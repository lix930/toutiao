package com.nowcoder.model;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/3/23.
 */

@Component
//保存登陆信息
public class HostHolder {
    //多线程时，使用ThreadLocal 来保证，setUser 和 getUser 只对本地操作。
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {

        return users.get();
    }

    public void setUser(User user) {

        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
