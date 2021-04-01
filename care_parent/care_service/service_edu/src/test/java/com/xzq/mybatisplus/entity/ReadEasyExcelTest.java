package com.xzq.mybatisplus.entity;

import com.alibaba.excel.EasyExcel;
import com.xzq.mybatisplus.listener.ExcelListener;
import org.junit.Test;

public class ReadEasyExcelTest {
    @Test
    public  void test(){
        String fileName = "D:\\EasyExcel\\test.xlsx";
        EasyExcel.read(fileName,ReadData.class,new ExcelListener()).sheet().doRead();
    }
}
