package com.xzq.serviceEdu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectVoOne {
    private String id;
    private String title;
    private List<SubjectVoTwo> children = new ArrayList<>();
}
