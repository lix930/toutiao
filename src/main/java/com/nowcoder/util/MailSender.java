package com.nowcoder.util;

import org.apache.log4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/12.
 */

@Service
public class MailSender implements InitializingBean{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ToutiaoUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
