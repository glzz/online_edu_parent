package com.atguigu.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
/**
 * @author: 了飘尘
 * @date: 2022/5/6 4:33
 * @version: 1.0
 * @Description:
 */
@Data
public class CourseInfoVO {

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "二级分类ID")
    private String childSubjectId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "一级分类ID")
    private String parentSubjectId;

    @ApiModelProperty(value = "课程简介")
    private String description;
}
