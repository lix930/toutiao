package com.nowcoder.service;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.util.ToutiaoUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/24.
 */

@Service
public class QiniuService {

    private static final Logger logger = LoggerFactory.getLogger(ToutiaoUtil.class);

    String accessKey = "QeoHOT9jtWnJ4CMj8kqgKkDExSvPZtvuBNnOlE-y";

    String secretKey = "ID69ZZO0ltdd1VuGmj0Qu2Ch9t3ErV0V3gzJMa47";

    String bucket = "nowcoder";
    //密钥配置
    Auth auth = Auth.create(accessKey, secretKey);
    ///创建 上传对象
    UploadManager uploadManager = new UploadManager();

    public String getUpToken() {
        return auth.uploadToken(bucket);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            //验证传过来的文件  是不是图片
            int dotPos = file.getOriginalFilename().lastIndexOf("."); //
            if (dotPos < 0) {
                return null;
            }

            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            if (!ToutiaoUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            System.out.println(res.bodyString());
            if (res.isOK() && res.isJson()) {
                String key = JSONObject.parseObject(res.bodyString()).get("key").toString();
                return ToutiaoUtil.QINIU_DOMAIN_PREFIX + key;
            } else {
                logger.error("七牛云异常：" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            logger.error("七牛云 异常", e.getMessage());
            return null;
        }
    }
}
