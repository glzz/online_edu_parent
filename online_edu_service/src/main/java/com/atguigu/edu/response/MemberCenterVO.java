package com.atguigu.edu.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 会员表
 * </p>
 *
*/
/**
 * @Description:
 * @author: GaoNianZhao
 *  @Date: 2022/7/19 19:14
 */
@Data
public class MemberCenterVO  {

    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;


}
