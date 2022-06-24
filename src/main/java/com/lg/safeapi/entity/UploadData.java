package com.lg.safeapi.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 正能量导师
 * @date 2022/6/24-13:23
 * @Description
 */
@Data
@ExcelIgnoreUnannotated
public class UploadData{

    @ExcelProperty(value = "姓名",index = 0)
    private String name;

    @ExcelProperty(value = "常用邮件地址",index = 1)
    private String emailAddress;

    @ExcelProperty(value = "备用邮件地址1",index = 2)
    private String emailAddress1;

    @ExcelProperty(value = "备用邮件地址2",index = 3)
    private String emailAddress2;

    @ExcelProperty(value = "常用手机号码",index = 4)
    private String almostUsePhoneNumber;

    @ExcelProperty(value = "备用手机号码1",index = 5)
    private String phoneNumber1;

    @ExcelProperty(value = "备用手机号码2",index = 6)
    private String phoneNumber2;

    @ExcelProperty(value = "固定电话",index = 7)
    private String locationPhone;

    @ExcelProperty(value = "生日(XXXX-XX-XX)" ,index = 8)
    private String birthday;

    @ExcelProperty(value = "家庭住址",index = 9)
    private String address;

    @ExcelProperty(value = "公司名称",index = 10)
    private String companyName;

    @ExcelProperty(value = "备注",index = 11)
    private String remark;

    @ExcelProperty(value = "所属分组",index = 12)
    private String groupName;






}
