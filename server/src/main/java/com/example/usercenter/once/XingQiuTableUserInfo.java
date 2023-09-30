package com.example.usercenter.once;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: 张宇航
 * @date: 2023/09/30
 * @ClassName: server
 * @Description: 星球表格用户信息
 */
@Data
public class XingQiuTableUserInfo {
    /**
     * id
     */
    @ExcelProperty("成员编号")
    private String planetCode;

    /**
     * 用户昵称
     */
    @ExcelProperty("成员昵称")
    private String username;

}