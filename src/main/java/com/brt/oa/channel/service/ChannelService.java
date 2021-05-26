package com.brt.oa.channel.service;

import com.brt.oa.channel.pojo.Channel;

import java.util.List;

public interface ChannelService {
    void insert(Channel channel);

    void updateTagById(Integer id, Integer tag);

    List<Channel> selectChannel(String name);

    void updateById(Channel channel,Integer id);

    String findNameById(Integer channelid);
}
