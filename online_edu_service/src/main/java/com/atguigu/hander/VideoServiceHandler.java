package com.atguigu.hander;

import com.atguigu.edu.service.VideoServiceFeign;
import com.atguigu.response.RetVal;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 了飘尘
 * @date: 2022/5/29 2:35
 * @version: 1.0
 * @Description:
 */
@Component
public class VideoServiceHandler implements VideoServiceFeign {
    @Override
    public RetVal deleteSingleVideo(String videoId) {

        return RetVal.error().message("兜底数据");
    }

    @Override
    public RetVal deleteMultiVideo(List<String> videoIdList) {
        return RetVal.error().message("兜底数据");

    }
}
