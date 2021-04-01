package com.xzq.mybatisplus.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xzq.mybatisplus.entity.ReadData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ReadData> {


    List<ReadData> list =new ArrayList<ReadData>();

    //一行一行的读
    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        System.err.println("**********"+readData);
        list.add(readData);
    }
    @Override
    public  void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.err.println("表头信息"+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
