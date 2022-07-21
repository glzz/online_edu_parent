package com.atguigu.edu.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 了飘尘
 * @date: 2022/4/21 14:30
 * @version: 1.0
 * @Description:
 */
@Data
public class SubjectVO {
    @ApiModelProperty(value = "课程类别ID")
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "分类名称")
    private List<SubjectVO> children = new ArrayList<>();


}
