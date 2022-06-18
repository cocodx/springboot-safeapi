package com.lg.safeapi.job;

import com.lg.safeapi.entity.User;
import com.lg.safeapi.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 正能量导师
 * @date 2022/6/18-15:59
 * @Description
 */
@Component
public class JobTask {

    private static final Logger log = LoggerFactory.getLogger(JobTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每三秒执行一次
    @Scheduled(fixedRate = 3000)
    public void jobTask(){
        User user = new User();
        user.setId(new Random(10).nextInt(10000));
        user.setName("bro");
        user.setBirthday(new Date(System.currentTimeMillis()));
        log.info(GsonUtil.toJson(user));
    }
}
