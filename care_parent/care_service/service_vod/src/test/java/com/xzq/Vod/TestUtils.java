package com.xzq.Vod;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("wei");
        list.add("sehn");
        list.add("mo");
        String join = StringUtils.join(list.toArray(), ",");
        System.err.println(join);
    }

}
