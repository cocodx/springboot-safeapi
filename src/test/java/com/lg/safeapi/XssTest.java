package com.lg.safeapi;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

/**
 * 测试对html进行转义
 * @author amazfit
 * @date 2022-07-16 下午2:01
 **/
public class XssTest {

    @Test
    public void testXss(){
        //转义html
        System.out.println(StringEscapeUtils.escapeHtml4("<img src=x onerror=alert(24)>"));
    }
}
