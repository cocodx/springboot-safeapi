package com.lg.safeapi.controller;

import com.lg.safeapi.mapper.primary.BookMapper;
import com.lg.safeapi.mapper.second.UserMapper;
import com.lg.safeapi.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 正能量导师
 * @date 2022/6/15-9:52
 * @Description
 */
@RestController
public class TestController {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/book")
    public String getBooks(){
        return GsonUtil.toJson(bookMapper.getBooks());
    }

    @RequestMapping("/user")
    public String getUsers() throws InterruptedException {
        Thread.sleep(5000);
        return GsonUtil.toJson(userMapper.getUsers());
    }

    /**
     * 访问 localhost:8080/book$?ids=1 or 1=1
     * @param ids
     * @return
     */
    @RequestMapping("/book$")
    public String book$(@RequestParam String ids){
        bookMapper.deleteBooks(ids);
        return "1111";
    }

    /**
     * localhost:8080/book2?ids=1 or 1=1
     * @param ids
     * @return
     */
    @RequestMapping("/book2")
    public String book2(@RequestParam String ids){
        bookMapper.deleteBooks2(ids);
        return "1111";
    }
}
