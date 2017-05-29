package com.nowcoder.controller;

import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.News;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.NewsService;
import com.nowcoder.util.ToutiaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/26.
 */

@Controller
public class LikeController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    LikeService likeService;

    @Autowired
    NewsService newsService;

    @Autowired
    EventProducer eventProducer;

    /**
     * 点赞
     *
     * @param model
     * @param newsId 向哪一条新闻点赞
     * @return
     */
    @RequestMapping(path = {"/like"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String like(Model model, @RequestParam("newsId") int newsId) {

        if (hostHolder.getUser() != null) {
            int userId = hostHolder.getUser().getId();

            //在redis中添加点赞信息 并 新闻中的点赞数
            long likeCount = likeService.like(userId, EntityType.ENTITY_NEWS, newsId);

            News news = newsService.getById(newsId);

            newsService.updateLikeCount(newsId, (int) likeCount);

            //异步发送 站内信
            eventProducer.fireEvent(new EventModel(EventType.LIKE)
                    .setActorId(hostHolder.getUser().getId()).setEntityId(newsId)
                    .setEntityType(EntityType.ENTITY_NEWS).setEntityOwnerId(news.getUserId()));

            return ToutiaoUtil.getJSONString(0, String.valueOf(likeCount));
        } else {
            return ToutiaoUtil.getJSONString(1, "请登录后再执行此操作");
        }


    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String dislike(Model model, @RequestParam("newsId") int newsId) {

        if (hostHolder.getUser() != null) {
            int userId = hostHolder.getUser().getId();
            long likeCount = likeService.disLike(userId, EntityType.ENTITY_NEWS, newsId);
            newsService.updateLikeCount(newsId, (int) likeCount);
            return ToutiaoUtil.getJSONString(0, String.valueOf(likeCount));
        } else {
            return ToutiaoUtil.getJSONString(1, "请登录后再执行此操作");
        }

    }
}
