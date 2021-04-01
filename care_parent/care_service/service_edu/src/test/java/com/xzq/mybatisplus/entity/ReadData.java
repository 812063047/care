package com.xzq.mybatisplus.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReadData {
    @ExcelProperty(index = 0)
    private int id;
    @ExcelProperty(index = 1)
    private  String name;
    @ExcelProperty(index = 2)
    private  int age;

    @Override
    public String toString() {
        return "ReadData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
