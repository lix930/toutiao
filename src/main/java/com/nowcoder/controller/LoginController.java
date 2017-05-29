package com.nowcoder.controller;

import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.model.News;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.ToutiaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nowcoder on 2016/7/2.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    EventProducer eventProducer;

    //注册
    @RequestMapping(path = {"/reg/ "}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "rember", defaultValue = "0") int rember,
                      HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.rigister(username, password);
            if (map.isEmpty()) {
//                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
//                cookie.setPath("/");
//
//                if(rember > 0) {
//                    cookie.setMaxAge(3600*24*5);
//                }
//
//                response.addCookie(cookie);

                return ToutiaoUtil.getJSONString(0, "注册成功");
            } else {
                return ToutiaoUtil.getJSONString(1, map);
            }
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());

            return ToutiaoUtil.getJSONString(1, "注册异常");
        }

    }

    //登陆
    @RequestMapping(path = {"/login/ "}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rember", defaultValue = "0") int rember,
                        HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rember > 0) {
                    cookie.setMaxAge(3600 * 24 * 5);
                }
                response.addCookie(cookie);

//                //发出事件
//                eventProducer.fireEvent(new EventModel(EventType.LOGIN)
//                        .setActorId((Integer) map.get("userId"))
//                        .setExt("username", username)
//                        .setExt("email", "lix930@163.com"));
                return ToutiaoUtil.getJSONString(0, "登陆成功");
            } else {
                return ToutiaoUtil.getJSONString(1, map);
            }
        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());

            return ToutiaoUtil.getJSONString(1, "登陆异常");
        }

    }

    //登出
    @RequestMapping(path = {"/logout/"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket) {

        userService.logout(ticket); //设置ticket 无效

        return "redirect:/";
    }


}
