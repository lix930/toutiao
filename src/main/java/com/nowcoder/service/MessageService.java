package com.nowcoder.service;

import com.nowcoder.dao.MessageDAO;
import com.nowcoder.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    public int addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        return messageDAO.getConversationDetail(conversationId, offset, limit);

    }

    public List<Message> getConversationList(int userId, int offset, int limit) {
        return messageDAO.getConversationList(userId, offset, limit);

    }

    public int getConversationUnreadCount(int userID, String conversationId) {
        return messageDAO.getConversationUnreadCount(userID, conversationId);
    }
}
