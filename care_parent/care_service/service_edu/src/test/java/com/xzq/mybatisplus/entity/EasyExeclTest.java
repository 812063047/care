package com.xzq.mybatisplus.entity;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EasyExeclTest {

    @Test
    public void Test(){
        String FileName = "D:\\EasyExcel\\test.xlsx";
        EasyExcel.write(FileName,DemoData.class).sheet("学生列表").doWrite(EasyExeclTest.getStuData());

    }

    public static List<DemoData> getStuData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setId(i);
            demoData.setAge(i);
            demoData.setName("care" + i);
            list.add(demoData);
        }
        return  list;
    }
}
