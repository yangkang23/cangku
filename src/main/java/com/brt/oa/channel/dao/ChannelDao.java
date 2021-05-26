package com.brt.oa.channel.dao;

import com.brt.oa.channel.pojo.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelDao {
    void insert(Channel record);


    void updateTagById(@Param("id") Integer id, @Param("tag") Integer tag);

    List<Channel> selectChannel(@Param("name") String name);

    void updateById(@Param("channel") Channel channel, @Param("id") Integer id);

    String findNameById(@Param("channelid") Integer channelid);
}