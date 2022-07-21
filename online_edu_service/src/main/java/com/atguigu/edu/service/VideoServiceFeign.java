package com.atguigu.edu.service;

import com.atguigu.edu.response.RetVal;
import com.atguigu.hander.VideoServiceHandler;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: 了飘尘
 * @date: 2022/5/29 2:45
 * @version: 1.0
 * @Description:
 */

@FeignClient(value = "EDU-VIDEO" ,fallback = VideoServiceHandler.class)
public interface VideoServiceFeign {

    //2.删除单个视频
    @DeleteMapping("aliyun/{videoId}")
    public RetVal deleteSingleVideo(@ApiParam("视频ID") @PathVariable("videoId") String videoId);

    //2.删除多个视频
    @DeleteMapping("aliyun/deleteMultiVideo")
    public RetVal deleteMultiVideo(@RequestParam("videoIdList") List<String> videoIdList);
}
