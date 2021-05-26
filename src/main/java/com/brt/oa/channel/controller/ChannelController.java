package com.brt.oa.channel.controller;

import com.brt.oa.annotation.UserLoginToken;
import com.brt.oa.channel.pojo.Channel;
import com.brt.oa.channel.service.ChannelService;
import com.brt.oa.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 渠道接口
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    /**
     * 插入渠道
     *
     * @param channel
     * @return
     */
    @PostMapping("/insert")
    @UserLoginToken
    public ApiResult insert(@RequestBody Channel channel) {
        log.info("请求参数为：" + channel.toString());
        channelService.insert(channel);
        return ApiResult.success();
    }

    /**
     * 设为常用
     *
     * @param channel
     * @return
     */
    @PostMapping("/updateTag")
    @UserLoginToken
    public ApiResult updateTag(@RequestBody Channel channel) {
        log.info("请求参数为：" + channel.toString());
        channelService.updateTagById(channel.getId(), channel.getTag());
        return ApiResult.success();
    }

    /**
     * 渠道列表
     *
     * @param name
     * @return
     */
    @GetMapping("/select")
    @UserLoginToken
    public ApiResult select(@RequestParam(required = false) String name
                            //@RequestParam(required = false) Integer pageIndex,
                            //@RequestParam(required = false) Integer pageSize
    ) {
//        if (pageIndex == null || pageSize== null){
//            pageIndex = 1;
//            pageSize =20;
//        }
        log.info("请求参数为：" + name);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);
        List<Channel> list = channelService.selectChannel(name);


        return ApiResult.success(list);
    }

    /**
     * 编辑
     *
     * @param channel
     * @return
     */
    @PostMapping("/updateById")
    @UserLoginToken
    public ApiResult updateById(@RequestBody Channel channel) {
        log.info("请求参数为：" + channel.toString());
        channelService.updateById(channel, channel.getId());
        return ApiResult.success();
    }


}
