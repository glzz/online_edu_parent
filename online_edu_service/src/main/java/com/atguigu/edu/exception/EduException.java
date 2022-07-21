package com.atguigu.edu.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//继承RuntimeException 遇到事务可以回滚
//继承Exception       遇到事务不可以回滚
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@ApiModel("这是一个自定义异常")
public class EduException extends RuntimeException {
    @ApiModelProperty(value = "异常编号")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String message;

}
