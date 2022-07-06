package com.lg.safeapi.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lg.safeapi.entity.UploadData;
import com.lg.safeapi.excel.UploadDataListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author 正能量导师
 * @date 2022/6/24-13:20
 * @Description
 */
@RestController
@RequestMapping("excel")
public class ExcelController {

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
//        EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener()).excelType(ExcelTypeEnum.CSV).charset(Charset.forName("gbk")).sheet().doRead();
        return "success";
    }
}
