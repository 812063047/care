package com.xzq.mybatisplus.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty("学生编号")
    private int id;
    @ExcelProperty("学生名字")
    private  String name;
    @ExcelProperty("学生年纪")
    private  int age;
}
